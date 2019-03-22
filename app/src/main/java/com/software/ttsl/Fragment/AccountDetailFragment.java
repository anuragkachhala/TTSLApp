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
import com.software.ttsl.R;
import com.software.ttsl.Request.AccountDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.EmployConstantUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountDetailFragment extends Fragment {

    private final static String TAG = AccountDetailFragment.class.getName();


    @BindView(R.id.linear_layout_account_owner)
    LinearLayout linearLayoutAccountOwner;

    @BindView(R.id.linear_layout_account_rating)
    LinearLayout linearLayoutAccountRating;

    @BindView(R.id.linear_layout_account_name)
    LinearLayout linearLayoutAccountName;

    @BindView(R.id.linear_layout_account_phone)
    LinearLayout linearLayoutAccountPhone;

    @BindView(R.id.linear_layout_account_site)
    LinearLayout linearLayoutAccountSite;


    @BindView(R.id.linear_layout_account_fax)
    LinearLayout linearLayoutAccountFax;

    @BindView(R.id.linear_layout_account_parent)
    LinearLayout linearLayoutAccountParent;

    @BindView(R.id.linear_layout_account_website)
    LinearLayout linearLayoutAccountWebsite;

    @BindView(R.id.linear_layout_account_number)
    LinearLayout linearLayoutAccountNumber;

    @BindView(R.id.linear_layout_account_ticker_symbol)
    LinearLayout linearLayoutAccountTickerSymbol;

    @BindView(R.id.linear_layout_account_type)
    LinearLayout linearLayoutAccountType;

    @BindView(R.id.linear_layout_account_ownership)
    LinearLayout linearLayoutAccountOwnerShip;

    @BindView(R.id.linear_layout_account_industry)
    LinearLayout linearLayoutAccountIndustry;

    @BindView(R.id.linear_layout_account_employeee)
    LinearLayout linearLayoutAccountEmployee;

    @BindView(R.id.linear_layout_account_revenue)
    LinearLayout linearLayoutAccountRevenue;

    @BindView(R.id.linear_layout_account_sic_code)
    LinearLayout linearLayoutAccountSicCode;

    @BindView(R.id.layout_address_info)
    TextView linearLayoutAddressInfo;

    @BindView(R.id.layout_account_bill_address_street)
    LinearLayout linearLayoutBillingStreet;

    @BindView(R.id.layout_account_bill_address_city)
    LinearLayout linearLayoutBillingAddressCity;

    @BindView(R.id.layout_account_bill_address_state)
    LinearLayout linearLayoutBillingAddressState;

    @BindView(R.id.layout_account_bill_address_code)
    LinearLayout linearLayoutBillingAddressCode;

    @BindView(R.id.layout_account_bill_address_country)
    LinearLayout linearLayoutBillAddressCountry;

    @BindView(R.id.layout_account_shipping_address_street)
    LinearLayout linearLayoutShippingAddressStreet;

    @BindView(R.id.layout_account_shipping_address_city)
    LinearLayout linearLayoutShippingAddressCity;

    @BindView(R.id.layout_account_shipping_address_state)
    LinearLayout linearLayoutShippingAddressState;

    @BindView(R.id.layout_account_shipping_address_code)
    LinearLayout linearLayoutShippingAddressCode;

    @BindView(R.id.layout_account_shipping_address_country)
    LinearLayout linearLayoutShippingAddressCountry;

    @BindView(R.id.layout_tv_account_description)
    LinearLayout LayoutAccountDescription;

    @BindView(R.id.layout_account_description)
    LinearLayout linearLayoutAccountDescription;


    @BindView(R.id.tv_account_owner)
    TextView textViewAccountOwner;

    @BindView(R.id.tv_account_rating)
    TextView textViewAccountRating;

    @BindView(R.id.tv_account_name)
    TextView textViewAccountName;

    @BindView(R.id.tv_account_phone)
    TextView textViewAccountPhone;

    @BindView(R.id.tv_account_site)
    TextView textViewAccountSite;

    @BindView(R.id.tv_account_fax)
    TextView textViewAccountFax;

    @BindView(R.id.tv_account_parent)
    TextView textViewAccountParent;

    @BindView(R.id.tv_account_website)
    TextView textViewAccountWebsite;

    @BindView(R.id.tv_account_number)
    TextView textViewAccountNumber;

    @BindView(R.id.tv_account_ticker_symbol)
    TextView textViewAccountTickerSymbol;

    @BindView(R.id.tv_account_type)
    TextView textViewAccountType;

    @BindView(R.id.tv_account_ownership)
    TextView textViewAccountOwnerShip;

    @BindView(R.id.tv_account_industry)
    TextView textViewAccountIndustry;

    @BindView(R.id.tv_account_employees)
    TextView textViewAccountEmployees;

    @BindView(R.id.tv_account_revenue)
    TextView textViewAccountRevenue;

    @BindView(R.id.tv_account_sic_code)
    TextView textViewAccountSicCode;

    @BindView(R.id.tv_account_created_by)
    TextView textViewAccountCreatedBy;

    @BindView(R.id.tv_account_modify_by)
    TextView textViewAccountModifyBy;

    @BindView(R.id.tv_account_created_time)
    TextView textViewAccountCreatedTime;

    @BindView(R.id.tv_account_modify_time)
    TextView textViewAccountModfifyTime;

    @BindView(R.id.tv_account_bill_street)
    TextView textViewAccountBillStreet;

    @BindView(R.id.tv_account_bill_address_city)
    TextView textViewAccountBillCity;

    @BindView(R.id.tv_account_bill_address_state)
    TextView textViewAccountBillState;

    @BindView(R.id.tv_account_bill_address_code)
    TextView textViewAccountBillCode;

    @BindView(R.id.tv_account_bill_address_country)
    TextView textViewAccountBillCountry;

    @BindView(R.id.tv_account_shipping_street)
    TextView textViewAccountShippingSteet;

    @BindView(R.id.tv_account_shipping_address_city)
    TextView textViewAccountShippingCity;

    @BindView(R.id.tv_account_shipping_address_state)
    TextView textViewAccountShippingState;

    @BindView(R.id.tv_account_shipping_address_code)
    TextView textViewAccountShippingCode;

    @BindView(R.id.tv_account_shipping_address_country)
    TextView textViewAccountShippingCountry;

    @BindView(R.id.tv_account_description)
    TextView textViewDescription;

    @BindView(R.id.tv_smart_view)
    TextView textViewSmartView;

    @BindView(R.id.tv_account_label_name)
    TextView textViewAccountLabelName;

    private DataBaseAdapter dataBaseAdapter;
    private Long accountId;
    private AccountDataModel accountDataModel;
    private AccountDetailsActivity accountDetailsActivity;

    public AccountDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_detail, container, false);
        ButterKnife.bind(this, view);


        textViewAccountLabelName.setText(Html.fromHtml(EmployConstantUtil.ASTERISK +getResources().getString(R.string.label_add_account_account_name)));
        dataBaseAdapter = new DataBaseAdapter(getContext());
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            accountId = bundle.getLong(EmployConstantUtil.KEY_ACCOUNT_ID, 0);
        }


        textViewSmartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smartView();
            }
        });


        getAccount();
        setAccountInfo();

        return view;


    }



    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }



    private void updateUI() {
        /*getAccount();*/
        accountDataModel = accountDetailsActivity.getAccountData();
        setAccountInfo();

    }

    private void smartView() {
        if (textViewSmartView.getText().toString().equals(getResources().getString(R.string.footer_show_all_fields))) {
            textViewSmartView.setText(getResources().getString(R.string.footer_smart_view));
            linearLayoutAccountOwner.setVisibility(View.VISIBLE);
            linearLayoutAccountRating.setVisibility(View.VISIBLE);
            linearLayoutAccountPhone.setVisibility(View.VISIBLE);
            linearLayoutAccountSite.setVisibility(View.VISIBLE);
            linearLayoutAccountFax.setVisibility(View.VISIBLE);
            linearLayoutAccountParent.setVisibility(View.VISIBLE);
            linearLayoutAccountWebsite.setVisibility(View.VISIBLE);
            linearLayoutAccountIndustry.setVisibility(View.VISIBLE);
            linearLayoutAccountEmployee.setVisibility(View.VISIBLE);
            linearLayoutAccountNumber.setVisibility(View.VISIBLE);
            linearLayoutAccountRevenue.setVisibility(View.VISIBLE);
            linearLayoutAccountSicCode.setVisibility(View.VISIBLE);
            LayoutAccountDescription.setVisibility(View.VISIBLE);
            linearLayoutBillingStreet.setVisibility(View.VISIBLE);
            linearLayoutBillAddressCountry.setVisibility(View.VISIBLE);
            linearLayoutBillingAddressCode.setVisibility(View.VISIBLE);
            linearLayoutBillingAddressCity.setVisibility(View.VISIBLE);
            linearLayoutBillingAddressState.setVisibility(View.VISIBLE);
            linearLayoutShippingAddressStreet.setVisibility(View.VISIBLE);
            linearLayoutShippingAddressState.setVisibility(View.VISIBLE);
            linearLayoutShippingAddressCode.setVisibility(View.VISIBLE);
            linearLayoutShippingAddressCity.setVisibility(View.VISIBLE);
            linearLayoutShippingAddressCountry.setVisibility(View.VISIBLE);
            linearLayoutAccountTickerSymbol.setVisibility(View.VISIBLE);
            linearLayoutAccountType.setVisibility(View.VISIBLE);
            linearLayoutAccountOwnerShip.setVisibility(View.VISIBLE);
            linearLayoutAddressInfo.setVisibility(View.VISIBLE);


        } else {
            textViewSmartView.setText(getResources().getString(R.string.footer_show_all_fields));
            setVisibilityGone();
            setAccountInfo();
        }
    }

    private void setVisibilityGone() {
        linearLayoutAccountOwner.setVisibility(View.GONE);
        linearLayoutAccountRating.setVisibility(View.GONE);
        linearLayoutAccountPhone.setVisibility(View.GONE);
        linearLayoutAccountSite.setVisibility(View.GONE);
        linearLayoutAccountFax.setVisibility(View.GONE);
        linearLayoutAccountParent.setVisibility(View.GONE);
        linearLayoutAccountWebsite.setVisibility(View.GONE);
        linearLayoutAccountIndustry.setVisibility(View.GONE);
        linearLayoutAccountEmployee.setVisibility(View.GONE);
        linearLayoutAccountRevenue.setVisibility(View.GONE);
        linearLayoutAccountNumber.setVisibility(View.GONE);
        linearLayoutAccountSicCode.setVisibility(View.GONE);
        LayoutAccountDescription.setVisibility(View.GONE);
        linearLayoutBillingStreet.setVisibility(View.GONE);
        linearLayoutBillAddressCountry.setVisibility(View.GONE);
        linearLayoutBillingAddressCode.setVisibility(View.GONE);
        linearLayoutBillingAddressCity.setVisibility(View.GONE);
        linearLayoutBillingAddressState.setVisibility(View.GONE);
        linearLayoutShippingAddressStreet.setVisibility(View.GONE);
        linearLayoutShippingAddressState.setVisibility(View.GONE);
        linearLayoutShippingAddressCode.setVisibility(View.GONE);
        linearLayoutShippingAddressCity.setVisibility(View.GONE);
        linearLayoutShippingAddressCountry.setVisibility(View.GONE);
        linearLayoutAddressInfo.setVisibility(View.GONE);
        linearLayoutAccountTickerSymbol.setVisibility(View.GONE);
        linearLayoutAccountType.setVisibility(View.GONE);
        linearLayoutAccountOwnerShip.setVisibility(View.GONE);
    }


    private void setAccountInfo() {
        textViewAccountOwner.setText(accountDataModel.getAccountOwner());
        textViewAccountName.setText(accountDataModel.getAccountName());
        textViewAccountCreatedBy.setText(accountDataModel.getCreatedBy());
        textViewAccountModifyBy.setText(accountDataModel.getModifyBy());
        textViewAccountCreatedTime.setText(DateAndTimeUtil.longToDate(accountDataModel.getCreatedTime()));
        textViewAccountModfifyTime.setText(DateAndTimeUtil.longToDate(accountDataModel.getModifyTime()));

        if (!accountDataModel.getRating().isEmpty()) {
            linearLayoutAccountRating.setVisibility(View.VISIBLE);
            textViewAccountRating.setText(accountDataModel.getRating());
        }
        if (!accountDataModel.getPhone().isEmpty()) {
            linearLayoutAccountPhone.setVisibility(View.VISIBLE);
            textViewAccountPhone.setText(accountDataModel.getPhone());
        }
        if (!accountDataModel.getAccountSite().isEmpty()) {
            linearLayoutAccountSite.setVisibility(View.VISIBLE);
            textViewAccountSite.setText(accountDataModel.getAccountSite());
        }
        if (!accountDataModel.getFax().isEmpty()) {
            linearLayoutAccountFax.setVisibility(View.VISIBLE);
            textViewAccountFax.setText(accountDataModel.getFax());
        }
        if (!accountDataModel.getParentAccount().isEmpty()) {
            linearLayoutAccountParent.setVisibility(View.VISIBLE);
            textViewAccountParent.setText(accountDataModel.getParentAccount());
        }
        if (!accountDataModel.getWebSite().isEmpty()) {
            linearLayoutAccountWebsite.setVisibility(View.VISIBLE);
            textViewAccountWebsite.setText(accountDataModel.getWebSite());
        }
        if (!accountDataModel.getIndustry().isEmpty()) {
            linearLayoutAccountIndustry.setVisibility(View.VISIBLE);
            textViewAccountIndustry.setText(accountDataModel.getIndustry());
        }
        if (!accountDataModel.getEmployees().isEmpty() && !accountDataModel.getEmployees().equals("0")) {
            linearLayoutAccountEmployee.setVisibility(View.VISIBLE);
            textViewAccountEmployees.setText(accountDataModel.getEmployees());
        }
        if (accountDataModel.getAccountNumber() != 0) {
            linearLayoutAccountNumber.setVisibility(View.VISIBLE);
            textViewAccountNumber.setText(String.valueOf(accountDataModel.getAccountNumber()));
        }

        if (!accountDataModel.getTickerSymbol().isEmpty()) {
            linearLayoutAccountTickerSymbol.setVisibility(View.VISIBLE);
            textViewAccountTickerSymbol.setText(accountDataModel.getTickerSymbol());
        }
        if (!accountDataModel.getAccountType().isEmpty()) {
            linearLayoutAccountType.setVisibility(View.VISIBLE);
            textViewAccountType.setText(accountDataModel.getAccountType());
        }
        if (!accountDataModel.getOwnerShip().isEmpty()) {
            linearLayoutAccountOwnerShip.setVisibility(View.VISIBLE);
            textViewAccountOwnerShip.setText(accountDataModel.getOwnerShip());
        }

        if (!accountDataModel.getAnnualRevenue().isEmpty() && !accountDataModel.getAnnualRevenue().equals("0")) {
            linearLayoutAccountRevenue.setVisibility(View.VISIBLE);
            textViewAccountRevenue.setText(accountDataModel.getAnnualRevenue());
        }
        if (!accountDataModel.getSicCode().isEmpty() && !accountDataModel.getSicCode().equals("0")) {
            linearLayoutAccountSicCode.setVisibility(View.VISIBLE);
            textViewAccountSicCode.setText(accountDataModel.getSicCode());
        }
        if (!accountDataModel.getDescription().isEmpty()) {
            LayoutAccountDescription.setVisibility(View.VISIBLE);
            textViewDescription.setText(accountDataModel.getDescription());
        }
        if (!accountDataModel.getBillingAddressStreet().isEmpty()) {
            linearLayoutAddressInfo.setVisibility(View.VISIBLE);
            linearLayoutBillingStreet.setVisibility(View.VISIBLE);
            textViewAccountBillStreet.setText(accountDataModel.getBillingAddressStreet());
        }
        if (!accountDataModel.getBillingAddressState().isEmpty()) {
            linearLayoutAddressInfo.setVisibility(View.VISIBLE);
            linearLayoutBillingAddressState.setVisibility(View.VISIBLE);
            textViewAccountBillState.setText(accountDataModel.getBillingAddressState());

        }
        if (!accountDataModel.getBillingAddressCity().isEmpty()) {
            linearLayoutAddressInfo.setVisibility(View.VISIBLE);
            linearLayoutBillingAddressCity.setVisibility(View.VISIBLE);
            textViewAccountBillCity.setText(accountDataModel.getBillingAddressCity());
        }
        if (!accountDataModel.getBillingAddressCode().isEmpty()) {
            linearLayoutAddressInfo.setVisibility(View.VISIBLE);
            linearLayoutBillingAddressCode.setVisibility(View.VISIBLE);
            textViewAccountBillCode.setText(accountDataModel.getBillingAddressCode());
        }
        if (!accountDataModel.getBillingAddressCountry().isEmpty()) {
            linearLayoutAddressInfo.setVisibility(View.VISIBLE);
            linearLayoutBillAddressCountry.setVisibility(View.VISIBLE);
            textViewAccountBillCountry.setText(accountDataModel.getBillingAddressCountry());
        }
        if (!accountDataModel.getShippingAddressState().isEmpty()) {
            linearLayoutAddressInfo.setVisibility(View.VISIBLE);
            linearLayoutShippingAddressState.setVisibility(View.VISIBLE);
            textViewAccountShippingState.setText(accountDataModel.getShippingAddressState());
        }
        if (!accountDataModel.getShippingAddressCode().isEmpty()) {
            linearLayoutAddressInfo.setVisibility(View.VISIBLE);
            linearLayoutShippingAddressCode.setVisibility(View.VISIBLE);
            textViewAccountShippingCode.setText(accountDataModel.getShippingAddressCode());
        }
        if (!accountDataModel.getShippingAddressStreet().isEmpty()) {
            linearLayoutAddressInfo.setVisibility(View.VISIBLE);
            linearLayoutShippingAddressStreet.setVisibility(View.VISIBLE);
            textViewAccountShippingSteet.setText(accountDataModel.getShippingAddressStreet());
        }
        if (!accountDataModel.getShippingAddressCity().isEmpty()) {
            linearLayoutAddressInfo.setVisibility(View.VISIBLE);
            linearLayoutShippingAddressCity.setVisibility(View.VISIBLE);
            textViewAccountShippingCity.setText(accountDataModel.getShippingAddressCity());

        }
        if (!accountDataModel.getShippingAddressCountry().isEmpty()) {
            linearLayoutAddressInfo.setVisibility(View.VISIBLE);
            linearLayoutShippingAddressCountry.setVisibility(View.VISIBLE);
            textViewAccountShippingCountry.setText(accountDataModel.getShippingAddressCountry());
        }


    }

    private void getAccount() {

        //accountDataModel = dataBaseAdapter.getAccountById(accountId);
        accountDetailsActivity = (AccountDetailsActivity) getActivity();
        accountDataModel = accountDetailsActivity.getAccountData();
    }


}
