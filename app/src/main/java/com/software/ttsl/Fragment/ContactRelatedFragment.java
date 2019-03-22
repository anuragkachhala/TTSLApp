package com.software.ttsl.Fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.software.ttsl.AccountDetailsActivity;
import com.software.ttsl.Adapter.DealListAdapter;
import com.software.ttsl.ContactDetailActivity;
import com.software.ttsl.R;
import com.software.ttsl.Request.DealDataModel;
import com.software.ttsl.Request.EventDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.CustomModuleInflaterUtil;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.model.AddContactData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.software.ttsl.Utils.EmployConstantUtil.CONTACT_TYPE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactRelatedFragment extends BaseUtilFragment implements View.OnClickListener {


    @BindView(R.id.tv_contact_name)
    TextView textViewContactName;

    @BindView(R.id.tv_contact_email_id)
    TextView textViewContactEmailId;

    @BindView(R.id.tv_contact_mobile_no)
    TextView textViewContactMobileNo;

    @BindView(R.id.tv_account_name)
    TextView textViewAccountName;

    @BindView(R.id.layout_contact_account)
    LinearLayout linearLayoutContactAccount;


    @BindView(R.id.recycler_view_deals)
    RecyclerView recyclerViewDeals;

    /*@BindView(R.id.recycler_view_event)
    RecyclerView recyclerViewEvent;*/

    @BindView(R.id.call_container)
    LinearLayout linearLayoutCallContainer;

    @BindView(R.id.task_container)
    LinearLayout linearLayoutTaskContainer;

    @BindView(R.id.event_container)
    LinearLayout linearLayoutEventContainer;


    private long contactId;
    private DataBaseAdapter dataBaseAdapter;
    private AddContactData addContactData;
    private ArrayList<DealDataModel> dealDataModelList = new ArrayList<>();
    private ArrayList<EventDataModel> eventDataModelsList = new ArrayList<>();
    private CustomModuleInflaterUtil customModuleInflaterUtil;
    private DealListAdapter dealListAdapter;
    private ContactDetailActivity contactDetailActivity;
    private long accountID;


    public ContactRelatedFragment() {
        // Required empty public constructor
    }


    @Override
    public BaseUtilFragment provideYourFragment() {
        return new ContactRelatedFragment();
    }

    @Override
    public View provideYourFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_releated, parent, false);


        ButterKnife.bind(this, view);
        dataBaseAdapter = new DataBaseAdapter(getContext());
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            contactId = bundle.getLong(EmployConstantUtil.KEY_CONTACT_ID, 0);
        }

        contactDetailActivity = (ContactDetailActivity) getActivity();
        addContactData = contactDetailActivity.getContactData();

        customModuleInflaterUtil = new CustomModuleInflaterUtil(getContext(), this);
        customModuleInflaterUtil.setAllTask(CONTACT_TYPE, contactId, linearLayoutTaskContainer);
        customModuleInflaterUtil.setAllCall(CONTACT_TYPE, contactId, linearLayoutCallContainer);
        customModuleInflaterUtil.setAllEvent(CONTACT_TYPE, contactId, linearLayoutEventContainer);


        setClickListener();
        updateUI();
        return view;
    }

    @Override
    public void startActivityForResult1(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);

    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }





    private void setClickListener() {
        linearLayoutContactAccount.setOnClickListener(this);
    }

    private void updateUI() {

        addContactData = contactDetailActivity.getContactData();
        setContactInfo();


        dealDataModelList.clear();
        dealDataModelList.addAll(dataBaseAdapter.getDealByContactId(contactId));
        if (dealDataModelList.size() != 0) {
            recyclerViewDeals.setVisibility(View.VISIBLE);
           // dealListAdapter.notifyDataSetChanged();
        }


    }

    private void setContactInfo() {
        textViewContactName.setText((addContactData.getSalutation() + " " + DateAndTimeUtil.firstLatterUpper(addContactData.getFirstName()) + " " + DateAndTimeUtil.firstLatterUpper(addContactData.getLastName())).trim());
        if (addContactData.getEmail() != null && !addContactData.getEmail().isEmpty()) {
            textViewContactEmailId.setText(addContactData.getEmail());
        }
        if (!addContactData.getPhone().isEmpty()) {
            textViewContactMobileNo.setText(addContactData.getPhone());
        }
        if (!addContactData.getAccountName().isEmpty()) {
            linearLayoutContactAccount.setVisibility(View.VISIBLE);
            textViewAccountName.setText(addContactData.getAccountName());
            accountID = addContactData.getAccountId();
        }

    }

    private void getAccount() {
        addContactData = dataBaseAdapter.getContactById(contactId);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.layout_contact_account:
                if (dataBaseAdapter.getAccountById(accountID) != null) {
                    Intent intent = new Intent(getContext(), AccountDetailsActivity.class);
                    intent.putExtra(EmployConstantUtil.KEY_ACCOUNT_ID, accountID);
                    startActivityForResult(intent, 1000);
                } else {
                    Toast.makeText(getContext(), "Account has been deleted", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (requestCode == Activity.RESULT_OK) {
                String result = data.getStringExtra(EmployConstantUtil.KEY_ACCOUNT_ID);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }

        }
    }


}
