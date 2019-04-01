package com.software.ttsl;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.software.ttsl.Fragment.LeadDetailsFragment;
import com.software.ttsl.Fragment.LeadRelatedFragment;
import com.software.ttsl.Interfacce.AlertDialogCallback;
import com.software.ttsl.Request.LeadDataModel;
import com.software.ttsl.Response.ImageResponse;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.ConnectivityReceiver;
import com.software.ttsl.Utils.DialogUtitlity;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.Utils.Imageutils;
import com.software.ttsl.model.AddLeadData;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeadDetailActivity extends AppCompatActivity implements AlertDialogCallback, Callback<String>, Imageutils.ImageAttachmentListener {

    public static final String TAG = LeadDetailActivity.class.getName();
    private static final int REQUEST_CODE_EDIT_LEAD = 2000;


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    Long leadId;

    private DataBaseAdapter dataBaseAdapter;
    private AddLeadData addLeadData;
    private String leadImage;
    private LeadDataModel leadDataModel;
    private boolean isSync;
    private Imageutils imageutils;
    private Bitmap bitmap;
    private String file_name;
    private byte leadImg[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_detail);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageutils = new Imageutils(this);

        Intent intent = getIntent();
        leadId = intent.getLongExtra(EmployConstantUtil.KEY_LEAD_ID, 0);
        Log.v(TAG, "LEAD ID IN LEAD DETAIL ACTIVITY  " + leadId);


        dataBaseAdapter = new DataBaseAdapter(this);
        updateUI();


        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }


    public LeadDataModel getLeadDate() {

        return leadDataModel;
    }

    public String getLeadImage() {
        return leadImage;
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        updateUI();
    }

    public void updateUI() {
        leadDataModel = dataBaseAdapter.getLeadByID(leadId);
        leadImage = dataBaseAdapter.getImage(leadId);
        isSync = leadDataModel.isSync();

    }


    private void setupViewPager(ViewPager viewPager) {
        Bundle bundle = new Bundle();
        bundle.putLong(EmployConstantUtil.KEY_LEAD_ID, leadId);
        LeadRelatedFragment leadRelatedFragment = new LeadRelatedFragment();

        leadRelatedFragment.setArguments(bundle);
        LeadDetailsFragment leadDetailsFragment = new LeadDetailsFragment();
        leadDetailsFragment.setArguments(bundle);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(leadRelatedFragment, "Related");
        adapter.addFragment(leadDetailsFragment, "Detail");

        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lead_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();
                break;
            case R.id.action_edit:
                Intent intent = new Intent(LeadDetailActivity.this, AddLeadActivity.class);
                intent.putExtra(EmployConstantUtil.KEY_LEAD_ID, leadId);
                Log.v(TAG, "SEND LEAD ID TO EDIT LEAD  " + leadId);
                startActivityForResult(intent, REQUEST_CODE_EDIT_LEAD);
                break;

            case R.id.action_refresh:
                DialogUtitlity.showLoading(this);
                new Thread() {
                    public void run() {
                        try {
                            // sleep the thread, whatever time you want.
                            updateUI();

                            sleep(1000);
                        } catch (Exception e) {
                        }
                        DialogUtitlity.dismiss();
                    }
                }.start();

                break;

            case R.id.action_delete:
                DeleteLead();
                break;

            case R.id.action_upload_photo:
                Toast.makeText(this, "image clicked", Toast.LENGTH_SHORT).show();
                imageutils.imagepicker(1);
                break;
            case R.id.action_convert_lead:

        }

        return super.onOptionsItemSelected(item);
    }

    private boolean checkConnection() {


        boolean isConnected = ConnectivityReceiver.isConnected();
        if (!isConnected) {

            return false;
        }

        return true;
    }


    private void DeleteLead() {

        if (!isSync) {
            if (dataBaseAdapter.deleteLead(leadId)) {
                dataBaseAdapter.deleteImage(leadId);
                Toast.makeText(this, "Lead has been Deleted not sync", Toast.LENGTH_SHORT).show();

                AlertDialogManager.showAlertDialogMessage(this, "", "Are you sure to delete Lead", false, this);

            } else {
                Toast.makeText(this, " Please try again later ", Toast.LENGTH_SHORT).show();
            }

        } else {

            if (checkConnection()) {
                AlertDialogManager.showAlertDialogMessage(this, "", "Are you sure to delete Lead", false, this);
            } else {
                Toast.makeText(this, " Please check your Internet connection ", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void alertDialogCallbackOk() {

        if(!isSync)
        {
            finishActivity();
        }else {
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);

            final Call<String> response = apiService.removeLead(leadId);
            response.enqueue(this);
        }

    }

    @Override
    public void alertDialogCallbackCancel() {

    }


    private void finishActivity(){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();

    }
    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        int statusCode = response.code();
        if (statusCode == 200) {
            if (dataBaseAdapter.deleteLead(leadId)) {
                dataBaseAdapter.deleteImage(leadId);
                Toast.makeText(this, "Lead has been Deleted ", Toast.LENGTH_SHORT).show();
                finishActivity();
            } else {
                Toast.makeText(this, "Please try again later ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        Log.d("testing", "" + t.getMessage());
    }

    @Override
    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {
        this.bitmap = file;
        this.file_name = filename;
        String path = Environment.getExternalStorageDirectory() + File.separator + "ImageAttach" + File.separator + "ttls" + File.separator;
        ;
        ImageResponse imageResponse = new ImageResponse();
        imageResponse.setImageId(leadId);
        imageResponse.setImageFile(path + file_name);
        if (leadImage == null) {
            imageutils.createImage(file, filename, path, false, 0);
            dataBaseAdapter.addImage(imageResponse);

        } else {
            imageutils.createImage(file, filename, path, true, 0);
            dataBaseAdapter.updateImage(imageResponse);

        }

        // dataBaseAdapter.updateImage(imageResponse);
        Fragment page = getSupportFragmentManager().getFragments().get(0);
        if (viewPager.getCurrentItem() == 0 && page != null) {
            ((LeadRelatedFragment) page).image_attachment(bitmap);
        }

        leadImage = path + file_name;
    }


    private String convertImageToBase64String() {
        if (convertImageToByteArray() != null) {
            return Base64.encodeToString(convertImageToByteArray(), Base64.NO_WRAP);
        } else return null;
    }

    private byte[] convertImageToByteArray() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream);
            leadImg = byteArrayOutputStream.toByteArray();
            return leadImg;
        } else {
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_EDIT_LEAD:
                    updateUI();
                    viewPager.setCurrentItem(0);
                    break;
                case RESULT_CANCELED:
                    viewPager.setCurrentItem(0);
                    break;
                default:
                    imageutils.onActivityResult(requestCode, resultCode, data);
            }

        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}