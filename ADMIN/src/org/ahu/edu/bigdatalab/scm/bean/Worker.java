package org.ahu.edu.bigdatalab.scm.bean;

/**
 * Created by WangJiang on 2016/7/10.
 * Spark集群每一台节点
 */
public class Worker {

    private String id;
    private String address;
    private String state;
    private String cores;
    private String memory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCores() {
        return cores;
    }

    public void setCores(String cores) {
        this.cores = cores;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }
}
