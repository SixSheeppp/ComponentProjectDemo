package com.lzy.demo.moduleb;

import java.io.Serializable;

/**
 * Created by LZY on 2017/5/9.
 * ＊ Description ${TODO}
 */

public class UserInfo implements Serializable {

    private String username;
    private String phone;
    private String userImg;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
}
