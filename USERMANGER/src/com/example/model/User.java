package com.example.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * Created by sb on 16-7-13.
 */
public class User extends Model<User> {
    public static final User dao = new User();
}
