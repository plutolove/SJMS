package org.ahu.edu.bigdatalab.sum.helper;

import org.ahu.edu.bigdatalab.sum.bean.Job;
import org.ahu.edu.bigdatalab.sum.bean.Worker;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by WangJiang on 2016/7/10.
 */
public class SparkHelper {

    /**
     * SPARK管理页面的url
     */
    public final static String SPARK_URL = "http://172.19.142.206:8080/";

    private Document document = null;

    public SparkHelper() {
        try {
            this.document = Jsoup.connect(SPARK_URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有的worker列表
     *
     * @return
     */
    public List<Worker> getAllWorkers() {
        List<Worker> list = new LinkedList<>();
        Element workersTbody = document.select("table tbody").first();
        Elements workersTr = workersTbody.select("tr");

        for (Element tr : workersTr) {
            Worker worker = new Worker();
            worker.setId(tr.child(0).text());
            worker.setAddress(tr.child(1).text());
            worker.setState(tr.child(2).text());
            worker.setCores(tr.child(3).text());
            worker.setMemory(tr.child(4).text());

            list.add(worker);
        }
        return list;
    }

    /**
     * 获取正在运行的任务
     */
    public List<Job> getRunningJobs() {
        Element jobsTbody = document.select("table tbody").get(1);
        return getJobsByTbody(jobsTbody);
    }

    /**
     * 获取已经结束的任务
     */
    public List<Job> getFinishedJobs() {
        Element jobsTbody = document.select("table tbody").get(2);
        return getJobsByTbody(jobsTbody);
    }

    /**
     * 解析Tbody获取Jobs
     *
     * @param jobsTbody
     * @return
     */
    private List<Job> getJobsByTbody(Element jobsTbody) {
        List<Job> list = new LinkedList<>();
        Elements jobsTr = jobsTbody.select("tr");
        for (Element tr : jobsTr) {
            Job job = new Job();
            job.setId(tr.child(0).text().replace(" (kill)", ""));  //app-20160715200742-0003 (kill)  剔除kill
            job.setName(tr.child(1).text());
            job.setCores(tr.child(2).text());
            job.setMemoryPerNode(tr.child(3).text());
            job.setSubmittedTime(tr.child(4).text());
            job.setUser(tr.child(5).text());
            job.setState(tr.child(6).text());
            job.setDuration(tr.child(7).text());

            list.add(job);
        }
        return list;
    }

    /**
     * 获得worker的总数
     */
    public String getCountOfAllWorkers() {
        String count = document.select("li").get(2).text();
        return count.replace("Workers:", "");
    }

    /**
     * 获取可用的节点总数
     */
    public int getCountOfEnabledWorkers() {
        List<Worker> list = getAllWorkers();
        int count = 0;
        for (Worker worker : list) {
            if ("ALIVE".equals(worker.getState())) {
                count++;
            }
        }
        return count;
    }

    /**
     * 获取不可以的节点总数
     */
    public int getCountOfDisabledWorkers() {
        List<Worker> list = getAllWorkers();
        int count = 0;
        for (Worker worker : list) {
            if (!"ALIVE".equals(worker.getState())) {
                count++;
            }
        }
        return count;
    }

    /**
     * 获取正在运行的任务数量
     *
     * @return
     */
    public int getCountOfRunningJobs() {
        return getRunningJobs().size();
    }

    /**
     * 获取spark的URL spark://master:7077
     *
     * @return
     */
    public String getSparkUrl() {
        return document.select("li").get(0).text().replace("URL:", "");
    }

    /**
     * 获取 REST URL: spark://172.19.142.206:6066 (cluster mode)
     *
     * @return
     */
    public String getSparkRestUrl() {
        return document.select("li").get(1).text().replace("REST URL:", "");
    }

    /**
     * 获取cores的使用信息
     *
     * @return 总共 80, 80 已用
     */
    public String getCoresInfo() {
        String info = document.select("li").get(3).text();
        info = info.replace("Cores:", "").replace("Total", "").replace("Used", "已用");
        info = "总共" + info;
        return info;
    }

    /**
     * 获取内存的使用信息
     *
     * @return 总共 100.0 GB, 5.0 GB 已用
     */
    public String getMemoryInfo() {
        String info = document.select("li").get(4).text();
        info = info.replace("Memory:", "").replace("Total", "").replace("Used", "已用");
        info = "总共" + info;
        return info;
    }

    /**
     * 获取任务信息
     *
     * @return 1 个正在运行, 8 个已经完成
     */
    public String getJobsInfo() {
        String info = document.select("li").get(5).text();
        info = info.replace("Applications:", "").replace("Running", "个正在运行").replace("Completed", "个已经完成");
        return info;
    }

    /**
     * 返回驱动信息
     *
     * @return 0 个正在运行, 0 个已经完成
     */
    public String getDriversInfo() {
        String info = document.select("li").get(6).text();
        info = info.replace("Drivers:", "").replace("Running", "个正在运行").replace("Completed", "个已经完成");
        return info;
    }

    /**
     * 获取spark版本信息
     *
     * @return 1.4.0
     */
    public String getVersionOfSpark() {
        return document.select("span").first().text();
    }

    /**
     * 获取Spark的状态
     *
     * @return ALIVE
     */
    public String getSparkStatus() {
        return document.select("li").get(7).text().replace("Status:", "");
    }

    public static void main(String args[]) {
        SparkHelper sparkHelper = new SparkHelper();
//        List<Worker> list = sparkHelper.getAllWorkers();
//
//        for (Worker worker : list) {
//            System.out.println(worker.getId());
//        }

        System.out.println(sparkHelper.getCoresInfo());
    }

}
