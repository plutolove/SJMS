package org.ahu.edu.BigDataLab.util.File;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.ahu.edu.BigDataLab.util.HdfsDao;
import org.apache.hadoop.fs.FileStatus;
import com.google.gson.*;


/**
 * Created by Hash.meng on 16-7-15.
 */

class FileList {
    public ArrayList<HdfsFile> list = new ArrayList<HdfsFile>();
}


public class GetListOfJson {

    static String getSize(long size) {
        if(size < 1024) {
            return size+"B";
        }else if(size>=1024 && size < 1024* 1024) {
            return size/1024+"KB";
        }else {
            return size/1024/1024+"MB";
        }
    }

    public static String getJson(String path) throws Exception {
        HdfsDao hdfs = new HdfsDao();
        FileStatus[] files = hdfs.ListFIles(path);
        Gson gs = new Gson();
        FileList flist = new FileList();
        for(int i=0; i<files.length; i++) {
            String name = files[i].getPath().getName();
            int type = files[i].isDir()? 0:1;
            long size = files[i].getLen();
            long sec = files[i].getModificationTime();

            Date date = new Date(sec);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
            String time = sdf.format(date);
            String siz = getSize(size);

            flist.list.add(new HdfsFile(name, type, siz, time));
        }
        String res = gs.toJson(flist);
        return res;
    }
}
