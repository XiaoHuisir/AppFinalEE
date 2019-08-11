package com.example.a86066.appfinale.adaptereding;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a86066.appfinale.R;
import com.example.a86066.appfinale.jsoned.Itemed;

import java.util.ArrayList;
import java.util.List;

public class FragAdapter extends RecyclerView.Adapter<FragAdapter.ViewHolder> {
    List<Itemed.DataBean.DatasBean> list =new ArrayList<>();

    public FragAdapter(List<Itemed.DataBean.DatasBean> list) {

if (list!=null){
    this.list = list;

}
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.cyc_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.text.setText(list.get(position).getDesc());
        Context context = holder.image.getContext();
        String url = list.get(position).getEnvelopePic();
        Glide.with(context).load(url).into(holder.image);
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
            image=itemView.findViewById(R.id.image_cyc);
            text=itemView.findViewById(R.id.text_cyc);
        }
    }
}
