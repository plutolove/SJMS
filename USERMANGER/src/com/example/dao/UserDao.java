package com.example.dao;

import com.example.model.User;

public class UserDao {
    public static User findByUsername(String username){
        User user = User.dao.findFirst("select * from user where username = ?", username);
        return user;
    }
}
