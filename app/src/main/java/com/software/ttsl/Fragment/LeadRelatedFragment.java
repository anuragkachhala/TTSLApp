package com.software.ttsl.Fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software.ttsl.LeadDetailActivity;
import com.software.ttsl.R;
import com.software.ttsl.Request.CallDataModel;
import com.software.ttsl.Request.EventDataModel;
import com.software.ttsl.Request.LeadDataModel;
import com.software.ttsl.Request.TaskDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.CustomModuleInflaterUtil;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.Utils.Imageutils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;
import static com.software.ttsl.Utils.EmployConstantUtil.LEAD_TYPE;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeadRelatedFragment extends BaseUtilFragment implements View.OnClickListener {

    public static final String TAG = LeadRelatedFragment.class.getName();


    @BindView(R.id.tv_lead_name)
    TextView textViewLeadName;

    @BindView(R.id.tv_lead_email_id)
    TextView textViewLeadEmailId;

    @BindView(R.id.tv_lead_mobile_no)
    TextView textViewLeadMobileNo;


    @BindView(R.id.iv_lead_image)
    ImageView imageViewLeadimage;

    @BindView(R.id.tv_lead_owner)
    TextView textViewLeadOwner;

    @BindView(R.id.iv_add_notes)
    ImageView imageViewAddNotes;

    @BindView(R.id.iv_add_attachment)
    ImageView imageViewAddAttachment;

    @BindView(R.id.employee_iv_add_task)
    ImageView imageViewAddTask;

    @BindView(R.id.iv_add_call)
    ImageView imageViewAddCall;

    @BindView(R.id.employee_iv_add_event)
    ImageView imageViewAddEvent;


    @BindView(R.id.task_container)
    LinearLayout linearLayoutTaskContainer;

    @BindView(R.id.event_container)
    LinearLayout linearLayoutEventContainer;

    @BindView(R.id.call_container)
    LinearLayout linearLayoutCallContainer;

    Long leadId;
    private List<TaskDataModel> taskDataModelList = new ArrayList<>();
    private List<EventDataModel> eventDataModelList = new ArrayList<>();
    private List<CallDataModel> callDataModelList = new ArrayList<>();
    private DataBaseAdapter dataBaseAdapter;
    private LeadDataModel leadDataModel;
    private byte[] leadImageByte;
    private LeadDetailActivity leadDetailActivity;
    private LayoutInflater layoutInflater;
    private CustomModuleInflaterUtil customModuleInflaterUtil;


    public LeadRelatedFragment() {
        // Required empty public constructorSolution
    }






    @Override
    public BaseUtilFragment provideYourFragment() {
        return new LeadRelatedFragment();
    }

    @Override
    public View provideYourFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lead_related, parent, false);

        ButterKnife.bind(this, view);
        layoutInflater = LayoutInflater.from(getContext());
        dataBaseAdapter = new DataBaseAdapter(getContext());

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            leadId = bundle.getLong(EmployConstantUtil.KEY_LEAD_ID, 0);
        }

        Log.v(TAG, "lead related fragment   " + leadId);


        setClickListener();


        leadDetailActivity = (LeadDetailActivity) getActivity();
        customModuleInflaterUtil = new CustomModuleInflaterUtil(getContext(),this);
        updateUI();
        customModuleInflaterUtil.setAllTask(LEAD_TYPE,leadId,linearLayoutTaskContainer);
        customModuleInflaterUtil.setAllEvent(LEAD_TYPE,leadId,linearLayoutEventContainer);
        customModuleInflaterUtil.setAllCall(LEAD_TYPE,leadId,linearLayoutCallContainer);
        //setAllLeadEvent();
        //setAllLeadCall();


        return view;
    }


    private void setLeadImage(){
        String fileName = leadDetailActivity.getLeadImage();
        if(fileName!=null) {
            imageViewLeadimage.setImageBitmap(convertFileToBitmap(fileName));
        }
    }


    private Bitmap convertFileToBitmap(String filePath ){
        File file = new File(filePath);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inSampleSize = 2;
        options.inTempStorage = new byte[16 * 1024];

        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(),options);

        if(bitmap!=null)
            return bitmap;
        else
            return null;
    }


    private void updateUI(){
        leadDataModel = leadDetailActivity.getLeadDate();
        setLeadImage();
        setLeadData();

    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    /*private void getLeadById() {
        DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(getContext());
        dataBaseAdapter.openDataBase();
        leadDataModel= dataBaseAdapter.getLeadByID(leadId);
        setLeadData();
    }
*/


    public void image_attachment(Bitmap file) {

        imageViewLeadimage.setImageBitmap(file);


    }


    @Override
    public void startActivityForResult1(Intent intent, int requestCode){
        startActivityForResult(intent,requestCode);
    }
    private void setLeadData() {
        leadImageByte = leadDataModel.getUploadedInputStream();
        textViewLeadName.setText((leadDataModel.getSalutation() + " " + DateAndTimeUtil.firstLatterUpper(leadDataModel.getFirstName()) + " " + DateAndTimeUtil.firstLatterUpper(leadDataModel.getLastName())).trim());
        textViewLeadOwner.setText(leadDataModel.getLeadOwner());
        if (!leadDataModel.getEmail().isEmpty()) {
            textViewLeadEmailId.setText(leadDataModel.getEmail());
        }
        if (!leadDataModel.getPhone().isEmpty()) {
            textViewLeadMobileNo.setText(leadDataModel.getPhone());
        }
        if (leadImageByte != null) {

            imageViewLeadimage.setImageBitmap(convertImageToBitMap(leadImageByte));

        }

    }


    private Bitmap convertImageToBitMap(byte[] leadImageByte) {

        return BitmapFactory.decodeByteArray(leadImageByte, 0, leadImageByte.length);
    }

    private void setClickListener() {
        imageViewAddNotes.setOnClickListener(this);
        imageViewAddAttachment.setOnClickListener(this);
        imageViewAddCall.setOnClickListener(this);
        imageViewAddEvent.setOnClickListener(this);
        imageViewAddTask.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.iv_add_notes:
                break;
            case R.id.iv_add_attachment:
                break;
            case R.id.iv_add_call:
                break;
            case R.id.employee_iv_add_event:
                break;
            case R.id.employee_iv_add_task:
                break;

        }

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




    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {
                customModuleInflaterUtil.setAllTask(LEAD_TYPE,leadId,linearLayoutTaskContainer);

            }
        }
            }
    private String checkCallDuration(long callDuration) {

        return callDuration ==0 ? "--:--" : DateAndTimeUtil.longToTime(callDuration,EmployConstantUtil.TIME_FORMAT_DURATION);
    }

}
