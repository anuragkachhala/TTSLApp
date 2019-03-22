package com.software.ttsl;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import com.software.ttsl.Utils.DateAndTimeUtil;
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

public class AddSalesBudgetActivity extends AppCompatActivity implements View.OnClickListener, AlertDialogCallback {
    private static final String TAG = AddSalesBudgetActivity.class.getName();
    private static final int REQUEST_SECTOR_CODE = 1000;
    private static final int REQUEST_SALES_SECTOR_CODE = 2000;


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_budget_year)
    EditText editTextBudgetYear;

    @BindView(R.id.et_budget_salesman_sector)
    EditText editTextBudgetSalesman_sector;

    @BindView(R.id.et_budget_salesman)
    EditText editTextBudgetSalesman;

    @BindView(R.id.et_budget_sector)
    TextView textViewBudgetSector;

    @BindView(R.id.et_budget_roe)
    EditText editTextBudgetRoe;

    @BindView(R.id.et_budget_note)
    EditText editTextBudgetNote;

    @BindView(R.id.et_budget_currency)
    TextView textViewBudgetCurrency;
    int year;
    int selectedYear;
    private SalesBudgetModel salesBudgetModel;
    private AlertDialogManager alertDialogManager;
    private SharedPrefUtil sharedPrefUtil;
    private DataBaseAdapter dataBaseAdapter;
    private long salesId, createdTime;
    private Intent intent;
    private String itemList[];
    private String title;
    private String titleCurrency, createdBy, titleSector, titleSalesman_Sector, errorTitle, emptySalesYear, errMsgEmptysector, errMsgEmptyCurrency, errMsgEmptySalsman;
    private String[] currencyList, sectorList, selectorList;
    private SessionManager sessionManager;
    private boolean editSalesBudget = false, isSync = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales_budget);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SessionManager.setContext(getApplicationContext());

        alertDialogManager = new AlertDialogManager();
        dataBaseAdapter = new DataBaseAdapter(this);
        sessionManager = SessionManager.getInstance();


        SharedPrefUtil.setContext(getApplicationContext());
        sharedPrefUtil = SharedPrefUtil.getInstance();

        Intent intent = getIntent();
        salesId = intent.getLongExtra(EmployConstantUtil.KEY_SALES_BUDGET_ID, 0);
        if (salesId != 0) {
            editSalesBudget = true;
        }


        setClickListener();
        getStringResources();

        if (editSalesBudget) {
            getSalesBudgetById();
            toolbar.setTitle(getResources().getString(R.string.title_edit_customer_challenge));
        }
    }


    private void getSalesBudgetById() {
        salesBudgetModel = dataBaseAdapter.getSalesById(salesId);
        setBudgetData();

    }

    private void setBudgetData() {
        editTextBudgetYear.setText(salesBudgetModel.getSalesYear());
        //textViewBudgetCurrency.setText(salesBudgetModel.getC());
        editTextBudgetNote.setText(salesBudgetModel.getSalesNote());
        /*editTextBudgetSalesman.setText(salesBudgetModel.getSalesman());
        textViewBudgetSector.setText(salesBudgetModel.getSalesSector());*/
        editTextBudgetRoe.setText(salesBudgetModel.getSalesROE());
        createdTime = salesBudgetModel.getSalesCreatedTime();
        createdBy = salesBudgetModel.getSalesCreatedBY();
        isSync = salesBudgetModel.isSync();


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
        textViewBudgetSector.setOnClickListener(this);
        textViewBudgetCurrency.setOnClickListener(this);
        editTextBudgetYear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.et_budget_salesman_sector:
                startNewActivity(titleSalesman_Sector, selectorList, REQUEST_SALES_SECTOR_CODE);
                break;
            case R.id.et_budget_sector:
                startNewActivity(titleSector, sectorList, REQUEST_SECTOR_CODE);
                break;
            case R.id.et_budget_currency:
                currencyPicker();
                break;
            case R.id.et_budget_year:
                showYearDialog();
                break;
        }
    }

    private void startNewActivity(String title, String[] itemList, int requestCode) {

        intent = new Intent(AddSalesBudgetActivity.this, CustomListActivity.class);
        intent.putExtra(EmployConstantUtil.ITEM_LIST, itemList);
        intent.putExtra(EmployConstantUtil.TITLE, title);
        startActivityForResult(intent, requestCode);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_task, menu);
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
                    addSalesData();

                break;
        }

        return super.onOptionsItemSelected(item);


    }

    // Method to manually check connection status
    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        if (!isConnected) {

            return false;
        }

        return true;
    }

    private void addSalesData() {
        salesBudgetModel = new SalesBudgetModel();
        if (editSalesBudget) {
            salesId = DateAndTimeUtil.currentTimeStamp();
        }
        salesBudgetModel.setSalesId(salesId);
        salesBudgetModel.setSalesYear(editTextBudgetYear.getText().toString().trim());
        /*salesBudgetModel.setSalesSalesSector(editTextBudgetSalesman_sector.getText().toString().trim());
        salesBudgetModel.setSalesSector(textViewBudgetSector.getText().toString().trim());
        salesBudgetModel.setSalesman(editTextBudgetSalesman.getText().toString().trim());
        salesBudgetModel.setSalesCurrency(textViewBudgetCurrency.getText().toString().trim());*/
        salesBudgetModel.setSalesROE(editTextBudgetRoe.getText().toString().trim());
        salesBudgetModel.setSalesNote(editTextBudgetNote.getText().toString().trim());
        salesBudgetModel.setSalesCreatedBY(createdBy);
        salesBudgetModel.setSalesModifyBy(sessionManager.getUserKeyId());
        salesBudgetModel.setSalesCreatedTime(DateAndTimeUtil.currentTimeStamp());
        salesBudgetModel.setSalesModifyTime(DateAndTimeUtil.currentTimeStamp());


        if (!editSalesBudget) {
            if (checkConnection()) {
                addSalesBudgetDataToServer();
            } else {
                salesBudgetModel.setSync(false);
                dataBaseAdapter.addSalesBudget(salesBudgetModel);
                alertDialogManager.showAlertDialogMessage(AddSalesBudgetActivity.this, "", "Sales Budget is created ", false, AddSalesBudgetActivity.this);
            }


        } else {
            if (!isSync) {

                if (checkConnection()) {
                    addSalesBudgetDataToServer();
                } else {
                    dataBaseAdapter.updateSalseBudget(salesBudgetModel, salesId);
                    alertDialogManager.showAlertDialogMessage(this, "", "Sales is Updated ", false, this);
                }


            } else {

                if (checkConnection()) {
                    addSalesBudgetDataToServer();
                } else {
                    Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
                }
            }
        }


        /*Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();*/


    }

    private void addSalesBudgetDataToServer() {
//        DialogUtitlity.showLoading(AddCallActivity.this);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final Call<String> response = apiService.salesBudget(salesBudgetModel, SessionManager.getInstance().getUserKeyId());
        response.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //DialogUtitlity.hideLoading();
                int statusCode = response.code();
                switch (statusCode) {
                    case 201:
                        salesBudgetModel.setSync(true);


                        break;
                    case 400:
                        salesBudgetModel.setSync(false);
                        break;
                }

                if (!editSalesBudget) {
                    dataBaseAdapter.addSalesBudget(salesBudgetModel);
                    alertDialogManager.showAlertDialogMessage(AddSalesBudgetActivity.this, "", "Sales Budget is created ", false, AddSalesBudgetActivity.this);
                } else {
                    int result = dataBaseAdapter.updateSalseBudget(salesBudgetModel, salesId);
                    alertDialogManager.showAlertDialogMessage(AddSalesBudgetActivity.this, "", "Sales is updated ", false, AddSalesBudgetActivity.this);


                }





            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //DialogUtitlity.hideLoading();
                Log.e(TAG, "NOT CREATED");
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();


            }
        });

    }

    private boolean checkValidation() {
        if (editTextBudgetYear.getText().toString().trim().isEmpty()) {
            alertDialogManager.showAlertDialog(this, errorTitle, emptySalesYear, false);
            return false;
        }
        /*if (editTextBudgetSalesman_sector.getText().toString().trim().equals("-None-")) {
            alertDialogManager.showAlertDialog(this, errorTitle, "Select ", false);
            return false;
        }*/

        if (editTextBudgetSalesman.getText().toString().trim().isEmpty()) {

            showErrorDialog(errMsgEmptySalsman);
            return false;
        }

        if (textViewBudgetSector.getText().toString().trim().equals("-None-")) {
            showErrorDialog(errMsgEmptysector);
            return false;
        }
        if (textViewBudgetCurrency.getText().toString().trim().equals("-None-")) {

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
        String selectedItem = "";
        if (data != null) {
            selectedItem = data.getStringExtra(EmployConstantUtil.SELECTED_ITEM);
        }
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_SALES_SECTOR_CODE:
                    editTextBudgetSalesman_sector.setText(selectedItem);
                    break;
                case REQUEST_SECTOR_CODE:
                    textViewBudgetSector.setText(selectedItem);
                    break;
            }
        }

    }


    public void showYearDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View yearDialog = inflater.inflate(R.layout.yeardialog, null);
        TextView year_text = (TextView) yearDialog.findViewById(R.id.year_text);
        year = Calendar.getInstance().get(Calendar.YEAR);
        year_text.setText("Current Year " + year);
        final NumberPicker nopicker = (NumberPicker) yearDialog.findViewById(R.id.numberPicker);
        nopicker.setMaxValue(year + 50);
        nopicker.setMinValue(year - 50);
        nopicker.setWrapSelectorWheel(false);
        if (!editTextBudgetYear.getText().toString().trim().isEmpty()) {
            nopicker.setValue(Integer.parseInt(editTextBudgetYear.getText().toString().trim()));
        } else {
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


    public void currencyPicker() {
        final CurrencyPicker picker = CurrencyPicker.newInstance("Select Currency");  // dialog title
        picker.setListener(new CurrencyPickerListener() {
            @Override
            public void onSelectCurrency(String name, String code, String symbol, int flagDrawableResID) {
                if (code.equals("INR")) {
                    textViewBudgetCurrency.setText(getString(R.string.Rs));
                } else {
                    textViewBudgetCurrency.setText(symbol);
                }
                picker.dismiss();
                // Implement your code here
            }
        });
        picker.show(getSupportFragmentManager(), "CURRENCY_PICKER");
    }




    @Override
    public void alertDialogCallbackOk() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void alertDialogCallbackCancel() {

    }
}
