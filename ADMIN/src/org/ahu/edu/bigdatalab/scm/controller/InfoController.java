package org.ahu.edu.bigdatalab.scm.controller;

import com.jfinal.core.Controller;

/**
 * Created by WangJiang on 2016/7/11.
 */
public class InfoController extends Controller {

    public void index() {
        renderJsp("../info.jsp");
    }

}
