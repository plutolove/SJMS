package com.example.controller;

import com.example.model.User;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sb on 16-7-14.
 */
public class AdminController extends Controller {

    public void index(){
        User user = getSessionAttr("user");
        //处理上次登陆时间
        Timestamp lastLoginTime = user.getTimestamp("last_login_time");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        setAttr("last_login_time", sdf.format(lastLoginTime));
        user.set("last_login_time", new Timestamp(System.currentTimeMillis())).update();

        setAttr("user", user);

        render("index.jsp");
    }
}
