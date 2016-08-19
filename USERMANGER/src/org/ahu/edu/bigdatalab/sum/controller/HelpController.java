package org.ahu.edu.bigdatalab.sum.controller;

import com.jfinal.core.Controller;

/**
 * Created by WangJiang on 2016/8/4.
 */
public class HelpController extends Controller {

    public void index() {

        renderJsp("help.jsp");
    }

}
