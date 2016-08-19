package org.ahu.edu.bigdatalab.scm.controller;

import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;
import org.ahu.edu.bigdatalab.scm.bean.Result;
import org.ahu.edu.bigdatalab.scm.model.Admin;
import org.ahu.edu.bigdatalab.scm.util.FileUtil;
import org.ahu.edu.bigdatalab.scm.util.MD5Util;

import java.util.List;

/**
 * Created by WangJiang on 2016/7/10.
 *
 * 管理员控制器
 */
public class AdminController extends Controller {

    public void index() {
        List<Admin> list = Admin.DAO.getAllAdmins();
        setAttr("list", list);
        renderJsp("../admin.jsp");
    }

    public void add() {
        UploadFile uploadFile = getFile("photo", "/upload/photo", 10 * 1024 * 1024, "utf-8");
        String photoName = "admin_photo.jpg";
        if (uploadFile != null) {
            photoName = FileUtil.savePhoto(uploadFile);
        }

        Admin admin = new Admin();
        admin.set(Admin.photo, photoName);
        admin.set(Admin.name, getPara("name"));
        admin.set(Admin.email, getPara("email"));
        admin.set(Admin.password, MD5Util.getMD5(getPara("password")));
        admin.set(Admin.number, getPara("number"));
        admin.save();

        renderJson(new Result(Result.OK));
    }

    /**
     * 修改用户自己的信息，修改完成之后，更新session
     */
    public void editSelf() {
        UploadFile uploadFile = getFile("photo", "/upload/photo", 10 * 1024 * 1024, "utf-8");
        //从session中获取登录用户的id
        Admin admin = ((Admin) getSession().getAttribute("admin"));

        if (uploadFile != null) {
            String photoName = FileUtil.savePhoto(uploadFile);
            admin.set(Admin.photo, photoName);
        }
        admin.set(Admin.name, getPara("name"));
        //admin.set(Admin.email, getPara("email"));     //email暂时不允许修改
        if (getPara("password") != null && !getPara("password").equals("")) {
            admin.set(Admin.password, MD5Util.getMD5(getPara("password")));
        }
        admin.set(Admin.number, getPara("number"));
        admin.update();

        getSession().setAttribute("admin", admin);  //更新session，此时用户信息改变
        renderJson(new Result(Result.OK));
    }

    public void delete() {
        int adminId = getParaToInt("id");
        if (((Admin) getSession().getAttribute("admin")).getInt(Admin.id) != adminId) {
            //管理员不能删除自己的账号
            //没有删除用户的图片
            Admin.DAO.deleteById(adminId);
            renderJson(new Result(Result.OK));
        } else {
            renderJson(new Result(Result.ERROR));
        }
    }
}
