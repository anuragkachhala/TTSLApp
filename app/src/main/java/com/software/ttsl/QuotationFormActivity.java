package com.software.ttsl;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software.ttsl.Adapter.ViewPagerAdapter;
import com.software.ttsl.Fragment.CargoFirstFragment;
import com.software.ttsl.Fragment.CargoSecondFragment;
import com.software.ttsl.Fragment.CargoThirdFragment;
import com.software.ttsl.Fragment.NonSwipeableViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuotationFormActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

     
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.layout_step_view)
    LinearLayout mmStepViewLayout;
    
    @BindView(R.id.iv_step_view)
    ImageView mStepView;

    @BindView(R.id.btn_next)
     Button buttonNext;

    @BindView(R.id.btn_previous)
            Button buttonPre;

    @BindView(R.id.viewpager)
    NonSwipeableViewPager viewPager;

    CargoFirstFragment cargoFirstFragment;
    CargoSecondFragment cargoSecondFragment;
    CargoThirdFragment cargoThirdFragment;

    int total_count;
    private int current_page;
    private int pageCount;
    
    
    boolean nextEnable =false;
    public String labelNext,labelPre,labelFinish;

    ViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation_form);

        ButterKnife.bind(this);
        mStepView.setImageDrawable(getResources().getDrawable(R.drawable.step_first));

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpClickListener();
        setupViewPager(viewPager);




        pageCount = adapter.getCount();

        }

    private void setUpClickListener() {
        buttonPre.setOnClickListener(this);
        buttonNext.setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);
    }


    private void setupViewPager(ViewPager viewPager) {
        viewPager.setOffscreenPageLimit(4);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        cargoFirstFragment=new CargoFirstFragment();
        cargoSecondFragment=new CargoSecondFragment();
        cargoThirdFragment=new CargoThirdFragment();
        adapter.addFragment(cargoFirstFragment);
        adapter.addFragment(cargoSecondFragment);
        adapter.addFragment(cargoThirdFragment);
        total_count=adapter.getCount()-1;
        viewPager.setAdapter(adapter);



    }

    public void nextClick() {
        buttonNext.performClick();
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        int position = viewPager.getCurrentItem();
        switch (id){
            case R.id.btn_next:


                if(position==0){
                    nextEnable =  cargoFirstFragment.checkEntries();
                }
                else if(position==1){
                    nextEnable= cargoSecondFragment.checkEntries();
                }

                if(nextEnable)
                {
                    if (position < total_count) {
                        viewPager.setCurrentItem(position + 1);
                    } else {
                        // checkEntries();
                    }
                }
                break;
            case R.id.btn_previous:
                if(position==0){
                    super.onBackPressed();
                    }
                viewPager.setCurrentItem(current_page-1);

                break;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        current_page=position;
        labelNext= getResources().getString(R.string.btn_next);
        labelPre = getResources().getString(R.string.btn_previous);
        labelFinish = getResources().getString(R.string.btn_finish);
        if(position==0){
            buttonNext.setEnabled(true);
            buttonPre.setEnabled(true);
            buttonPre.setVisibility(View.VISIBLE);
            buttonNext.setText(labelNext);
            buttonPre.setText(labelPre);
            mStepView.setImageDrawable(getResources().getDrawable(R.drawable.step_first));

        }else if(position==pageCount-1){
            buttonNext.setEnabled(true);
            buttonPre.setEnabled(true);
            buttonPre.setVisibility(View.VISIBLE);
            buttonNext.setText(labelFinish);
            buttonPre.setText(labelPre);
            mStepView.setImageDrawable(getResources().getDrawable(R.drawable.step_third));

        }
        else {
            buttonPre.setEnabled(true);
            buttonPre.setEnabled(true);
            buttonPre.setVisibility(View.VISIBLE);
            buttonNext.setText(labelNext);
            buttonPre.setText(labelPre);

            mStepView.setImageDrawable(getResources().getDrawable(R.drawable.step_second));

        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
