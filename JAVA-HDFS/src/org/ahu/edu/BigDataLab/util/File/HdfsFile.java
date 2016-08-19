package org.ahu.edu.BigDataLab.util.File;

/**
 * Created by Hash.meng on 16-7-15.
 */
public class HdfsFile {
    String fname;
    int ftype;
    String size;
    String mtime;
    public HdfsFile(String fname, int ftype, String size, String mtime) {
        this.fname = fname;
        this.ftype = ftype;
        this.mtime = mtime;
        this.size = size;
    }
}
