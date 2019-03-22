package com.software.ttsl.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software.ttsl.AccountDetailsActivity;
import com.software.ttsl.ContactDetailActivity;
import com.software.ttsl.CustomView.CustomProgressBar;
import com.software.ttsl.DealDetailsActivity;
import com.software.ttsl.R;
import com.software.ttsl.Request.AccountDataModel;
import com.software.ttsl.Request.DealDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.model.AddContactData;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class DealRelatedFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.tv_deal_name)
    TextView textViewDealName;

    @BindView(R.id.tv_deal_owner)
    TextView textViewDealOwner;

    @BindView(R.id.tv_deal_closing_date)
    TextView textViewDealClosingDate;


    @BindView(R.id.tv_account_name)
    TextView textViewAccountName;

    @BindView(R.id.tv_stage_name)
    TextView textViewStage;

    @BindView(R.id.progress_bar)
    CustomProgressBar customProgressBar;

    @BindView(R.id.layout_contact)
    LinearLayout linearLayoutContact;

    @BindView(R.id.tv_contact_name)
    TextView textViewContactName;

    @BindView(R.id.layout_account)
    LinearLayout linearLayoutAccount;

    private long dealId;
    private DataBaseAdapter dataBaseAdapter;
    private DealDataModel dealDataModel;
    private AccountDataModel accountDataModel;
    private AddContactData addContactData;
    private DealDetailsActivity dealDetailsActivity;
    private long accountID, contactID;
    private boolean isAccountClick = true;
    private AlertDialogManager alertDialogManager;

    public DealRelatedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deal_related, container, false);

        ButterKnife.bind(this, view);

        dataBaseAdapter = new DataBaseAdapter(getContext());
        alertDialogManager = new AlertDialogManager();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            dealId = bundle.getLong(EmployConstantUtil.KEY_DEAL_ID, 0);
        }


        dealDetailsActivity = (DealDetailsActivity) getActivity();

        setClickListener();
        updateUI();


        return view;
    }

    private void setClickListener() {
        linearLayoutAccount.setOnClickListener(this);
        linearLayoutContact.setOnClickListener(this);
    }


    private void updateUI() {
        dealDataModel = dealDetailsActivity.getDealDataModel();
        setDealInfo();

    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }


    private void setDealInfo() {
        textViewDealName.setText(dealDataModel.getDealName());
        textViewDealOwner.setText(dealDataModel.getDealOwner());
        textViewDealClosingDate.setText(dealDataModel.getClosingDate());
        customProgressBar.setProgress((int) (long) dealDataModel.getProbability());
        textViewStage.setText(dealDataModel.getStage());
        if (!dealDataModel.getAccountName().isEmpty()) {
            linearLayoutAccount.setVisibility(View.VISIBLE);
            textViewAccountName.setText(dealDataModel.getAccountName());
            accountID = dealDataModel.getAccountId();

        }
        if (!dealDataModel.getContactName().isEmpty()) {
            linearLayoutContact.setVisibility(View.VISIBLE);
            textViewContactName.setText(dealDataModel.getContactName());
            contactID = dealDataModel.getContactId();
        }


    }

    private void getDeal() {
        dealDataModel = dataBaseAdapter.getDealById(dealId);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.layout_account:
                accountDataModel = dataBaseAdapter.getAccountById(accountID);
                if (accountDataModel != null) {
                    startNewActivity(AccountDetailsActivity.class, accountID, EmployConstantUtil.KEY_ACCOUNT_ID);
                } else {
                    alertDialogManager.showAlertDialog(getContext(), "", "Sorry ! this account has been deleted", false);

                }
                break;

            case R.id.layout_contact:
                if (dataBaseAdapter.getContactById(contactID) != null) {
                    startNewActivity(ContactDetailActivity.class, contactID, EmployConstantUtil.KEY_CONTACT_ID);
                } else {
                    alertDialogManager.showAlertDialog(getContext(), "", "Sorry ! this contact has been deleted", false);

                }

                break;


        }
    }


    private void startNewActivity(Class<?> cls, long id, String key) {
        Intent intent = new Intent(getContext(), cls);
        intent.putExtra(key, id);
        startActivity(intent);
    }
}
