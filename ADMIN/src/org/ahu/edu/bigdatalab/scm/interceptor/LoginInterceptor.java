package org.ahu.edu.bigdatalab.scm.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import org.ahu.edu.bigdatalab.scm.model.Admin;
import org.ahu.edu.bigdatalab.scm.util.TestUtil;

/**
 * Created by WangJiang on 2016/7/19.
 * 用于判断是否登陆的拦截器
 */
public class LoginInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        Admin admin = controller.getSessionAttr("admin");
        if (admin != null) {
            inv.invoke();
        } else {
            String id = controller.getCookie("id");
            String loginToken = controller.getCookie("loginToken");
            TestUtil.log(id + "-------" + loginToken);
            admin = Admin.DAO.findFirst("select * from admin where id=? and login_token=?", id, loginToken);
            if (admin != null) {
                //验证通过，将admin加到session中
                controller.getSession().setAttribute("admin", admin);
                inv.invoke();
            } else {
                controller.redirect("/login.html");
            }
        }

    }
}
