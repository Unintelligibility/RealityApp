package com.reality.realityapp;

import android.text.TextUtils;

import com.reality.realityapp.bean.User;
import com.reality.realityapp.utils.SPUtils;

/**
 * Created by 铠联 on 2018/2/5.
 */

public class UserInfoHolder {
    private static UserInfoHolder userInfoHolder = new UserInfoHolder();
    private User user;
    private static final String KEY_USERNAME = "key_username";

    public static UserInfoHolder getInstance() {
        return userInfoHolder;
    }

    /**
     * 取出持久化的用户信息
     *
     * @return
     */
    public User getUser() {
        if (user == null) {
            String username = (String) SPUtils.getInstance().get(KEY_USERNAME, "");
            if (!TextUtils.isEmpty(username)) {
                user = new User();
                user.setUsername(username);
            }
        }
        return user;
    }

    /**
     * 登陆成功之后在本地持久化存储用户信息，便于以后自动登录
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
        if (user != null) {
            SPUtils.getInstance().put(KEY_USERNAME, user.getUsername());
        }
    }
}
