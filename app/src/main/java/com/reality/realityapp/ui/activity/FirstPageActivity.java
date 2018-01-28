package com.reality.realityapp.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.reality.realityapp.R;
import com.reality.realityapp.bean.NewsItem;
import com.reality.realityapp.mock.NewsListMock;
import com.reality.realityapp.ui.activity.fragment.NewsListFragment;

import java.util.ArrayList;

public class FirstPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        final Fragment[] fragments = new Fragment[]{
                NewsListFragment.newInstance(NewsListMock.getNewItemList1()),
                NewsListFragment.newInstance(NewsListMock.getNewItemList2()),
                NewsListFragment.newInstance(NewsListMock.getNewItemList3())
        };

        ViewPager viewPager = (ViewPager) findViewById(R.id.id_viewpager);

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
}
