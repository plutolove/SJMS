package org.ahu.edu.bigdatalab.scm.controller;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import org.ahu.edu.bigdatalab.scm.model.Admin;
import org.ahu.edu.bigdatalab.scm.util.DateUtil;
import org.ahu.edu.bigdatalab.scm.util.MD5Util;
import org.ahu.edu.bigdatalab.scm.util.TextUtil;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.spi.SyncResolver;

/**
 * Created by WangJiang on 2016/7/10.
 * 该控制器清除所有拦截器
 */
@Clear
public class LoginController extends Controller {

    public void index() {
        redirect("login.html");
    }

    public void login() {
        String email = getPara("email");
        String password = getPara("password");
        password = MD5Util.getMD5(password);

        Admin admin = Admin.DAO.findFirst("select * from admin where email=? and password=?", email, password);
        if (admin != null) {
            //更新登陆时间
            admin.set(Admin.loginTime, DateUtil.getCurrentDateSpan());
            String loginToken = TextUtil.getRandomString(32);   //32位随机字符串
            admin.set(Admin.loginToken, loginToken);
            admin.update();

            HttpSession session = getSession();
            session.setAttribute("admin", admin);
            //后台返回数据，前台负责处理，如跳转到index.html或者提示密码错误
            renderJson("{\"result\":\"OK\",\"id\":\"" + admin.get(Admin.id) + "\",\"loginToken\":\"" + loginToken + "\"}");
        } else {
            renderJson("{\"result\":\"ERROR\"}");
        }
    }

    public void logout() {
        HttpSession session = getSession();
        if (session != null) {
            session.invalidate();
        }
        redirect("login.html");
    }

}
