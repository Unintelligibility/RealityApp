package com.reality.realityapp.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import com.squareup.picasso.Target;

import java.util.Map;

/**
 * Created by 铠联 on 2018/1/30.
 */

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.NewsListItemViewHolder> {

    private Map<String,NewsItem> newsItems;
    private Context context;
    private LayoutInflater inflater;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onClick(View view, int position);
    }

    public ThemeAdapter(Context context, Map<String,NewsItem> newsItems) {
        this.context = context;
        this.newsItems = newsItems;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public NewsListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.theme_item, parent, false);
        return new NewsListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final NewsListItemViewHolder holder, final int position) {
        NewsItem newsItem = newsItems.get(String.valueOf(position));

        Log.d("picture", "picture: "+newsItem.getPicture());
        Picasso.with(context)
                .load(newsItem.getPicture())
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        if (android.os.Build.VERSION.SDK_INT > 15) {
                            holder.themeView.setBackground(new BitmapDrawable(context.getResources(), bitmap));
                        } else {
                            holder.themeView.setBackgroundDrawable(new BitmapDrawable(context.getResources(), bitmap));
                        }
                    }

                    @Override
                    public void onBitmapFailed(final Drawable errorDrawable) {
                    }

                    @Override
                    public void onPrepareLoad(final Drawable placeHolderDrawable) {
                    }
                });

        holder.titleTv.setText(newsItem.getTitle());
        holder.sourceTv.setText(newsItem.getSource());
        holder.timeTv.setText(newsItem.getTime());
        holder.reliabilityTv.setText(newsItem.getReliability());

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

        public View themeView;
        public TextView titleTv;
        public TextView sourceTv;
        public TextView timeTv;
        public TextView reliabilityTv;

        public NewsListItemViewHolder(View itemView) {
            super(itemView);

            themeView = itemView.findViewById(R.id.id_bg_theme);
            titleTv = (TextView) itemView.findViewById(R.id.id_tv_title);
            sourceTv= (TextView) itemView.findViewById(R.id.id_tv_source);
            timeTv= (TextView) itemView.findViewById(R.id.id_tv_time);
            reliabilityTv = (TextView) itemView.findViewById(R.id.id_tv_reliability);

        }
    }
}
