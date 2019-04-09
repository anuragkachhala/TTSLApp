package com.software.ttsl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.software.ttsl.Adapter.LocationSearchAdapter;
import com.software.ttsl.Response.FormDropDown.DropDownDataModel;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Utils.DialogUtitlity;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.Utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.software.ttsl.AddLeadActivity.KEY_CITY;
import static com.software.ttsl.AddLeadActivity.KEY_COUNTRY;
import static com.software.ttsl.AddLeadActivity.KEY_LOCATION;
import static com.software.ttsl.AddLeadActivity.KEY_STATE;

public class LocationSearchActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener, LocationSearchAdapter.LocationListAdapterListener {

    private static final String TAG = LocationSearchActivity.class.getName();

    public static final String LOCATION_KEY ="KEY";
    public static final String LOCATION_VALUE="VALUE";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_search_vessel)
    EditText editTextSearchVessel;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.iv_clear_search)
    ImageView imageViewClearSearch;

    private SessionManager sessionManager;
    private DropDownDataModel dropDownDataModel;
    private List<DropDownDataModel> dropDownDataModelList = new ArrayList<DropDownDataModel>();
    private List<DropDownDataModel> dropDownDataModelListFilter = new ArrayList<DropDownDataModel>();
    private LocationSearchAdapter locationSearchAdapter;
    private Intent intent;
    private String locationType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_search);
        ButterKnife.bind(this);



        SessionManager.setContext(getApplicationContext());
        sessionManager = SessionManager.getInstance();

        intent = getIntent();
        locationType = intent.getStringExtra(KEY_LOCATION);

        setToolbar();
        setClickListener();

        locationSearchAdapter = new LocationSearchAdapter(this, dropDownDataModelListFilter, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(locationSearchAdapter);


    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        switch (locationType){
            case KEY_STATE:
                toolbar.setTitle("Search State");
                break;
            case KEY_COUNTRY:
                toolbar.setTitle("Search Country");
                break;
            case KEY_CITY:
                toolbar.setTitle("Search City");
                break;
        }
    }


    private void setClickListener() {
        editTextSearchVessel.addTextChangedListener(this);
        imageViewClearSearch.setOnClickListener(this);
        editTextSearchVessel.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
    }


    private void getCityList(String searchQuery) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<DropDownDataModel>> listCall = null ;
        switch (locationType){
            case KEY_STATE:
                listCall = apiInterface.getState("Bearer " + sessionManager.getAccessToken(), searchQuery);
                break;
            case KEY_COUNTRY:
                listCall = apiInterface.getCountry("Bearer " + sessionManager.getAccessToken(), searchQuery);
                break;
            case KEY_CITY:
                listCall = apiInterface.getCities("Bearer " + sessionManager.getAccessToken(), searchQuery);
                break;
        }

        if(listCall!=null) {
            // Log.i(TAG, "inside getAll lead from server");
            listCall.enqueue(new Callback<List<DropDownDataModel>>() {
                @Override
                public void onResponse(Call<List<DropDownDataModel>> call, Response<List<DropDownDataModel>> response) {
                    DialogUtitlity.hideLoading();
                    // Log.i(TAG, "inside getAll lead from server");
                    int statusCode = response.code();
                    //Log.i(TAG, String.valueOf(statusCode));
                    if (statusCode == 200) {
                        if (response.body() instanceof List) {
                            dropDownDataModelList = response.body();
                            dropDownDataModelListFilter.clear();
                            //dropDownDataModelListFilter.addAll(dropDownDataModelList);
                            locationSearchAdapter.setList(dropDownDataModelList);
                            locationSearchAdapter.notifyDataSetChanged();


                        }

                    }

                }

                @Override
                public void onFailure(Call<List<DropDownDataModel>> call, Throwable t) {
                    DialogUtitlity.hideLoading();
                }
            });

        }

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.toString().length() == 3) {

            getCityList(charSequence.toString());
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (!editTextSearchVessel.getText().toString().trim().isEmpty()) {
            imageViewClearSearch.setVisibility(View.VISIBLE);
        } else {
            imageViewClearSearch.setVisibility(View.GONE);
        }
        if (editable.toString().length() > 3) {
            locationSearchAdapter.getFilter().filter(editable.toString());

        }if(editable.toString().length() == 0){
           locationSearchAdapter.setList(new ArrayList<DropDownDataModel>());
           locationSearchAdapter.notifyDataSetChanged();

        }


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_clear_search) {
            editTextSearchVessel.setText("");
        }

    }

    @Override
    public void onLocationSelected(String key,String value) {
        Intent intent = new Intent();
        intent.putExtra(LOCATION_KEY, key);
        intent.putExtra(LOCATION_VALUE, value);
        intent.putExtra(KEY_LOCATION, locationType);
        setResult(RESULT_OK, intent);
        finish();
    }


}
