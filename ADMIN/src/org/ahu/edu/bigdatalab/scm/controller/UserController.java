package org.ahu.edu.bigdatalab.scm.controller;

import com.jfinal.core.Controller;
import org.ahu.edu.bigdatalab.scm.bean.Result;
import org.ahu.edu.bigdatalab.scm.model.User;

import java.util.List;

/**
 * Created by WangJiang on 2016/7/11.
 */
public class UserController extends Controller {

    public void index() {
        List<User> list = User.DAO.getAllUsers();
        setAttr("list", list);
        renderJsp("../user.jsp");
    }

    public void setRunJobStatus() {
        int userId = getParaToInt("userId");
        int status = getParaToInt("status");

        User user = User.DAO.findById(userId);
        user.set(User.runjobEnable, status);
        user.update();
        renderJson(new Result(Result.OK));
    }
}
