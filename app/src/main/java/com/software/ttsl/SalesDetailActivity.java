package com.software.ttsl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import com.software.ttsl.model.SalesBudgetModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesDetailActivity extends AppCompatActivity implements AlertDialogCallback, Callback<String> {

    public static final String TAG = SalesDetailActivity.class.getName();
    private static final int REQUEST_EDIT_SALES_BUDGET=1000;


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_budget_year)
    TextView textViewBudgetYear;

    @BindView(R.id.tv_budget_sector)
    TextView textViewBudgetSector;

    @BindView(R.id.tv_budget_salesman)
    TextView textViewBudgetSalesman;

    @BindView(R.id.tv_budget_currency)
    TextView textViewBudgetCurrency;

    @BindView(R.id.tv_budget_roe)
    TextView textViewBudgetRoe;

    @BindView(R.id.tv_budget_note)
    TextView textViewBudgetNote;

    @BindView(R.id.tv_budget_created_by)
    TextView textViewBudgetCreatedBy;

    @BindView(R.id.tv_modify_by)
    TextView textViewBudgetModifyBy;

    @BindView(R.id.tv_budget_created_time)
    TextView textViewBudgetCreatedTime;

    @BindView(R.id.tv_budget_modify_time)
    TextView textViewBudgetModifyTime;


    private DataBaseAdapter dataBaseAdapter;
    private Long salesId;
    private SalesBudgetModel salesBudgetModel;
    private boolean isSync;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_detail);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dataBaseAdapter = new DataBaseAdapter(this);
        Intent intent = getIntent();
        salesId = intent.getLongExtra(EmployConstantUtil.KEY_SALES_BUDGET_ID, 0);



        updateUI();


    }

    private void updateUI() {
        getSaleBudgetById();
        setSaleBudgetDetails();
    }

    private void setSaleBudgetDetails() {
        textViewBudgetYear.setText(salesBudgetModel.getSalesYear());
        //textViewBudgetSector.setText(salesBudgetModel.getSalesSector());
        //textViewBudgetCurrency.setText(salesBudgetModel.getSalesCurrency());
        // textViewBudgetSalesman.setText(salesBudgetModel.getSalesman());
        textViewBudgetRoe.setText(salesBudgetModel.getSalesROE());
        textViewBudgetNote.setText(salesBudgetModel.getSalesNote());
        textViewBudgetCreatedBy.setText(salesBudgetModel.getSalesCreatedBY());
        textViewBudgetModifyBy.setText(salesBudgetModel.getSalesModifyBy());
        textViewBudgetCreatedTime.setText(DateAndTimeUtil.longToDate(salesBudgetModel.getSalesCreatedTime()));
        textViewBudgetModifyTime.setText(DateAndTimeUtil.longToDate(salesBudgetModel.getSalesModifyTime()));
    }

    private void getSaleBudgetById() {

        salesBudgetModel = dataBaseAdapter.getSalesById(salesId);
        isSync = salesBudgetModel.isSync();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sales_budget, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();
                break;
            case R.id.action_edit:
                startNewActivityForResult(AddSalesBudgetActivity.class,salesId,EmployConstantUtil.KEY_SALES_BUDGET_ID,REQUEST_EDIT_SALES_BUDGET);
                Log.e(TAG, "send sales id to edit sales" + salesId);
                break;
            case R.id.action_delete:
                DeleteSalesBudget();
                break;


        }

        return super.onOptionsItemSelected(item);
    }

    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        if (!isConnected) {
            return false;
        }
        return true;
    }

    private void DeleteSalesBudget() {
        if (!isSync) {
            if (dataBaseAdapter.deleteSales(salesId)) {
                Toast.makeText(this, "Sales has been Deleted   not sync", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(this, "Please try again later ", Toast.LENGTH_SHORT).show();
            }

        } else {

            if (checkConnection()) {
                AlertDialogManager.showAlertDialogMessage(this, "", "Are you sure to delete Sales", false, this);
            } else {
                Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
            }
        }



        /*Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();*/
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        int statusCode = response.code();
        if (statusCode == 200) {
            if (dataBaseAdapter.deleteSales(salesId)) {
                Toast.makeText(this, "Sales has been Deleted ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(this, "Please try again later ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        Log.d("testing", "" + t.getMessage());
    }

    @Override
    public void alertDialogCallbackOk() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        final Call<String> response = apiService.removeSalesBudget(salesId, SessionManager.getInstance().getUserKeyId());
        response.enqueue(this);
    }

    @Override
    public void alertDialogCallbackCancel() {

    }


    private void startNewActivityForResult(Class<?> cls, long id, String key,int requestCode) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(key, id);
        startActivityForResult(intent,requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case REQUEST_EDIT_SALES_BUDGET:
                    updateUI();
            }
        }
    }
}
