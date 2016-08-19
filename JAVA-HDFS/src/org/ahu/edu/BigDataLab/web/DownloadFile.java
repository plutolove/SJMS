package org.ahu.edu.BigDataLab.web;

import com.jfinal.core.Controller;
import org.ahu.edu.BigDataLab.util.File.DeleteDir;
import org.ahu.edu.BigDataLab.util.HdfsDao;
import org.ahu.edu.BigDataLab.util.File.ZipFiles;
import java.io.File;
/**
 * Created by Hash.meng on 16-7-19.
 */
public class DownloadFile extends Controller {
    static String rtp = "/home/mycluster/IdeaProjects/javahdfs/web/LocalFile/";

    public static String getRtp() {
        return rtp;
    }
    public static void setRtp(String rtp) {
        DownloadFile.rtp = rtp;
    }

    public void index() throws Exception {
        String userid = getPara("userid");
        String full_path = getPara("path");
        String fname = getPara("filename");
        String local_path = rtp+userid+"-"+fname;
        //String downloadfile = local_path+".zip";
        System.out.println("------"+local_path);
        HdfsDao.getFileLocal(full_path + fname, local_path);
        //ZipFiles.zip(local_path, downloadfile);
        //DeleteDir.deleteDir(new File(local_path));
        //System.out.println(downloadfile);
        renderFile(new File(local_path));
    }
}
