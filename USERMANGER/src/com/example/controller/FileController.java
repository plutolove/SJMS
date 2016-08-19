package com.example.controller;

import com.example.model.FileModel;
import com.example.model.PathModel;
import com.example.model.Result;
import com.example.model.User;
import com.example.service.HDFSService;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sb on 16-7-15.
 */
public class FileController extends Controller {
    public void index(){
        String fullPath = getPara("path");
        if(fullPath == null || fullPath.equals("")){
            fullPath = "/";
        }else {
            if(!fullPath.substring(fullPath.length() - 1).equals("/")){
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
        for (int i = 0; i < pathArray.length; i++) {
            if(!pathArray[i].equals("")){
                s = s + pathArray[i] + "/";
                PathModel pathModel = new PathModel(pathArray[i], s);
                pathList.add(pathModel);
            }
        }
        setAttr("pathList", pathList);

        render("hdfs.jsp");
    }

    public void mkdir(){
        String fullPath = getPara("path");
        String filename = getPara("filename");
        User user = getSessionAttr("user");
        HDFSService service = new HDFSService(user.getInt("id"), user.getStr("password"));
        int result = service.mkdir(fullPath, filename);
        if(result == 0){
            renderJson(new Result("0", "创建成功"));
        }else if(result == 1){
            renderJson(new Result("1", "文件存在"));
        }
    }

    public void delete(){
        String path = getPara("path");
        String filename = getPara("filename");
        User user = getSessionAttr("user");
        HDFSService service = new HDFSService(user.getInt("id"), user.getStr("password"));
        String fullPath = path + "/" + filename;
        int result = service.deleteFile(fullPath);
        if(result == 0){
            renderJson(new Result("0", "删除成功"));
        }else if(result == 1){
            renderJson(new Result("1", "删除失败"));
        }
    }

    public void rename(){
        String fullPath = getPara("path");
        String preName = getPara("prename");
        String newName = getPara("newname");
        User user = getSessionAttr("user");
        HDFSService service = new HDFSService(user.getInt("id"), user.getStr("password"));
        int result = service.renameFile(fullPath, preName, newName);
        if(result == 0){
            renderJson(new Result("0", "操作成功"));
        }else if(result == 1){
            renderJson(new Result("1", "操作失败"));
        }
    }

    public void upload(){
        String HDFSUrl = PropKit.get("HDFSUrl");
        User user = getSessionAttr("user");
//        setAttr("user_id", user.getInt("id"));
        String path = "/home" + "/" + user.getInt("id") + getPara("path");
//        setAttr("path", path);
//        render("hdfs-upload.jsp");
        redirect(HDFSUrl + "/upload/hdfs-upload.jsp?full_path=" +  path);
    }

    /**
     * 获取前100行
     */
    public void get_lines(){
        String path = getPara("path");
        User user = getSessionAttr("user");
        HDFSService service = new HDFSService(user.getInt("id"), user.getStr("password"));
        String body = service.getLines(path);
        setAttr("body", body);
        render("get-lines.jsp");
    }

    public void download(){
        User user = getSessionAttr("user");
        String path = getPara("path");
        String filename = getPara("filename");
        redirect("http://172.19.142.65:8080/downloadFile?userid=" + user.getInt("id")
            + "&path=/home/" + user.getInt("id") + path + "&filename=" + filename
        );
    }

    public void fullpath(){
        String fullPath = getPara("path");
        String s = "/";
        String[] pathArray = fullPath.split("/");
        for (int i = 3; i < pathArray.length; i++) {
            if(!pathArray[i].equals("")){
                s = s + pathArray[i] + "/";
            }
        }
        redirect("/admin/file?path=" + s);
    }
}
