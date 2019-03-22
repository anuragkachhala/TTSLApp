package com.software.ttsl;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.software.ttsl.Interfacce.AlertDialogCallback;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.ConnectivityReceiver;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.Utils.SessionManager;
import com.software.ttsl.Utils.SharedPrefUtil;
import com.software.ttsl.model.SalesBudgetModel;
import com.mynameismidori.currencypicker.CurrencyPicker;
import com.mynameismidori.currencypicker.CurrencyPickerListener;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditSalesBudgetActivity extends AppCompatActivity implements View.OnClickListener, Callback<String>, AlertDialogCallback {

    public static final String TAG = EditSalesBudgetActivity.class.getName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_budget_year)
    EditText editTextBudgetYear;

    @BindView(R.id.et_budget_salesman_sector)
    EditText editTextBudgetSalesman_sector;

    @BindView(R.id.et_budget_salesman)
    EditText editTextBudgetSalesman;

    @BindView(R.id.et_budget_sector)
    EditText editTextBudgetSector;

    @BindView(R.id.et_budget_roe)
    EditText editTextBudgetRoe;

    @BindView(R.id.et_budget_note)
    EditText editTextBudgetNote;

    @BindView(R.id.et_budget_currency)
    EditText editTextBudgetCurrency;

    private SalesBudgetModel salesBudgetModel;
    private AlertDialogManager alertDialogManager;
    private SharedPrefUtil sharedPrefUtil;
    private DataBaseAdapter dataBaseAdapter;
    private Intent intent;
    private String itemList[];
    private String title,createdBy;
    private long salesId;
    private long createdTime;
    private Boolean isSync;


    private String titleCurrency,titleSector,titleSalesman_Sector,errorTitle,emptySalesYear,errMsgEmptyCurrency,errMsgEmptySalsman,errMsgEmptysector;
    private String[] currencyList,sectorList,selectorList;
    int year ;
    int selectedYear;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sales_budget);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SessionManager.setContext(getApplicationContext());

        createdBy = SessionManager.getInstance().getUserKeyId();
        alertDialogManager = new AlertDialogManager();
        dataBaseAdapter = new DataBaseAdapter(this);

        SharedPrefUtil.setContext(getApplicationContext());
        sharedPrefUtil = SharedPrefUtil.getInstance();

        Intent intent = getIntent();
        salesId = intent.getLongExtra(EmployConstantUtil.KEY_SALES_BUDGET_ID,0);
        salesBudgetModel = dataBaseAdapter.getSalesById(salesId);

        setBudgetData();

        setClickListener();
        getStringResources();
    }

    private void setBudgetData() {
        editTextBudgetYear.setText(salesBudgetModel.getSalesYear());
        //editTextBudgetCurrency.setText(salesBudgetModel.getC());
        editTextBudgetNote.setText(salesBudgetModel.getSalesNote());
        /*editTextBudgetSalesman.setText(salesBudgetModel.getSalesman());
        editTextBudgetSector.setText(salesBudgetModel.getSalesSector());*/
        editTextBudgetRoe.setText(salesBudgetModel.getSalesROE());
        createdTime = salesBudgetModel.getSalesCreatedTime();
    }

    private void getStringResources() {
        selectorList = getResources().getStringArray(R.array.list_sales_budget_selector);
        sectorList = getResources().getStringArray(R.array.list_sales_sector);
        titleSalesman_Sector = getResources().getString(R.string.title_selector);
        titleSector = getResources().getString(R.string.title_sectors);
        errorTitle = getResources().getString(R.string.err_msg_title_dialog);
        emptySalesYear = getResources().getString(R.string.msg_empety_error);
        errMsgEmptyCurrency = getResources().getString(R.string.err_select_currency);
        errMsgEmptySalsman = getResources().getString(R.string.err_msg_empty_salesman);
        errMsgEmptysector = getResources().getString(R.string.err_select_sector);

    }

    private void setClickListener() {
        editTextBudgetSalesman_sector.setOnClickListener(this);
        editTextBudgetSector.setOnClickListener(this);
        editTextBudgetCurrency.setOnClickListener(this);
        editTextBudgetYear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.et_budget_salesman_sector:
                startNewActivity(titleSalesman_Sector, selectorList);
                break;
            case R.id.et_budget_sector:
                startNewActivity(titleSector, sectorList);
                break;
            case R.id.et_budget_currency:
                currencyPicker();
                break;
            case R.id.et_budget_year:
                showYearDialog();
                break;
        }
    }
    private void startNewActivity(String title, String[] itemList) {

        intent = new Intent(EditSalesBudgetActivity.this, CustomListActivity.class);
        intent.putExtra(EmployConstantUtil.ITEM_LIST, itemList);
        intent.putExtra(EmployConstantUtil.TITLE, title);
        startActivityForResult(intent, 1000);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_task,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.add_task:

               /* if (setLeadData()) {
                    addLeadData();

                }*/
                if (checkValidation())
                    updateSalesData();

                break;
        }

        return super.onOptionsItemSelected(item);


    }

    private void updateSalesData() {
        salesBudgetModel = new SalesBudgetModel();
        salesBudgetModel.setSalesYear(editTextBudgetYear.getText().toString().trim());
        /*salesBudgetModel.setSalesSalesSector(editTextBudgetSalesman_sector.getText().toString().trim());
        salesBudgetModel.setSalesSector(editTextBudgetSector.getText().toString().trim());
        salesBudgetModel.setSalesman(editTextBudgetSalesman.getText().toString().trim());
        salesBudgetModel.setSalesCurrency(editTextBudgetCurrency.getText().toString().trim());*/
        salesBudgetModel.setSalesROE(editTextBudgetRoe.getText().toString().trim());
        salesBudgetModel.setSalesNote(editTextBudgetNote.getText().toString().trim());
        salesBudgetModel.setSalesCreatedBY("Test");
        salesBudgetModel.setSalesModifyBy("Test");
        salesBudgetModel.setSalesCreatedTime(createdTime);
        salesBudgetModel.setSalesModifyTime(currentTimeStamp());
      // int result= dataBaseAdapter.updateSalseBudget(salesBudgetModel,salesId);


        Log.e(TAG,"send sales id to update  " +salesId);

        /*if(result==1){
            Toast.makeText(this,"Budget Update Successfully",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,AddSalesBudgetListActivity.class));
            finish();
        }*/

        if (!isSync) {

            if (checkConnection()) {
                updateSalesDataToServer();
            } else {
                dataBaseAdapter.updateSalseBudget(salesBudgetModel, salesId);
                alertDialogManager.showAlertDialogMessage(this, "", "Sales is Updated ", false, this);
            }


        } else {

            if (checkConnection()) {
                updateSalesDataToServer();
            } else {
                Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        if (!isConnected) {

            return false;
        }

        return true;
    }

    private void updateSalesDataToServer() {

        //     DialogUtitlity.showLoading(EditContactActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> listCall = apiInterface.salesBudget(salesBudgetModel,SessionManager.getInstance().getUserKeyId());
        listCall.enqueue(this);

    }

    private boolean checkValidation() {
        if (editTextBudgetYear.getText().toString().trim().isEmpty()) {
            alertDialogManager.showAlertDialog(this, errorTitle, emptySalesYear, false);
            return false;
        }if(editTextBudgetSalesman_sector.getText().toString().trim().equals("-None-")){
            alertDialogManager.showAlertDialog(this, errorTitle, "Select ", false);
            return false;
        }

        if (editTextBudgetSalesman.getText().toString().trim().isEmpty()) {

            showErrorDialog(errMsgEmptySalsman);
            return false;
        }

        if (editTextBudgetSector.getText().toString().trim().equals("-None-")) {
            showErrorDialog(errMsgEmptysector);
            return false;
        }
        if (editTextBudgetCurrency.getText().toString().trim().equals("-None-")) {

            showErrorDialog(errMsgEmptyCurrency);


            return false;
        }
        return true;
    }


    public void showErrorDialog(String errMessage) {
        alertDialogManager.showAlertDialog(this, errorTitle, errMessage, false);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {
                String selectedItem = data.getStringExtra(EmployConstantUtil.SELECTED_ITEM);
                String title = data.getStringExtra(EmployConstantUtil.TITLE);
                if (title.equals(titleSector)) {
                    editTextBudgetSector.setText(selectedItem);

                } else if (title.equals(titleSalesman_Sector)) {
                    editTextBudgetSalesman_sector.setText(selectedItem);
                } /* else if (title.equals(titleOrigin)) {
                    editTextCustomerOrigin.setText(selectedItem);
                }*/
            }
        }
    }


    private long currentTimeStamp() {
        return System.currentTimeMillis() - 1000;
    }

    public void showYearDialog()
    {
        LayoutInflater inflater = getLayoutInflater();
        View yearDialog = inflater.inflate(R.layout.yeardialog, null);
        TextView year_text=(TextView)yearDialog.findViewById(R.id.year_text);
        year = Calendar.getInstance().get(Calendar.YEAR);
        year_text.setText("Current Year "+year);
        final NumberPicker nopicker = (NumberPicker)yearDialog.findViewById(R.id.numberPicker);
        nopicker.setMaxValue(year+50);
        nopicker.setMinValue(year-50);
        nopicker.setWrapSelectorWheel(false);
        if(!editTextBudgetYear.getText().toString().trim().isEmpty())
        {
            nopicker.setValue(Integer.parseInt(editTextBudgetYear.getText().toString().trim()));
        }else {
            nopicker.setValue(year);
        }
        nopicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        // this is set the view from XML inside AlertDialog
        alert.setView(yearDialog);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("SET", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                editTextBudgetYear.setText(String.valueOf(nopicker.getValue()));
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();




    }




    public void currencyPicker(){
        final CurrencyPicker picker = CurrencyPicker.newInstance("Select Currency");  // dialog title
        picker.setListener(new CurrencyPickerListener() {
            @Override
            public void onSelectCurrency(String name, String code, String symbol, int flagDrawableResID) {
                if(code.equals("INR")) {
                    editTextBudgetCurrency.setText(getString(R.string.Rs));
                }
                else {
                    editTextBudgetCurrency.setText(symbol);
                }
                picker.dismiss();
                // Implement your code here
            }
        });
        picker.show(getSupportFragmentManager(), "CURRENCY_PICKER");
    }


    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        int statusCode = response.code();
        if (statusCode == 200) {
            if (response.body() instanceof String) {

                salesBudgetModel.setSync(isSync);

            }


        } else if (statusCode == 201) {
            salesBudgetModel.setSync(true);


        } else if (statusCode == 500) {


            salesBudgetModel.setSync(isSync);

        }

        int result = dataBaseAdapter.updateSalseBudget(salesBudgetModel, salesId);
        alertDialogManager.showAlertDialogMessage(this, "", "Sales is updated ", false, this);

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }

    @Override
    public void alertDialogCallbackOk() {
        startActivity(new Intent(EditSalesBudgetActivity.this, AddSalesBudgetListActivity.class));
    }

    @Override
    public void alertDialogCallbackCancel() {

    }
}
