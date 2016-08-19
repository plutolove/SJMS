package org.ahu.edu.bigdatalab.sum.config;

import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import jdk.nashorn.internal.scripts.JO;
import org.ahu.edu.bigdatalab.sum.controller.*;
import org.ahu.edu.bigdatalab.sum.interceptor.LoginInterceptor;
import org.ahu.edu.bigdatalab.sum.model.Job;
import org.ahu.edu.bigdatalab.sum.model.Note;
import org.ahu.edu.bigdatalab.sum.model.User;

/**
 * Created by WangJiang on 2016/7/10.
 * web程序的总的配置文件
 */
public class WebConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants me) {
        //基本常量配置
        me.setDevMode(true);
        me.setEncoding("utf-8");
        me.setViewType(ViewType.JSP); //有时候，如果controller的方法没有render，会自动指向methodName.jsp
//        me.setError404View("/404.html");
//        me.setError500View("/500.html");
    }

    @Override
    public void configRoute(Routes routes) {
        //路由配置
        routes.add("/", LoginAndRegisterController.class);
//        routes.add("/admin", AdminController.class, "/admin");
        routes.add("/admin/user", UserController.class, "/admin");
        routes.add("/admin/job", JobController.class, "/admin");
        routes.add("/admin/file", FileController.class, "/admin");
        routes.add("/admin/sysnote", SysNoteController.class, "/admin");
        routes.add("/admin/help", HelpController.class, "/admin");
    }

    @Override
    public void configPlugin(Plugins me) {
        //数据库配置
        C3p0Plugin cp = new C3p0Plugin("jdbc:mysql://localhost:3306/sjms?characterEncoding=utf8", "lemon", "lemon123");
        me.add(cp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
        me.add(arp);
        arp.addMapping("note", Note.class);
        arp.addMapping("user", User.class);
        arp.addMapping("job", Job.class);
    }

    @Override
    public void configInterceptor(Interceptors me) {
        me.add(new LoginInterceptor()); //全局的登陆验证
    }

    @Override
    public void configHandler(Handlers me) {

    }
}
