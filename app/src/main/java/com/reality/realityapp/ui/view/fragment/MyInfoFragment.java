package com.reality.realityapp.ui.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reality.realityapp.R;

/**
 * Created by 铠联 on 2018/3/8.
 */

public class MyInfoFragment extends Fragment {


    public static MyInfoFragment newInstance(){
        MyInfoFragment myInfoFragment = new MyInfoFragment();
        return myInfoFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_myinfo, null);
        return v;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
