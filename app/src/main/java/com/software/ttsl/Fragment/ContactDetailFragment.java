package com.software.ttsl.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software.ttsl.AccountDetailsActivity;
import com.software.ttsl.ContactDetailActivity;
import com.software.ttsl.R;
import com.software.ttsl.Request.AccountDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.model.AddContactData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactDetailFragment extends Fragment {


    public static final String TAG = ContactDetailFragment.class.getName();
    public static final String KEY_CONTACT_ID = "contact_id";


    @BindView(R.id.linear_layout_contact_owner)
    LinearLayout linearLayoutContactOwner;

    @BindView(R.id.linear_layout_contact_lead_source)
    LinearLayout linearLayoutContactLeadSource;

    @BindView(R.id.linear_layout_contact_name)
    LinearLayout linearLayoutContactName;

    @BindView(R.id.linear_layout_contact_account_name)
    LinearLayout linearLayoutContactAccountName;

    @BindView(R.id.linear_layout_contact_email)
    LinearLayout linearLayoutContactEmail;

    @BindView(R.id.linear_layout_contact_title)
    LinearLayout linearLayoutContactTitle;

    @BindView(R.id.linear_layout_contact_department)
    LinearLayout linearLayoutContactDepartment;

    @BindView(R.id.linear_layout_contact_phone)
    LinearLayout linearLayoutContactPhone;

    @BindView(R.id.linear_layout_contact_home_phone)
    LinearLayout linearLayoutContactHomePhone;

    @BindView(R.id.linear_layout_contact_other_phone)
    LinearLayout linearLayoutContactOtherPhone;

    @BindView(R.id.linear_layout_contact_fax)
    LinearLayout linearLayoutContactFax;

    @BindView(R.id.linear_layout_contact_mobile)
    LinearLayout linearLayoutContactMobile;

    @BindView(R.id.linear_layout_date_of_birth)
    LinearLayout linearLayoutDateOfBirth;

    @BindView(R.id.linear_layout_assistant)
    LinearLayout linearLayoutAssistant;

    @BindView(R.id.linear_layout_contact_assistant_phone)
    LinearLayout linearLayoutContactAssistantPhone;

    @BindView(R.id.linear_layout_contact_report_to)
    LinearLayout linearLayoutContactReportTo;

    @BindView(R.id.linear_layout_contact_email_opt)
    LinearLayout linearLayoutContactEmailOpt;


    @BindView(R.id.linear_layout_contact_skypeid)
    LinearLayout linearLayoutContactSkypeId;

    @BindView(R.id.linear_layout_contact_secondary_mail)
    LinearLayout linearLayoutContactSecondaryMail;

    @BindView(R.id.linear_layout_contact_twitter)
    LinearLayout linearLayoutContactTwitter;

    @BindView(R.id.layout_address_info)
    TextView textViewAddressInfo;

    @BindView(R.id.layout_contact_mail_address_street)
    LinearLayout linearLayoutMailStreet;

    @BindView(R.id.layout_contact_mail_address_city)
    LinearLayout linearLayoutMailCity;

    @BindView(R.id.layout_contact_mail_address_state)
    LinearLayout linearLayoutMailState;

    @BindView(R.id.layout_contact_mail_address_code)
    LinearLayout linearLayoutMailCode;

    @BindView(R.id.layout_contact_mail_address_country)
    LinearLayout linearLayoutMailCountry;

    @BindView(R.id.layout_contact_other_address_street)
    LinearLayout linearLayoutOtherStreet;

    @BindView(R.id.layout_contact_other_address_city)
    LinearLayout linearLayoutOtherAddressCity;

    @BindView(R.id.layout_contact_other_address_state)
    LinearLayout linearLayoutOtherAddressState;

    @BindView(R.id.layout_contact_other_address_code)
    LinearLayout linearLayoutOtherAddressCode;

    @BindView(R.id.layout_contact_other_address_country)
    LinearLayout linearLayoutOtherAddressCountry;

    @BindView(R.id.layout_tv_account_description)
    LinearLayout textViewAccountDesription;

    @BindView(R.id.layout_account_description)
    LinearLayout linearLayoutAccountDescription;


    @BindView(R.id.tv_contact_owner)
    TextView textViewContactOwner;

    @BindView(R.id.tv_contact_lead_source)
    TextView textViewLeadSource;

    @BindView(R.id.tv_contact_name)
    TextView textViewContactName;

    @BindView(R.id.tv_contact_account_name)
    TextView textViewContactAccountName;

    @BindView(R.id.tv_contact_email)
    TextView textViewContactEmail;

    @BindView(R.id.tv_contact_title)
    TextView textViewContactTitle;

    @BindView(R.id.tv_contact_department)
    TextView textViewContactDepartment;

    @BindView(R.id.tv_contact_phone)
    TextView textViewContactPhone;

    @BindView(R.id.tv_contact_home_phone)
    TextView textViewContactHomePhone;


    @BindView(R.id.tv_contact_other_phone)
    TextView textViewContactOtherPhone;

    @BindView(R.id.tv_contact_fax)
    TextView textViewContactFax;

    @BindView(R.id.tv_contact_mobile)
    TextView textViewContactMobile;

    @BindView(R.id.tv_contact_date_of_birth)
    TextView textViewContactDateOfBirth;

    @BindView(R.id.tv_contact_assistant)
    TextView textViewContactAssistant;

    @BindView(R.id.tv_contact_assistant_phone)
    TextView textViewContactAssistantPhone;

    @BindView(R.id.tv_contact_report_to)
    TextView textViewContactReportTo;

    @BindView(R.id.tv_contact_email_opt)
    TextView textViewContactEmailOpt;

    @BindView(R.id.tv_contact_created_by)
    TextView textViewContactCreatedBy;



    @BindView(R.id.tv_contact_modify_time)
    TextView textViewModifyTime;

    @BindView(R.id.tv_contact_skypeid)
    TextView textViewContactSkypeID;

    @BindView(R.id.tv_contact_modify_by)
    TextView textViewContactModifyBy;

    @BindView(R.id.tv_contact_created_time)
    TextView textViewContactCreatedTime;

    @BindView(R.id.tv_contact_secondary_mail)
    TextView textViewContactSecondaryMail;

    @BindView(R.id.tv_contact_twitter)
    TextView textViewContactTwitter;

    @BindView(R.id.tv_contact_mail_address_street)
    TextView textViewMailStreet;

    @BindView(R.id.tv_contact_mail_city)
    TextView textViewMailCity;

    @BindView(R.id.tv_contact_mail_address_state)
    TextView textViewMailState;

    @BindView(R.id.tv_contact_mail_address_code)
    TextView textViewMailCode;

    @BindView(R.id.tv_contact_mail_address_country)
    TextView textViewMailCountry;

    @BindView(R.id.tv_contact_other_street)
    TextView textViewOtherStreet;

    @BindView(R.id.tv_contact_other_city)
    TextView textViewOtherCity;

    @BindView(R.id.tv_contact_other_state)
    TextView textViewOtherState;

    @BindView(R.id.tv_contact_other_code)
    TextView textViewOtherCode;

    @BindView(R.id.tv_contact_other_country)
    TextView textViewOtherCountry;

    @BindView(R.id.tv_account_description)
    TextView textViewDescription;

    @BindView(R.id.tv_smart_view)
    TextView textViewSmartView;

    @BindView(R.id.tv_label_contact_name)
    TextView textViewLabelContactName;

    private DataBaseAdapter dataBaseAdapter;
    private Long contactID;
    private AddContactData addContactData;
    private ContactDetailActivity contactDetailActivity;


    public ContactDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_detail, container, false);

        ButterKnife.bind(this, view);


        textViewLabelContactName.setText( Html.fromHtml(EmployConstantUtil.ASTERISK +getResources().getString(R.string.label_lead_last_name)));

        dataBaseAdapter = new DataBaseAdapter(getContext());
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            contactID = bundle.getLong(KEY_CONTACT_ID, 0);
        }




        textViewSmartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smartView();
            }
        });


        getContactData();


        return view;
    }




    public void smartView() {
        if (textViewSmartView.getText().toString().equals(getResources().getString(R.string.footer_show_all_fields))) {
            textViewSmartView.setText(getResources().getString(R.string.footer_smart_view));
            linearLayoutContactLeadSource.setVisibility(View.VISIBLE);
            linearLayoutContactName.setVisibility(View.VISIBLE);
            linearLayoutContactAccountName.setVisibility(View.VISIBLE);
            linearLayoutContactEmail.setVisibility(View.VISIBLE);
            linearLayoutContactTitle.setVisibility(View.VISIBLE);
            linearLayoutContactDepartment.setVisibility(View.VISIBLE);
            linearLayoutContactPhone.setVisibility(View.VISIBLE);
            linearLayoutContactHomePhone.setVisibility(View.VISIBLE);
            linearLayoutContactOtherPhone.setVisibility(View.VISIBLE);
            linearLayoutContactFax.setVisibility(View.VISIBLE);
            linearLayoutContactMobile.setVisibility(View.VISIBLE);
            linearLayoutDateOfBirth.setVisibility(View.VISIBLE);
            linearLayoutAssistant.setVisibility(View.VISIBLE);
            linearLayoutContactAssistantPhone.setVisibility(View.VISIBLE);
            linearLayoutContactReportTo.setVisibility(View.VISIBLE);
            linearLayoutContactSkypeId.setVisibility(View.VISIBLE);
            linearLayoutContactSecondaryMail.setVisibility(View.VISIBLE);
            linearLayoutContactTwitter.setVisibility(View.VISIBLE);
            linearLayoutContactEmailOpt.setVisibility(View.VISIBLE);
            textViewAddressInfo.setVisibility(View.VISIBLE);
            linearLayoutMailStreet.setVisibility(View.VISIBLE);
            linearLayoutMailCity.setVisibility(View.VISIBLE);
            linearLayoutMailState.setVisibility(View.VISIBLE);
            linearLayoutMailCode.setVisibility(View.VISIBLE);
            linearLayoutMailCountry.setVisibility(View.VISIBLE);
            linearLayoutOtherStreet.setVisibility(View.VISIBLE);
            linearLayoutOtherAddressCity.setVisibility(View.VISIBLE);
            linearLayoutOtherAddressState.setVisibility(View.VISIBLE);
            linearLayoutOtherAddressCode.setVisibility(View.VISIBLE);
            linearLayoutOtherAddressCountry.setVisibility(View.VISIBLE);
            textViewAccountDesription.setVisibility(View.VISIBLE);
            linearLayoutAccountDescription.setVisibility(View.VISIBLE);
        } else {
            textViewSmartView.setText(getResources().getString(R.string.footer_show_all_fields));
            setVisibilityGone();
            setContactInfo();;
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        getContactData();

    }

    private void setVisibilityGone() {
        linearLayoutContactLeadSource.setVisibility(View.GONE);
        linearLayoutContactName.setVisibility(View.GONE);
        linearLayoutContactAccountName.setVisibility(View.GONE);
        linearLayoutContactEmail.setVisibility(View.GONE);
        linearLayoutContactTitle.setVisibility(View.GONE);
        linearLayoutContactDepartment.setVisibility(View.GONE);
        linearLayoutContactPhone.setVisibility(View.GONE);
        linearLayoutContactHomePhone.setVisibility(View.GONE);
        linearLayoutContactOtherPhone.setVisibility(View.GONE);
        linearLayoutContactFax.setVisibility(View.GONE);
        linearLayoutContactMobile.setVisibility(View.GONE);
        linearLayoutDateOfBirth.setVisibility(View.GONE);
        linearLayoutAssistant.setVisibility(View.GONE);
        linearLayoutContactAssistantPhone.setVisibility(View.GONE);
        linearLayoutContactReportTo.setVisibility(View.GONE);
        linearLayoutContactEmailOpt.setVisibility(View.GONE);
        linearLayoutContactSkypeId.setVisibility(View.GONE);
        linearLayoutContactSecondaryMail.setVisibility(View.GONE);
        linearLayoutContactTwitter.setVisibility(View.GONE);
        textViewAddressInfo.setVisibility(View.GONE);
        linearLayoutMailStreet.setVisibility(View.GONE);
        linearLayoutMailCity.setVisibility(View.GONE);
        linearLayoutMailState.setVisibility(View.GONE);
        linearLayoutMailCode.setVisibility(View.GONE);
        linearLayoutMailCountry.setVisibility(View.GONE);
        linearLayoutOtherStreet.setVisibility(View.GONE);
        linearLayoutOtherAddressCity.setVisibility(View.GONE);
        linearLayoutOtherAddressState.setVisibility(View.GONE);
        linearLayoutOtherAddressCode.setVisibility(View.GONE);
        linearLayoutOtherAddressCountry.setVisibility(View.GONE);
        textViewAccountDesription.setVisibility(View.GONE);
        linearLayoutAccountDescription.setVisibility(View.GONE);
    }


    public void getContactData() {
        //addContactData = dataBaseAdapter.getContactById(contactID);
        contactDetailActivity = (ContactDetailActivity) getActivity();
        addContactData = contactDetailActivity.getContactData();

        setContactInfo();

    }

    public void setContactInfo() {
        textViewContactOwner.setText(addContactData.getContactOwner());
        textViewContactName.setText((addContactData.getSalutation()+" "+DateAndTimeUtil.firstLatterUpper(addContactData.getFirstName())+" "+DateAndTimeUtil.firstLatterUpper(addContactData.getLastName())).trim());
        textViewContactCreatedBy.setText(addContactData.getCreatedBy());
        textViewContactCreatedTime.setText(DateAndTimeUtil.longToDate(addContactData.getCreatedDate()));
        textViewContactModifyBy.setText(addContactData.getModifyBy());
        textViewModifyTime.setText(DateAndTimeUtil.longToDate(addContactData.getModifyDate()));
        if (!addContactData.getLeadSource().isEmpty()) {
            linearLayoutContactLeadSource.setVisibility(View.VISIBLE);
            textViewLeadSource.setText(addContactData.getLeadSource());
        }
        if (!addContactData.getAccountName().isEmpty()) {
            linearLayoutContactAccountName.setVisibility(View.VISIBLE);
            textViewContactAccountName.setText(addContactData.getAccountName());

        }
        if (!addContactData.getEmail().isEmpty()) {
            linearLayoutContactEmail.setVisibility(View.VISIBLE);
            textViewContactEmail.setText(addContactData.getEmail());
        }
        if (!addContactData.getTitle().isEmpty()) {
            linearLayoutContactTitle.setVisibility(View.VISIBLE);
            textViewContactTitle.setText(addContactData.getTitle());
        }
        if (!addContactData.getDepartment().isEmpty()) {
            linearLayoutContactDepartment.setVisibility(View.VISIBLE);
            textViewContactDepartment.setText(addContactData.getDepartment());
        }
        if (!addContactData.getPhone().isEmpty()) {
            linearLayoutContactPhone.setVisibility(View.VISIBLE);
            textViewContactPhone.setText(addContactData.getPhone());
        }
        if (!addContactData.getHomePhone().isEmpty()) {
            linearLayoutContactHomePhone.setVisibility(View.VISIBLE);
            textViewContactHomePhone.setText(addContactData.getHomePhone());
        }
        if (!addContactData.getOtherPhone().isEmpty()) {
            linearLayoutContactHomePhone.setVisibility(View.VISIBLE);
            textViewContactHomePhone.setText(addContactData.getOtherPhone());
        }
        if (!addContactData.getFax().isEmpty()) {
            linearLayoutContactFax.setVisibility(View.VISIBLE);
            textViewContactFax.setText(addContactData.getFax());
        }
        if (!addContactData.getMobile().isEmpty()) {
            linearLayoutContactMobile.setVisibility(View.VISIBLE);
            textViewContactMobile.setText(addContactData.getMobile());
        }
        if (!addContactData.getDateOfBirth().isEmpty()) {
            linearLayoutDateOfBirth.setVisibility(View.VISIBLE);
            textViewContactDateOfBirth.setText(addContactData.getDateOfBirth());
        }
        if (!addContactData.getAssistant().isEmpty()) {
            linearLayoutAssistant.setVisibility(View.VISIBLE);
            textViewContactAssistant.setText(addContactData.getAssistant());
        }
        if (!addContactData.getAsstPhone().isEmpty()) {
            linearLayoutContactAssistantPhone.setVisibility(View.VISIBLE);
            textViewContactAssistantPhone.setText(addContactData.getAsstPhone());
        }
        if (!addContactData.getReportsTo().isEmpty()) {
            linearLayoutContactReportTo.setVisibility(View.VISIBLE);
            textViewContactReportTo.setText(addContactData.getReportsTo());
        }
        if(addContactData.getEmailOptOut()){
            linearLayoutContactEmailOpt.setVisibility(View.VISIBLE);
            textViewContactEmailOpt.setText("YES");
        }
        if (!addContactData.getSecondaryEmail().isEmpty()) {
            linearLayoutContactSecondaryMail.setVisibility(View.VISIBLE);
            textViewContactSecondaryMail.setText(addContactData.getSecondaryEmail());
        }
        if (!addContactData.getTwitter().isEmpty()) {
            linearLayoutContactTwitter.setVisibility(View.VISIBLE);
            textViewContactTwitter.setText(addContactData.getTwitter());
        }
        if (!addContactData.getSkypeId().isEmpty()) {
            linearLayoutContactSkypeId.setVisibility(View.VISIBLE);
            textViewContactSkypeID.setText(addContactData.getSkypeId());
        }
        if (!addContactData.getDescription().isEmpty()) {
            linearLayoutAccountDescription.setVisibility(View.VISIBLE);
            textViewAccountDesription.setVisibility(View.VISIBLE);
            textViewDescription.setText(addContactData.getDescription());
        }
        if (!addContactData.getMailingAddressCity().isEmpty()) {
            linearLayoutMailCity.setVisibility(View.VISIBLE);
             textViewMailCity.setText(addContactData.getMailingAddressCity());
            textViewAddressInfo.setVisibility(View.VISIBLE);
        }
        if (!addContactData.getMailingAddressCountry().isEmpty()) {
            linearLayoutMailCountry.setVisibility(View.VISIBLE);
            textViewMailCountry.setText(addContactData.getMailingAddressCountry());
            textViewAddressInfo.setVisibility(View.VISIBLE);
        }
        if (!addContactData.getMailingAddressState().isEmpty()) {
            linearLayoutMailState.setVisibility(View.VISIBLE);
            textViewMailState.setText(addContactData.getMailingAddressState());
            textViewAddressInfo.setVisibility(View.VISIBLE);
        }
        if (!addContactData.getMailingAddressStreet().isEmpty()) {
            linearLayoutMailStreet.setVisibility(View.VISIBLE);
            textViewMailStreet.setText(addContactData.getMailingAddressStreet());
            textViewAddressInfo.setVisibility(View.VISIBLE);
        }
        if (!addContactData.getMailingAddressZip().isEmpty()) {
            linearLayoutMailCode.setVisibility(View.VISIBLE);
            textViewMailCode.setText(addContactData.getMailingAddressZip());
            textViewAddressInfo.setVisibility(View.VISIBLE);
        }
        if (!addContactData.getOtherAddressCity().isEmpty()) {
            linearLayoutOtherAddressCity.setVisibility(View.VISIBLE);
            textViewOtherCity.setText(addContactData.getOtherAddressCity());
            textViewAddressInfo.setVisibility(View.VISIBLE);
        }
        if (!addContactData.getOtherAddressCountry().isEmpty()) {
            linearLayoutOtherAddressCountry.setVisibility(View.VISIBLE);
            textViewOtherCountry.setText(addContactData.getOtherAddressCountry());
            textViewAddressInfo.setVisibility(View.VISIBLE);
        }
        if (!addContactData.getOtherAddressState().isEmpty()) {
            linearLayoutOtherAddressState.setVisibility(View.VISIBLE);
            textViewOtherState.setText(addContactData.getOtherAddressState());
        }
        if (!addContactData.getOtherAddressStreet().isEmpty()) {
            linearLayoutOtherStreet.setVisibility(View.VISIBLE);
            textViewOtherStreet.setText(addContactData.getOtherAddressStreet());
            textViewAddressInfo.setVisibility(View.VISIBLE);
        }
        if (!addContactData.getOtherAddressZip().isEmpty()) {
            linearLayoutOtherAddressCode.setVisibility(View.VISIBLE);
            textViewOtherCode.setText(addContactData.getOtherAddressZip());
            textViewAddressInfo.setVisibility(View.VISIBLE);
        }




    }


}