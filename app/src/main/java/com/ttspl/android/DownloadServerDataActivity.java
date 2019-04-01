package com.ttspl.android;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.software.ttsl.AddSalesBudgetListActivity;
import com.software.ttsl.CallListActivity;
import com.software.ttsl.CustomerChallengeListActivity;
import com.software.ttsl.DownloadDataActivity;
import com.software.ttsl.EmployeeHomeActivity;
import com.software.ttsl.EventListActivity;
import com.software.ttsl.Interfacce.AlertDialogCallback;
import com.software.ttsl.R;
import com.software.ttsl.Request.AccountDataModel;
import com.software.ttsl.Request.CallDataModel;
import com.software.ttsl.Request.DealDataModel;
import com.software.ttsl.Request.EventDataModel;
import com.software.ttsl.Request.LeadDataModel;
import com.software.ttsl.Request.TaskDataModel;
import com.software.ttsl.Response.FormDropDown.DropDownDataModel;
import com.software.ttsl.Response.ImageResponse;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.TaskListActivity;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.DialogUtitlity;
import com.software.ttsl.Utils.Imageutils;
import com.software.ttsl.Utils.SessionManager;
import com.software.ttsl.model.AddContactData;
import com.software.ttsl.model.CustomerChallengeModel;
import com.software.ttsl.model.SalesBudgetModel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadServerDataActivity extends AppCompatActivity implements AlertDialogCallback {
    private static final String TAG  = DownloadServerDataActivity.class.getName();

    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    private boolean sentToSettings = false;
    private SharedPreferences permissionStatus;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.tv_msg_download)
    TextView textViewMsgDownloads;

    private DataBaseAdapter dataBaseAdapter;
    private SessionManager sessionManager;
    private AlertDialogManager alertDialogManager;
    private boolean isNeverAskAgain = false;
    private String iname;
    private ImageResponse imageResponse;
    private List<DropDownDataModel> dropDownDataModelList;
    private ArrayList<ImageResponse> imageResponses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_download_server_data);
        ButterKnife.bind(this);
        dataBaseAdapter = new DataBaseAdapter(this);

        sessionManager = SessionManager.getInstance();



        permissionStatus = getSharedPreferences("permissionStatus",MODE_PRIVATE);

        checkStoragePermission();


    }

    private void checkStoragePermission(){


        if (ActivityCompat.checkSelfPermission(DownloadServerDataActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(DownloadServerDataActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //Show Information about why you need the permission
                AlertDialogManager.showAlertDialogMessage(this,"Need Storage Permission","This app needs storage permission.",true,this);

            } else if (permissionStatus.getBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE,false)) {
                //Previously Permission Request was cancelled with 'Dont Ask Again',
                // Redirect to Settings after showing Information about why you need the permission

                AlertDialogManager.showAlertDialogMessage(this,"Need Storage Permission","This app needs storage permission.",true,this);

            } else {
                //just request the permission
                ActivityCompat.requestPermissions(DownloadServerDataActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);
            }

            SharedPreferences.Editor editor = permissionStatus.edit();
            //getAllLead();

            editor.putBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE,true);
            editor.commit();

            getStatusDropDown();


        } else {
            //You already have the permission, just go ahead.
            proceedAfterPermission();
            getStatusDropDown();
        }
    }



    private void getStatusDropDown(){

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<DropDownDataModel>> listCall = apiInterface.getStatusDropDown("Bearer "+sessionManager.getAccessToken());
        Log.i(TAG, "inside getAll lead from server");
        listCall.enqueue(new Callback<List<DropDownDataModel>>() {
            @Override
            public void onResponse(Call<List<DropDownDataModel>> call, Response<List<DropDownDataModel>> response) {
                DialogUtitlity.hideLoading();
                Log.i(TAG, "inside getAll lead from server");
                int statusCode = response.code();
                Log.i(TAG, String.valueOf(statusCode));
                if (statusCode == 200) {
                    if (response.body() instanceof List) {
                        dropDownDataModelList= response.body();
                        dataBaseAdapter.setDropDown("Status",dropDownDataModelList);
                        //getAllAccount();

                    }

                }

            }

            @Override
            public void onFailure(Call<List<DropDownDataModel>> call, Throwable t) {
                   DialogUtitlity.hideLoading();
            }
        });

    }



    private void proceedAfterPermission(){
       // getAllLead();
      //  getLeadImage();
        Toast.makeText(getBaseContext(), "We got the Storage Permission", Toast.LENGTH_LONG).show();
    }

    public void getLeadImage() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<ImageResponse>> image = apiInterface.getImage(-1L);
        Log.e(TAG, "Inside get Lead Image");
        image.enqueue(new Callback<List<ImageResponse>>() {
            @Override
            public void onResponse(Call<List<ImageResponse>> call, Response<List<ImageResponse>> response) {
                int statusCode = response.code();
                Log.e(TAG, String.valueOf(statusCode));
                if (statusCode == 200) {
                    if (response.body() instanceof List) {

                        List<ImageResponse> imageResponses = response.body();
                        int count = 0;
                        for (ImageResponse imageResponse : imageResponses) {
                            createDirectoryAndSaveFile(Imageutils.StringToBitMap(imageResponse.getImageFile()), "leadImage" + imageResponse.getImageId(), imageResponse.getImageId());
                            count++;
                        }


                        dataBaseAdapter.addImage(imageResponse);




                    }
                }
            }

            @Override
            public void onFailure(Call<List<ImageResponse>> call, Throwable t) {
                Log.e(TAG, t + "");


            }
        });


    }


    private void createDirectoryAndSaveFile(Bitmap imageToSave, String fileName, long id) {

       /* File direct = new File(Environment.getExternalStorageDirectory() + "/ImageAttach");

        if (!direct.exists()) {
            File wallpaperDirectory = new File("/sdcard/ImageAttach/");
            wallpaperDirectory.mkdirs();
        }

        File file = new File(new File("/sdcard/ImageAttach/"), fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();

        System.out.println(root +" Root value in saveImage Function");

        File myDir = new File(root + "/ttsl");



        if (!myDir.exists()) {

            myDir.mkdirs();

        }



        Random generator = new Random();

        int n = 10000;

        n = generator.nextInt(n);

        iname = fileName + n + ".jpg";

        File file = new File(myDir, iname);

        if (file.exists())

            file.delete();

        try {

            FileOutputStream out = new FileOutputStream(file);

            imageToSave.compress(Bitmap.CompressFormat.JPEG, 90, out);

            out.flush();

            out.close();

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        String path = file.getPath();
        ImageResponse imageResponse = new ImageResponse();
        imageResponse.setImageFile(path);
        imageResponse.setImageId(id);
        imageResponses.add(imageResponse);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == EXTERNAL_STORAGE_PERMISSION_CONSTANT) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //The External Storage Write Permission is granted to you... Continue your left job...
                proceedAfterPermission();
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(DownloadServerDataActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    //Show Information about why you need the permission
                    AlertDialogManager.showAlertDialogMessage(this,"Need Storage Permission","This app needs storage permission.",true,this);

                } else {
                    Toast.makeText(getBaseContext(),"Unable to get Permission",Toast.LENGTH_LONG).show();
                }
            }
        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PERMISSION_SETTING) {
            if (ActivityCompat.checkSelfPermission(DownloadServerDataActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                //Got Permission
                proceedAfterPermission();
            }
        }
    }

    
    

    private void getAllLead() {
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<LeadDataModel>> listCall = apiInterface.getLeadData(0);
        Log.i(TAG, "inside getAll lead from server");
        listCall.enqueue(new Callback<List<LeadDataModel>>() {
            @Override
            public void onResponse(Call<List<LeadDataModel>> call, Response<List<LeadDataModel>> response) {

                Log.i(TAG, "inside getAll lead from server");
                switch (response.code())
                {
                    case 200:
                        if (response.body() instanceof List) {
                            List<LeadDataModel> leadDataModelList = response.body();
                            textViewMsgDownloads.setText("Downloading your Leads from the server Please wait..");
                            dataBaseAdapter.setAllLead(leadDataModelList);
                            progressBar.setVisibility(View.GONE);
                            getAllAccount();

                        }
                    case 204:
                        textViewMsgDownloads.setText("Downloading your Leads from the server Please wait..");
                        progressBar.setVisibility(View.GONE);
                        getAllAccount();

                }

            }

            @Override
            public void onFailure(Call<List<LeadDataModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);


            }
        });
    }


    private void getAllAccount() {
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<AccountDataModel>> listCall = apiInterface.getAllAccount(0);
        Log.i(TAG, "inside getAll Account from server");
        listCall.enqueue(new Callback<List<AccountDataModel>>() {
            @Override
            public void onResponse(Call<List<AccountDataModel>> call, Response<List<AccountDataModel>> response) {

                Log.i(TAG, "inside getAll Account from server");

                switch (response.code()){
                    case 200:
                        if (response.body() instanceof List) {
                            List<AccountDataModel> accountDataModelList = response.body();
                            textViewMsgDownloads.setText("Downloading your Accounts from the server Please wait..");
                            progressBar.setVisibility(View.GONE);
                            dataBaseAdapter.setAllAccounts(accountDataModelList);
                            getAllContact();



                        }
                    case 204:
                        textViewMsgDownloads.setText("Downloading your Accounts from the server Please wait..");
                        progressBar.setVisibility(View.GONE);
                        getAllContact();


                }


            }

            @Override
            public void onFailure(Call<List<AccountDataModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);

            }


        });


    }



    private void getAllContact() {
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<AddContactData>> listCall = apiInterface.getAllContact(0);
        listCall.enqueue(new Callback<List<AddContactData>>() {
            @Override
            public void onResponse(Call<List<AddContactData>> call, Response<List<AddContactData>> response) {
                Log.i(TAG, "inside getAll contact from server");

                switch (response.code()){
                    case 200:
                        if (response.body() instanceof List) {
                            textViewMsgDownloads.setText("Downloading your Contacts from the server Please wait..");
                            List<AddContactData> contactDataList = response.body();
                            dataBaseAdapter.setAllContacts(contactDataList);
                            //getAllEvent();
                            getAllTask();

                        }
                    case 204:
                        textViewMsgDownloads.setText("Downloading your Contacts from the server Please wait..");
                        progressBar.setVisibility(View.GONE);
                       // getAllEvent();
                        getAllTask();


                }

            }

            @Override
            public void onFailure(Call<List<AddContactData>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);

            }
        });
    }


    private void getAllDeal() {
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<DealDataModel>> listCall = apiInterface.getAllDeal(0);
        listCall.enqueue(new Callback<List<DealDataModel>>() {
            @Override
            public void onResponse(Call<List<DealDataModel>> call, Response<List<DealDataModel>> response) {
                progressBar.setVisibility(View.GONE);
                Log.i(TAG, "inside getAll lead from server");

                switch(response.code())
                {
                    case 200:
                        if (response.body() instanceof List) {
                            textViewMsgDownloads.setText("Downloading your Deals from the server Please wait..");
                            List<DealDataModel> dealDataModelList = response.body();
                            dataBaseAdapter.setAllDeal(dealDataModelList);

                             finishActivity();

                            // getAllCall();


                        }
                    case 204:
                        textViewMsgDownloads.setText("Downloading your Deals from the server Please wait..");
                        progressBar.setVisibility(View.GONE);
                        finishActivity();


                }

            }

            @Override
            public void onFailure(Call<List<DealDataModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                finishActivity();


            }
        });
    }

    private void finishActivity(){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();


    }



    private void getAllEvent() {
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<EventDataModel>> listCall = apiInterface.getAllEvent(0);
        listCall.enqueue(new Callback<List<EventDataModel>>() {
            @Override
            public void onResponse(Call<List<EventDataModel>> call, Response<List<EventDataModel>> response) {
                progressBar.setVisibility(View.GONE);
                Log.i(TAG, "inside getAll lead from server");
                switch (response.code()){
                    case 200:
                        if (response.body() instanceof List) {
                            textViewMsgDownloads.setText("Downloading your Event from the server Please wait..");
                            List<EventDataModel> eventDataModelList = response.body();
                            dataBaseAdapter.setAllEvent(eventDataModelList);
                            getAllTask();


                        }
                        break;
                    case 204:
                        textViewMsgDownloads.setText("Downloading your Event from the server Please wait..");
                        progressBar.setVisibility(View.GONE);
                        getAllTask();
                }


            }

            @Override
            public void onFailure(Call<List<EventDataModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }



    private void getAllTask() {
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<TaskDataModel>> listCall = apiInterface.getAllTask(-1);
        Log.i(TAG, "inside getAll task from server");
        listCall.enqueue(new Callback<List<TaskDataModel>>() {
            @Override
            public void onResponse(Call<List<TaskDataModel>> call, Response<List<TaskDataModel>> response) {
                progressBar.setVisibility(View.GONE);
                Log.i(TAG, "inside getAll task from server");
                switch (response.code()){
                    case 200:
                        if (response.body() instanceof List) {
                            textViewMsgDownloads.setText("Downloading your Task from the server Please wait..");
                            List<TaskDataModel> taskDataModelList = response.body();
                            dataBaseAdapter.setAllTask(taskDataModelList);
                            getAllCall();


                        }
                        break;
                    case 204:
                        textViewMsgDownloads.setText("Downloading your Task from the server Please wait..");
                        getAllCall();
                        break;

                }


            }

            @Override
            public void onFailure(Call<List<TaskDataModel>> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);

            }
        });




    }


    private void getAllCall() {
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<CallDataModel>> listCall = apiInterface.getAllCall(-1);
        listCall.enqueue(new Callback<List<CallDataModel>>() {
            @Override
            public void onResponse(Call<List<CallDataModel>> call, Response<List<CallDataModel>> response) {
                progressBar.setVisibility(View.GONE);
                Log.i(TAG, "inside getAll call from server");

                switch (response.code()){
                    case 200:
                        if (response.body() instanceof List) {
                            textViewMsgDownloads.setText("Downloading your Call from the server Please wait..");
                            List<CallDataModel> callDataModels = response.body();
                            dataBaseAdapter.setAllCall(callDataModels);
                             getAllDeal();

                        }
                        break;

                    case 204:
                        textViewMsgDownloads.setText("Downloading your Call from the server Please wait..");
                        getAllDeal();
                        break;


                }

            }

            @Override
            public void onFailure(Call<List<CallDataModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }



    private void getSalesBudget(){
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<SalesBudgetModel>> listCall = apiInterface.getAllSalesBudget(0);
        listCall.enqueue(new Callback<List<SalesBudgetModel>>() {
            @Override
            public void onResponse(Call<List<SalesBudgetModel>> call, Response<List<SalesBudgetModel>> response) {
                progressBar.setVisibility(View.GONE);
                Log.i(TAG, "inside getAll sales budget from server");
                int statusCode = response.code();
                switch (response.code()){
                    case 200:
                    if (response.body() instanceof List) {
                        List<SalesBudgetModel> salesBudgetModelList= response.body();
                        dataBaseAdapter.setAllSalesBudget(salesBudgetModelList);
                        textViewMsgDownloads.setText("Downloading your SalesBudget from the server Please wait..");

                    }
                    break;
                    case 204:
                        textViewMsgDownloads.setText("Downloading your SalesBudget from the server Please wait..");

                }
            }

            @Override
            public void onFailure(Call<List<SalesBudgetModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });

    }




    private void getAllCustomerChallenge() {
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<CustomerChallengeModel>> listCall = apiInterface.getAllCustomer(0);
        listCall.enqueue(new Callback<List<CustomerChallengeModel>>() {
            @Override
            public void onResponse(Call<List<CustomerChallengeModel>> call, Response<List<CustomerChallengeModel>> response) {
                progressBar.setVisibility(View.GONE);
                Log.i(TAG, "inside getAll customer challenge from server");
                switch (response.code()){
                    case 200:
                        if (response.body() instanceof List) {
                            List<CustomerChallengeModel> customerChallengeModelList = response.body();
                            dataBaseAdapter.setAllCustomerChallenge(customerChallengeModelList);
                            textViewMsgDownloads.setText("Downloading your SalesBudget from the server Please wait..");


                        }
                        break;
                    case 204:
                        textViewMsgDownloads.setText("Downloading your SalesBudget from the server Please wait..");


                }

            }

            @Override
            public void onFailure(Call<List<CustomerChallengeModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);

            }
        });
    }


    @Override
    public void alertDialogCallbackOk() {
        if(permissionStatus.getBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE,false)) {
            sentToSettings = true;
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
            Toast.makeText(getBaseContext(), "Go to Permissions to Grant Storage", Toast.LENGTH_LONG).show();
        }else {

            ActivityCompat.requestPermissions(DownloadServerDataActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);
        }
    }

    @Override
    public void alertDialogCallbackCancel() {

        sessionManager.logoutUser();
    }
}
