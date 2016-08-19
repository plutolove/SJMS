package org.ahu.edu.BigDataLab.web;

import com.jfinal.core.Controller;
import org.ahu.edu.BigDataLab.util.HdfsDao;
/**
 * Created by Hash.meng on 16-7-18.
 */
public class RenameFile extends Controller {
    public void index() throws Exception {
        String path = getPara("path");
        String pref = getPara("pre_filename");
        String newf = getPara("new_name");
        HdfsDao.RenameFile(path+pref, path+newf);
        renderText("all right");
    }
}
