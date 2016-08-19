package org.ahu.edu.bigdatalab.sum.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import org.ahu.edu.bigdatalab.sum.Test;
import org.ahu.edu.bigdatalab.sum.bean.*;
import org.ahu.edu.bigdatalab.sum.helper.HDFSHelper;
import org.ahu.edu.bigdatalab.sum.model.*;
import org.ahu.edu.bigdatalab.sum.model.Job;
import org.ahu.edu.bigdatalab.sum.service.HDFSService;
import org.ahu.edu.bigdatalab.sum.util.TestUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class JobController extends Controller {

    public void index() {
        User user = getSessionAttr("user");
        int userId = user.getInt(User.id);
        TestUtil.log(userId);

        List<Job> jobListRunning = new ArrayList<Job>();
        List<Job> jobListEndding = new ArrayList<Job>();
        List<Job> jobList = Job.DAO.findUserAll(userId);

        //工作列表分类
        for (Job job : jobList) {
            int status = job.getInt("status");
            switch (status) {
                case 3:
                case 2:
                case 1:
                    jobListEndding.add(job);
                    break;
                case 0:
                    jobListRunning.add(job);
                    break;
            }
        }
        //工作时间处理

        setAttr("jobListRunning", jobListRunning);
        setAttr("jobListEndding", jobListEndding);

        renderJsp("job.jsp");
    }

    public void job_add() {
        User user = getSessionAttr("user");
        setAttr("user", user);
        if (User.DAO.checkRunJobEnable(user.getInt(User.id))) {
            renderJsp("job-add.jsp");
        } else {
            renderJsp("job-add-refused.jsp");
        }
    }

    public void job_addP() {
        User user = getSessionAttr("user");
        String user_id = user.getInt("id").toString();
        String name = getPara("name");
        String main_class = getPara("main_class");
        String memory = getPara("memory");
        String cores = getPara("cores");
        String jar_path = "/home/" + user_id + getPara("jar_path");
        String in = "/home/" + user_id + getPara("in");
        String out = "/home/" + user_id + getPara("out");

        TestUtil.log("cores:" + cores + "    memory:" + memory);
        //检测内存核核数的配置是否合理
        if (Integer.parseInt(memory) > 10 && Integer.parseInt(cores) > 10) {
            renderJsp("job-add-error.jsp");
            return;
        }

        try {
            Document document = Jsoup.connect(HDFSHelper.URL + "runJar").postDataCharset("UTF-8")
                    .data("userid", user_id)
                    .data("name", name)
                    .data("main_class", main_class)
                    .data("memory", memory)
                    .data("cores", cores)
                    .data("jar_path", jar_path)
                    .data("in", in)
                    .data("out", out)
                    .post();
            TestUtil.log(document.baseUri());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            redirect("/admin/job");
        }
    }

    public void delete() {
        User user = getSessionAttr("user");
        int user_id = user.getInt("id");

        int result = 1;
        String id = getPara("id");
        Job job = Job.DAO.findById(id);
        if (job != null) {
            int uid = job.getInt("uid");
            if (user_id == uid) {
                Job.DAO.deleteById(id);
                result = 0;
            }
        }

        if (result == 0) {
            renderJson(new Result("0", "删除成功"));
        } else if (result == 1) {
            renderJson(new Result("1", "删除失败"));
        }
    }

    public void log() {
        String id = getPara("id");
        User user = getSessionAttr("user");
        int user_id = user.getInt("id");

        Job job = Job.DAO.findById(id);
        if (job != null) {
            int uid = job.getInt("uid");
            if (user_id == uid) {
                setAttr("log", job.get(Job.log));
                renderJsp("job-logview.jsp");
            }
        }
    }

    /**
     * 获取文件列表用于做文件选择功能
     */
    public void chooseFile() {
        String fullPath = getPara("path");

        if ("".equals(fullPath)) {
            fullPath = "/";
        } else if (!fullPath.substring(fullPath.length() - 1).equals("/")) {
            fullPath += "/";
        }
        //文件列表处理
        User user = getSessionAttr("user");
        HDFSService service = new HDFSService(user.getInt("id"), user.getStr("password"));
        List<FileModel> fileList = service.getFileList(fullPath);
        setAttr("path", fullPath);
        setAttr("fileList", fileList);

        //目录列表处理
        List<PathModel> pathList = new ArrayList<PathModel>();
        String folder = "";
        String[] pathArray = fullPath.split("/");
        for (String path : pathArray) {
            if (!"".equals(path)) {
                folder = folder + path + "/";
                PathModel pathModel = new PathModel(path, folder);
                pathList.add(pathModel);
            }
        }
        setAttr("pathList", pathList);
        renderJsp("job-add-choosefile.jsp?fileOnly=" + getPara("fileOnly"));
    }

    /**
     * 结束一个任务
     */
    public void stop() throws IOException {
        String jobId = getPara("jobId");
        User user = getSessionAttr("user");
        Job job = Job.DAO.findJobByJid(jobId);
        if (job != null && Objects.equals(job.getInt(Job.userId), user.getInt(User.id))) {
            Jsoup.connect(HDFSHelper.URL + "killJar?appid=" + jobId).get();
        }
        renderJson(new Result("0", "结束任务"));
    }

}