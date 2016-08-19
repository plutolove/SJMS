package org.ahu.edu.bigdatalab.sum.service;

import org.ahu.edu.bigdatalab.sum.helper.HDFSHelper;
import org.ahu.edu.bigdatalab.sum.model.FileModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sb on 16-7-19.
 */
public class HDFSService {

    private String id;
    private String password;

    public HDFSService(Integer id, String password){
        this.id = id.toString();
        this.password = password;
    }

    public List<FileModel> getFileList(String fullPath){
        try {
            Document document = Jsoup.connect(HDFSHelper.URL + "getFileList")
                    .data("userid", id)
//                    .data("passwd", password)
                    .data("full_path", "/home/" + id + fullPath)
                    .post();

            //json处理
            JSONObject body = new JSONObject(document.body().ownText());
            JSONArray list = body.getJSONArray("list");

            //循环加入list中
            List<FileModel> fileList = new ArrayList<FileModel>();
            for (int i = 0; i < list.length(); i++) {
                FileModel fileModel = new FileModel();
                fileModel.setFilename(list.getJSONObject(i).getString("fname"));
                fileModel.setFiletype(list.getJSONObject(i).getString("ftype"));
                fileModel.setFilesize(list.getJSONObject(i).getString("size"));
                fileModel.setFiletime(list.getJSONObject(i).getString("mtime"));
                fileList.add(fileModel);
            }
            return fileList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回值
     * 0.创建成功
     * 1.文件存在
     * 2.其他
     */
    public int mkdir(String fullPath, String filename){
        try {
            Document document = Jsoup.connect(HDFSHelper.URL + "mkdir")
                    .data("userid", id)
//                    .data("passwd", password)
                    .data("full_path", "/home/" + id + fullPath)
                    .data("filename", filename)
                    .post();
            String result = document.body().ownText();
            if(result.equals("创建成功")){
                return 0;
            }else if(result.equals("文件存在")){
                return 1;
            }else {
                return 2;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 2;
        }
    }

    /**
     * 返回值
     * 貌似都是成功
     */
    public int deleteFile(String fullPath){
        try {
            Document document = Jsoup.connect(HDFSHelper.URL + "deleteFile")
                    .data("userid", id)
//                    .data("passwd", password)
                    .data("full_path", "/home/" + id + fullPath)
                    .post();
            String result = document.body().ownText();
//            System.out.print(result);
            if(result.equals("删除成功")){
                return 0;
            }else {
                return 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * 返回值
     * 貌似全是all right
     * 貌似没效果
     */
    public int renameFile(String path, String preFilename, String newName){
        try {
            Document document = Jsoup.connect(HDFSHelper.URL + "renameFile")
                    .data("userid", id)
//                    .data("passwd", password)
                    .data("path", "/home/" + id + path)
                    .data("pre_filename", preFilename)
                    .data("new_name", newName)
                    .post();
            String result = document.body().ownText();
            System.out.print(result);
            if(result.equals("all right")){
                return 0;
            }else {
                return 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public String getLines(String path){
        try {
            Document document = Jsoup.connect(HDFSHelper.URL + "getLines")
                    .data("userid", id)
//                    .data("passwd", password)
                    .data("full_path", "/home/" + id + path)
                    .post();
            String result = document.body().ownText();
            System.out.print(result);
            return document.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args){
        HDFSService service = new HDFSService(3, "E10ADC3949BA59ABBE56E057F20F883E");
//        service.getFileList("/abc");
//        System.out.print(service.mkdir("/abc", "1"));
//        System.out.print(service.deleteFile("/1"));
//        System.out.println(service.renameFile("/", "abc", "123"));
//        service.getLines("/123.jpg");
    }
}
