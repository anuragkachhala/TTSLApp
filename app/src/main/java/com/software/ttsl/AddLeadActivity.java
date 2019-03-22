package com.software.ttsl;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.InputFilter;
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
import com.software.ttsl.Interfacce.AlertDialogCallback1;
import com.software.ttsl.Request.LeadDataModel;
import com.software.ttsl.Response.ImageResponse;
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
import com.software.ttsl.Utils.MoneyTextWatcher;
import com.software.ttsl.Utils.MoneyValueFilter;
import com.software.ttsl.Utils.SessionManager;
import com.software.ttsl.model.AddLeadData;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddLeadActivity extends AppCompatActivity implements View.OnClickListener, Imageutils.ImageAttachmentListener, Callback<String>, AlertDialogCallback, AlertDialogCallback1 {

    private static final String TAG = AddLeadActivity.class.getName();

    private static final int REQUEST_CODE_LEAD_SOURCE = 1000;
    private static final int REQUEST_CODE_LEAD_STATUS = 2000;
    private static final int REQUEST_CODE_RATING = 3000;
    private static final int REQUEST_CODE_SALUTATION = 4000;
    private static final int REQUEST_CODE_LEAD_INDUSTRY = 5000;
    private static final int REQUEST_CODE_LEAD_OWNER = 6000;


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.linear_layout_lead_source)
    LinearLayout linearLayoutLeadSource;

    @BindView(R.id.layout_lead_source)
    RelativeLayout relativeLayoutLeadSource;

    @BindView(R.id.layout_lead_status)
    RelativeLayout relativeLayoutLeadStatus;

    @BindView(R.id.et_lead_source)
    TextView textViewLeadSource;

    @BindView(R.id.linear_layout_lead_status)
    LinearLayout linearLayoutLeadStatus;

    @BindView(R.id.et_lead_status)
    TextView textViewLeadStatus;

    @BindView(R.id.linear_layout_lead_owner)
    LinearLayout linearLayoutLeadOwner;

    @BindView(R.id.layout_lead_owner)
    RelativeLayout relativeLayoutLeadOwner;

    @BindView(R.id.et_lead_owner)
    TextView textViewLeadOwner;

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

    @BindView(R.id.switch_lead_email_opt)
    Switch switchLeadEmailOpt;


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
    TextView textViewLeadIndustry;


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
    TextView textViewLeadRating;


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
    TextView textViewLeadSolution;

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

    @BindView(R.id.tv_error_company)
    TextView textViewErrorCompany;

    @BindView(R.id.tv_error_last_name)
    TextView textViewErrorLastName;

    @BindView(R.id.tv_error_phone)
    TextView textViewErrorPhone;

    @BindView(R.id.tv_error_mail)
    TextView textViewErrorMail;

    @BindView(R.id.tv_error_fax)
    TextView textViewErrorFax;

    @BindView(R.id.tv_error_mobile)
    TextView textViewErrorMobile;

    @BindView(R.id.tv_error_website)

    TextView textViewErrorWebsite;

    @BindView(R.id.tv_error_skype_id)
    TextView textViewErrorSkypId;

    @BindView(R.id.tv_error_lead_title)
    TextView textViewErrorTitle;

    @BindView(R.id.tv_error_first_name)
    TextView textViewErrorFirstName;

    @BindView(R.id.tv_error_secondary_mail)
    TextView textViewErrorSecondaryMail;

    @BindView(R.id.tv_error_twitter)
    TextView textViewErrorTwitter;

    TextView textViewError = null;
    long leadId, createdTime;
    Intent intent;
    DataBaseAdapter dataBaseAdapter;
    String path;
    MultipartBody.Part body;
    Uri uri;
    private ImageResponse imageResponse;
    private List<ImageResponse> imageResponses = new ArrayList<ImageResponse>(1);
    private Imageutils imageutils;
    private String leadOwner, company, annualRevenue, noOfEmployee, firstName, lastName, title, phone, email, fax, mobileNo, webSite, leadSource, leadStatus, industry, skypeId, solution, secondaryEmail, twitter, description, rating, street, zipCode, state, city, country, status, createdBy;
    private Boolean isEmailOptOut;
    private String[] itemList, leadSourceList, leadStatusList, leadIndustryList, leadRatingList, leadSolutionList;
    private Bitmap bitmap;
    private String file_name;
    private String selectedItem;
    private AddLeadData addLeadData;
    private AlertDialogManager alertDialogManager;
    private LeadDataModel leadDataModel;
    private String titleLeadSource, titleLeadStatus, titleLeadIndustry, titleLeadRating, titleLeadSolution, errorTitle, msgEmptyCompany, msgEmptyLastName, smartView, showAllFields, msgValidEmailAddress, msgValidSecondryEmailAddress, titleLeadOwner, errMsgInvalidPhoneNo, errMsgInvalidFax, errMsgInvalidURL, errMsgInvalidTwitterID, errMSgInvalidSktypeID;
    private byte[] leadImage;
    private boolean isSync = false, editLead = false;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lead);

        SessionManager.setContext(getApplicationContext());

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setAmount();
        editTextLeadRevenue.setFilters(new InputFilter[]{new MoneyValueFilter(editTextLeadRevenue)});

        sessionManager = SessionManager.getInstance();
        createdBy = sessionManager.getUserKeyId();

        alertDialogManager = new AlertDialogManager();
        dataBaseAdapter = new DataBaseAdapter(this);

        imageutils = new Imageutils(this);


        Intent intent = getIntent();

        leadId = intent.getLongExtra(EmployConstantUtil.KEY_LEAD_ID, 0);
        if (leadId != 0) {
            editLead = true;

        } else {
            leadId = DateAndTimeUtil.currentTimeStamp();
        }


        setClickListener();
        getStringResources();

        if (editLead) {
            getLeadById();
            toolbar.setTitle(getResources().getString(R.string.title_edit_lead_activity));
        }
        //getImage();
    }

    private void getLeadById() {
        leadDataModel = dataBaseAdapter.getLeadByID(leadId);

        setData();
    }

    private Bitmap convertFileToBitmap(String filePath) {
        File file = new File(filePath);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inSampleSize = 2;
        options.inTempStorage = new byte[16 * 1024];

        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);

        if (bitmap != null)
            return bitmap;
        else
            return null;
    }

    private void setData() {
        String leadImage = dataBaseAdapter.getImage(leadId);
        if (leadImage != null) {
            textViewAddPhoto.setVisibility(View.GONE);
            imageViewLeadCamera.setVisibility(View.GONE);
            imageViewLeadPhoto.setVisibility(View.VISIBLE);
            bitmap = convertFileToBitmap(leadImage);
            /*Imageutils.StringToBitMap(leadImage);*/

            imageViewLeadPhoto.setImageBitmap(bitmap);

        }
        textViewLeadOwner.setText(leadDataModel.getLeadOwner());
        editTextLeadCompany.setText(leadDataModel.getCompany());
        editTextLeadFirstName.setText(leadDataModel.getFirstName());
        editTextLeadLastName.setText(leadDataModel.getLastName());
        editTextLeadTitle.setText(leadDataModel.getTitle());
        editTextLeadPhone.setText(leadDataModel.getPhone());
        editTextEmail.setText(leadDataModel.getEmail());
        editTextLeadFax.setText(leadDataModel.getFax());
        editTextLeadMobile.setText(leadDataModel.getMobile());
        editTextLeadWebsite.setText(leadDataModel.getWebsite());
        textViewLeadStatus.setText(leadDataModel.getLeadStatus());
        textViewLeadSource.setText(leadDataModel.getLeadSource());
        textViewLeadIndustry.setText(leadDataModel.getIndustry());
        editTextLeadEmployees.setText(leadDataModel.getNoOfEmployees());
        editTextLeadRevenue.setText(leadDataModel.getAnnualRevenue());
        textViewLeadRating.setText(leadDataModel.getRating());
        switchLeadEmailOpt.setChecked(leadDataModel.getEmailOptOut());
        textViewLeadSolution.setText(leadDataModel.getSalutation());
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

    private Bitmap convertImageToBitMap(byte[] leadImageByte) {

        return BitmapFactory.decodeByteArray(leadImageByte, 0, leadImageByte.length);
    }

    private void getStringResources() {
        textViewLeadLastName.setText(Html.fromHtml(EmployConstantUtil.ASTERISK + getResources().getString(R.string.label_lead_last_name)));
        textViewLeadCompany.setText(Html.fromHtml(EmployConstantUtil.ASTERISK + getResources().getString(R.string.label_lead_company)));
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
        msgValidSecondryEmailAddress = getResources().getString(R.string.msg_secondary_email_validation);
        errMsgInvalidPhoneNo = getResources().getString(R.string.err_msg_phone_no_not_valid);
        titleLeadOwner = getResources().getString(R.string.title_lead_owner);
        errMsgInvalidFax = getResources().getString(R.string.err_msg_fax_not_valid);
        errMsgInvalidURL = getResources().getString(R.string.err_msg_url_not_valid);
        errMsgInvalidTwitterID = getResources().getString(R.string.err_msg_twitter_not_valid);
        errMSgInvalidSktypeID = getResources().getString(R.string.err_msg_skypeid_not_valid);
    }

    private void setClickListener() {
        textViewLeadOwner.setOnClickListener(this);
        textViewLeadStatus.setOnClickListener(this);
        textViewLeadIndustry.setOnClickListener(this);
        textViewLeadSource.setOnClickListener(this);
        textViewLeadRating.setOnClickListener(this);
        textViewLeadSolution.setOnClickListener(this);
        textViewSmartView.setOnClickListener(this);
        relativeLayoutLeadImage.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.et_lead_owner:
                itemList = new String[]{createdBy};
                title = titleLeadOwner;
                startNewActivity(title, itemList, REQUEST_CODE_LEAD_OWNER, textViewLeadOwner.getText().toString());
                break;
            case R.id.et_lead_source:
                startNewActivity(titleLeadSource, leadSourceList, REQUEST_CODE_LEAD_SOURCE, textViewLeadOwner.getText().toString());
                break;
            case R.id.et_lead_status:
                itemList = leadStatusList;
                startNewActivity(titleLeadStatus, leadStatusList, REQUEST_CODE_LEAD_STATUS, textViewLeadOwner.getText().toString());
                break;
            case R.id.et_lead_industry:
                startNewActivity(titleLeadIndustry, leadIndustryList, REQUEST_CODE_LEAD_INDUSTRY, textViewLeadOwner.getText().toString());
                break;
            case R.id.et_lead_rating:
                startNewActivity(titleLeadRating, leadRatingList, REQUEST_CODE_RATING, textViewLeadOwner.getText().toString());
                break;
            case R.id.et_lead_solution:
                startNewActivity(titleLeadSolution, leadSolutionList, REQUEST_CODE_SALUTATION, textViewLeadSolution.getText().toString());
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


    //addLeadDataToServer();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.add_task:

                if (setLeadData()) {
                    if (!editLead) {
                        alertDialogManager.showAlertDialogMessage(this, "", "Are you sure to create a lead ", false, this);
                    } else {
                        alertDialogManager.showAlertDialogMessage(this, "", "Are you sure to update a lead ", false, this);
                    }
                }

                break;
        }

        return super.onOptionsItemSelected(item);


    }

    private void addLeadData() {
        leadDataModel = new LeadDataModel();
        //  leadDataModel.setUploadedInputStream(leadImage);
        if (editLead) {
            leadDataModel.setLeadId(leadId);

        } else {
            createdTime = DateAndTimeUtil.currentTimeStamp();

        }
        leadDataModel.setCreatedDate(createdTime);
        leadDataModel.setLeadId(leadId);
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

        leadDataModel.setModifyDate(DateAndTimeUtil.currentTimeStamp());
        leadDataModel.setSync(isSync);


        //addImageData();
        if (uri != null) {
            convertInMultipart();
        }
        dataBaseAdapter.openDataBase();


        if (editLead) {
            if (!isSync) {

                if (checkConnection()) {
                    addLeadDataToServer();
                } else {

                    dataBaseAdapter.updateLeadData(leadDataModel, leadId);
                    dataBaseAdapter.updateImage(imageResponse);
                    //   alertDialogManager.showAlertDialogMessage1(this, "", "Lead is Updated ", false, this);
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }


            } else {

                if (checkConnection()) {
                    addLeadDataToServer();
                } else {
                    Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
                }
            }

        } else {

            if (checkConnection()) {
                addLeadDataToServer();
            } else {

                leadDataModel.setSync(false);
                if (imageResponses != null) {
                    dataBaseAdapter.addImage(imageResponses);
                    dataBaseAdapter.addLeadData(leadDataModel);
                }
                //  alertDialogManager.showAlertDialogMessage1(this, "", "Lead is Created", false, this);

                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();

            }
        }


    }

    public void addImageData() {
        imageResponse = new ImageResponse();
        imageResponse.setImageId(leadId);
        imageResponse.setImageFile(path + file_name);
        imageResponses.add(imageResponse);

    }

    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        if (!isConnected) {

            //showSnack(isConnected);
            return false;
        }

        return true;
    }

    private boolean setLeadData() {

        if (!CheckValidation()) {

            return false;

        }

        leadOwner = checkSelectListItem(textViewLeadOwner.getText().toString().trim());
        firstName = editTextLeadFirstName.getText().toString().trim();
        title = editTextLeadTitle.getText().toString().trim();
        leadSource = checkSelectListItem(textViewLeadSource.getText().toString().trim());
        industry = checkSelectListItem(textViewLeadIndustry.getText().toString().trim());
        noOfEmployee = editTextLeadEmployees.getText().toString().trim();
        annualRevenue = editTextLeadRevenue.getText().toString().trim();
        rating = checkSelectListItem(textViewLeadRating.getText().toString().trim());
        isEmailOptOut = switchLeadEmailOpt.isChecked();
        solution = checkSelectListItem(textViewLeadSolution.getText().toString().trim());
        description = editTextLeadDiscription.getText().toString().trim();
        street = editTextLeadStreet.getText().toString().trim();
        city = editTextLeadCity.getText().toString().trim();
        zipCode = editTextLeadZipCode.getText().toString().trim();
        country = editTextLeadCountry.getText().toString().trim();
        state = editTextLeadState.getText().toString().trim();
        status = checkSelectListItem(textViewLeadStatus.getText().toString().trim());

        return true;

    }

    private String checkSelectListItem(String selectedItem) {

        return selectedItem.equals(EmployConstantUtil.NOTING_SELECTED_VALUE) ? "" : selectedItem;
    }

    private boolean CheckValidation() {
        if (textViewError != null) {
            textViewError.setVisibility(View.GONE);
        }
        company = editTextLeadCompany.getText().toString().trim();
        lastName = editTextLeadLastName.getText().toString().trim();
        if (company.isEmpty()) {
            //showErrorDialog(msgEmptyCompany);
            showErrorMsg(textViewErrorCompany, msgEmptyCompany, editTextLeadCompany);
            return false;

        }
        if (!EmployeeValidationUtil.validateName(company, 3)) {
            showErrorMsg(textViewErrorCompany, getResources().getString(R.string.err_msg_company_name_char), editTextLeadCompany);
            return false;
        }
        if (lastName.isEmpty()) {
            //showErrorDialog(msgEmptyLastName);
            showErrorMsg(textViewErrorLastName, msgEmptyLastName, editTextLeadLastName);
            return false;


        }
        if (!EmployeeValidationUtil.validateName(lastName, 3)) {
            showErrorMsg(textViewErrorLastName, getResources().getString(R.string.err_msg_last_name_char), editTextLeadLastName);
            return false;
        }


        email = editTextEmail.getText().toString().trim();
        phone = editTextLeadPhone.getText().toString().trim();

        if (!email.isEmpty() && !EmployeeValidationUtil.validateEmail(email)) {
            //showErrorDialog(msgValidEmailAddress);
            showErrorMsg(textViewErrorMail, msgValidEmailAddress, editTextEmail);
            return false;


        }
        if (!phone.isEmpty() && !EmployeeValidationUtil.validateMobile(phone)) {
            //showErrorDialog(errMsgInvalidPhoneNo);
            showErrorMsg(textViewErrorPhone, errMsgInvalidPhoneNo, editTextLeadPhone);
            return false;


        }
        fax = editTextLeadFax.getText().toString().trim();
        mobileNo = editTextLeadMobile.getText().toString().trim();
        webSite = editTextLeadWebsite.getText().toString().trim();
        secondaryEmail = editTextSecondaryMail.getText().toString().trim();
        twitter = editTextLeadTwitter.getText().toString().trim();
        skypeId = editTextLeadSkypeId.getText().toString().trim();
        firstName = editTextLeadFirstName.getText().toString().trim();
        title = editTextLeadTitle.getText().toString().trim();
        if (textViewSmartView.getText().toString().equals(smartView)) {


            if (!firstName.isEmpty() && !EmployeeValidationUtil.validateName(firstName, 3)) {
                //showErrorDialog(msgEmptyLastName);
                showErrorMsg(textViewErrorFirstName, getResources().getString(R.string.err_msg_first_name_char), editTextLeadFirstName);
                return false;


            }
            if (!title.isEmpty() && !EmployeeValidationUtil.validateName(title, 3)) {
                //showErrorDialog(msgEmptyLastName);
                showErrorMsg(textViewErrorTitle, getResources().getString(R.string.err_msg_title_char), editTextLeadTitle);
                return false;

            }


            if (!fax.isEmpty() && !EmployeeValidationUtil.validateFax(fax)) {
                //showErrorDialog(errMsgInvalidFax);
                showErrorMsg(textViewErrorFax, errMsgInvalidFax, editTextLeadFax);
                return false;

            }

            if (!mobileNo.isEmpty() && !EmployeeValidationUtil.validateMobile(mobileNo)) {
                // showErrorDialog(errMsgInvalidPhoneNo);
                showErrorMsg(textViewErrorMobile, errMsgInvalidPhoneNo, editTextLeadMobile);
                return false;

            }
            if (!webSite.isEmpty() && !EmployeeValidationUtil.validateUrl(webSite)) {
                //showErrorDialog(errMsgInvalidURL);
                showErrorMsg(textViewErrorWebsite, errMsgInvalidURL, editTextLeadWebsite);
                return false;


            }

            if (!secondaryEmail.isEmpty() && !EmployeeValidationUtil.validateEmail(secondaryEmail)) {
                // showErrorDialog(msgValidSecondryEmailAddress);
                showErrorMsg(textViewErrorSecondaryMail, msgValidSecondryEmailAddress, editTextSecondaryMail);
                return false;

            }


            if (!twitter.isEmpty() && !EmployeeValidationUtil.validateUserName(twitter)) {
                // showErrorDialog(errMsgInvalidTwitterID);
                showErrorMsg(textViewErrorTwitter, errMsgInvalidTwitterID, editTextLeadTwitter);
                return false;


            }
            if (!skypeId.isEmpty() && !EmployeeValidationUtil.validateUserName(skypeId)) {
                // showErrorDialog(errMSgInvalidSktypeID);
                showErrorMsg(textViewErrorSkypId, errMSgInvalidSktypeID, editTextLeadSkypeId);
                return false;
            }


        }


        return true;
    }


    private boolean showErrorMsg(TextView textView, String errorMessage, EditText editText) {
        editText.requestFocus();
        textView.setText(errorMessage);
        textView.setVisibility(View.VISIBLE);
        textViewError = textView;
        return false;
    }


    public void showErrorDialog(String errMessage) {
        alertDialogManager.showAlertDialog(this, errorTitle, errMessage, false);

    }

    private byte[] convertImageToByteArray() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream);
            leadImage = byteArrayOutputStream.toByteArray();
            Log.e(TAG, leadImage + "");
            return leadImage;

        } else {
            return null;
        }
    }

    private String convertImageToBase64String() {

        if (convertImageToByteArray() != null) {
            return Base64.encodeToString(convertImageToByteArray(), Base64.NO_WRAP);
        } else return null;
    }

    @Override
    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {
        this.bitmap = file;
        this.file_name = filename;
        this.uri = uri;
        textViewAddPhoto.setVisibility(View.GONE);
        imageViewLeadCamera.setVisibility(View.GONE);
        imageViewLeadPhoto.setVisibility(View.VISIBLE);
        imageViewLeadPhoto.setImageBitmap(file);
        path = Environment.getExternalStorageDirectory() + File.separator + "ImageAttach" + File.separator + "ttls" + File.separator;
        if (file != null) {
            imageutils.createImage(file, filename, path, false, leadId);
            addImageData();
        }
        convertImageToByteArray();

    }

    public void convertInMultipart() {
        //pass it like this
        String path1 = path + file_name;
        File file = new File(path1);
      /*  RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);*/

        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(getContentResolver().getType(uri)),
                        file
                );

        // MultipartBody.Part is used to send also the actual file name
        body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);


    }


    private void startNewActivity(String title, String[] itemList, int requestCode, String selectedItem) {
        this.selectedItem = selectedItem;
        intent = new Intent(AddLeadActivity.this, CustomListActivity.class);
        intent.putExtra(EmployConstantUtil.ITEM_LIST, itemList);
        intent.putExtra(EmployConstantUtil.TITLE, title);
        startActivityForResult(intent, requestCode);


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            selectedItem = data.getStringExtra(EmployConstantUtil.SELECTED_ITEM);
        }
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_LEAD_OWNER:
                    textViewLeadOwner.setText(selectedItem);
                    break;
                case REQUEST_CODE_LEAD_INDUSTRY:
                    textViewLeadIndustry.setText(selectedItem);
                    break;
                case REQUEST_CODE_LEAD_SOURCE:
                    textViewLeadSource.setText(selectedItem);
                    break;
                case REQUEST_CODE_LEAD_STATUS:
                    textViewLeadStatus.setText(selectedItem);
                    break;
                case REQUEST_CODE_RATING:
                    textViewLeadRating.setText(selectedItem);
                    break;
                case REQUEST_CODE_SALUTATION:
                    textViewLeadSolution.setText(selectedItem);
                    break;
                default:
                    imageutils.onActivityResult(requestCode, resultCode, data);

            }
        }

    }


    private void addLeadDataToServer() {
        DialogUtitlity.showLoading(AddLeadActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> listCall = apiInterface.addLead(leadDataModel, "admin");
        Log.i(TAG, "inside addLeadData to server");
        listCall.enqueue(this);


    }


    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        DialogUtitlity.hideLoading();
        Log.i(TAG, "inside addLeadData to server");
        int statusCode = response.code();
        switch (statusCode) {
            case 200:
                leadDataModel.setSync(isSync);
                break;
            case 201:
                leadDataModel.setSync(true);
                /*if (imageResponses.get(0).getImageFile() != null) {*/
                addImageToServer();
                //}
                break;
            case 500:
                leadDataModel.setSync(isSync);
                break;
            case 400:
                // addImageToServer();
                break;
            case 404:
                // addImageToServer();
                break;

        }
    }

    private void addImageToServer() {
        DialogUtitlity.showLoading(AddLeadActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> listCall = apiInterface.addImage(body, leadId, "LEAD");
        Log.i(TAG, "inside image to server");
        listCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                DialogUtitlity.hideLoading();
                Log.i(TAG, "inside addImage to server");
                int statusCode = response.code();
                Log.i(TAG, String.valueOf(statusCode));
                if (statusCode == 201) {
                    if (response.body() instanceof String) {
                        //dataBaseAdapter.addImage(imageResponses);

                       /* imageResponse.setImageId(leadId);
                        imageResponse.setImageFile();
                        imageResponses.add(imageResponse);*/

                    }
                }

                addUpdateLead();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                DialogUtitlity.hideLoading();
                /* addUpdateLead();*/

            }
        });


    }

    private void addUpdateLead() {
        if (!editLead) {
            dataBaseAdapter.addLeadData(leadDataModel);
            dataBaseAdapter.addImage(imageResponses);
            // alertDialogManager.showAlertDialogMessage1(AddLeadActivity.this, "", "Lead is Created ", false, AddLeadActivity.this);
        } else {

            leadDataModel.setSync(true);
            dataBaseAdapter.updateLeadData(leadDataModel, leadId);
            dataBaseAdapter.updateImage(imageResponse);
            //  alertDialogManager.showAlertDialogMessage1(AddLeadActivity.this, "", "Lead is Updated ", false, AddLeadActivity.this);

        }


        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();


    }


    @Override
    public void onFailure(Call<String> call, Throwable t) {
        DialogUtitlity.hideLoading();
        Log.e(TAG, "fail");
        addUpdateLead();


    }


    @Override
    public void alertDialogCallbackOk() {
        addLeadData();


    }

    @Override
    public void alertDialogCallbackCancel() {


    }

    @Override
    public void alertDialogCallbackOk1() {

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();

    }

    @Override
    protected void onStop() {
        super.onStop();
        DialogUtitlity.hideLoading();
    }


    private void setAmount() {
        editTextLeadRevenue.addTextChangedListener(new MoneyTextWatcher(editTextLeadRevenue));
        editTextLeadEmployees.addTextChangedListener(new MoneyTextWatcher(editTextLeadEmployees));

    }


}
