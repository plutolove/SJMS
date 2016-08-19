package com.example.controller;

import com.example.dao.JobDao;
import com.example.model.Job;
import com.example.model.Result;
import com.example.model.User;
import com.example.util.TestUtil;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.upload.UploadFile;
import net.coobird.thumbnailator.Thumbnails;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sb on 16-7-15.
 */
public class JobController extends Controller {
    public void index() {
        TestUtil.log("running");

        List<Job> jobListRunning = new ArrayList<Job>();
        List<Job> jobListEndding = new ArrayList<Job>();
        List<Job> jobList = JobDao.findAll();

        //工作列表分类
        for (Iterator<Job> it = jobList.iterator(); it.hasNext(); ) {
            Job job = it.next();
            int status = job.getInt("status");
            switch (status) {
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

        render("job.jsp");
    }

    public void job_add() {
        User user = getSessionAttr("user");
        setAttr("user", user);


        render("job-add.jsp");
    }

    public void job_addP() {
        String HDFSUrl = PropKit.get("HDFSUrl");

        User user = getSessionAttr("user");
        String user_id = user.getInt("id").toString();
        String name = getPara("name");
        String main_class = getPara("main_class");
        String memory = getPara("memory");
        String cores = getPara("cores");
        String jar_path = "/home/" + user_id + getPara("jar_path");
        String in = "/home/" + user_id + getPara("in");
        String out = "/home/" + user_id + getPara("out");
//        redirect(HDFSUrl + "/runJar?" + "user_id=" + user_id
//                + "&name=" + name
//                + "&main_class=" + main_class
//                + "&memory=" + memory
//                + "&cores=" + cores
//                + "&jar_path=" + jar_path
//                + "&in=" + in
//                + "&out=" + out);
        try {
            Document document = Jsoup.connect(HDFSUrl + "/runJar")
                    .data("userid", user_id)
                    .data("name", name)
                    .data("main_class", main_class)
                    .data("memory", memory)
                    .data("cores", cores)
                    .data("jar_path", jar_path)
                    .data("in", in)
                    .data("out", out)
                    .post();
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
        Job job = Job.Dao.findById(id);
        if (job != null) {
            int uid = job.getInt("uid");
            if (user_id == uid) {
                Job.Dao.deleteById(id);
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

        TestUtil.log(id);

        Job job = Job.Dao.findById(id);
        if (job != null) {
            int uid = job.getInt("uid");
            if (user_id == uid) {
                setAttr("log", job.get(Job.log));
                renderJsp("job-logview.jsp");
            }
        }
    }

//    /**
//     * 工作文件上传
//     */
//    public void upload_job(){
//        render("upload_job.jsp");
//    }
//
//    public void upload_jobP(){
//        //设置上传路径
//        String uploadPath = PropKit.get("uploadJobPath");
//        UploadFile uploadFile = getFile("file", uploadPath);
//
//        //修改文件名
//        String uploadFileName = uploadFile.getFileName();
//        String suffix = uploadFileName.substring(uploadFileName.lastIndexOf('.'));
//
//
//        //改名
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
//        String fileName = sdf.format(System.currentTimeMillis());
//        String fullName = fileName + suffix;//完整文件名
//        String path = "/upload" + uploadPath + "/";//不带名路径
//        String fullpath = path + fullName;//带名路径
//        String filePath = PathKit.getWebRootPath() + fullpath;//文件本地路径
//        boolean b = uploadFile.getFile().renameTo(new File(filePath));
//
//
//        redirect("/admin/job");
//
//    }
}
