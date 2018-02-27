package com.example.user.xmlpursingdemo1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 02/27/18.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {


    private ArrayList<Data> dataArrayList = new ArrayList<>();
    private Context ct;

    public ItemAdapter(Context ct, ArrayList<Data> data) {
        this.ct = ct;
        this.dataArrayList = data;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(ct).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        holder.tv.setText("Title : \n"+dataArrayList.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ct, DescriptionActivity.class);
                Bundle bd=new Bundle();
                bd.putString("Description",dataArrayList.get(position).getDecription());
                intent.putExtras(bd);
                ct.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;
        private View v;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
            v = itemView;

        }
    }
}
