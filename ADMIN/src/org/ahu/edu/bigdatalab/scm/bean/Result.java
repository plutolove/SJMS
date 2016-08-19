package org.ahu.edu.bigdatalab.scm.bean;

import com.jfinal.plugin.activerecord.Model;

/**
 * Created by WangJiang on 2016/8/6.
 */
public class Result extends Model<Result>{

    public final static String OK = "OK";
    public final static String ERROR = "ERROR";

    public String result = "result";

    public Result(String result) {
        this.set(this.result, result);
    }

}
