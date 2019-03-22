package com.software.ttsl.Fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.software.ttsl.AccountDetailsActivity;
import com.software.ttsl.AddAccountActivity;
import com.software.ttsl.LeadDetailActivity;
import com.software.ttsl.R;
import com.software.ttsl.Request.AccountDataModel;
import com.software.ttsl.Request.TaskDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.CustomModuleInflaterUtil;
import com.software.ttsl.Utils.EmployConstantUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.software.ttsl.Utils.EmployConstantUtil.ACCOUNT_TYPE;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountReletedFragment extends BaseUtilFragment implements View.OnClickListener {

    private static final String TAG = AccountReletedFragment.class.getName();

    @BindView(R.id.tv_account_name)
    TextView textViewAccountName;

    @BindView(R.id.tv_account_website)
    TextView textViewAccountWebsite;

    @BindView(R.id.tv_account_mobile_no)
    TextView textViewAccountMobileNo;

    @BindView(R.id.tv_account_owner)
    TextView textViewAccountOwner;

    @BindView(R.id.tv_account_parent)
    TextView textViewAccountParent;

    @BindView(R.id.layout_parent_account)
    LinearLayout linearLayoutParentAccount;

    @BindView(R.id.task_container)
    LinearLayout linearLayoutTaskContainer;

    @BindView(R.id.call_container)
    LinearLayout linearLayoutCallContainer;

    @BindView(R.id.event_container)
    LinearLayout linearLayoutEventContainer;

    private long accountId;
    private ArrayList<TaskDataModel> taskDataModelList = new ArrayList<>();
    private DataBaseAdapter dataBaseAdapter;
    private AccountDataModel accountDataModel;
    private AccountDetailsActivity accountDetailsActivity;
    private long parentAccountID;
    private LayoutInflater layoutInflater;
    private CustomModuleInflaterUtil customModuleInflaterUtil;
    public AccountReletedFragment() {
        // Required empty public constructor
    }



    @Override
    public BaseUtilFragment provideYourFragment() {
        return new AccountReletedFragment();
    }

    @Override
    public View provideYourFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account_releted, parent, false);

        ButterKnife.bind(this,view);
        layoutInflater = LayoutInflater.from(getContext());
        dataBaseAdapter = new DataBaseAdapter(getContext());
        Bundle bundle =this.getArguments();
        if(bundle!=null){
            accountId =bundle.getLong(EmployConstantUtil.KEY_ACCOUNT_ID,0);
        }


        accountDetailsActivity = (AccountDetailsActivity) getActivity();


        customModuleInflaterUtil = new CustomModuleInflaterUtil(getContext(),this);
        customModuleInflaterUtil.setAllTask(ACCOUNT_TYPE,accountId,linearLayoutTaskContainer);
        customModuleInflaterUtil.setAllCall(ACCOUNT_TYPE,accountId,linearLayoutCallContainer);
        customModuleInflaterUtil.setAllEvent(ACCOUNT_TYPE,accountId,linearLayoutEventContainer);

        setClickListener();

        updateUI();

      //  setAllAccountTask();


        return view;
    }

    @Override
    public void startActivityForResult1(Intent intent, int requestCode) {
        startActivityForResult(intent,requestCode);

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

    private void setAccountInfo() {
        textViewAccountName.setText(accountDataModel.getAccountName());
        if(!accountDataModel.getWebSite().isEmpty()){
            textViewAccountWebsite.setText(accountDataModel.getWebSite());
        }if(!accountDataModel.getPhone().isEmpty()){
            textViewAccountMobileNo.setText(accountDataModel.getPhone());
        }if(!accountDataModel.getAccountOwner().isEmpty()){
            textViewAccountOwner.setText(accountDataModel.getAccountOwner());
        }if(accountDataModel.getParentAccount()!=null&&!accountDataModel.getParentAccount().isEmpty()){
            linearLayoutParentAccount.setVisibility(View.VISIBLE);
            textViewAccountParent.setText(accountDataModel.getParentAccount());
            parentAccountID= accountDataModel.getParentAccountId();
            Log.e(TAG, "parent Account ID "+parentAccountID);
        }

    }


    private void setClickListener() {
        linearLayoutParentAccount.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
           case  R.id.layout_parent_account:
               if(dataBaseAdapter.getAccountById(parentAccountID)!=null) {
                   startNewActivity(AccountDetailsActivity.class);
               }else {
                   Toast.makeText(getContext(),"Parent Account has been deleted ",Toast.LENGTH_SHORT).show();
               }
               break;
        }
    }


    private void startNewActivity(Class<?> cls) {
        Intent intent = new Intent(getContext(), cls);
        intent.putExtra(EmployConstantUtil.KEY_ACCOUNT_ID,parentAccountID);
        startActivity(intent);
        }


    private void setAllAccountTask() {
        /*new implement */
        linearLayoutTaskContainer.removeAllViews();
        taskDataModelList.clear();
        taskDataModelList.addAll(dataBaseAdapter.getAllTaskByAccountId(accountId));
        for (TaskDataModel taskDataModel : taskDataModelList) {
            View view = layoutInflater.inflate(R.layout.row_task_list, null);
            CheckBox checkBoxTask = (CheckBox) view.findViewById(R.id.checkBox_task);
            TextView taskSubject = (TextView) view.findViewById(R.id.tv_task_subject);
            TextView taskContact = (TextView) view.findViewById(R.id.tv_task_contact);
            TextView taskImage = (TextView) view.findViewById(R.id.tv_task_owner_image);
            TextView taskPriority = (TextView) view.findViewById(R.id.tv_task_priority);

            if (taskDataModel.getTaskStatus().equals("Completed")) {
                checkBoxTask.setChecked(true);

            }
            taskPriority.setText(taskDataModel.getTaskPriority());
            taskContact.setText(taskDataModel.getTaskContact());
            taskImage.setText(taskDataModel.getTaskOwner().substring(0, 2).toUpperCase());
            taskSubject.setText(taskDataModel.getTaskSubject());
            linearLayoutTaskContainer.addView(view);

        }
    }




}
