package org.ahu.edu.bigdatalab.sum.controller;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import org.ahu.edu.bigdatalab.sum.model.Result;
import org.ahu.edu.bigdatalab.sum.model.User;
import org.ahu.edu.bigdatalab.sum.service.HDFSService;
import org.ahu.edu.bigdatalab.sum.tools.CheckIdentity;
import org.ahu.edu.bigdatalab.sum.tools.MD5Util;

import java.sql.Timestamp;

/**
 * 登陆和注册类
 */
@Clear
public class LoginAndRegisterController extends Controller {

    public void index(){
        redirect("/login");
    }

    public void login(){
        renderJsp("login.jsp");
    }

    public void loginP(){
        String username = getPara("username");
        String password = getPara("password");
        if(username.equals("")|| password.equals("")){
            //参数错误
            renderJson(new Result("1", "用户名或密码不能为空"));
            return;
        }
        password = MD5Util.getMD5(password);
        User user = User.DAO.findByUsername(username);
        if(user == null){
            //用户名不存在
            renderJson(new Result("1", "用户名不存在"));
            return;
        }
        if(!user.getStr("password").equals(password)){
            //密码错误
            renderJson(new Result("1", "密码错误"));
            return;
        }

        /*
        新增: 创建文件夹
         */
        HDFSService service = new HDFSService(user.getInt("id"), user.getStr("password"));
        service.mkdir("/", "");

        setSessionAttr("user", user);
        renderJson(new Result("0", "登陆成功"));
    }

    public void logout(){
        setSessionAttr("user", null);
        redirect("/login");
    }

    public void register(){
        String username = getSessionAttr("jw_username");
        if(username == null || username.equals("")){
            redirect("/check_identity");
            return;
        }
        System.out.println(username);
        setAttr("username", username);
        renderJsp("register.jsp");
    }

    public void registerP(){
        String username = getSessionAttr("jw_username");
        String password = getPara("password");
        String email    = getPara("email");
        String nickname = getPara("nickname");
        int identity    = getSessionAttr("identity");
        if(username.equals("") || password.equals("") || email.equals("") || nickname.equals("") ){
            renderJson(new Result("1", "所填信息不能为空"));
            return;
        }
        User user = User.DAO.findByUsername(username);
        if(user != null){
            renderJson(new Result("1", "重复注册"));
            return;
        }
        password = MD5Util.getMD5(password);
        new User().set("username", username)
                .set("password", password)
                .set("email", email)
                .set("nickname", nickname)
                .set("last_login_time", new Timestamp(System.currentTimeMillis()))
                .set("thumbnail", "/img/admin_photo.jpg")
                .set("identity", identity)
                .save();
        //注册成功
        renderJson(new Result("0", "注册成功"));
    }

    /**
     * 重置密码
     */
    public void reset_password(){
        String username = getSessionAttr("jw_username");
        if(username == null || username .equals("")){
            redirect("/check_identity");
            return;
        }
        renderJsp("reset_password.jsp");
    }

    public void reset_passwordP(){
        String username = getSessionAttr("jw_username");
        String password = getPara("password");
        String passwordr = getPara("passwordr");
        if(!password.equals(passwordr)){
            renderJson(new Result("1", "两次输入不一致"));
            return;
        }
        if(password == null || password .equals("")){
            renderJson(new Result("1", "密码不能为空"));
            return;
        }
        password = MD5Util.getMD5(password);
        User user = User.DAO.findByUsername(username);
        user.set("password", password).update();
        renderJson(new Result("0", "修改成功"));
        return;
    }
    /**
     * 验证身份
     */
    public void check_identity(){
        render("check_identity.jsp");
    }

    public void check_identityP(){
        String jw_username = getPara("jw_username");
        String jw_password = getPara("jw_password");
        String jw_identity = getPara("identity");

        if(jw_identity == null){
            renderJson(new Result("1", "请选择身份"));
            return;
        }

        int identity = Integer.parseInt(getPara("identity"));

        //有待优化
        //不清楚renderJson的范围, 所以没有将相同的代码提取出来
        switch (identity){
            case 1:
                if(CheckIdentity.checkStudent(jw_username, jw_password)){
                    User user = User.DAO.findByUsername(jw_username);
                    setSessionAttr("jw_username", jw_username);
                    setSessionAttr("identity", identity);
                    if(user == null){
                        renderJson(new Result("0", "认证成功"));
                        return;
                    }else {
                        //用户已存在
                        redirect("/reset_password");
                        renderJson(new Result("2", "用户已存在"));
                    }
                }else {
                    //身份认证失败
                    renderJson(new Result("1", "身份验证失败"));
                }
                break;
            case 2:
                if(CheckIdentity.checkTeacher(jw_username, jw_password)){
                    User user = User.DAO.findByUsername(jw_username);
                    setSessionAttr("jw_username", jw_username);
                    setSessionAttr("identity", identity);
                    if(user == null){
                        renderJson(new Result("0", "认证成功"));
                        return;
                    }else {
                        //用户已存在
                        redirect("/reset_password");
                        renderJson(new Result("2", "用户已存在"));
                    }
                }else {
                    //身份认证失败
                    renderJson(new Result("1", "身份验证失败"));
                }
                break;
            default:
                renderJson(new Result("1", "身份验证失败"));
        }


    }
}
