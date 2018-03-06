package com.reality.realityapp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.reality.realityapp.R;
import com.reality.realityapp.bean.NewsItem;
import com.reality.realityapp.ui.activity.NewsInfoActivity;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * Created by 铠联 on 2018/1/30.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListItemViewHolder> {

    private Map<String,NewsItem> newsItems;
    private Context context;
    private LayoutInflater inflater;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onClick(View view,int position);
    }

    public NewsListAdapter(Context context, Map<String,NewsItem> newsItems) {
        this.context = context;
        this.newsItems = newsItems;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public NewsListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.news_item, parent, false);
        return new NewsListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsListItemViewHolder holder, final int position) {
        NewsItem newsItem = newsItems.get(String.valueOf(position));

        Picasso.with(context)
                .load(newsItem.getPicture())
                .placeholder(R.drawable.item_image)
                .into(holder.imageView);

        holder.titleTv.setText(newsItem.getTitle());
        holder.sourceTv.setText(newsItem.getSource());
        holder.timeTv.setText(newsItem.getTime());

        if (onItemClickListener!=null){
            holder.titleTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(v,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    class NewsListItemViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView titleTv;
        public TextView sourceTv;
        public TextView timeTv;

        public NewsListItemViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.id_iv_image);
            titleTv = (TextView) itemView.findViewById(R.id.id_tv_title);
            sourceTv= (TextView) itemView.findViewById(R.id.id_tv_source);
            timeTv= (TextView) itemView.findViewById(R.id.id_tv_time);



        }
    }
}
