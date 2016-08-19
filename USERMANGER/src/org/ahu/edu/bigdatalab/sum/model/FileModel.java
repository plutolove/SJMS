package org.ahu.edu.bigdatalab.sum.model;
/**
 * 文件类
 */
public class FileModel {
    private String filename;
    private String filetype;
    private String filesize;
    private String filetime;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public String getFiletime() {
        return filetime;
    }

    public void setFiletime(String filetime) {
        this.filetime = filetime;
    }

    public FileModel(String filename, String filetype, String filesize, String filetime) {

        this.filename = filename;
        this.filetype = filetype;
        this.filesize = filesize;
        this.filetime = filetime;
    }

    public FileModel(){}


}
