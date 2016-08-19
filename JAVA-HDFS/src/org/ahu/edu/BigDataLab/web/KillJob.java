package org.ahu.edu.BigDataLab.web;

import com.jfinal.core.Controller;
import org.ahu.edu.BigDataLab.DB.Job;
import org.ahu.edu.BigDataLab.config.Config;
import org.ahu.edu.BigDataLab.util.HdfsDao;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Hash.meng on 16-7-21.
 */
public class KillJob extends Controller {
    static String kill = Config.getKill();
    public void index() throws IOException {
        String appid = getPara("appid");
        /*String sh = "/home/Hash.meng/spark/bin/spark-submit kill "+appid;//+" --master "+spark;
        Runtime rt = Runtime.getRuntime();
        Process proc = rt.exec(sh);
        InputStream stderr = proc.getErrorStream();
        InputStreamReader isr = new InputStreamReader(stderr);
        BufferedReader br = new BufferedReader(isr);
        //String log = "";
        StringBuilder log = new StringBuilder();
        String line = null;
        while((line = br.readLine()) != null) {
            log.append(line+"\n");
        }*/

        Jsoup.connect(kill)
                .data("id", appid)
                .data("terminate", "true")
                .post();

        Job job = new Job();
        List<Job> joblist = job.find("select * from job where jid='"+appid+"'");
        joblist.get(0).set("status", "3");
        joblist.get(0).set("log", "Job has been killed");
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String et = format.format(date);
        joblist.get(0).set("et", et);
        joblist.get(0).update();
        renderText("there is nothing");
    }
}
