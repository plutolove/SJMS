package org.ahu.edu.BigDataLab.Job;

import org.ahu.edu.BigDataLab.DB.Job;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.ahu.edu.BigDataLab.util.GetJobID;
import org.ahu.edu.BigDataLab.util.File.DeleteDir;
import org.ahu.edu.BigDataLab.util.HdfsDao;
import org.ahu.edu.BigDataLab.config.Config;

/**
 * Created by Hash.meng on 16-7-16.
 */
public class JobManage {
    static String spark = Config.getSpark();
    static String hdfs = Config.getHdfs();
    static String rtp = Config.getRtp();
    static String REGEX = "(Exception in thread)";

    static String getSubmitBash(String uid, String main_class, String memory,
                                String cores, String jar_local, String in, String out) {
        String res = "/home/mycluster/hadoop1.2.1spark1.4/spark/bin/spark-submit --class "+main_class+" --master spark://172.19.142.206:7077 " +
                "--executor-memory "+memory+" --total-executor-cores "+cores+" "+jar_local+" "+in+" "+out;
        /*
        /home/Hash.meng/spark/bin/spark-submit --class test --master
        spark://172.19.142.65:7077 --executor-memory 2G --total-executor-cores 3
        /home/Hash.meng/a.jar hdfs://127.0.0.1:9000/home/D/new.c hdfs://127.0.0.1:9000/sdsfg
         */
        return res;
    }

    public static void submit(Job job) {
        System.out.println("--------------");
        Date date = null;
        String log = "";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String jar_local = null;
        Pattern p = Pattern.compile(REGEX);
        String uid = job.get("userid");
        String main_class = job.get("main_class");
        String memory = job.get("memory");
        String cores = job.get("cores");
        String jar_path = job.get("jar_path");
        String in = hdfs + job.get("in_path");
        String out = hdfs + job.get("out_path");
        try {

            memory = memory + "G";

            String ff[] = jar_path.split("/");

            String jarname = ff[ff.length - 1];
            jar_local = rtp + uid + "-" + jarname;

            System.out.println(jar_path+"::::::::::::::"+jar_local);

            HdfsDao.getFileLocal(jar_path, jar_local);

            String sh = getSubmitBash(uid, main_class, memory, cores, jar_local, in, out);

            System.out.println(sh);

            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(sh);
            InputStream stderr = proc.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line = null;

            boolean flag = true;
            date = new Date();
            String st = format.format(date);
            job.set("st", st);
            job.update();

            boolean isuccess = true;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                log = log + line + "\n";
                if (flag) {
                    String tmp = GetJobID.getId(line);
                    if (tmp != null) {
                        job.set("jid", tmp);
                        flag = false;
                        job.update();
                    }
                }
                if (isuccess) {
                    Matcher mm = p.matcher(line);
                    if (mm.find()) {
                        isuccess = false;
                    }
                }
            }
            date = new Date();
            String et = format.format(date);
            job.set("et", et);
            if(isuccess) job.set("status", 1);
            else job.set("status", 2);
            job.set("log", log);
            job.update();
            DeleteDir.deleteDir(new File(jar_local));
        }catch (Exception e) {
            System.out.println("sdfgsdfffffffffffffffffffffffffffffffffffffffgsdfjg");
            e.printStackTrace();
            date = new Date();
            String et = format.format(date);
            job.set("et", et);
            job.set("status", 2);
            job.set("log", log);
            job.update();
            DeleteDir.deleteDir(new File(jar_local));
        }


    }
}
