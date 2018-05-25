package com.example.asus.yd1512qjddemo.ui.homepage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.yd1512qjddemo.R;
import com.example.asus.yd1512qjddemo.bean.AdBean;
import com.example.asus.yd1512qjddemo.inter.OnItemClickListener;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;

public class RvSecKillAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<AdBean.MiaoshaBean.ListBeanX> list;
    private LayoutInflater inflater;
    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    public RvSecKillAdapter(FragmentActivity activity, List<AdBean.MiaoshaBean.ListBeanX> list) {
        this.context=activity;
        this.list=list;
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.rvseckill_item, parent, false);
        SecKillViewHolder secKillViewHolder=new SecKillViewHolder(view);
        return secKillViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        SecKillViewHolder secKillViewHolder= (SecKillViewHolder) holder;
        AdBean.MiaoshaBean.ListBeanX listBeanX = list.get(position);
        String split = listBeanX.getImages().split("\\|")[0];
        secKillViewHolder.iv.setImageURI(split);

        secKillViewHolder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SecKillViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView iv;

        public SecKillViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);

        }
    }
}
