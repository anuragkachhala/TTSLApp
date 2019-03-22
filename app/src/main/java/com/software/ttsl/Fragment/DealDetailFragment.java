package com.software.ttsl.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software.ttsl.DealDetailsActivity;
import com.software.ttsl.R;
import com.software.ttsl.Request.AccountDataModel;
import com.software.ttsl.Request.DealDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.EmployConstantUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class DealDetailFragment extends Fragment {

    private final static String TAG = DealDetailFragment.class.getName();


    @BindView(R.id.linear_layout_deal_amount)
    LinearLayout linearLayoutDealAmount;

    @BindView(R.id.linear_layout_deal_type)
    LinearLayout linearLayoutDealType;

    @BindView(R.id.linear_layout_deal_probability)
    LinearLayout linearLayoutDealProbability;

    @BindView(R.id.linear_layout_deal_next_step)
    LinearLayout linearLayoutDealNextStep;

    @BindView(R.id.linear_layout_deal_ex_revenue)
    LinearLayout linearLayoutDealExRevenue;

    @BindView(R.id.linear_layout_deal_lead_source)
    LinearLayout linearLayoutDealLeadSource;

    @BindView(R.id.linear_layout_deal_campaign_source)
    LinearLayout linearLayoutDealCampaignSource;

    @BindView(R.id.linear_layout_deal_contact_name)
    LinearLayout linearLayoutDealContactName;

    @BindView(R.id.layout_deal_description)
    LinearLayout linearLayoutDealDescription;


    @BindView(R.id.tv_deal_owner)
    TextView textViewDealOwner;

    @BindView(R.id.tv_deal_amount)
    TextView textViewDealAmount;

    @BindView(R.id.tv_deal_name)
    TextView textViewDealName;

    @BindView(R.id.tv_deal_closing_date)
    TextView textViewDealClosingData;

    @BindView(R.id.tv_deal_account_name)
    TextView textViewDealAccountName;

    @BindView(R.id.tv_deal_stage)
    TextView textViewDealStage;

    @BindView(R.id.tv_deal_type)
    TextView textViewDealType;

    @BindView(R.id.tv_deal_probability)
    TextView textViewDealProbability;

    @BindView(R.id.tv_deal_next_step)
    TextView textViewDealNextStep;

    @BindView(R.id.tv_deal_ex_revenue)
    TextView textViewDealExRevenue;

    @BindView(R.id.tv_deal_lead_source)
    TextView textViewDealLeadSource;

    @BindView(R.id.tv_deal_campaign_source)
    TextView textViewDealCampaignSource;

    @BindView(R.id.tv_deal_contact_name)
    TextView textViewDealContactName;

    @BindView(R.id.tv_deal_created_by)
    TextView textViewDealCreatedBy;

    @BindView(R.id.tv_deal_modify_by)
    TextView textViewModifyBy;

    @BindView(R.id.tv_deal_created_time)
    TextView textViewDealCreatedTime;

    @BindView(R.id.tv_deal_modify_time)
    TextView  textViewModifyTime;

    @BindView(R.id.tv_deal_description)
    TextView textViewDealDescription;

    @BindView(R.id.tv_smart_view)
    TextView textViewSmartView;

    private DataBaseAdapter dataBaseAdapter;
    private Long dealId;
    private DealDataModel dealDataModel;
    private DealDetailsActivity dealDetailsActivity;





    public DealDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_deal_detail, container, false);
        ButterKnife.bind(this,view);

        dataBaseAdapter = new DataBaseAdapter(getContext());

        Bundle bundle =this.getArguments();
        if(bundle!=null){
            dealId =bundle.getLong(EmployConstantUtil.KEY_DEAL_ID,0);
        }


        textViewSmartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smartView();
            }
        });


        dealDetailsActivity = (DealDetailsActivity) getActivity();
        updateUI();

        return view;
    }


    private void updateUI(){
        dealDataModel= dealDetailsActivity.getDealDataModel();
        setDealInfo();

    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }



    private void smartView() {
        if (textViewSmartView.getText().toString().equals(getResources().getString(R.string.footer_show_all_fields))) {
            textViewSmartView.setText(getResources().getString(R.string.footer_smart_view));
            linearLayoutDealAmount.setVisibility(View.VISIBLE);
            linearLayoutDealCampaignSource.setVisibility(View.VISIBLE);
            linearLayoutDealContactName.setVisibility(View.VISIBLE);
            linearLayoutDealDescription.setVisibility(View.VISIBLE);
            linearLayoutDealExRevenue.setVisibility(View.VISIBLE);
            linearLayoutDealLeadSource.setVisibility(View.VISIBLE);
            linearLayoutDealNextStep.setVisibility(View.VISIBLE);
            linearLayoutDealProbability.setVisibility(View.VISIBLE);
            linearLayoutDealType.setVisibility(View.VISIBLE);
        }else {
            textViewSmartView.setText(getResources().getString(R.string.footer_show_all_fields));
            setVisibilityGone();
            setDealInfo();
        }
    }

    private void setDealInfo() {

        textViewDealOwner.setText(dealDataModel.getDealOwner());
        textViewDealAccountName.setText(DateAndTimeUtil.firstLatterUpper(dealDataModel.getAccountName()));
        textViewDealStage.setText(dealDataModel.getStage());
        textViewDealClosingData.setText(dealDataModel.getClosingDate());
        textViewDealName.setText(dealDataModel.getDealName());
        textViewDealCreatedBy.setText(dealDataModel.getCreatedBy());
        textViewDealCreatedTime.setText(DateAndTimeUtil.longToDate(dealDataModel.getCreatedTime()));
        textViewModifyBy.setText(dealDataModel.getModifiedBy());
        textViewModifyTime.setText(DateAndTimeUtil.longToDate(dealDataModel.getModifiedTime()));
        if(dealDataModel.getAmount()!=0){
           textViewDealAmount.setText(String.valueOf(dealDataModel.getAmount()));
           linearLayoutDealAmount.setVisibility(View.VISIBLE);
        }if(!dealDataModel.getType().isEmpty()){
            textViewDealType.setText(dealDataModel.getType());
            linearLayoutDealType.setVisibility(View.VISIBLE);
        }if(dealDataModel.getProbability()!=0){
            textViewDealProbability.setText(String.valueOf(dealDataModel.getProbability()));
            linearLayoutDealProbability.setVisibility(View.VISIBLE);
        }if(!dealDataModel.getNextStep().isEmpty()){
            textViewDealNextStep.setText(dealDataModel.getNextStep());
            linearLayoutDealNextStep.setVisibility(View.VISIBLE);
        }if(!dealDataModel.getExpectedRevenue().isEmpty()){
            textViewDealExRevenue.setText(dealDataModel.getExpectedRevenue());
            linearLayoutDealExRevenue.setVisibility(View.VISIBLE);
        }if(!dealDataModel.getLeadSource().isEmpty()){
            textViewDealLeadSource.setText(dealDataModel.getLeadSource());
            linearLayoutDealLeadSource.setVisibility(View.VISIBLE);
        }if(!dealDataModel.getCampaignSource().isEmpty()){
            textViewDealCampaignSource.setText(dealDataModel.getCampaignSource());
            linearLayoutDealCampaignSource.setVisibility(View.VISIBLE);
        }if(!dealDataModel.getContactName().isEmpty()){
            textViewDealContactName.setText(dealDataModel.getContactName());
            linearLayoutDealContactName.setVisibility(View.VISIBLE);
        }if(!dealDataModel.getDescription().isEmpty()){
            textViewDealDescription.setText(dealDataModel.getDescription());
            linearLayoutDealDescription.setVisibility(View.VISIBLE);
        }


    }

    private void setVisibilityGone() {
        linearLayoutDealAmount.setVisibility(View.GONE);
        linearLayoutDealCampaignSource.setVisibility(View.GONE);
        linearLayoutDealContactName.setVisibility(View.GONE);
        linearLayoutDealDescription.setVisibility(View.GONE);
        linearLayoutDealExRevenue.setVisibility(View.GONE);
        linearLayoutDealLeadSource.setVisibility(View.GONE);
        linearLayoutDealNextStep.setVisibility(View.GONE);
        linearLayoutDealProbability.setVisibility(View.GONE);
        linearLayoutDealType.setVisibility(View.GONE);

    }


    private void getAccount() {


     dealDataModel = dataBaseAdapter.getDealById(dealId);


    }


}
