package org.ahu.edu.BigDataLab.web;

import com.jfinal.core.Controller;
import org.ahu.edu.BigDataLab.util.HdfsDao;
import java.io.IOException;

/**
 * Created by Hash.meng on 16-7-18.
 */
public class CreateDir extends Controller {
    public void index() throws IOException {
        String full_path = getPara("full_path");
        String fname = getPara("filename");
        String res = HdfsDao.mkdir(full_path+fname);
        renderText(res);
    }
}