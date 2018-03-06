package com.reality.realityapp.bean;

import java.io.Serializable;

/**
 * Created by 铠联 on 2018/1/26.
 */

public class User implements Serializable {
    private String userid;
    private String password;
    private String username;
    private String icon;
    private String token;

    public User(){}

    public User(String userid,String username,String password,String token){
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.token = token;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
