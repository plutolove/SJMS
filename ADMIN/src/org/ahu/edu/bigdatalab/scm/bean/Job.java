package org.ahu.edu.bigdatalab.scm.bean;

/**
 * Created by WangJiang on 2016/7/10.
 */
public class Job {

    private String id;
    private String name;
    private String cores;
    private String memoryPerNode;
    private String submittedTime;
    private String user;
    private String state;
    private String duration;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCores() {
        return cores;
    }

    public void setCores(String cores) {
        this.cores = cores;
    }

    public String getMemoryPerNode() {
        return memoryPerNode;
    }

    public void setMemoryPerNode(String memoryPerNode) {
        this.memoryPerNode = memoryPerNode;
    }

    public String getSubmittedTime() {
        return submittedTime;
    }

    public void setSubmittedTime(String submittedTime) {
        this.submittedTime = submittedTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
