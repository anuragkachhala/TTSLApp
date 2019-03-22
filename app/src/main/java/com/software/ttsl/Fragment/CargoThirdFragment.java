package com.software.ttsl.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.software.ttsl.Adapter.ChargesListAdapter;
import com.software.ttsl.R;
import com.software.ttsl.model.GetQuotationChargesData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CargoThirdFragment extends Fragment {


    @BindView(R.id.charges_list)
    ListView mChargesList;
    String[] chargesList;
    ChargesListAdapter chargesListAdapter;
    GetQuotationChargesData getQuotationChargesData;
    List<GetQuotationChargesData> chargesDataList = new ArrayList<>();

    public CargoThirdFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cargo_third, container, false);
         ButterKnife.bind(this,view);
        chargesList = getResources().getStringArray(R.array.charges_list);
        prepareDate();
        chargesListAdapter = new ChargesListAdapter(getContext(), chargesDataList);
        mChargesList.setAdapter(chargesListAdapter);


        return view;
    }

    private void prepareDate() {
        for (String chargesType : chargesList) {
            getQuotationChargesData = new GetQuotationChargesData();
            getQuotationChargesData.setChargesType(chargesType);
            getQuotationChargesData.setChargesAmount(454564);
            chargesDataList.add(getQuotationChargesData);

        }
    }

}
