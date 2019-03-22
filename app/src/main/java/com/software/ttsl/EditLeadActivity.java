package com.software.ttsl;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.software.ttsl.Interfacce.AlertDialogCallback;
import com.software.ttsl.Request.LeadDataModel;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.ConnectivityReceiver;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.DialogUtitlity;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.Utils.EmployeeValidationUtil;
import com.software.ttsl.Utils.Imageutils;
import com.software.ttsl.Utils.SessionManager;
import com.software.ttsl.model.AddLeadData;

import java.io.ByteArrayOutputStream;
import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditLeadActivity extends AppCompatActivity implements View.OnClickListener, Imageutils.ImageAttachmentListener, Callback<String>, AlertDialogCallback {

    public static final String TAG = EditLeadActivity.class.getName();



    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.linear_layout_lead_source)
    LinearLayout linearLayoutLeadSource;


    @BindView(R.id.layout_lead_source)
    RelativeLayout relativeLayoutLeadSource;

    @BindView(R.id.layout_lead_status)
    RelativeLayout relativeLayoutLeadStatus;

    @BindView(R.id.et_lead_source)
    EditText editTextLeadSource;

    @BindView(R.id.linear_layout_lead_status)
    LinearLayout linearLayoutLeadStatus;

    @BindView(R.id.et_lead_status)
    EditText editTextLeadStatus;

    @BindView(R.id.linear_layout_lead_owner)
    LinearLayout linearLayoutLeadOwner;

    @BindView(R.id.layout_lead_owner)
    RelativeLayout relativeLayoutLeadOwner;

    @BindView(R.id.et_lead_owner)
    EditText editTextLeadOwner;

    @BindView(R.id.et_lead_company)
    EditText editTextLeadCompany;

    @BindView(R.id.et_lead_first_name)
    EditText editTextLeadFirstName;

    @BindView(R.id.et_lead_last_name)
    EditText editTextLeadLastName;

    @BindView(R.id.linear_layout_lead_title)
    LinearLayout linearLayoutLeadTitle;

    @BindView(R.id.et_lead_title)
    EditText editTextLeadTitle;


    @BindView(R.id.et_lead_phone)
    EditText editTextLeadPhone;

    @BindView(R.id.linear_layout_lead_fax)
    LinearLayout linearLayoutLeadFax;

    @BindView(R.id.et_lead_fax)
    EditText editTextLeadFax;

    @BindView(R.id.et_lead_email)
    EditText editTextEmail;

    @BindView(R.id.linear_layout_lead_mobile)
    LinearLayout linearLayoutLeadMobile;

    @BindView(R.id.et_lead_mobile)
    EditText editTextLeadMobile;

    @BindView(R.id.linear_layout_lead_website)
    LinearLayout linearLayoutLeadWebsite;

    @BindView(R.id.et_lead_website)
    EditText editTextLeadWebsite;

    @BindView(R.id.linear_layout_industry)
    LinearLayout linearLayoutIndustry;

    @BindView(R.id.layout_lead_industry)
    RelativeLayout relativeLayoutLeadIndustry;

    @BindView(R.id.et_lead_industry)
    EditText editTextLeadIndustry;


    @BindView(R.id.linear_layout_lead_employee)
    LinearLayout linearLayoutLeadEmployees;

    @BindView(R.id.et_lead_employee)
    EditText editTextLeadEmployees;

    @BindView(R.id.linear_layout_lead_revenue)
    LinearLayout linearLayoutLeadRevenue;

    @BindView(R.id.et_lead_revenue)
    EditText editTextLeadRevenue;

    @BindView(R.id.linear_layout_rating)
    LinearLayout linearLayoutLeadRating;

    @BindView(R.id.layout_lead_rating)
    RelativeLayout relativeLayoutLeadRating;

    @BindView(R.id.et_lead_rating)
    EditText editTextLeadRating;

    @BindView(R.id.switch_lead_email_opt)
    Switch switchLeadEmailOpt;

    @BindView(R.id.layout_lead_email_opt)
    RelativeLayout relativeLayoutEmailOpt;

    @BindView(R.id.et_lead_email_opt)
    EditText editTextEmailOpt;

    @BindView(R.id.linear_layout_lead_skype_id)
    LinearLayout linearLayoutLeadSkypeId;

    @BindView(R.id.linear_layout_solution)
    LinearLayout linearLayoutSolution;

    @BindView(R.id.layout_lead_solution)
    RelativeLayout relativeLayoutLeadSolution;

    @BindView(R.id.et_lead_solution)
    EditText editTextLeadSolution;

    @BindView(R.id.linear_layout_lead_secondry_mail)
    LinearLayout linearLayoutLeadSecondryMail;

    @BindView(R.id.et_lead_secondry_mail)
    EditText editTextSecondaryMail;

    @BindView(R.id.linear_layout_lead_twitter)
    LinearLayout linearLayoutLeadTwitter;

    @BindView(R.id.et_lead_twitter)
    EditText editTextLeadTwitter;

    @BindView(R.id.et_lead_skype_id)
    EditText editTextLeadSkypeId;

    @BindView(R.id.et_lead_city)
    EditText editTextLeadCity;

    @BindView(R.id.et_lead_zip_code)
    EditText editTextLeadZipCode;

    @BindView(R.id.et_lead_country)
    EditText editTextLeadCountry;

    @BindView(R.id.et_lead_street)
    EditText editTextLeadStreet;

    @BindView(R.id.et_lead_state)
    EditText editTextLeadState;

    @BindView(R.id.et_lead_description)
    EditText editTextLeadDiscription;

    @BindView(R.id.iv_lead_photo)
    ImageView imageViewLeadPhoto;


    @BindView(R.id.linear_layout_address_information)
    LinearLayout linearLayoutAddressInformation;


    @BindView(R.id.layout_lead_image)
    RelativeLayout relativeLayoutLeadImage;


    @BindView(R.id.iv_lead_camera)
    ImageView imageViewLeadCamera;

    @BindView(R.id.tv_add_photo)
    TextView textViewAddPhoto;

    @BindView(R.id.tv_smart_view)
    TextView textViewSmartView;

    @BindView(R.id.tv_lead_company)
    TextView textViewLeadCompany;

    @BindView(R.id.tv_lead_last_name)
    TextView textViewLeadLastName;

    long leadId;
    private Imageutils imageutils;
    Intent intent;
    private String leadOwner, company, annualRevenue, noOfEmployee, firstName, lastName, title, phone, email, fax, mobileNo, webSite, leadSource, leadStatus, industry, skypeId, solution, secondaryEmail, twitter, description, rating, street, zipCode, state, city, country, status;
    private Boolean isEmailOptOut;
    private String[] itemList, leadSourceList, leadStatusList, leadIndustryList, leadRatingList, leadSolutionList;
    private Bitmap bitmap;
    private String file_name;
    private AddLeadData addLeadData;
    private AlertDialogManager alertDialogManager;
    private LeadDataModel leadDataModel;
    private String titleLeadSource, titleLeadStatus, titleLeadIndustry, titleLeadRating, titleLeadSolution, errorTitle, msgEmptyCompany, msgEmptyLastName, smartView, showAllFields, msgValidEmailAddress, titleLeadOwner, errMsgInvalidPhoneNo, errMsgInvalidFax, errMsgInvalidURL, errMsgInvalidTwitterID, errMSgInvalidSktypeID,createdBy;
    private byte[] leadImage;
    private long createdTime;
    private boolean isSync;
    DataBaseAdapter dataBaseAdapter;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lead);
        SessionManager.setContext(getApplicationContext());

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dataBaseAdapter = new DataBaseAdapter(this);
        SessionManager.setContext(getApplicationContext());
        sessionManager = SessionManager.getInstance();


        alertDialogManager = new AlertDialogManager();

        Intent intent = getIntent();
        leadId = intent.getLongExtra(EmployConstantUtil.KEY_LEAD_ID, 0);
        Log.e(TAG, "EDIT LEAD ACTIVITY  " + leadId);

        imageutils = new Imageutils(this);
        getLeadById();


        setClickListener();

        getStringResources();
    }

    private void getLeadById() {
        DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(this);
        dataBaseAdapter.openDataBase();
        leadDataModel = dataBaseAdapter.getLeadByID(leadId);
        setData();
    }


    private void getStringResources() {
        textViewLeadLastName.setText(Html.fromHtml(getResources().getString(R.string.label_lead_last_name)));
        textViewLeadCompany.setText(Html.fromHtml(getResources().getString(R.string.label_lead_company)));
        leadSourceList = getResources().getStringArray(R.array.lead_source);
        leadStatusList = getResources().getStringArray(R.array.lead_status);
        leadIndustryList = getResources().getStringArray(R.array.lead_industry);
        leadRatingList = getResources().getStringArray(R.array.lead_rating);
        leadSolutionList = getResources().getStringArray(R.array.lead_solution);
        titleLeadSource = getResources().getString(R.string.title_lead_source);
        titleLeadStatus = getResources().getString(R.string.title_lead_status);
        titleLeadIndustry = getResources().getString(R.string.title_lead_industry);
        titleLeadRating = getResources().getString(R.string.title_lead_rating);
        titleLeadSolution = getResources().getString(R.string.title_lead_solution);
        errorTitle = getResources().getString(R.string.err_msg_title_dialog);
        msgEmptyCompany = getResources().getString(R.string.err_msg_company_empty);
        msgEmptyLastName = getResources().getString(R.string.err_msg_last_name_empty);
        smartView = getResources().getString(R.string.footer_smart_view);
        showAllFields = getResources().getString(R.string.footer_show_all_fields);
        msgValidEmailAddress = getResources().getString(R.string.msg_email_validation);
        titleLeadOwner = getResources().getString(R.string.title_lead_owner);
        errMsgInvalidPhoneNo = getResources().getString(R.string.err_msg_phone_no_not_valid);
        errMsgInvalidFax = getResources().getString(R.string.err_msg_fax_not_valid);
        errMsgInvalidURL = getResources().getString(R.string.err_msg_url_not_valid);
        errMsgInvalidTwitterID = getResources().getString(R.string.err_msg_twitter_not_valid);
        errMSgInvalidSktypeID = getResources().getString(R.string.err_msg_skypeid_not_valid);
    }


    private void setData() {
        leadImage = leadDataModel.getUploadedInputStream();
        if(leadImage!=null){
            textViewAddPhoto.setVisibility(View.GONE);
            imageViewLeadCamera.setVisibility(View.GONE);
            imageViewLeadPhoto.setVisibility(View.VISIBLE);
            imageViewLeadPhoto.setImageBitmap(convertImageToBitMap(leadImage));
        }
        editTextLeadOwner.setText(leadDataModel.getLeadOwner());
        editTextLeadCompany.setText(leadDataModel.getCompany());
        editTextLeadFirstName.setText(leadDataModel.getFirstName());
        editTextLeadLastName.setText(leadDataModel.getLastName());
        editTextLeadTitle.setText(leadDataModel.getTitle());
        editTextLeadPhone.setText(leadDataModel.getPhone());
        editTextEmail.setText(leadDataModel.getEmail());
        editTextLeadFax.setText(leadDataModel.getFax());
        editTextLeadMobile.setText(leadDataModel.getMobile());
        editTextLeadWebsite.setText(leadDataModel.getWebsite());
        editTextLeadStatus.setText(leadDataModel.getLeadStatus());
        editTextLeadSource.setText(leadDataModel.getLeadSource());
        editTextLeadIndustry.setText(leadDataModel.getIndustry());
        editTextLeadEmployees.setText(leadDataModel.getNoOfEmployees());
        editTextLeadRevenue.setText(leadDataModel.getAnnualRevenue());
        editTextLeadRating.setText(leadDataModel.getRating());
        switchLeadEmailOpt.setChecked(leadDataModel.getEmailOptOut());
        editTextLeadSolution.setText(leadDataModel.getSalutation());
        editTextLeadSkypeId.setText(leadDataModel.getSkypeId());
        editTextSecondaryMail.setText(leadDataModel.getSecondaryEmailId());
        editTextLeadTwitter.setText(leadDataModel.getTwitter());
        editTextLeadState.setText(leadDataModel.getAddressState());
        editTextLeadStreet.setText(leadDataModel.getAddressStreet());
        editTextLeadCity.setText(leadDataModel.getAddressCity());
        editTextLeadZipCode.setText(leadDataModel.getAddressZipCode());
        editTextLeadCountry.setText(leadDataModel.getAddressCounty());
        editTextLeadDiscription.setText(leadDataModel.getDescription());
        createdTime = leadDataModel.getCreatedDate();
        createdBy = leadDataModel.getCreatedBy();
        isSync = leadDataModel.isSync();

    }

    private void setClickListener() {
        editTextLeadOwner.setOnClickListener(this);
        editTextLeadStatus.setOnClickListener(this);
        editTextLeadIndustry.setOnClickListener(this);
        editTextLeadSource.setOnClickListener(this);
        editTextLeadRating.setOnClickListener(this);
        editTextLeadSolution.setOnClickListener(this);
        textViewSmartView.setOnClickListener(this);
        relativeLayoutLeadImage.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.et_lead_owner:
                itemList = new String[]{"Test"};
                title = titleLeadOwner;
                startNewActivity(title, itemList);
                break;
            case R.id.et_lead_source:
                itemList = leadSourceList;
                title = titleLeadSource;
                startNewActivity(title, itemList);
                break;
            case R.id.et_lead_status:
                itemList = leadStatusList;
                title = titleLeadStatus;
                startNewActivity(title, itemList);
                break;
            case R.id.et_lead_industry:
                itemList = leadIndustryList;
                title = titleLeadIndustry;
                startNewActivity(title, itemList);
                break;
            case R.id.et_lead_rating:
                itemList = leadRatingList;
                title = titleLeadRating;
                startNewActivity(title, itemList);
                break;
            case R.id.et_lead_solution:
                itemList = leadSolutionList;
                title = titleLeadSolution;
                startNewActivity(title, itemList);
                break;
            case R.id.tv_smart_view:
                startSmartView();
                break;

            case R.id.layout_lead_image:
                Toast.makeText(this, "image clicked", Toast.LENGTH_SHORT).show();
                imageutils.imagepicker(1);
                break;

        }

    }


    private void startSmartView() {
        if (textViewSmartView.getText().toString().equals(smartView)) {
            linearLayoutLeadOwner.setVisibility(View.GONE);
            linearLayoutLeadTitle.setVisibility(View.GONE);
            linearLayoutLeadFax.setVisibility(View.GONE);
            linearLayoutIndustry.setVisibility(View.GONE);
            linearLayoutLeadMobile.setVisibility(View.GONE);
            linearLayoutLeadWebsite.setVisibility(View.GONE);
            linearLayoutLeadEmployees.setVisibility(View.GONE);
            linearLayoutLeadRevenue.setVisibility(View.GONE);
            linearLayoutLeadRating.setVisibility(View.GONE);
            linearLayoutLeadSkypeId.setVisibility(View.GONE);
            linearLayoutSolution.setVisibility(View.GONE);
            linearLayoutLeadSecondryMail.setVisibility(View.GONE);
            linearLayoutLeadTwitter.setVisibility(View.GONE);
            linearLayoutLeadSource.setVisibility(View.GONE);
            linearLayoutLeadStatus.setVisibility(View.GONE);

            linearLayoutAddressInformation.setVisibility(View.GONE);
            textViewSmartView.setText(showAllFields);
        } else {
            linearLayoutLeadOwner.setVisibility(View.VISIBLE);
            linearLayoutLeadTitle.setVisibility(View.VISIBLE);
            linearLayoutLeadFax.setVisibility(View.VISIBLE);
            linearLayoutIndustry.setVisibility(View.VISIBLE);
            linearLayoutLeadMobile.setVisibility(View.VISIBLE);
            linearLayoutLeadWebsite.setVisibility(View.VISIBLE);
            linearLayoutLeadEmployees.setVisibility(View.VISIBLE);
            linearLayoutLeadRevenue.setVisibility(View.VISIBLE);
            linearLayoutLeadRating.setVisibility(View.VISIBLE);
            linearLayoutLeadSkypeId.setVisibility(View.VISIBLE);
            linearLayoutSolution.setVisibility(View.VISIBLE);
            linearLayoutLeadSecondryMail.setVisibility(View.VISIBLE);
            linearLayoutLeadTwitter.setVisibility(View.VISIBLE);
            linearLayoutLeadSource.setVisibility(View.VISIBLE);
            linearLayoutLeadStatus.setVisibility(View.VISIBLE);
            linearLayoutAddressInformation.setVisibility(View.VISIBLE);
            textViewSmartView.setText(smartView);
        }

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

                if(setLeadData()) {

                    updateLeadData();
                }

                break;
        }

        return super.onOptionsItemSelected(item);


    }

    private void updateLeadData() {
        leadDataModel = new LeadDataModel();
        leadDataModel.setUploadedInputStream(leadImage);
        leadDataModel.setLeadId(System.currentTimeMillis() - 1000);
        leadDataModel.setLeadOwner(leadOwner);
        leadDataModel.setFirstName(firstName);
        leadDataModel.setLastName(lastName);
        leadDataModel.setCompany(company);
        leadDataModel.setTitle(title);
        leadDataModel.setEmail(email);
        leadDataModel.setPhone(phone);
        leadDataModel.setFax(fax);
        leadDataModel.setMobile(mobileNo);
        leadDataModel.setWebsite(webSite);
        leadDataModel.setLeadSource(leadSource);
        leadDataModel.setLeadStatus(status);
        leadDataModel.setIndustry(industry);
        leadDataModel.setNoOfEmployees(noOfEmployee);
        leadDataModel.setAnnualRevenue(annualRevenue);
        leadDataModel.setRating(rating);
        leadDataModel.setEmailOptOut(isEmailOptOut);
        leadDataModel.setSkypeId(skypeId);
        leadDataModel.setAddressStreet(street);
        leadDataModel.setAddressCity(city);
        leadDataModel.setAddressZipCode(zipCode);
        leadDataModel.setAddressCounty(country);
        leadDataModel.setAddressState(state);
        leadDataModel.setDescription(description);
        leadDataModel.setCreatedBy(createdBy);
        leadDataModel.setModifyBy(sessionManager.getUserKeyId());
        leadDataModel.setSalutation(solution);
        leadDataModel.setSecondaryEmailId(secondaryEmail);
        leadDataModel.setTwitter(twitter);
        leadDataModel.setCreatedDate(createdTime);
        leadDataModel.setModifyDate(DateAndTimeUtil.currentTimeStamp());
        leadDataModel.setSync(isSync);

        dataBaseAdapter.openDataBase();


       /* if (checkConnection()) {
            updateLeadDataToServer();
        }else {

           *//* dataBaseAdapter.addLeadData(leadDataModel);
            alertDialogManager.showAlertDialogMessage(this, "", "Lead is created ", false, this);

*//*




        }

*/



        if (!isSync) {

            if (checkConnection()) {
                updateLeadDataToServer();
            } else {
                dataBaseAdapter.updateLeadData(leadDataModel, leadId);
                alertDialogManager.showAlertDialogMessage(this, "", "Lead is Updated ", false, this);
            }


        } else {

            if (checkConnection()) {
                updateLeadDataToServer();
            } else {
                Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
            }
        }


        /*Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();*/


    }


    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        if (!isConnected) {

            //showSnack(isConnected);
            return false;
        }

        return true;
    }

    private void updateLeadDataToServer() {

        DialogUtitlity.showLoading(EditLeadActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> listCall = apiInterface.addLead(leadDataModel,"admin");
        Log.e("updateLead", "inside updateLeadDataToServer");
        listCall.enqueue(this);

    }


    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        DialogUtitlity.hideLoading();
        Log.e("addLead", "inside UpdateLeadData to server");
        int statusCode = response.code();
        Log.e("addLead", String.valueOf(statusCode));
        if (statusCode == 200) {
            if (response.body() instanceof String) {

                leadDataModel.setSync(isSync);

            }


        } else if (statusCode == 201) {
            leadDataModel.setSync(true);


        } else if (statusCode == 500) {


            leadDataModel.setSync(isSync);

        }

        int result = dataBaseAdapter.updateLeadData(leadDataModel, leadId);
        alertDialogManager.showAlertDialogMessage(this, "", "Lead is created ", false, this);


    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }





    private boolean setLeadData() {

        if (!CheckValidation()) {

            return false;

        }

        leadOwner = checkSelectListItem(editTextLeadOwner.getText().toString().trim());
        firstName = editTextLeadFirstName.getText().toString().trim();
        title = editTextLeadTitle.getText().toString().trim();
        leadSource = checkSelectListItem(editTextLeadSource.getText().toString().trim());
        industry = checkSelectListItem(editTextLeadIndustry.getText().toString().trim());
        noOfEmployee = editTextLeadEmployees.getText().toString().trim();
        annualRevenue = editTextLeadRevenue.getText().toString().trim();
        rating = checkSelectListItem(editTextLeadRating.getText().toString().trim());
        isEmailOptOut = switchLeadEmailOpt.isChecked();
        solution = checkSelectListItem(editTextLeadSolution.getText().toString().trim());
        description = editTextLeadDiscription.getText().toString().trim();
        street = editTextLeadStreet.getText().toString().trim();
        city = editTextLeadCity.getText().toString().trim();
        zipCode = editTextLeadZipCode.getText().toString().trim();
        country = editTextLeadCountry.getText().toString().trim();
        state = editTextLeadState.getText().toString().trim();
        status = checkSelectListItem(editTextLeadStatus.getText().toString().trim());

        return true;

    }


    private String checkSelectListItem(String selectedItem) {

        return selectedItem.equals(EmployConstantUtil.NOTING_SELECTED_VALUE) ? "" : selectedItem;
    }


    private boolean CheckValidation() {
        company = editTextLeadCompany.getText().toString().trim();
        lastName = editTextLeadLastName.getText().toString().trim();
        if (company.isEmpty()) {
            showErrorDialog(msgEmptyCompany);
            return false;

        }
        if (lastName.isEmpty()) {
            showErrorDialog(msgEmptyLastName);
            return false;


        }

        email = editTextEmail.getText().toString().trim();
        phone = editTextLeadPhone.getText().toString().trim();

        if (!email.isEmpty() && !EmployeeValidationUtil.validateEmail(email)) {
            showErrorDialog(msgValidEmailAddress);

            return false;


        }
        if (!phone.isEmpty() && !EmployeeValidationUtil.validateMobile(phone)) {
            showErrorDialog(errMsgInvalidPhoneNo);
            return false;


        }
        fax = editTextLeadFax.getText().toString().trim();
        mobileNo = editTextLeadMobile.getText().toString().trim();
        webSite = editTextLeadWebsite.getText().toString().trim();
        secondaryEmail = editTextSecondaryMail.getText().toString().trim();
        twitter = editTextLeadTwitter.getText().toString().trim();
        skypeId = editTextLeadSkypeId.getText().toString().trim();
        if (textViewSmartView.getText().toString().equals(smartView)) {

            if (!fax.isEmpty() && !EmployeeValidationUtil.validateFax(fax)) {
                showErrorDialog(msgValidEmailAddress);
                return false;

            }

            if (!mobileNo.isEmpty() && !EmployeeValidationUtil.validateMobile(mobileNo)) {
                showErrorDialog(errMsgInvalidPhoneNo);
                return false;

            }
            if (!webSite.isEmpty() && !EmployeeValidationUtil.validateUrl(webSite)) {
                showErrorDialog(errMsgInvalidURL);
                return false;


            }

            if (!secondaryEmail.isEmpty() && !EmployeeValidationUtil.validateEmail(secondaryEmail)) {
                showErrorDialog(msgValidEmailAddress);
                return false;

            }


            if (!twitter.isEmpty() && !EmployeeValidationUtil.validateUserName(twitter)) {
                showErrorDialog(errMsgInvalidTwitterID);
                return false;


            }
            if (!skypeId.isEmpty() && !EmployeeValidationUtil.validateUserName(skypeId)) {
                showErrorDialog(errMSgInvalidSktypeID);
                return false;
            }


        }


        return true;
    }



    public void showErrorDialog(String errMessage) {
        alertDialogManager.showAlertDialog(this, errorTitle, errMessage, false);

    }


    private byte[] convertImageToByteArray() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if(bitmap!=null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream);
            leadImage = byteArrayOutputStream.toByteArray();
            return leadImage;
        }
        else {
            return  null;
        }
    }


    private String convertImageToBase64String() {
        if(convertImageToByteArray()!= null) {
            return Base64.encodeToString(convertImageToByteArray(), Base64.NO_WRAP);
        }
        else return null;
    }





    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {
        this.bitmap = file;
        this.file_name = filename;
        textViewAddPhoto.setVisibility(View.GONE);
        imageViewLeadCamera.setVisibility(View.GONE);
        imageViewLeadPhoto.setVisibility(View.VISIBLE);
        imageViewLeadPhoto.setImageBitmap(file);


        String path = Environment.getExternalStorageDirectory() + File.separator + "ImageAttach" + File.separator;
        imageutils.createImage(file, filename, path, false,0);
        convertImageToByteArray();

    }


    private Bitmap convertImageToBitMap(byte[] leadImageByte) {

        return BitmapFactory.decodeByteArray(leadImageByte, 0, leadImageByte.length);
    }




    private void startNewActivity(String title, String[] itemList) {

        intent = new Intent(EditLeadActivity.this, CustomListActivity.class);
        intent.putExtra(EmployConstantUtil.ITEM_LIST, itemList);
        intent.putExtra(EmployConstantUtil.TITLE, title);
        startActivityForResult(intent, 1000);


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {
                String selectedItem = data.getStringExtra(EmployConstantUtil.SELECTED_ITEM);
                String title = data.getStringExtra(EmployConstantUtil.TITLE);
                if (title.equals(titleLeadSource)) {
                    editTextLeadSource.setText(selectedItem);

                } else if (title.equals(titleLeadStatus)) {
                    editTextLeadStatus.setText(selectedItem);
                } else if (title.equals(titleLeadRating)) {
                    editTextLeadRating.setText(selectedItem);
                } else if (title.equals(titleLeadSolution)) {
                    editTextLeadSolution.setText(selectedItem);
                } else if (title.equals(titleLeadIndustry)) {
                    editTextLeadIndustry.setText(selectedItem);
                } else if (title.equals(titleLeadOwner)) {
                    editTextLeadOwner.setText(selectedItem);
                }
            }

        } else {
            imageutils.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public void alertDialogCallbackOk() {
            startActivity(new Intent(EditLeadActivity.this, LeadListActivity.class));
            finish();

    }

    @Override
    public void alertDialogCallbackCancel() {

    }


}
