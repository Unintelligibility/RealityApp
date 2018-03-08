package com.reality.realityapp.ui.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reality.realityapp.R;
import com.reality.realityapp.bean.NewsItem;
import com.reality.realityapp.business.NewsBusiness;
import com.reality.realityapp.mock.NewsListMock;
import com.reality.realityapp.net.CommonCallback;
import com.reality.realityapp.ui.activity.NewsInfoActivity;
import com.reality.realityapp.ui.adapter.NewsListAdapter;
import com.reality.realityapp.ui.view.refresh.SwipeRefresh;
import com.reality.realityapp.ui.view.refresh.SwipeRefreshLayout;
import com.reality.realityapp.utils.BundleData;
import com.reality.realityapp.utils.T;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 用来存放首页新闻（专题）列表的fragment（容器）,由viewPager控制，可在不同fragment间切换
 * Created by 铠联 on 2018/1/27.
 */

public class NewsListFragment extends Fragment {

//    private BundleData bundleData;
    private Map<String ,NewsItem> newsItems = new HashMap<>();

    private NewsBusiness newsBusiness = new NewsBusiness();
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private NewsListAdapter newsListAdapter;

    public static NewsListFragment newInstance() {
        NewsListFragment fragment = new NewsListFragment();
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
        View view = inflater.inflate(R.layout.fragment_newslist, null);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.id_swiperefresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);

        initView();

        refreshNews();

        initEvent();

//        textView.setText(title);
//
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                newsBusiness.newsArrayDisplay(textView,getActivity());
//
//            }
//        });
        return view;
    }

    private void initView() {
//        newsItemList = NewsListMock.getNewItemList2();
        newsListAdapter = new NewsListAdapter(getActivity(), newsItems);


        //swipeRefreshLayout设置
        swipeRefreshLayout.setMode(SwipeRefresh.Mode.BOTH);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLACK, Color.GREEN, Color.YELLOW);

        //recyclerview设置
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(newsListAdapter);

    }

    private void initEvent() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefresh.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshNews();
            }
        });

        swipeRefreshLayout.setOnPullUpRefreshListener(new SwipeRefreshLayout.OnPullUpRefreshListener() {
            @Override
            public void onPullUpRefresh() {
                loadMore();
            }
        });

        newsListAdapter.setOnItemClickListener(new NewsListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                NewsItem newsItem = newsItems.get(String.valueOf(position));
                String content = newsItem.getContent();
                Log.d("content", "onClick---content: "+content);
                toNewsInfoActivity(content);
            }
        });

    }
//
//    private Map<String ,NewsItem> getNewsItems(){
//
//    }


    /**
     * 刷新新闻列表
     */
    private void refreshNews() {
        final String TAG = "NewsBusiness-request";
//        T.showToast("size:"+recyclerView.getLayoutManager().getItemCount());
        newsBusiness.newsListDisplay(new CommonCallback<Map<String, NewsItem>>() {
            @Override
            public void onError(Exception e) {
                T.showToast(e.getMessage());
            }

            @Override
            public void onResponse(Map<String, NewsItem> response) {
                newsItems.clear();
                newsItems.putAll(response);
//                Log.d(TAG, "map: " + newsItems.size());
                newsListAdapter.notifyDataSetChanged();
                if (swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(false);
                }
//                for (int i = 0; i < recyclerView.getLayoutManager().getItemCount(); i++) {
//                    final TextView titleTv = (TextView) recyclerView.getLayoutManager().findViewByPosition(i).findViewById(R.id.id_tv_title);
//                    final TextView sourceTv = (TextView) recyclerView.getLayoutManager().findViewByPosition(i).findViewById(R.id.id_tv_source);
//                    final TextView timeTv = (TextView) recyclerView.getLayoutManager().findViewByPosition(i).findViewById(R.id.id_tv_time);
//                    final TextView reliabilityTv = (TextView) recyclerView.getLayoutManager().findViewByPosition(i).findViewById(R.id.id_tv_reliability);
//                    final String title = response.get(String.valueOf(i)).getTitle();
//                    final String source = response.get(String.valueOf(i)).getSource();
//                    final String time = response.get(String.valueOf(i)).getTime();
//                    final String reliability = response.get(String.valueOf(i)).getReliability();
//                    Log.d(TAG, "onResponse: title:" + title);
//
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            titleTv.setText(title);
//                            sourceTv.setText(source);
//                            timeTv.setText(time);
//                            reliabilityTv.setText(reliability);
////                        newsListAdapter.notifyDataSetChanged();
//                            if (swipeRefreshLayout.isRefreshing()) {
//                                swipeRefreshLayout.setRefreshing(false);
//                            }
//                        }
//                    });
//                }
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

    /**
     * 加载更多新闻
     */
    private void loadMore() {
    }

    private void toNewsInfoActivity(String content){
        Intent intent = new Intent(getActivity(), NewsInfoActivity.class);
        intent.putExtra("content",content);
        startActivity(intent);
    }

}
