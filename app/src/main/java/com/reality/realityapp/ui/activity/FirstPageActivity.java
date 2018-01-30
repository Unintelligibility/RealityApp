package com.reality.realityapp.ui.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.reality.realityapp.R;
import com.reality.realityapp.business.NewsBusiness;
import com.reality.realityapp.mock.NewsListMock;
import com.reality.realityapp.ui.view.fragment.NewsListFragment;
import com.reality.realityapp.ui.view.refresh.SwipeRefresh;
import com.reality.realityapp.ui.view.refresh.SwipeRefreshLayout;

public class FirstPageActivity extends AppCompatActivity implements TabHost.TabContentFactory {

    private TabHost tabHost;

    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        initView();

        initEvent();

    }

    private void initView() {
        tabHost = (TabHost) findViewById(R.id.id_tab_host);
        //fragment组成的viewpager
        viewPager = (ViewPager) findViewById(R.id.id_viewpager);

        final Fragment[] fragments = new Fragment[]{
                NewsListFragment.newInstance(NewsListMock.getNewItemList1()),
                NewsListFragment.newInstance(NewsListMock.getNewItemList2()),
                NewsListFragment.newInstance(NewsListMock.getNewItemList3())
        };

        //初始化总布局
        tabHost.setup();

        //tab做处理
        int typeIDs[] = {R.string.recommend, R.string.sports, R.string.entertainment};

        for (int index = 0; index < typeIDs.length; index++) {
            //设置tab本身视图的内容
            View view = getLayoutInflater().inflate(R.layout.newslist_tab, null, false);
            TextView typesView = (TextView) view.findViewById(R.id.id_tv_type);
            View tab = view.findViewById(R.id.id_tab_bg);

            typesView.setText(typeIDs[index]);

            tab.setBackgroundColor(getResources().getColor(R.color.white));

            //将tab加入tabhost中
            tabHost.addTab(tabHost.newTabSpec(getString(typeIDs[index]))
                    .setIndicator(view)
                    .setContent(this)
            );
        }

        //将viewPager的滑动行为和tab关联
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });

    }

    private void initEvent() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (tabHost!=null){
                    tabHost.setCurrentTab(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        viewPager.setOffscreenPageLimit(1);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabHost!=null){
                    int position = tabHost.getCurrentTab();
                    viewPager.setCurrentItem(position);
                }
            }
        });
    }

    @Override
    public View createTabContent(String tag) {
        View view = new View(this);
        view.setMinimumWidth(0);
        view.setMinimumHeight(0);
        return view;
    }
}
