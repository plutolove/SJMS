package org.ahu.edu.BigDataLab.Test;

/**
 * Created by Hash.meng on 16-7-15.
 */
import org.ahu.edu.BigDataLab.util.File.ZipFiles;
import org.ahu.edu.BigDataLab.util.File.DeleteDir;
import org.ahu.edu.BigDataLab.Job.JobManage;
import org.ahu.edu.BigDataLab.util.HdfsDao;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) throws Exception {
        HdfsDao.getFileLocal("/home/A/quick-3.5final.zip", "/home/Hash.meng/IdeaProjects/javahdfs/web/LocalFile/a.zip");
        //ZipFiles.zip("/home/Hash.meng/localfile/A-A", "/home/Hash.meng/IdeaProjects/javahdfs/web/LocalFile/");
        //DeleteDir.deleteDir(new File("/home/Hash.meng/localfile/A-A"));
        //JobManage.submit();
        /*String REGEX = "(ERROR)";
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher("sdfg dsfgsdfg dsfgsdfgERROR sdfgsdfg");
        if(m.find()) {
            System.out.println(m.group(0));
        }*/
        HdfsDao.getTop100("/home/3/10KB.txt");
    }
}