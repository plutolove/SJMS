package org.ahu.edu.BigDataLab.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import org.ahu.edu.BigDataLab.DB.Job;
import org.ahu.edu.BigDataLab.DB.User;
import org.ahu.edu.BigDataLab.web.*;
import org.ahu.edu.BigDataLab.config.Config;

public class AppConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		//基本常量配置
		me.setDevMode(true);
		me.setEncoding("utf-8");
		me.setMaxPostSize(Integer.MAX_VALUE);

        /*Config.setHdfs("hdfs://127.0.0.1:9000");
        Config.setSpark("spark://172.19.142.65:7077");
        Config.setRtp("/home/Hash.meng/IdeaProjects/javahdfs/web/LocalFile/");
		Config.setKill("http://172.19.142.65:8088/app/kill/");*/
	}

	@Override
	public void configRoute(Routes me) {
		//路由配置
		//me.add("/login",LoginController.class);
        me.add("/", JobContrl.class);
        me.add("/uploadFile", UploadFileController.class);
        me.add("/getFileList", GetFileList.class);
        me.add("/mkdir", CreateDir.class);
        me.add("/deleteFile", DeleteFile.class);
        me.add("/renameFile", RenameFile.class);
        me.add("/getLines", GetFileLines.class);
        me.add("/downloadFile", DownloadFile.class);
        me.add("/runJar", RunJar.class);
		me.add("/killJar", KillJob.class);
	}

	@Override
	public void configPlugin(Plugins me) {
		//数据库配置
		C3p0Plugin cp = new C3p0Plugin("jdbc:mysql://172.19.142.178/sjms?useSSL=false&characterEncoding=utf8", "root", "lemon123");

		me.add(cp);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
		me.add(arp);
		arp.addMapping("user", "id", User.class);
        arp.addMapping("job", "id", Job.class);
	}

	@Override
	public void configInterceptor(Interceptors me) {

	}

	@Override
	public void configHandler(Handlers me) {

	}

}
