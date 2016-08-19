package org.ahu.edu.BigDataLab.web;

import com.jfinal.core.Controller;
import com.jfinal.render.ContentType;
import org.ahu.edu.BigDataLab.util.HdfsDao;
import java.io.IOException;

/**
 * Created by Hash.meng on 16-7-19.
 */
public class GetFileLines extends Controller {
    public void index() throws IOException {
        String full_path = getPara("full_path");
        String res = HdfsDao.getTop100(full_path);
        renderText(res);
    }
}
