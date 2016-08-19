package com.example.config;

import com.example.controller.*;
import com.example.interceptor.LoginInterceptor;
import com.example.model._MappingKit;
import com.jfinal.config.*;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

/**
 * Created by sb on 16-7-13.
 */
public class WebConfig extends JFinalConfig {
    /**
     * 配置常量
     */
    public void configConstant(Constants constants) {
        PropKit.use("web_config.txt");
        constants.setDevMode(true);
        constants.setViewType(ViewType.JSP);
    }

    /**
     * 配置路由
     */
    public void configRoute(Routes routes) {
        routes.add("/", LoginAndRegisterController.class);
        routes.add("/admin", AdminController.class, "/admin");
        routes.add("/admin/user", UserController.class, "/admin");
        routes.add("/admin/job", JobController.class, "/admin");
        routes.add("/admin/file", FileController.class, "/admin");
        routes.add("/admin/sysnote", SysNoteController.class, "/admin");
    }

    /**
     * 配置插件
     */
    public void configPlugin(Plugins plugins) {
        // 配置C3p0数据库连接池插件
        C3p0Plugin C3p0Plugin = createC3p0Plugin();
        plugins.add(C3p0Plugin);

        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(C3p0Plugin);
        plugins.add(arp);

        // 所有配置在 MappingKit 中搞定
        _MappingKit.mapping(arp);
    }
    public static C3p0Plugin createC3p0Plugin() {
        return new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
    }

    /**
     * 拦截器 Global、 Class、 Method 三个层次
     */
    public void configInterceptor(Interceptors interceptors) {
//        interceptors.addGlobalActionInterceptor(new LoginInterceptor());
        interceptors.add(new LoginInterceptor());
    }

    /**
     * 处理器
     */
    public void configHandler(Handlers handlers) {

    }
}
