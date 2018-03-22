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
import com.reality.realityapp.bean.NewsItem;
import com.reality.realityapp.bean.ThemeItem;
import com.reality.realityapp.business.NewsBusiness;
import com.reality.realityapp.net.CommonCallback;
import com.reality.realityapp.ui.activity.NewsInfoActivity;
import com.reality.realityapp.ui.activity.ThemeNewsListActivity;
import com.reality.realityapp.ui.adapter.ThemeListAdapter;
import com.reality.realityapp.ui.view.refresh.SwipeRefresh;
import com.reality.realityapp.ui.view.refresh.SwipeRefreshLayout;
import com.reality.realityapp.utils.T;

import java.util.HashMap;
import java.util.Map;

/**
 * 用来存放首页新闻（专题）列表的fragment（容器）,由viewPager控制，可在不同fragment间切换
 * Created by 铠联 on 2018/1/27.
 */

public class ThemeListFragment extends Fragment {

    //    private BundleData bundleData;
    private Map<String, ThemeItem> themeItems = new HashMap<>();

    private NewsBusiness newsBusiness = new NewsBusiness();
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ThemeListAdapter themeAdapter;

    public static ThemeListFragment newInstance() {
        ThemeListFragment fragment = new ThemeListFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_themelist, null);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.id_swiperefresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);

        initView();

        refreshThemes();

        initEvent();

        return view;
    }

    private void initView() {
//        newsItemList = NewsListMock.getNewItemList2();
        themeAdapter = new ThemeListAdapter(getActivity(), themeItems);


        //swipeRefreshLayout设置
        swipeRefreshLayout.setMode(SwipeRefresh.Mode.BOTH);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLACK, Color.GREEN, Color.YELLOW);

        //recyclerview设置
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(themeAdapter);

    }

    private void initEvent() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefresh.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshThemes();
            }
        });

        swipeRefreshLayout.setOnPullUpRefreshListener(new SwipeRefreshLayout.OnPullUpRefreshListener() {
            @Override
            public void onPullUpRefresh() {
                refreshThemes();
            }
        });

        themeAdapter.setOnItemClickListener(new ThemeListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                ThemeItem themeItem = themeItems.get(String.valueOf(position));
                String theme_title = themeItem.getTheme_title();
                toThemeNewsListActivity(theme_title);
            }
        });

    }

    /**
     * 刷新新闻列表
     */
    public void refreshThemes() {
        final String TAG = "NewsBusiness-request";
//        T.showToast("size:"+recyclerView.getLayoutManager().getItemCount());
        String userid = UserInfoHolder.getInstance().getUser().getUserid();
//        Log.d(TAG, "userid-----: " + userid);
        newsBusiness.themeListDisplay(new CommonCallback<Map<String, ThemeItem>>() {
            @Override
            public void onError(Exception e) {
                T.showToast(e.getMessage());
            }

            @Override
            public void onResponse(Map<String, ThemeItem> response) {
                themeItems.clear();
                themeItems.putAll(response);
                Log.d(TAG, "theme-map: " + themeItems.size());
                themeAdapter.notifyDataSetChanged();
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    /**
     * 加载更多新闻
     */
    private void loadMore() {
        final String TAG = "NewsBusiness-request";
//        T.showToast("size:"+recyclerView.getLayoutManager().getItemCount());
        String userid = UserInfoHolder.getInstance().getUser().getUserid();
        Log.d(TAG, "userid-----theme: " + userid);
        newsBusiness.themeListDisplay(new CommonCallback<Map<String, ThemeItem>>() {
            @Override
            public void onError(Exception e) {
                T.showToast(e.getMessage());
            }

            @Override
            public void onResponse(Map<String, ThemeItem> response) {
                themeItems.putAll(response);
                themeAdapter.notifyDataSetChanged();
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    private void toThemeNewsListActivity(String theme_title) {
        Intent intent = new Intent(getActivity(), ThemeNewsListActivity.class);
        intent.putExtra("theme_title", theme_title);
        startActivity(intent);
    }
}
