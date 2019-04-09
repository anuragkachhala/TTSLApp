package com.software.ttsl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.software.ttsl.Adapter.CustomListAdapter;
import com.software.ttsl.Request.AccountDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.EmployConstantUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = CustomListActivity.class.getName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.list_view)
    ListView listView;

    @BindView(R.id.tv_msg)
    TextView textViewMsg;

    CustomListAdapter customListAdapter;
    String[] listItems = new String[]{};
    String[] listItemsKey = new String[]{};
    String title;
    private String selectedItem, selectedItemKey;
    private DataBaseAdapter dataBaseAdapter;
    private List<AccountDataModel> accountDataModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        Intent intent = getIntent();
        title = intent.getStringExtra(EmployConstantUtil.TITLE);
        listItems = intent.getStringArrayExtra(EmployConstantUtil.ITEM_LIST);
        listItemsKey = intent.getStringArrayExtra(EmployConstantUtil.ITEM_LIST_KEY);


        if (listItems == null || listItems.length == 0) {
            textViewMsg.setVisibility(View.VISIBLE);
        } else {

            customListAdapter = new CustomListAdapter(this, R.id.tv_list_item, listItems);
            listView.setAdapter(customListAdapter);
            listView.setOnItemClickListener(this);
        }

        toolbar.setTitle(title);


    }


    private void backActivity(String selectedItem) {
        Intent intent = new Intent();
        intent.putExtra(EmployConstantUtil.SELECTED_ITEM, selectedItem);
        intent.putExtra(EmployConstantUtil.SELECTED_ITEM_KEY, selectedItemKey);
        intent.putExtra(EmployConstantUtil.TITLE, title);
        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        selectedItem = listItems[position];

        if(listItemsKey[position]!=null) {
            selectedItemKey = listItemsKey[position];
        }

        Toast.makeText(this, selectedItem + " clicked ", Toast.LENGTH_SHORT).show();
        backActivity(selectedItem);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
