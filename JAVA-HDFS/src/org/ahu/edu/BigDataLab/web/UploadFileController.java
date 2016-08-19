package org.ahu.edu.BigDataLab.web;

import com.jfinal.core.Controller;
import com.jfinal.core.Injector;
import com.jfinal.upload.UploadFile;
import org.ahu.edu.BigDataLab.util.HdfsWrite;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hash.meng on 16-7-18.
 */
public class UploadFileController extends Controller {
    public void index() throws IOException {
        List<UploadFile> file = getFiles("", Integer.MAX_VALUE);
        for(UploadFile uf : file) {
            String path = getPara("full_path") + uf.getFileName();
            BufferedReader br = new BufferedReader(new FileReader(uf.getFile()));
            HdfsWrite hw = new HdfsWrite(path);
            String line = null;
            if(uf.getFileName().endsWith(".jar") || uf.getFileName().endsWith(".tar.gz")) {
                InputStream in = new FileInputStream(uf.getFile());
                byte b[] = new byte[1024];
                int eof = 0;
                while ((eof = in.read()) != -1) {
                    hw.write(eof);
                }
            }else {
                while ((line = br.readLine()) != null) {
                    hw.write(line + "\n");
                }
            }
            hw.close();
        }
        getResponse().setHeader("Access-Control-Allow-Origin", "*");
        renderText("There is nothing");
    }
}