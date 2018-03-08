package com.reality.realityapp.ui.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.reality.realityapp.R;
import com.reality.realityapp.UserInfoHolder;
import com.reality.realityapp.bean.NewsItem;
import com.reality.realityapp.business.NewsBusiness;
import com.reality.realityapp.mock.NewsListMock;
import com.reality.realityapp.net.CommonCallback;
import com.reality.realityapp.ui.activity.base.BaseActivity;
import com.reality.realityapp.ui.view.fragment.MyInfoFragment;
import com.reality.realityapp.ui.view.fragment.NewsListFragment;
import com.reality.realityapp.ui.view.refresh.SwipeRefresh;
import com.reality.realityapp.ui.view.refresh.SwipeRefreshLayout;
import com.reality.realityapp.utils.T;

import java.util.List;
import java.util.Map;

public class FirstPageActivity extends BaseActivity implements TabHost.TabContentFactory {

    private static final String TAG = "firstPage";
    private TabHost tabHost;

    private ViewPager viewPager;

    private NewsBusiness newsBusiness = new NewsBusiness();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        initView();

        initEvent();

        T.showToast("id:" + UserInfoHolder.getInstance().getUser().getUserid() + "token:" + UserInfoHolder.getInstance().getUser().getToken());

    }

    private void initView() {
        tabHost = (TabHost) findViewById(R.id.id_tab_host);
        //fragment组成的viewpager
        viewPager = (ViewPager) findViewById(R.id.id_viewpager);

        //TODO 还未加入真正的页面切换,暂时定为3
        final Fragment[] fragments = new Fragment[]{
                NewsListFragment.newInstance(),
                NewsListFragment.newInstance(),
                MyInfoFragment.newInstance()
        };

        //初始化总布局
        tabHost.setup();

        //tab做处理
        int typeIDs[] = {R.string.home, R.string.theme, R.string.me};
        int drawbleIDs[] = {R.drawable.home_img_skip, R.drawable.theme_img_skip, R.drawable.my_img_skip};

        for (int index = 0; index < typeIDs.length; index++) {
            //设置tab本身视图的内容
            View view = getLayoutInflater().inflate(R.layout.newslist_tab, null, false);
            ImageView typesIcon = (ImageView) view.findViewById(R.id.id_img_types);
            TextView typesView = (TextView) view.findViewById(R.id.id_tv_type);
            View tab = view.findViewById(R.id.id_tab_bg);

            typesIcon.setImageResource(drawbleIDs[index]);
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
                if (tabHost != null) {
                    tabHost.setCurrentTab(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //缓存五页fragment，即当前fragment左右两个fragment会被缓存，切换时不访问服务器，提高速度
        viewPager.setOffscreenPageLimit(5);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabHost != null) {
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
