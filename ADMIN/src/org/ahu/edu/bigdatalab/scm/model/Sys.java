package org.ahu.edu.bigdatalab.scm.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * Created by WangJiang on 2016/7/12.
 */
public class Sys extends Model<Sys> {

    public final static String loginState = "login_state";
    public final static String jobState = "job_state";

    public final static int LOGIN_ENABLE = 1;
    public final static int LOGIN_DISABLE = 0;
    public final static int JOB_ENABLE = 1;
    public final static int JOB_DISABLE = 0;

    public final static Sys DAO = new Sys();

    /**
     * 获取系统是否允许登陆
     * @return
     */
    public int getLoginState() {
        Sys sys = DAO.findFirst("select* from sys");
        return sys.get(loginState);
    }

    /**
     * 获取系统是否允许提交任务
     * @return
     */
    public int getJobState() {
        Sys sys = DAO.findFirst("select* from sys");
        return sys.get(jobState);
    }

}
