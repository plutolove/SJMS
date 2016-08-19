package org.ahu.edu.bigdatalab.scm.model;

import com.jfinal.plugin.activerecord.Model;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.FieldAccessor_Boolean;

import java.util.List;

/**
 * Created by WangJiang on 2016/7/22.
 */
public class User extends Model<User> {

    public final static String id = "id";
    public final static String name = "username";
    public final static String password = "password";
    public final static String nickName = "nickname";
    public final static String email = "email";
    public final static String avatar = "avatar";
    public final static String thumbnail = "thumbnail";
    public final static String lastLoginTime = "last_login_time";
    public final static String identity = "identity";
    public final static String loginEnable = "login_enable";
    public final static String runjobEnable = "runjob_enable";

    public final static int IDENTITY_STUDENT = 1;
    public final static int IDENTITY_TEACHER = 2;

    public final static int RUN_JOB_ENABLE = 1;
    public final static int RUN_JOB_DISABLE = 0;

    public final static User DAO = new User();

    public List<User> getAllUsers() {
        return DAO.find("select * from user");
    }

}
