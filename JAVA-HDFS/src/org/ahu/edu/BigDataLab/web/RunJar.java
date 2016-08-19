package org.ahu.edu.BigDataLab.web;

import com.jfinal.core.Controller;
import org.ahu.edu.BigDataLab.DB.Job;
import org.ahu.edu.BigDataLab.Job.JobManage;
import java.io.IOException;

/**
 * Created by Hash.meng on 16-7-19.
 */
public class RunJar extends Controller {
    public void index() throws IOException {
        System.out.println("sdfhdfgshdjklfgkjsdhg");
        String uid = getPara("userid");
        String main_class = getPara("main_class");
        String memory = getPara("memory");
        String cores = getPara("cores");
        String jar_path = getPara("jar_path");
        String in = getPara("in");
        String out = getPara("out");
        String name = getPara("name");

        /*
    int id;
    String jid;
    int uid;
    String main_class;
    String memory;
    String cores;
    String jar_path;
    String in, out;
    int status;
    String st, et;
    String log;
     */

        Job job = new Job();
        job.set("uid", uid);
        job.set("main_class", main_class);
        job.set("memory", memory);
        job.set("cores", cores);
        job.set("jar_path", jar_path);
        job.set("in_path", in);
        job.set("out_path", out);
        job.set("status", 0);
        job.set("name", name);
        job.save();
        System.out.println("8777777777777777");
        JobManage.submit(job);
        renderText("There is nothing");
    }
}
