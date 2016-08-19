package org.ahu.edu.bigdatalab.scm.controller;

import com.jfinal.core.Controller;
import org.ahu.edu.bigdatalab.scm.model.Admin;
import org.ahu.edu.bigdatalab.scm.model.Note;
import org.ahu.edu.bigdatalab.scm.model.Sys;
import org.ahu.edu.bigdatalab.scm.util.DateUtil;


/**
 * Created by WangJiang on 2016/7/11.
 */
public class SysController extends Controller {

    public void index() {
        renderJsp("../sys.jsp");
    }

    public void viewAll() {
        renderJsp("../sys-note.jsp");
    }

    /**
     * 添加提高通知
     */
    public void addNote() {
        Note note = new Note();
        note.set(Note.title, getPara("title"));
        note.set(Note.content, getPara("content"));
        note.set(Note.time, DateUtil.getCurrentDateSpan());
        note.set(Note.author, ((Admin) getSession().getAttribute("admin")).get(Admin.name));
        note.save();

        redirect("../sys-note-view.jsp?id=" + note.get(Note.id));
    }

    /**
     * 保存修改后的内容
     */
    public void editNote() {
        int id = getParaToInt(0);

        Note note = Note.DAO.findById(id);
        note.set(Note.title, getPara("title"));
        note.set(Note.content, getPara("content"));
        note.update();

        redirect("../../sys-note-view.jsp?id=" + id);
    }

    /**
     * 删除Note记录
     */
    public void deleteNote() {
        Note.DAO.deleteById(getPara("id"));

        renderJson("{\"result\":\"OK\"}");
    }


    /**
     * 改变系统设置
     * 发布系统提高通知，禁止用户登陆等等
     */
    public void changeSysState() {
        int action = getParaToInt("action");
        Sys sys = Sys.DAO.findFirst("select * from sys");
        String title = null;
        String content = null;
        switch (action) {
            case 0:
                //允许登陆
                title = "登陆设置状态改变";
                content = Note.LOGIN_ENABLE;
                //修改数据库
                sys.set(Sys.loginState, Sys.LOGIN_ENABLE);
                break;
            case 1:
                //禁止登陆
                title = "登陆设置状态改变";
                content = Note.LOGIN_DISABLE;
                //修改数据库
                sys.set(Sys.loginState, Sys.LOGIN_DISABLE);
                break;
            case 2:
                //允许提交
                title = "任务设置状态改变";
                content = Note.JOB_ENABLE;
                //修改数据库
                sys.set(Sys.jobState, Sys.JOB_ENABLE);
                break;
            case 3:
                //禁止提交
                title = "任务设置状态改变";
                content = Note.JOB_DISABLE;
                //修改数据库
                sys.set(Sys.jobState, Sys.JOB_DISABLE);
                break;
        }
        //更新数据
        sys.update();

        Note note = new Note();
        note.set(Note.title, title);
        note.set(Note.content, content);
        note.set(Note.time, DateUtil.getCurrentDateSpan());
        note.set(Note.author, Note.system);
        note.save();

        renderJson("{\"result\":\"OK\"}");
    }

}
