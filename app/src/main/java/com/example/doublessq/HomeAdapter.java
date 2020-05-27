package com.example.doublessq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doublessq.ItemBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Context context;


    List<ItemBean>  itemBeanList = new ArrayList<>();


    public HomeAdapter(Context context) {
        this.context = context;
    }

    public void setItemBeanList(List<ItemBean> itemBeanList) {
        this.itemBeanList.addAll(itemBeanList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ItemBean itemBean = itemBeanList.get(position);
        holder.date.setText(itemBean.date);

        holder.one.setText(itemBean.one);
        holder.two.setText(itemBean.two);
        holder.three.setText(itemBean.three);
        holder.four.setText(itemBean.four);
        holder.five.setText(itemBean.five);
        holder.six.setText(itemBean.six);

        holder.blue.setText(itemBean.blue);


    }

    @Override
    public int getItemCount() {
        return itemBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView one,two,three,four,five,six,blue;
        TextView date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);

            one = itemView.findViewById(R.id.one);
            two = itemView.findViewById(R.id.two);
            three = itemView.findViewById(R.id.three);
            four = itemView.findViewById(R.id.four);
            five = itemView.findViewById(R.id.five);
            six = itemView.findViewById(R.id.six);
            blue = itemView.findViewById(R.id.blue);
        }
    }
}
