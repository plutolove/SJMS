package org.ahu.edu.bigdatalab.sum.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * Created by sb on 16-7-13.
 */
public class User extends Model<User> {

    //数据库表结构
    public final static String id = "id";
    public final static String name = "username";
    public final static String password = "password";
    public final static String nickName = "nickname";
    public final static String email = "email";
    public final static String avatar = "avatar";
    public final static String thumbnail = "thumbnail";
    public final static String lastLoginTime = "last_login_time";
    public final static String loginEnable = "login_enable";
    public final static String runjobEnable = "runjob_enable";

    public static final User DAO = new User();

    /**
     * 通过用户名称找用户
     *
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return User.DAO.findFirst("select * from user where username = ?", username);
    }

    /**
     * 判断是否允许该用户登陆
     *
     * @param userId
     * @return
     */
    public boolean checkLoginEnable(int userId) {
        return DAO.findById(userId).getInt(loginEnable) == 1;
    }

    /**
     * 判断是否允许该用户提交任务
     *
     * @param userId
     * @return
     */
    public boolean checkRunJobEnable(int userId) {
        return DAO.findById(userId).getInt(runjobEnable) == 1;
    }

}
