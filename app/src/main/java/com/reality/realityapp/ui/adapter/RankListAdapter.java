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
import com.reality.realityapp.bean.RankItem;
import com.reality.realityapp.bean.RankItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 铠联 on 2018/3/19.
 * 其实本来只需要NewsListAdapter一个适配器，但是后来调整了数据接口，新旧数据结构不同，
 * 所以新写一个适配器
 */

public class RankListAdapter extends RecyclerView.Adapter<RankListAdapter.RelatedNewsViewHolder> {

    private List<RankItem> rankItems;
    private Context context;
    private LayoutInflater inflater;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onClick(View view, int position);
    }

    public RankListAdapter(Context context, List<RankItem> rankItems) {
        this.context = context;
        this.rankItems = rankItems;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RelatedNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.rank_item, parent, false);
        return new RelatedNewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RelatedNewsViewHolder holder, final int position) {
        RankItem rankItem = rankItems.get(position);

//        Log.d("relate-picture", "relate-picture: "+rankItem.getPicture());
//        Picasso.with(context)
//                .load(rankItem.getPicture())
//                .placeholder(R.drawable.item_image)
//                .into(holder.imageView);

        holder.rankTv.setText(String.valueOf(position+1));
        holder.sourceTv.setText(rankItem.getSource());
        holder.countTv.setText(rankItem.getCount());

//        if (onItemClickListener!=null){
//            holder.titleTv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onItemClickListener.onClick(v,position);
//                }
//            });
//        }
    }

    @Override
    public int getItemCount() {
        return rankItems.size();
    }

    class RelatedNewsViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView rankTv;
        public TextView sourceTv;
        public TextView countTv;

        public RelatedNewsViewHolder(View itemView) {
            super(itemView);

            rankTv = (TextView) itemView.findViewById(R.id.id_tv_rank);
            sourceTv= (TextView) itemView.findViewById(R.id.id_tv_source);
            countTv = (TextView) itemView.findViewById(R.id.id_tv_count);

        }
    }
}
