package org.ahu.edu.BigDataLab.web;

import com.jfinal.core.Controller;
import org.ahu.edu.BigDataLab.util.File.GetListOfJson;
/**
 * Created by Hash.meng on 16-7-18.
 */
public class GetFileList extends Controller {
    public void index() throws Exception {
        String full_path = getPara("full_path");
        renderText(GetListOfJson.getJson(full_path));
    }
}
