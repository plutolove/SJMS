package com.example.dao;

import com.example.model.Job;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sb on 16-7-22.
 */
public class JobDao {
    public static List<Job> findAll(){
        return Job.Dao.find("select * from job");
    }

    public static void main(String[] args){
        JobDao.findAll();
    }
}

