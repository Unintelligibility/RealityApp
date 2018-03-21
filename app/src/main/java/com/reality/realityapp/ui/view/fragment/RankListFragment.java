package com.reality.realityapp.ui.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reality.realityapp.R;
import com.reality.realityapp.UserInfoHolder;
import com.reality.realityapp.bean.RankItem;
import com.reality.realityapp.business.RankBusiness;
import com.reality.realityapp.net.CommonCallback;
import com.reality.realityapp.ui.activity.NewsInfoActivity;
import com.reality.realityapp.ui.adapter.NewsListAdapter;
import com.reality.realityapp.ui.adapter.RankListAdapter;
import com.reality.realityapp.ui.view.refresh.SwipeRefresh;
import com.reality.realityapp.ui.view.refresh.SwipeRefreshLayout;
import com.reality.realityapp.utils.T;

import java.util.ArrayList;
import java.util.List;

/**
 * 用来存放首页新闻（专题）列表的fragment（容器）,由viewPager控制，可在不同fragment间切换
 * Created by 铠联 on 2018/1/27.
 */

public class RankListFragment extends Fragment {

    //    private BundleData bundleData;
    private List<RankItem> rankItems = new ArrayList<>();

    private RankBusiness newsBusiness = new RankBusiness();
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RankListAdapter rankListAdapter;

    public static RankListFragment newInstance() {
        RankListFragment fragment = new RankListFragment();
//        Bundle bundle = new Bundle();
//        BundleData bundleData = new BundleData();
//        bundleData.setNewsItems(newsItems);
//        bundle.putSerializable(BUNDLE_DATA, bundleData);
//        Iterator iterator = newsItems.iterator();
//        while (iterator.hasNext()) {
//            NewsItem newsItem = (NewsItem) iterator.next();
//            bundle.putString(TITLE, newsItem.getTitle());
//            bundle.putString(SOURCE, newsItem.getSource());
//            bundle.putString(TIME, newsItem.getTime());
//            bundle.putString(RELIABILITY, newsItem.getReliability());
//            bundle.putString(PICTURE, newsItem.getPicture());
//        }
//        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            bundleData = (BundleData) getArguments().getSerializable(BUNDLE_DATA);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranklist, null);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.id_swiperefresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);

        initView();

        refreshRank();

        initEvent();

        return view;
    }

    private void initView() {
//        newsItemList = NewsListMock.getNewItemList2();
        rankListAdapter = new RankListAdapter(getActivity(), rankItems);


        //swipeRefreshLayout设置
        swipeRefreshLayout.setMode(SwipeRefresh.Mode.BOTH);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLACK, Color.GREEN, Color.YELLOW);

        //recyclerview设置
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(rankListAdapter);

    }

    private void initEvent() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefresh.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshRank();
            }
        });

        swipeRefreshLayout.setOnPullUpRefreshListener(new SwipeRefreshLayout.OnPullUpRefreshListener() {
            @Override
            public void onPullUpRefresh() {
                return;
            }
        });

//        rankListAdapter.setOnItemClickListener(new NewsListAdapter.OnItemClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                RankItem newsItem = rankItems.get(position);
//                String content = newsItem.getContent();
//                String source = newsItem.getSource();
//                Log.d("content", "onClick---content: " + content);
//                Log.d("source", "onClick---source: " + source);
//                Log.d("news_id", "onClick-----news_id " + news_id);
//                toNewsInfoActivity(content, source, news_id);
//            }
//        });

    }

    /**
     * 刷新新闻列表
     */
    private void refreshRank() {
        final String TAG = "NewsBusiness-request";
//        T.showToast("size:"+recyclerView.getLayoutManager().getItemCount());
//        String rank_id = UserInfoHolder.getInstance().getUser().getUserid();
        newsBusiness.rankListDisplay(new CommonCallback<List<RankItem>>() {
            @Override
            public void onError(Exception e) {
                T.showToast(e.getMessage());
            }

            @Override
            public void onResponse(List<RankItem> response) {
                rankItems.clear();
                rankItems.addAll(response);
                Log.d(TAG, "rankItems_size: " + rankItems.size());
                rankListAdapter.notifyDataSetChanged();
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }


//    private void refreshNews() {
//        final String TAG = "NewsBusiness-request";
//        newsBusiness.newsListDisplay(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d(TAG, "onFailure() called with: call = [" + call + "], e = [" + e + "]");
//            }
//
//            @Override
//            public void onResponse(Call call,Response response) throws IOException {
//                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
//
//                textView = (TextView) recyclerView.getLayoutManager().findViewByPosition(0).findViewById(R.id.id_tv_title);
//                final String data = response.body().string();
//                Log.d(TAG, "onResponse: data:"+data);
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        textView.setText(data);
////                        newsListAdapter.notifyDataSetChanged();
//                        if (swipeRefreshLayout.isRefreshing()){
//                            swipeRefreshLayout.setRefreshing(false);
//                        }
//                    }
//                });
//            }
//        });
//    }

    private void toNewsInfoActivity(String content, String source, String news_id) {
        Intent intent = new Intent(getActivity(), NewsInfoActivity.class);
        intent.putExtra("content", content);
        intent.putExtra("source", source);
        intent.putExtra("news_id", news_id);
        startActivity(intent);
    }

}
