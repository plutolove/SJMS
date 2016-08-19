package org.ahu.edu.bigdatalab.sum.interceptor;


import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import org.ahu.edu.bigdatalab.sum.model.User;
import org.ahu.edu.bigdatalab.sum.util.TestUtil;

/**
 * Created by WangJiang on 2016/7/19.
 * 用于判断是否登陆的拦截器
 */
public class LoginInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        User user = controller.getSessionAttr("user");
        if (user != null) {
            inv.invoke();
        } else {
            controller.redirect("/login");
        }

    }
}
