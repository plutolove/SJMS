package org.ahu.edu.BigDataLab.config;

/**
 * Created by Hash.meng on 16-8-5.
 */
public class Config {
    static String hdfs = "hdfs://172.19.142.206:9000";
    static String rtp = "/home/mycluster/LocalFile/";
    static String spark = "spark://172.19.142.206:7077";
    static String kill = "http://172.19.142.206:8080/app/kill/";

    public static String getKill() {
        return kill;
    }

    public static void setKill(String kill) {
        Config.kill = kill;
    }

    public static String getHdfs() {
        return hdfs;
    }

    public static void setHdfs(String hdfs) {
        Config.hdfs = hdfs;
    }

    public static String getRtp() {
        return rtp;
    }

    public static void setRtp(String rtp) {
        Config.rtp = rtp;
    }

    public static String getSpark() {
        return spark;
    }

    public static void setSpark(String spark) {
        Config.spark = spark;
    }

    //static String shell = "";
}
