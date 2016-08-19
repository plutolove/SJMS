package org.ahu.edu.BigDataLab.util;

/**
 * Created by Hash.meng on 16-7-15.
 */
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.ahu.edu.BigDataLab.config.Config;

public class HdfsDao {
    static String url = Config.getHdfs(); //"hdfs://127.0.0.1:9000";
    static Configuration conf = new Configuration();
    public static void setUrl(String url) {
        HdfsDao.url = url;
    }
    //判断文件是否存在
    public static boolean isexits(Path path) throws IOException {
        FileSystem fs = FileSystem.get(URI.create(url), conf);
        boolean res = fs.exists(path);
        return res;
    }
    //判断文件是否存在
    public static boolean isexits(String path) throws IOException {
        FileSystem fs = FileSystem.get(URI.create(url), conf);
        Path tmp = new Path(path);
        boolean res = fs.exists(tmp);
        return res;
    }
    //创建文件夹 传入文件夹完整路径 创建不成功返回文件存在否则返回创建成功
    public static String mkdir(String full_path) throws IOException {
        FileSystem fs = FileSystem.get(URI.create(url), conf);
        Path path = new Path(full_path);
        if(isexits(path)) return "文件存在";
        fs.mkdirs(path);
        return "创建成功";
    }
    // 重命名文件
    public static void RenameFile(String pre, String now) throws Exception{
        FileSystem fs = FileSystem.get(URI.create(url),conf);
        Path path = new Path(pre);
        Path newPath = new Path(now);
        fs.rename(path, newPath);
    }
    // 返回某个文件夹下的所有文件
    public static FileStatus[] ListFIles(String path) throws Exception {
        FileSystem fs = FileSystem.get(URI.create(url), conf);
        Path dst = new Path(path);
        FileStatus[] files = fs.listStatus(dst);
        return files;
    }
    //删除文件夹及其文件
    public static void deleter(String folder) throws IOException {
        Path path = new Path(folder);
        FileSystem fs = FileSystem.get(URI.create(url), conf);
        fs.delete(path, true);
        System.out.println("Delete: " + folder);
    }
    //读取文件前100行
    public static String getTop100(String file) throws IOException {
        StringBuilder res = new StringBuilder();
        Path path = new Path(file);
        FileSystem fs = FileSystem.get(URI.create(url), conf);
        InputStream in = fs.open(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "GBK"));
        String line = null;

        for(int i=0; i<500 && (line = br.readLine())!=null; i++) {
            //res = res.append("&lt;tr&gt;&lt;td&gt;"+line+"&lt;/td&gt;&lt;/tr&gt;");
            res = res.append(new String((line+"&lt;br&gt;").getBytes(), "UTF-8"));
        }
        br.close();
        in.close();
        return res.toString();
    }

    //下载到本地
    public static String getFileLocal(String hdfs_path, String local_path) throws IOException {
        Path path = new Path(hdfs_path);
        FileSystem fs = FileSystem.get(URI.create(url), conf);
        System.out.println(path);
        fs.copyToLocalFile(path, new Path(local_path));
        return "copy to local file";
    }
}