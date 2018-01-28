package com.reality.realityapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reality.realityapp.R;
import com.reality.realityapp.bean.NewsItem;
import com.reality.realityapp.business.NewsBusiness;
import com.reality.realityapp.constant.Url;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 用来存放首页新闻（专题）列表的fragment（容器）,由viewPager控制，可在不同fragment间切换
 * Created by 铠联 on 2018/1/27.
 */

public class NewsListFragment extends Fragment {

    public static final String TITLE = "title";
    private String title;

    private NewsBusiness newsBusiness = new NewsBusiness();

    public static NewsListFragment newInstance(List<NewsItem> newsItems) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        //TODO 还未将网络请求到的新闻列表数据填充进fragment，目前只是传递了一个字符串暂代
        bundle.putString(TITLE, newsItems.get(0).getTitle());
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(TITLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newslist, null);
        final TextView textView = (TextView) view.findViewById(R.id.id_tv_title);
        textView.setText(title);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsBusiness.newsArrayDisplay(textView,getActivity());

            }
        });
        return view;
    }

}
