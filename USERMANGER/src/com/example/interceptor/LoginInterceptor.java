package com.example.interceptor;

import com.example.model.User;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * Created by sb on 16-7-14.
 */
public class LoginInterceptor implements Interceptor {
    public void intercept(Invocation invocation) {
        Controller controller = invocation.getController();
        User user = controller.getSessionAttr("user");
//        if(user != null){
        if (true) {
            invocation.invoke();
        } else {
            controller.redirect("/login");
        }
    }
}
