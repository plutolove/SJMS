package org.ahu.edu.BigDataLab.web;

import com.jfinal.core.Controller;
import org.ahu.edu.BigDataLab.util.HdfsDao;
import java.io.IOException;

/**
 * Created by Hash.meng on 16-7-18.
 */
public class DeleteFile extends Controller {
    public void index() throws IOException {
        String full_path = getPara("full_path");
        HdfsDao.deleter(full_path);
        renderText("删除成功");
    }
}
