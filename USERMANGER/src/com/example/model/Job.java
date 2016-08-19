package com.example.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * Created by sb on 16-7-16.
 */
public class Job extends Model<Job> {

    //数据库字段
    public final static String id = "id";
    public final static String name = "name";
    public final static String jobId = "jid";
    public final static String userId = "uid";
    public final static String jarPath = "jar_path";
    public final static String inPath = "in_path";
    public final static String outPath = "out_path";
    public final static String mainClass = "main_class";
    public final static String memory = "memory";
    public final static String cores = "cores";
    public final static String status = "status";
    public final static String log = "log";
    public final static String startTime = "st";
    public final static String endTime = "et";

    public static final Job Dao =  new Job();
}
