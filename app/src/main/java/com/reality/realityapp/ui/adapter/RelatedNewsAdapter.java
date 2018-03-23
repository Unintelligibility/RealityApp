package com.reality.realityapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.reality.realityapp.R;
import com.reality.realityapp.bean.NewsItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 铠联 on 2018/3/19.
 * 其实本来只需要NewsListAdapter一个适配器，但是后来调整了数据接口，新旧数据结构不同，
 * 所以新写一个适配器
 */

public class RelatedNewsAdapter extends RecyclerView.Adapter<RelatedNewsAdapter.RelatedNewsViewHolder> {

    private List<NewsItem> newsItems;
    private Context context;
    private LayoutInflater inflater;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onClick(View view, int position);
    }

    public RelatedNewsAdapter(Context context, List<NewsItem> newsItems) {
        this.context = context;
        this.newsItems = newsItems;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RelatedNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.news_item, parent, false);
        return new RelatedNewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RelatedNewsViewHolder holder, final int position) {
        NewsItem newsItem = newsItems.get(position);

        Log.d("relate-picture", "relate-picture: "+newsItem.getPicture());
        Picasso.with(context)
                .load(newsItem.getPicture())
                .placeholder(R.drawable.item_image2)
                .into(holder.imageView);

        holder.titleTv.setText(newsItem.getTitle());
        holder.sourceTv.setText(newsItem.getSource());
        holder.timeTv.setText(newsItem.getTime());
        if (newsItem.getClickbait()==1){
            holder.clickbaitIv.setVisibility(View.VISIBLE);
        }

        if (onItemClickListener!=null){
            holder.titleTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(v,position);
                }
            });
            holder.imageView.setOnClickListener(new View.OnClickListener() {
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

    class RelatedNewsViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView titleTv;
        public TextView sourceTv;
        public TextView timeTv;
        public ImageView clickbaitIv;

        public RelatedNewsViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.id_iv_image);
            titleTv = (TextView) itemView.findViewById(R.id.id_tv_title);
            sourceTv= (TextView) itemView.findViewById(R.id.id_tv_source);
            timeTv= (TextView) itemView.findViewById(R.id.id_tv_time);
            clickbaitIv = (ImageView) itemView.findViewById(R.id.id_clickbait_image);

        }
    }
}
