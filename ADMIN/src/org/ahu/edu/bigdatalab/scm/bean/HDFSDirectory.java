package org.ahu.edu.bigdatalab.scm.bean;

import java.util.List;

/**
 * Created by WangJiang on 2016/7/18.
 */
public class HDFSDirectory {

    public final static int isFolder = 0;
    public final static int isFile = 1;

    /**
     * fname : Hash.meng
     * ftype : 0
     * size : 0B
     * mtime : 2016-07-27-10:42:15
     */

    private List<HDFSFile> list;

    public List<HDFSFile> getList() {
        return list;
    }

    public void setList(List<HDFSFile> list) {
        this.list = list;
    }

    public static class HDFSFile {
        private String fname;
        private int ftype;
        private String size;
        private String mtime;

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public int getFtype() {
            return ftype;
        }

        public void setFtype(int ftype) {
            this.ftype = ftype;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getMtime() {
            return mtime;
        }

        public void setMtime(String mtime) {
            this.mtime = mtime;
        }
    }
}
