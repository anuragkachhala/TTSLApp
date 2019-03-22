package com.software.ttsl.Fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software.ttsl.AccountDetailsActivity;
import com.software.ttsl.CallDetailActivity;
import com.software.ttsl.ContactDetailActivity;
import com.software.ttsl.LeadDetailActivity;
import com.software.ttsl.R;
import com.software.ttsl.Request.AccountDataModel;
import com.software.ttsl.Request.CallDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.EmployConstantUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class CallRelatedFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = CallRelatedFragment.class.getName();

    @BindView(R.id.iv_call_type)
    ImageView imageViewCallType;

    @BindView(R.id.tv_call_subject)
    TextView textViewCallSubject;

    @BindView(R.id.tv_call_type)
    TextView textViewCallType;

    @BindView(R.id.tv_call_time)
    TextView textViewCallTime;

    @BindView(R.id.tv_call_duration)
    TextView textViewCallDuration;

    @BindView(R.id.tv_contact_name)
    TextView textViewContactName;

    @BindView(R.id.tv_account_name)
    TextView textViewAccountName;

    @BindView(R.id.layout_contact)
    LinearLayout linearLayoutContact;

    @BindView(R.id.layout_account)
    LinearLayout linearLayoutAccount;

    @BindView(R.id.tv_contact)
    TextView textViewLabelContact;

    private long callID, leadId;
    private DataBaseAdapter dataBaseAdapter;
    private CallDataModel callDataModel;
    private CallDetailActivity callDetailActivity;
    private long accountID, contactID;
    private AccountDataModel accountDataModel;
    private boolean isAccountClick = true, isLead;
    private AlertDialogManager alertDialogManager;


    public CallRelatedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_call_related, container, false);

        ButterKnife.bind(this, view);

        dataBaseAdapter = new DataBaseAdapter(getContext());
        alertDialogManager = new AlertDialogManager();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            callID = bundle.getLong(EmployConstantUtil.KEY_CALL_ID, 0);
        }


        callDetailActivity = (CallDetailActivity) getActivity();

        setClickListener();
        updateUI();


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        callDataModel = callDetailActivity.getCallDataModel();
        textViewCallSubject.setText(callDataModel.getSubject());
        textViewCallDuration.setText(DateAndTimeUtil.longToTime(callDataModel.getCallDuration(), EmployConstantUtil.TIME_FORMAT_DURATION));
        textViewCallTime.setText(DateAndTimeUtil.longToDate(callDataModel.getCallStartDate(), EmployConstantUtil.DATE_FORMAT) + " " + DateAndTimeUtil.longToTime(callDataModel.getCallStartTime(), EmployConstantUtil.TIME_FORMAT));
        textViewCallType.setText(callDataModel.getCallType());

        if (callDataModel.getCallType().equals("Inbound")) {
            imageViewCallType.setImageResource(R.drawable.cmd_phone_incoming);
        } else {
            imageViewCallType.setImageResource(R.drawable.cmd_phone_outgoing);
        }
        if (!callDataModel.getAccount().isEmpty()) {
            linearLayoutAccount.setVisibility(View.VISIBLE);
            textViewAccountName.setText(callDataModel.getAccount());
            accountID = callDataModel.getAccountId();

        }
        if (!callDataModel.getContact().isEmpty()) {
            linearLayoutContact.setVisibility(View.VISIBLE);
            textViewContactName.setText(callDataModel.getContact());
            contactID = callDataModel.getContactId();
            textViewLabelContact.setText("Contacts");
            isLead = false;
        } else if (!callDataModel.getContactLeadName().isEmpty()) {
            linearLayoutContact.setVisibility(View.VISIBLE);
            textViewContactName.setText(callDataModel.getContactLeadName());
            leadId = callDataModel.getLeadId();
            isLead = true;
            textViewLabelContact.setText("Lead");
        }

    }

    private void setClickListener() {
        linearLayoutAccount.setOnClickListener(this);
        linearLayoutContact.setOnClickListener(this);
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
                    isAccountClick = true;
                }
                break;

            case R.id.layout_contact:

                if (isLead) {

                    if (dataBaseAdapter.getLeadByID(leadId) != null) {
                        startNewActivity(LeadDetailActivity.class, leadId, EmployConstantUtil.KEY_LEAD_ID);
                    } else {
                        alertDialogManager.showAlertDialog(getContext(), "", "Sorry ! this Lead has been deleted", false);
                        isAccountClick = false;
                    }
                } else {
                    if (dataBaseAdapter.getContactById(contactID) != null) {
                        startNewActivity(ContactDetailActivity.class, contactID, EmployConstantUtil.KEY_CONTACT_ID);
                    } else {
                        alertDialogManager.showAlertDialog(getContext(), "", "Sorry ! this contact has been deleted", false);
                        isAccountClick = false;
                    }
                }
                break;

        }


    }


    private void startNewActivity(Class<?> cls, long id, String key) {
        Intent intent = new Intent(getContext(), cls);
        intent.putExtra(key, id);
        startActivity(intent);
    }

    public Drawable setVectorForPreLollipop(int resourceId, Context activity) {
        Drawable icon;
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            icon = VectorDrawableCompat.create(activity.getResources(), resourceId, activity.getTheme());
        } else {
            icon = activity.getResources().getDrawable(resourceId, activity.getTheme());
        }

        return icon;
    }


}
