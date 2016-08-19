package org.ahu.edu.bigdatalab.scm.config;

import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import org.ahu.edu.bigdatalab.scm.controller.*;
import org.ahu.edu.bigdatalab.scm.interceptor.LoginInterceptor;
import org.ahu.edu.bigdatalab.scm.model.Admin;
import org.ahu.edu.bigdatalab.scm.model.Note;
import org.ahu.edu.bigdatalab.scm.model.Sys;
import org.ahu.edu.bigdatalab.scm.model.User;

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
        //me.setViewType(ViewType.JSP); //有时候，如果controller的方法没有render，会自动指向methodName.jsp
        //me.setError404View("/404.html");
        //me.setError500View("/500.html");
    }

    @Override
    public void configRoute(Routes me) {
        //路由配置
        me.add("/", LoginController.class);
        me.add("/admin", IndexController.class);
        me.add("/admin/cluster", ClusterController.class);
        me.add("/admin/user", UserController.class);
        me.add("/admin/job", JobController.class);
        me.add("/admin/hdfs", HDFSController.class);
        me.add("/admin/sys", SysController.class);
        me.add("/admin/admin", AdminController.class);
        me.add("/admin/info", InfoController.class);
    }

    @Override
    public void configPlugin(Plugins me) {
        //数据库配置
        C3p0Plugin cp = new C3p0Plugin("jdbc:mysql://172.19.142.178:3306/sjms?characterEncoding=utf8", "lemon", "lemon123");
        me.add(cp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
        me.add(arp);
        arp.addMapping("admin", Admin.class);
        arp.addMapping("note", Note.class);
        arp.addMapping("sys", Sys.class);
        arp.addMapping("user", User.class);
    }

    @Override
    public void configInterceptor(Interceptors me) {
        me.add(new LoginInterceptor()); //全局的登陆验证
    }

    @Override
    public void configHandler(Handlers me) {

    }
}
