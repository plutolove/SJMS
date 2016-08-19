package org.ahu.edu.bigdatalab.sum.model;

public class PathModel {
    private String folderName;
    private String folderPath;

    public PathModel(String folderName, String folderPath) {
        this.folderName = folderName;
        this.folderPath = folderPath;
    }

    public PathModel(){}

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    @Override
    public String toString() {
        return "PathModel{" +
                "folderName='" + folderName + '\'' +
                ", folderPath='" + folderPath + '\'' +
                '}';
    }
}
