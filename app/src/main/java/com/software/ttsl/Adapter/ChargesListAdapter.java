package com.software.ttsl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.software.ttsl.R;
import com.software.ttsl.model.GetQuotationChargesData;

import java.util.List;



public class ChargesListAdapter extends BaseAdapter{
    Context context;
    LayoutInflater inflater;
    List<GetQuotationChargesData> chargesDataList;

    public ChargesListAdapter(Context context, List<GetQuotationChargesData> chargesDataList) {
        this.context = context;
        this.chargesDataList = chargesDataList;
    }

    @Override
    public int getCount() {
        return chargesDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return chargesDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context)
                   .inflate(R.layout.charges_list_row,null);
        TextView mChargesType = (TextView)view.findViewById(R.id.charges_type);
        TextView mChargesAmount=(TextView)view.findViewById(R.id.charges_amount);
        GetQuotationChargesData chargesData = chargesDataList.get(position);
        String rupeeSymbol = context.getResources().getString(R.string.Rs);
        mChargesType.setText(chargesData.getChargesType());
        mChargesAmount.setText(rupeeSymbol+ " " +String.valueOf(chargesData.getChargesAmount()));

        return view;
    }
}
