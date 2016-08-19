package org.ahu.edu.bigdatalab.scm.controller;

import com.jfinal.core.Controller;
import org.ahu.edu.bigdatalab.scm.helper.SigarHelper;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 * Created by WangJiang on 2016/7/10.
 * 处理首页
 */
public class IndexController extends Controller {

    public void index() {
        renderJsp("index.jsp");
    }

    /**
     * 获取内存占用信息
     * 返回结果{"code":0,"data":10.23}
     */
    public void getMemInfo() throws SigarException {
        Sigar sigar = SigarHelper.getInstance();
        Mem mem = sigar.getMem();

        mem.getTotal();
        mem.getUsed();
        String jsonResult = "{\"code\":0,\"data\":"+100.0*mem.getUsed()/mem.getTotal()+",\"total\":"+mem.getTotal()/1024/1024+"}";
        renderJson(jsonResult);
    }

    /**
     * 获取CPU信息
     * 返回结果{"code":0,"data":10.23}
     * @throws SigarException
     */
    public void getCpuInfo() throws SigarException {
        CpuPerc cpuCerc = SigarHelper.getInstance().getCpuPerc();
        CpuPerc.format(cpuCerc.getCombined());
        String jsonResult = "{\"code\":0,\"data\":"+CpuPerc.format(cpuCerc.getCombined()).replace("%", "")+"}";
        renderJson(jsonResult);
    }

}
