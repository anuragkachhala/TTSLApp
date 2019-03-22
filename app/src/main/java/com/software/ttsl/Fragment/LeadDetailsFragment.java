package com.software.ttsl.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software.ttsl.LeadDetailActivity;
import com.software.ttsl.R;
import com.software.ttsl.Request.LeadDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.model.AddLeadData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeadDetailsFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.tv_smart_view)
    TextView textViewSmartView;

    @BindView(R.id.tv_label_lead_company)
    TextView textViewLabelLeadCompany;

    @BindView(R.id.tv_label_lead_name)
    TextView textViewLabelLeadName;


    @BindView(R.id.layout_discription_info)
    TextView textViewLeadDiscriptionInfo;

    @BindView(R.id.layout_address_info)
    TextView textViewLeadAddressInfo;


    @BindView(R.id.tv_lead_owner)
    TextView textViewLeadOwner;

    @BindView(R.id.tv_lead_company)
    TextView textViewLeadCompany;

    @BindView(R.id.tv_lead_first_name)
    TextView textViewFirstName;

    @BindView(R.id.tv_lead_last_name)
    TextView textViewLastName;

    @BindView(R.id.layout_lead_status)
    LinearLayout linearLayoutLeadStatus;

    @BindView(R.id.tv_lead_status)
    TextView  textViewLeadStatus;

    @BindView(R.id.tv_lead_title)
    TextView textViewTitle;

    @BindView(R.id.tv_lead_email_id)
    TextView textViewEmailId;

    @BindView(R.id.tv_lead_phone)
    TextView textViewLeadPhone;

    @BindView(R.id.tv_lead_fax)
    TextView textViewLeadFax;

    @BindView(R.id.tv_lead_mobile)
    TextView textViewLeadMobile;

    @BindView(R.id.tv_lead_website)
    TextView textViewLeadWebsite;

    @BindView(R.id.tv_lead_source)
    TextView textViewLeadSource;

    @BindView(R.id.tv_lead_industry)
    TextView textViewIndustry;

    @BindView(R.id.tv_lead_employee)
    TextView textViewEmployees;

    @BindView(R.id.tv_lead_Modified_By)
    TextView textViewModifiedBy;

    @BindView(R.id.tv_lead_revenue)
    TextView textViewLeadRevenue;

    @BindView(R.id.tv_lead_rating)
    TextView textViewRating;

    @BindView(R.id.tv_lead_created_by)
    TextView textViewLeadCreatedBy;

    @BindView(R.id.tv_lead_email_opt)
    TextView textViewEmailOption;

    @BindView(R.id.tv_lead_created_time)
    TextView textViewLeadCreatedTime;

    @BindView(R.id.tv_lead_modified_time)
    TextView textViewLeadModifiedTime;

    @BindView(R.id.tv_lead_secondry_mail)
    TextView textViewSecondaryMail;

    @BindView(R.id.tv_lead_last_activity_time)
    TextView textViewLastActivityTime;

    @BindView(R.id.tv_lead_address_street)
    TextView textViewLeadStreet;

    @BindView(R.id.tv_lead_address_city)
    TextView textViewLeadCity;

    @BindView(R.id.tv_lead_state)
    TextView textViewLeadState;

    @BindView(R.id.tv_lead_address_zip_code)
    TextView textViewLeadZipCode;

    @BindView(R.id.tv_lead_address_country)
    TextView textViewLeadCountry;

    @BindView(R.id.tv_lead_description)
    TextView textViewDescription;


    @BindView(R.id.linear_layout_lead_owner)
    LinearLayout linearLayoutLeadOwner;

    @BindView(R.id.layout_lead_first_name)
    LinearLayout linearLayoutFistName;

    @BindView(R.id.layout_lead_last_name)
    LinearLayout linearLayoutLastName;

    @BindView(R.id.layout_lead_industry)
    LinearLayout linearLayoutIndustry;

    @BindView(R.id.layout_lead_company)
    LinearLayout linearLayoutLeadCompany;

    @BindView(R.id.layout_lead_email_id)
    LinearLayout linearLayoutEmailId;

    @BindView(R.id.layout_lead_phone)
    LinearLayout linearLayoutLeadPhone;

    @BindView(R.id.layout_lead_fax)
    LinearLayout linearLayoutLeadFax;

    @BindView(R.id.layout_lead_mobile)
    LinearLayout linearLayoutLeadMobile;

    @BindView(R.id.layout_lead_title)
    LinearLayout linearLayoutLeadTitle;

    @BindView(R.id.layout_lead_rating)
    LinearLayout linearLayoutLeadRating;

    @BindView(R.id.layout_lead_revenue)
    LinearLayout linearLayoutLeadRevenue;

    @BindView(R.id.layout_lead_employee)
    LinearLayout linearLayoutEmaployees;

    @BindView(R.id.layout_lead_secondry_mail)
    LinearLayout  linearLayoutLeadSecondryMail;

    @BindView(R.id.layout_lead_description)
    LinearLayout linearLayoutLeadDescription;

    @BindView(R.id.layout_lead_website)
    LinearLayout linearLayoutWebSite;

    @BindView(R.id.layout_lead_street)
    LinearLayout linearLayoutLeadStreet;

    @BindView(R.id.layout_lead_city)
    LinearLayout linearLayoutLeadCity;

    @BindView(R.id.layout_lead_state)
    LinearLayout linearLayoutLeadState;

    @BindView(R.id.layout_lead_zip_code)
    LinearLayout linearLayoutLeadZipCode;

    @BindView(R.id.layout_lead_country)
    LinearLayout linearLayoutLeadCountry;

    @BindView(R.id.layout_lead_source)
    LinearLayout linearLayoutLeadSource;


    @BindView(R.id.tv_lead_solution)
    TextView textViewLeadSolution;


    @BindView(R.id.layout_lead_solution)
    LinearLayout linearLayoutLeadSolution;

    @BindView(R.id.layout_lead_skype_id)
    LinearLayout linearLayoutSkypeId;

    @BindView(R.id.tv_lead_skype_id)
    TextView textViewSkypeId;


    @BindView(R.id.layout_lead_twitter)
    LinearLayout linearLayoutLeadTwitter;

    @BindView(R.id.tv_lead_twitter)
    TextView textViewLeadTwitter;



    @BindView(R.id.layout_lead_email_opt)
    LinearLayout linearLayoutLeadEmailOpt;







    private AddLeadData addLeadData;
    private LeadDataModel leadDataModel;

    long leadId;
    private LeadDetailActivity leadDetailActivity;

    public LeadDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view =inflater.inflate(R.layout.fragment_lead_details, container, false);
        ButterKnife.bind(this,view);
        // Inflate the layout for this fragment

        Bundle bundle =this.getArguments();
        if(bundle!=null){
            leadId =bundle.getLong(EmployConstantUtil.KEY_LEAD_ID,0);
        }


        textViewSmartView.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smartView();
            }
        });



        setClickListener();

        setStringResouce();

        getLeadById();
        return view;



    }

    private void setStringResouce(){
        textViewLabelLeadName.setText(Html.fromHtml(EmployConstantUtil.ASTERISK + getResources().getString(R.string.label_lead_last_name)));
        textViewLabelLeadCompany.setText(Html.fromHtml(EmployConstantUtil.ASTERISK + getResources().getString(R.string.label_lead_company)));
    }

    private void setClickListener() {
        textViewSecondaryMail.setOnClickListener(this);
        textViewLeadPhone.setOnClickListener(this);
        textViewLeadWebsite.setOnClickListener(this);
        textViewLeadMobile.setOnClickListener(this);
        textViewEmailId.setOnClickListener(this);
    }

    private void smartView() {
        if (textViewSmartView.getText().toString().equals(getResources().getString(R.string.footer_show_all_fields))){
            textViewSmartView.setText(getResources().getString(R.string.footer_smart_view));
            linearLayoutLeadTitle.setVisibility(View.VISIBLE);
            linearLayoutEmailId.setVisibility(View.VISIBLE);
            linearLayoutLeadPhone.setVisibility(View.VISIBLE);
            linearLayoutLeadFax.setVisibility(View.VISIBLE);
            linearLayoutWebSite.setVisibility(View.VISIBLE);
            linearLayoutLeadSource.setVisibility(View.VISIBLE);
            linearLayoutLeadStatus.setVisibility(View.VISIBLE);
            linearLayoutLeadMobile.setVisibility(View.VISIBLE);
            linearLayoutLeadSolution.setVisibility(View.VISIBLE);
            linearLayoutIndustry.setVisibility(View.VISIBLE);
            linearLayoutEmaployees.setVisibility(View.VISIBLE);
            linearLayoutLeadRevenue.setVisibility(View.VISIBLE);
            linearLayoutLeadRating.setVisibility(View.VISIBLE);
            linearLayoutLeadEmailOpt.setVisibility(View.VISIBLE);
            linearLayoutSkypeId.setVisibility(View.VISIBLE);
            linearLayoutLeadTwitter.setVisibility(View.VISIBLE);
            linearLayoutLeadStreet.setVisibility(View.VISIBLE);
            linearLayoutLeadCity.setVisibility(View.VISIBLE);
            linearLayoutLeadState.setVisibility(View.VISIBLE);
            linearLayoutLeadCountry.setVisibility(View.VISIBLE);
            linearLayoutLeadZipCode.setVisibility(View.VISIBLE);
            textViewLeadDiscriptionInfo.setVisibility(View.VISIBLE);
            linearLayoutLeadDescription.setVisibility(View.VISIBLE);
            textViewLeadAddressInfo.setVisibility(View.VISIBLE);
            linearLayoutLeadSecondryMail.setVisibility(View.VISIBLE);



        }else {
            textViewSmartView.setText(getResources().getString(R.string.footer_show_all_fields));
            setData();

        }

    }

    private void getLeadById() {
       /* DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(getContext());
        dataBaseAdapter.openDataBase();
        addLeadData= dataBaseAdapter.getLeadByID(leadId);*/

        leadDetailActivity = (LeadDetailActivity)getActivity();
        leadDataModel = leadDetailActivity.getLeadDate();
        setData();








    }


    @Override
    public void onResume() {
        super.onResume();
        getLeadById();
    }

    private void setData() {
        textViewLeadOwner.setText(leadDataModel.getLeadOwner());
        textViewLeadCompany.setText(leadDataModel.getCompany());
        textViewFirstName.setText((leadDataModel.getSalutation()+" "+ DateAndTimeUtil.firstLatterUpper(leadDataModel.getFirstName())+" "+DateAndTimeUtil.firstLatterUpper(leadDataModel.getLastName())).trim());
        textViewLeadModifiedTime.setText(DateAndTimeUtil.longToDate(leadDataModel.getModifyDate()));
        textViewLeadCreatedTime.setText(DateAndTimeUtil.longToDate(leadDataModel.getCreatedDate()));
        textViewLeadCreatedBy.setText(leadDataModel.getCreatedBy());
        textViewModifiedBy.setText(leadDataModel.getModifyBy());
        if(leadDataModel.getTitle().isEmpty()){
            linearLayoutLeadTitle.setVisibility(View.GONE);

        }else {
            textViewTitle.setText(leadDataModel.getTitle());
            linearLayoutLeadTitle.setVisibility(View.VISIBLE);
        }
        if(leadDataModel.getEmail().isEmpty()){
            linearLayoutEmailId.setVisibility(View.GONE);
        }else {
            textViewEmailId.setText(leadDataModel.getEmail());
            linearLayoutEmailId.setVisibility(View.VISIBLE);
        }if(leadDataModel.getPhone().isEmpty()){
            linearLayoutLeadPhone.setVisibility(View.GONE);

        }else {
            textViewLeadPhone.setText(leadDataModel.getPhone());
            linearLayoutLeadPhone.setVisibility(View.VISIBLE);
        }
        if(leadDataModel.getFax().isEmpty()){
            linearLayoutLeadFax.setVisibility(View.GONE);
        }else {
            linearLayoutLeadFax.setVisibility(View.VISIBLE);
            textViewLeadFax.setText(leadDataModel.getFax());
        }if(leadDataModel.getMobile().isEmpty()){
            linearLayoutLeadMobile.setVisibility(View.GONE);
        }else {
            linearLayoutLeadMobile.setVisibility(View.VISIBLE);
            textViewLeadMobile.setText(leadDataModel.getMobile());
        }
        if(leadDataModel.getWebsite().isEmpty()){
            linearLayoutWebSite.setVisibility(View.GONE);

        }else {
            linearLayoutWebSite.setVisibility(View.VISIBLE);
            textViewLeadWebsite.setText(leadDataModel.getWebsite());
        }if(leadDataModel.getLeadStatus().isEmpty()){
            linearLayoutLeadStatus.setVisibility(View.GONE);
        }else {
            linearLayoutLeadStatus.setVisibility(View.VISIBLE);
            textViewLeadStatus.setText(leadDataModel.getLeadStatus());
        }/*if(leadDataModel.getSalutation().isEmpty()){
            linearLayoutLeadSolution.setVisibility(View.GONE);
        }else {
            linearLayoutLeadSolution.setVisibility(View.VISIBLE);
            textViewLeadSolution.setText(leadDataModel.getSalutation());
        }*//*if(!leadDataModel.getEmailOptOut()){
            linearLayoutLeadEmailOpt.setVisibility(View.GONE);
        }else {
            linearLayoutLeadEmailOpt.setVisibility(View.VISIBLE);
            textViewEmailOption.setText("YES");

        }*/if(leadDataModel.getTwitter().isEmpty()){
            linearLayoutLeadTwitter.setVisibility(View.GONE);
        }else {
            linearLayoutLeadTwitter.setVisibility(View.VISIBLE);
            textViewLeadTwitter.setText(leadDataModel.getTitle());
        }if(leadDataModel.getSkypeId().isEmpty()){
            linearLayoutSkypeId.setVisibility(View.GONE);
        }else {
            linearLayoutSkypeId.setVisibility(View.VISIBLE);
            textViewSkypeId.setText(leadDataModel.getSkypeId());
        }if(leadDataModel.getSecondaryEmailId().isEmpty()){
            linearLayoutLeadSecondryMail.setVisibility(View.GONE);
        }else {
            linearLayoutLeadSecondryMail.setVisibility(View.VISIBLE);
            textViewSecondaryMail.setText(leadDataModel.getSecondaryEmailId());
        }
        if(leadDataModel.getAddressStreet().isEmpty()){
            linearLayoutLeadStreet.setVisibility(View.GONE);
        }else {
            linearLayoutLeadStreet.setVisibility(View.VISIBLE);
            textViewLeadStreet.setText(leadDataModel.getAddressStreet());
        }if(leadDataModel.getAddressCity().isEmpty()){
            linearLayoutLeadCity.setVisibility(View.GONE);

        }else {
            linearLayoutLeadCity.setVisibility(View.VISIBLE);
            textViewLeadCity.setText(leadDataModel.getAddressCity());
        }if(leadDataModel.getAddressZipCode().isEmpty()){
            linearLayoutLeadZipCode.setVisibility(View.GONE);
        }else {
            linearLayoutLeadZipCode.setVisibility(View.VISIBLE);
            textViewLeadZipCode.setText(leadDataModel.getAddressZipCode());
        }if(leadDataModel.getAddressState().isEmpty()){
            linearLayoutLeadState.setVisibility(View.GONE);
        }else {
            linearLayoutLeadState.setVisibility(View.VISIBLE);
            textViewLeadState.setText(leadDataModel.getAddressState());
        }if(leadDataModel.getAddressCounty().isEmpty()){
            linearLayoutLeadCountry.setVisibility(View.GONE);
        }else {
            linearLayoutLeadCountry.setVisibility(View.VISIBLE);
            textViewLeadCountry.setText(leadDataModel.getAddressCounty());
        }if(leadDataModel.getDescription().isEmpty()){
           linearLayoutLeadDescription.setVisibility(View.GONE);
           textViewLeadDiscriptionInfo.setVisibility(View.GONE);
        }else {
            linearLayoutLeadDescription.setVisibility(View.VISIBLE);
            textViewLeadDiscriptionInfo.setVisibility(View.VISIBLE);
            textViewDescription.setText(leadDataModel.getDescription());
        }if(leadDataModel.getAnnualRevenue().isEmpty() || leadDataModel.getAnnualRevenue().equals("0")){

            linearLayoutLeadRevenue.setVisibility(View.GONE);

        }else {
            linearLayoutLeadRevenue.setVisibility(View.VISIBLE);
            textViewLeadRevenue.setText(leadDataModel.getAnnualRevenue());
        }if(leadDataModel.getNoOfEmployees().isEmpty() || leadDataModel.getNoOfEmployees().equals("0")){
            linearLayoutEmaployees.setVisibility(View.GONE);

        }else {
            linearLayoutEmaployees.setVisibility(View.VISIBLE);
            textViewEmployees.setText(leadDataModel.getNoOfEmployees());
        } if(leadDataModel.getMobile().isEmpty()){
         linearLayoutLeadMobile.setVisibility(View.GONE);

        }else {
            linearLayoutLeadMobile.setVisibility(View.VISIBLE);
            textViewLeadMobile.setText(leadDataModel.getMobile());
        }if(leadDataModel.getLeadSource().isEmpty()){
            linearLayoutLeadSource.setVisibility(View.GONE);
        }else {
            linearLayoutLeadSource.setVisibility(View.VISIBLE);
            textViewLeadSource.setText(leadDataModel.getLeadSource());
        }if(leadDataModel.getIndustry()!=null && leadDataModel.getIndustry().isEmpty()){
            linearLayoutIndustry.setVisibility(View.GONE);
        }else {
            linearLayoutIndustry.setVisibility(View.VISIBLE);
            textViewIndustry.setText(leadDataModel.getIndustry());
        }/*if(leadDataModel.getRating().isEmpty()){
            linearLayoutLeadRating.setVisibility(View.GONE);
        }else {
            linearLayoutLeadRating.setVisibility(View.GONE);
            textViewRating.setText(leadDataModel.getRating());
        }*/if(leadDataModel.getAddressCity().isEmpty()&& leadDataModel.getAddressState().isEmpty()&& leadDataModel.getAddressCounty().isEmpty()&&leadDataModel.getAddressZipCode().isEmpty()&& leadDataModel.getAddressStreet().isEmpty()){
            textViewLeadAddressInfo.setVisibility(View.GONE);
        }else {
            textViewLeadAddressInfo.setVisibility(View.VISIBLE);
        }




    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.et_lead_email:
                break;
            case R.id.et_lead_phone:
                break;
            case R.id.et_lead_secondry_mail:
                break;
            case R.id.et_lead_website:
                break;
            case R.id.et_lead_mobile:
                break;
        }

    }
}
