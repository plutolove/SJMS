package org.ahu.edu.bigdatalab.sum.model;

import com.jfinal.plugin.activerecord.Model;
import org.ahu.edu.bigdatalab.sum.util.DateUtil;

import java.util.List;

public class Note extends Model<Note> {

    public final static String id = "id";
    public final static String title = "title";
    public final static String time = "time";
    public final static String author = "author";   //system是保留用户，用于发布系统提高通知
    public final static String content = "content";

    //系统设置变化的通知常量
    public final static String LOGIN_ENABLE = "当前系统已经设置为允许登陆！";
    public final static String LOGIN_DISABLE = "当前系统已经设置为禁止登陆！";
    public final static String JOB_ENABLE = "当前系统已经设置为允许提交任务！";
    public final static String JOB_DISABLE = "当前系统已经设置为禁止提交任务！";

    public final static String system = "system";   //系统的名称

    public final static Note DAO = new Note();

    //本月通知
    public List<Note> findInMonth(){
        return DAO.find("select * from note where date_format(time,'%Y-%m')=date_format(now(),'%Y-%m') order by time DESC");
    }

    public List<Note> findMonthAgo(){
        return DAO.find("select * from note where date_format(time,'%Y-%m')<date_format(now(),'%Y-%m') order by time DESC");
    }

    /**
     * 获取本月的通告通知
     *TODO where条件会导致扫描全部记录，以后将time设置为数据库属性
     * @return
     */
    public List<Note> getThisMonthNotes() {
        //按照id倒着排列
        return DAO.find("select * from note where time >= ?  order by id desc", DateUtil.getThisMonthDate());
    }

    /**
     * 获取最近的5条通知记录
     * top mysql不支持
     * @return
     */
    public List<Note> getTop5Notes() {
        return DAO.find("select * from note order by id desc limit 5");
    }

    /**
     * 返回note条数
     * TODO 这个太占用资源了，需要优化
     * @return
     */
    public int getCountOfNotes() {
        return DAO.find("select * from note").size();
    }

    /**
     * 获取更久的通告通知
     *
     * @return
     */
    public List<Note> getOlderNotes() {
        //按照id倒着排列
        return DAO.find("select * from note where time < ?  order by id desc", DateUtil.getThisMonthDate());
    }

}
