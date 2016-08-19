package org.ahu.edu.bigdatalab.scm.controller;

import com.google.gson.Gson;
import com.jfinal.core.Controller;
import org.ahu.edu.bigdatalab.scm.bean.HDFSDirectory;
import org.ahu.edu.bigdatalab.scm.helper.HDFSHelper;
import org.ahu.edu.bigdatalab.scm.util.TestUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by WangJiang on 2016/7/11.
 * HDFS管理
 */
public class HDFSController extends Controller {

    public void index() {
        String path = getPara("path");
        HDFSDirectory directory = null;
        if (path == null) {
            //如果目录为空则认为是Home根目录
            path = "/home/";
        }
        TestUtil.log(path);
        Document document = null;
        try {
            document = Jsoup.connect(HDFSHelper.URL + "getFileList?full_path=" + path).get();
            System.out.println(document.text());
            // 使用Gson解析数据
            directory = new Gson().fromJson(document.text(), HDFSDirectory.class);
        } catch (Exception e) {
            e.printStackTrace();
            directory = new HDFSDirectory();
            directory.setList(new LinkedList<>());  //防止JSP异常
            TestUtil.log(e.toString());
        }
        setAttr("directory", directory);
        setAttr("path", path);
        renderJsp("../hdfs.jsp");
    }

    public void add() {
        String path = getPara("path");
        String name = getPara("name");
        String url = HDFSHelper.URL + "mkdir?full_path=" + path + "/&filename=" + name;
       hdfsUrlGet(url);
    }

    public void rename() {
        String path = getPara("path");
        String oldName = getPara("oldName");
        String newName = getPara("newName");

        String url = HDFSHelper.URL +
                "renameFile?path=" + path + "/&pre_filename=" + oldName + "&new_name=" + newName;
       hdfsUrlGet(url);
    }

    /**
     * 删除文件或文件夹
     */
    public void delete() throws IOException {
        String file = getPara("file");
        String url = HDFSHelper.URL + "deleteFile?full_path=" + file;
        hdfsUrlGet(url);
    }

    /**
     * 文件预览前100行
     */
    public void fileView() throws IOException {
        String file = getPara("file");      //xx.txt
        String url = HDFSHelper.URL + "getLines?full_path=" + file;
        TestUtil.log(url);
        Document document = Jsoup.connect(url).get();

        TestUtil.log(document.text());
        String data = document.text();
        setAttr("data", data);
        renderJsp("../hdfs-file.jsp");
    }

    public void download() {
        String file = getPara("file");      //xx.txt
        String path = getPara("path");      // /home/XX/XXX

        String userId = path.replace("/home/", "").split("/")[0];    // /home/XXX
        String url = HDFSHelper.URL + "downloadFile?userid=" + userId + "&path=" + path + "/&filename=" + file;
        TestUtil.log(url);
        redirect(url);
    }

    /**
     * 调用远程接口，提交参数
     */
    private void hdfsUrlGet(String url) {
        try {
            //调用接口
            Jsoup.connect(url).get();
            renderJson("{\"result\":\"OK\"}");
        } catch (IOException e) {
            e.printStackTrace();
            renderJson("{\"result\":\"ERROR\"}");
        }
    }
}
