package org.ahu.edu.bigdatalab.sum.controller;

import com.jfinal.core.Controller;
import org.ahu.edu.bigdatalab.sum.model.User;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 该控制器暂时弃用
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

        renderJsp("index.jsp");
    }
}
