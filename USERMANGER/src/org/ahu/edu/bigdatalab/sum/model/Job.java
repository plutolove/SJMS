package org.ahu.edu.bigdatalab.sum.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.List;


public class Job extends Model<Job> {

    //数据库字段
    public final static String id = "id";
    public final static String name = "name";
    public final static String jobId = "jid";
    public final static String userId = "uid";
    public final static String jarPath = "jar_path";
    public final static String inPath = "in_path";
    public final static String outPath = "out_path";
    public final static String mainClass = "main_class";
    public final static String memory = "memory";
    public final static String cores = "cores";
    public final static String status = "status";
    public final static String log = "log";
    public final static String startTime = "st";
    public final static String endTime = "et";

    public static final Job DAO = new Job();

    /**
     * 查找该用户的所有项目
     *
     * @return
     */
    public List<Job> findUserAll(int userId) {
        return Job.DAO.find("select * from job where uid=?", userId);
    }

    /**
     * app-20160804151357-0010
     * @param jid
     * @return
     */
    public Job findJobByJid(String jid) {
        return DAO.findFirst("select * from job where jid=?", jid);
    }

}
