package com.example.roguinn;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<MyItem> mList;

    public MyAdapter(List<MyItem> list) {
        mList = list;
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull final MyAdapter.ViewHolder holder, int position) {
        MyItem myItem = mList.get(position);
        holder.mImageview.setImageResource(myItem.getImageId());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mView;
        public ImageView mImageview;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageview = itemView.findViewById(R.id.image_item);
        }
    }
}
