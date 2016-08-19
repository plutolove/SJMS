package org.ahu.edu.bigdatalab.scm.util;

import com.jfinal.core.JFinal;
import com.jfinal.upload.UploadFile;

import java.io.*;

/**
 * Created by WangJiang on 2016/7/10.
 */
public class FileUtil {

    public final static String REAL_PATH = JFinal.me().getServletContext().getRealPath("/");


    /**
     * 返回存储的文件名称
     * @param uploadFile
     * @return
     */
    public static String savePhoto(UploadFile uploadFile){
        String fileName = null;

        if(uploadFile != null){
            //上传的文件存在
            File tempFile = uploadFile.getFile();
            TestUtil.log(uploadFile.getFileName());

            //重命名文件，时间+随机的6位字符串+文件原始名称
            fileName =DateUtil.getCurrentDateSimple() +TextUtil.getRandomString(6)+ uploadFile.getFileName();
            try {
                FileInputStream fis = new FileInputStream(tempFile);
                File targetDir = new File(REAL_PATH+"upload/photo/");
                if (!targetDir.exists()) {
                    targetDir.mkdirs();
                }
                //保存
                File target = new File(targetDir,  fileName);
                if (!target.exists()) {
                    target.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(target);
                byte[] bts = new byte[300];
                while (fis.read(bts, 0, 300) != -1) {
                    fos.write(bts, 0, 300);
                }
                fos.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

}
