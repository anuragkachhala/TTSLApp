package com.software.ttsl.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software.ttsl.R;
import com.software.ttsl.model.ServicesData;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder> {
    Context context;
    List<ServicesData> servicesDataList;


    public ServicesAdapter(Context context, List<ServicesData> servicesDataList) {
        this.context = context;
        this.servicesDataList = servicesDataList;
    }

    @Override
    public ServicesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_data_row, parent, false);

        return new ServicesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ServicesViewHolder holder, int position) {
        ServicesData  servicesData =servicesDataList.get(position);
        holder.title.setText(servicesData.getTitle());
        holder.thumbnail.setImageDrawable(context.getResources().getDrawable(servicesData.getThumbnail()));
        holder.discription.setText(servicesData.getDiscription());

    }

    @Override
    public int getItemCount() {
        return servicesDataList.size();
    }

    public class ServicesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, discription;
        public ImageView thumbnail,toggleDiscription;
        public LinearLayout discriptionLayout;

        public ServicesViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            toggleDiscription = (ImageView) view.findViewById(R.id.toggle_discription);
            discriptionLayout = (LinearLayout) view.findViewById(R.id.layout_discription);
            discription = (TextView) view.findViewById(R.id.tv_service_discription);
            toggleDiscription.setOnClickListener(this);
           // toggleDiscription.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_arrow_down));


        }

       @Override
        public void onClick(View view) {
              float rotation = toggleDiscription.getRotation();
             toggleDiscription.animate().rotation(rotation + 180).start();
             if(discriptionLayout.getVisibility()==View.VISIBLE){
                discriptionLayout.setVisibility(View.GONE);

                //toggleDiscription.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_arrow_down));
                }else {
                discriptionLayout.setVisibility(View.VISIBLE);
                //toggleDiscription.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_arrow_up));
            }

            ;



        }
    }
}
