package com.example.a86066.appfinalee.tabandvp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a86066.appfinalee.R;

import java.util.ArrayList;
import java.util.List;

public class Cycadagter extends RecyclerView.Adapter<Cycadagter.ViewHolder> {
    private  List<Demo.DataBean.DatasBean> list =new ArrayList<>();

    public Cycadagter(List<Demo.DataBean.DatasBean> list) {
        if (list!=null){
            this.list = list;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.cyc_ding, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.image.getContext();
        String url = list.get(position).getEnvelopePic();
        Glide.with(context).load(url).into(holder.image);
        holder.text.setText(list.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;
        public ViewHolder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image_ding);
            text=itemView.findViewById(R.id.text_ding);
        }
    }
}
