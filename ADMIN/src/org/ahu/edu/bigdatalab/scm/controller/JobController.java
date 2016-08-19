package org.ahu.edu.bigdatalab.scm.controller;

import com.jfinal.core.Controller;

/**
 * Created by WangJiang on 2016/7/11.
 * Job管理控制器
 */
public class JobController extends Controller {

    public void index() {

        renderJsp("../job.jsp");
    }

}
