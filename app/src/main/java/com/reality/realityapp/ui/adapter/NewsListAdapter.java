package com.reality.realityapp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 铠联 on 2018/1/30.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListItemViewHolder> {

    @Override
    public NewsListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(NewsListItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class NewsListItemViewHolder extends RecyclerView.ViewHolder{

        public NewsListItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
