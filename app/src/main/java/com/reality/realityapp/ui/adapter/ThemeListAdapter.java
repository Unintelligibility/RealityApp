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
import android.widget.TextView;

import com.reality.realityapp.R;
import com.reality.realityapp.bean.ThemeItem;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.Map;

/**
 * Created by 铠联 on 2018/1/30.
 */

public class ThemeListAdapter extends RecyclerView.Adapter<ThemeListAdapter.NewsListItemViewHolder> {

    private Map<String,ThemeItem> themItems;
    private Context context;
    private LayoutInflater inflater;

    private OnItemClickListener onItemClickListener;
    private Target target;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onClick(View view, int position);
    }

    public ThemeListAdapter(Context context, Map<String,ThemeItem> themItems) {
        this.context = context;
        this.themItems = themItems;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public NewsListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.theme_item, parent, false);
        return new NewsListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final NewsListItemViewHolder holder, final int position) {
        ThemeItem themItem = themItems.get(String.valueOf(position));

        Log.d("picture", "picture: "+themItem.getPic_url());
        target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.d("picture", "pic-loaded: ");
                if (android.os.Build.VERSION.SDK_INT > 15) {
                    holder.themeView.setBackground(new BitmapDrawable(context.getResources(), bitmap));
                } else {
                    holder.themeView.setBackgroundDrawable(new BitmapDrawable(context.getResources(), bitmap));
                }
            }

            @Override
            public void onBitmapFailed(final Drawable errorDrawable) {
                Log.d("picture", "pic-failure: ");
            }

            @Override
            public void onPrepareLoad(final Drawable placeHolderDrawable) {

            }
        };
        Picasso.with(context)
                .load(themItem.getPic_url())
                .into(target);

        holder.titleTv.setText(themItem.getTheme_title());
        holder.timeTv.setText(themItem.getTime());
//        holder.reliabilityTv.setText(themItem.getReliability());

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
        return themItems.size();
    }

    class NewsListItemViewHolder extends RecyclerView.ViewHolder {

        public View themeView;
        public TextView titleTv;
        public TextView timeTv;

        public NewsListItemViewHolder(View itemView) {
            super(itemView);

            themeView = itemView.findViewById(R.id.id_bg_theme);
            titleTv = (TextView) itemView.findViewById(R.id.id_tv_title);
            timeTv= (TextView) itemView.findViewById(R.id.id_tv_time);

        }
    }
}
