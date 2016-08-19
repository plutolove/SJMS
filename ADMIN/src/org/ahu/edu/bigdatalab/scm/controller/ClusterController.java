package org.ahu.edu.bigdatalab.scm.controller;

import com.jfinal.core.Controller;

/**
 * Created by WangJiang on 2016/7/10.
 * 节点管理
 */
public class ClusterController extends Controller {

    public void index() {
        renderJsp("../cluster.jsp");
    }

    /**
     * 关闭节点
     */
    public void shutdown() {
        renderText("shutdown");
    }

    /**
     * 重启节点
     */
    public void reboot() {
        renderText("reboot");
    }

}
