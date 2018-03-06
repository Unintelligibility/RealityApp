package com.reality.realityapp.bean;

/**
 * Created by 铠联 on 2018/3/6.
 */

/**
 * 用来接收登陆后服务端返回的token验证字段和用户id
 */
public class Token {
    private String _id;
    private String token;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
