package org.ahu.edu.bigdatalab.scm.model;

import com.jfinal.plugin.activerecord.Model;
import org.ahu.edu.bigdatalab.scm.util.DateUtil;
import org.ahu.edu.bigdatalab.scm.util.HTMLUtil;

import java.util.List;

/**
 * Created by WangJiang on 2016/7/10.
 */
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

    /**
     * 获取最新的note，并且不是由系统发布的
     *
     * @return
     */
    public Note getLatestNote() {
        //按照id倒着排列
        Note note = DAO.findFirst("select * from note where author != ? order by id desc", Note.system);
        if (note == null) {
            //系统通告通知为空
            note = new Note();
            note.set(Note.id, -1);     //如果没查到id赋值为-1
            note.set(Note.title, "系统通知");
            note.set(Note.content, "系统通知为空！");
            note.set(Note.author, Note.system);
            note.set(Note.time, DateUtil.getCurrentDateSpan());
        }
        return note;
    }

    /**
     * 获取所有Note并且过滤掉html，截断过长的部分
     *
     * @return
     */
    public List<Note> getAllNotesWithoutHTML() {
        List<Note> list = DAO.find("select * from note");
        for (Note note : list) {
            String content = note.get(Note.content);
            content = HTMLUtil.HTML2Text(content);
            if (content.length() > 60) {
                content = content.substring(0, 59) + "...";
            }
            note.put(Note.content, content);
        }
        return list;
    }

}
