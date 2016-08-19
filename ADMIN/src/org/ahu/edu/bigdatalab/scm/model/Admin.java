package org.ahu.edu.bigdatalab.scm.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.List;

/**
 * Created by WangJiang on 2016/7/10.
 */
public class Admin extends Model<Admin> {

    public final static String id = "id";
    public final static String name = "name";    //system是保留用户，用于发布系统提高通知
    public final static String password = "password";
    public final static String photo = "photo";
    public final static String email = "email";
    public final static String number = "number";
    public final static String loginTime = "login_time";
    public final static String loginToken = "login_token";

    public final static Admin DAO = new Admin();

    public List<Admin> getAllAdmins(){
        return DAO.find("select * from admin");
    }


}
