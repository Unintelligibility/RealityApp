package com.reality.realityapp.ui.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.reality.realityapp.R;
import com.reality.realityapp.UserInfoHolder;

/**
 * Created by 铠联 on 2018/3/8.
 */

public class MyInfoFragment extends Fragment {
    private TextView textinput_username;
    private String username = UserInfoHolder.getInstance().getUser().getUsername();
    public static MyInfoFragment newInstance(){
        MyInfoFragment myInfoFragment = new MyInfoFragment();
        return myInfoFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    private void initView() {
//        textinput_username = (TextView) findViewById(R.id.id_tv_register);
//    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_myinfo, null);

        textinput_username = (TextView)v.findViewById(R.id.id_username);
        textinput_username.setText(username);
        return v;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
