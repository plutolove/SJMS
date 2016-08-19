package org.ahu.edu.bigdatalab.sum.controller;

import com.jfinal.core.Controller;
import org.ahu.edu.bigdatalab.sum.helper.HDFSHelper;
import org.ahu.edu.bigdatalab.sum.model.FileModel;
import org.ahu.edu.bigdatalab.sum.model.PathModel;
import org.ahu.edu.bigdatalab.sum.model.Result;
import org.ahu.edu.bigdatalab.sum.model.User;
import org.ahu.edu.bigdatalab.sum.service.HDFSService;
import org.ahu.edu.bigdatalab.sum.util.TestUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileController extends Controller {
    public void index() {
        String fullPath = getPara("path");
        if (fullPath == null || fullPath.equals("")) {
            fullPath = "/";
        } else {
            if (!fullPath.substring(fullPath.length() - 1).equals("/")) {
                fullPath += "/";
            }
        }
        //文件列表处理
        User user = getSessionAttr("user");
        HDFSService service = new HDFSService(user.getInt("id"), user.getStr("password"));
        List<FileModel> fileList = service.getFileList(fullPath);
        setAttr("path", fullPath);
        setAttr("fileList", fileList);

        //目录列表处理
        List<PathModel> pathList = new ArrayList<PathModel>();
        String s = "";
        String[] pathArray = fullPath.split("/");
        for (String path : pathArray) {
            if (!path.equals("")) {
                s = s + path + "/";
                PathModel pathModel = new PathModel(path, s);
                pathList.add(pathModel);
            }
        }
        setAttr("pathList", pathList);

        renderJsp("hdfs.jsp");
    }

    public void mkdir() {
        String fullPath = getPara("path");
        String filename = getPara("filename");
        User user = getSessionAttr("user");
        HDFSService service = new HDFSService(user.getInt("id"), user.getStr("password"));
        int result = service.mkdir(fullPath, filename);
        if (result == 0) {
            renderJson(new Result("0", "创建成功"));
        } else if (result == 1) {
            renderJson(new Result("1", "文件存在"));
        }
    }

    public void delete() {
        String path = getPara("path");
        String filename = getPara("filename");
        User user = getSessionAttr("user");
        HDFSService service = new HDFSService(user.getInt("id"), user.getStr("password"));
        String fullPath = path + "/" + filename;
        int result = service.deleteFile(fullPath);
        if (result == 0) {
            renderJson(new Result("0", "删除成功"));
        } else if (result == 1) {
            renderJson(new Result("1", "删除失败"));
        }
    }

    public void rename() {
        String fullPath = getPara("path");
        String preName = getPara("prename");
        String newName = getPara("newname");
        User user = getSessionAttr("user");
        HDFSService service = new HDFSService(user.getInt("id"), user.getStr("password"));
        int result = service.renameFile(fullPath, preName, newName);
        if (result == 0) {
            renderJson(new Result("0", "操作成功"));
        } else if (result == 1) {
            renderJson(new Result("1", "操作失败"));
        }
    }

    public void upload() {
        User user = getSessionAttr("user");
//        setAttr("user_id", user.getInt("id"));
        String path = "/home/" + user.getInt("id") + getPara("path");
        redirect(HDFSHelper.URL + "upload/hdfs-upload.jsp?full_path=" + path);
    }


    /**
     * 文件预览
     */
    public void fileView() throws IOException {
        User user = getSessionAttr("user");
        String file = getPara("file");      //xx.txt
        String url = HDFSHelper.URL + "getLines?full_path=/home/" + user.getInt("id") + file;
        TestUtil.log(url);
        Document document = Jsoup.connect(url).get();
        TestUtil.log(document.text());
        String data = document.text();
        setAttr("data", data);
        renderJsp("hdfs-file.jsp");
    }

    public void download() {
        User user = getSessionAttr("user");
        String path = getPara("path");
        String filename = getPara("filename");
        int userId = user.getInt("id");
        redirect(HDFSHelper.URL + "downloadFile?userid=" + userId + "&path=/home/" + userId + path + "/&filename=" + filename);
    }

    public void fullpath() {
        String fullPath = getPara("path");
        String s = "/";
        String[] pathArray = fullPath.split("/");
        for (int i = 3; i < pathArray.length; i++) {
            if (!pathArray[i].equals("")) {
                s = s + pathArray[i] + "/";
            }
        }
        redirect("/admin/file?path=" + s);
    }
}
