package com.software.ttsl.Fragment;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.software.ttsl.Adapter.LeadListFragmentAdapter;
import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.LeadDetailActivity;
import com.software.ttsl.LeadListActivity;
import com.software.ttsl.R;
import com.software.ttsl.Request.LeadDataModel;
import com.software.ttsl.Response.ImageResponse;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.Utils.Imageutils;
import com.software.ttsl.model.AddLeadData;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddLeadFragment extends Fragment implements ItemClickListener {

    public static final String TAG = AddLeadFragment.class.getName();


    public static final int ORDERBY = 100;

    Long leadId;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    LeadListFragmentAdapter leadListFragmentAdapter;

    List<ImageResponse> imageResponses1 = new ArrayList<>();
    List<AddLeadData> addLeadDataList = new ArrayList<>();
    List<LeadDataModel> leadDataModelList = new ArrayList<>();
    DataBaseAdapter dataBaseAdapter;
    private String createdTime;
    private String createdTimeChanged;

    public AddLeadFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_lead, container, false);
        ButterKnife.bind(this, view);


        dataBaseAdapter = new DataBaseAdapter(getContext());

        leadListFragmentAdapter = new LeadListFragmentAdapter(getContext(), leadDataModelList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(leadListFragmentAdapter);
        recyclerView.setHasFixedSize(true);

        updateUI();
        getLeadImage();
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


    }

    private void updateUI() {
        leadDataModelList.clear();
        leadDataModelList.addAll(dataBaseAdapter.getAllLead());
        if (!leadDataModelList.isEmpty()) {
            createdTime = DateAndTimeUtil.longToDate(leadDataModelList.get(0).getCreatedDate());
            for (LeadDataModel leadDataModel : leadDataModelList) {
                createdTimeChanged = DateAndTimeUtil.longToDate(leadDataModel.getCreatedDate());
                if (!createdTime.equals(createdTimeChanged)) {
                    leadDataModel.setHeaderShow(true);
                    createdTime = createdTimeChanged;
                }

                Log.e(TAG, " " + leadDataModel.isHeaderShow());
            }

            leadDataModelList.get(0).setHeaderShow(true);
        }


        leadListFragmentAdapter.notifyDataSetChanged();
    }


    public void getLeadImage() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<ImageResponse>> image = apiInterface.getImage(-1L);
        Log.e(TAG, "Inside get Lead Image");
        image.enqueue(new Callback<List<ImageResponse>>() {
            @Override
            public void onResponse(Call<List<ImageResponse>> call, Response<List<ImageResponse>> response) {
                int statusCode = response.code();
                Log.e(TAG, String.valueOf(statusCode));
                if (statusCode == 200) {
                    if (response.body() instanceof List) {

                            List<ImageResponse> imageResponses = response.body();
                            int count = 0;
                            for (ImageResponse imageResponse : imageResponses) {
                                createDirectoryAndSaveFile(Imageutils.StringToBitMap(imageResponse.getImageFile()), "leadImage" + imageResponse.getImageId(), imageResponse.getImageId());
                                count++;
                            }


                            dataBaseAdapter.addImage(imageResponses1);




                    }
                }
            }

            @Override
            public void onFailure(Call<List<ImageResponse>> call, Throwable t) {
                Log.e(TAG, t + "");


            }
        });


    }
     String iname;
    //createDirectoryAndSaveFile
    private void createDirectoryAndSaveFile(Bitmap imageToSave, String fileName,long id) {

       /* File direct = new File(Environment.getExternalStorageDirectory() + "/ImageAttach");

        if (!direct.exists()) {
            File wallpaperDirectory = new File("/sdcard/ImageAttach/");
            wallpaperDirectory.mkdirs();
        }

        File file = new File(new File("/sdcard/ImageAttach/"), fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();

        System.out.println(root +" Root value in saveImage Function");

        File myDir = new File(root + "/Screenshots");



        if (!myDir.exists()) {

            myDir.mkdirs();

        }



        Random generator = new Random();

        int n = 10000;

        n = generator.nextInt(n);

        iname = "Image-" + n + ".jpg";

        File file = new File(myDir, iname);

        if (file.exists())

            file.delete();

        try {

            FileOutputStream out = new FileOutputStream(file);

            imageToSave.compress(Bitmap.CompressFormat.JPEG, 90, out);

            out.flush();

            out.close();

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        String path = file.getPath();
        ImageResponse imageResponse = new ImageResponse();
        imageResponse.setImageFile(path);
        imageResponse.setImageId(id);
        imageResponses1.add(imageResponse);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1000:
                    //TODO get intent data
                    updateUI();
                    break;


                case ORDERBY:
                    String data1 = data.getStringExtra("OrderBy");
                    break;


            }
        }
    }


    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions((LeadListActivity) getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }

    private boolean checkPermission(String permission){
        if (Build.VERSION.SDK_INT >= 23) {
            int result = ContextCompat.checkSelfPermission(getContext(), permission);
            if (result == PackageManager.PERMISSION_GRANTED){
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }



    @Override
    public void onItemClick(int position) {
        leadId = leadDataModelList.get(position).getLeadId();

        Log.v(TAG, "LEAD ID IN ADD LEAD FRAGMENT  " + leadId);
        OpenLeadDetailActivity(leadId);
    }

    private void OpenLeadDetailActivity(Long leadId) {
        Intent intent = new Intent(getContext(), LeadDetailActivity.class);
        intent.putExtra(EmployConstantUtil.KEY_LEAD_ID, leadId);
        startActivityForResult(intent, 1000);


    }
}

