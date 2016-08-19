package org.ahu.edu.BigDataLab.Test;

/**
 * Created by Hash.meng on 16-7-15.
 */
import org.ahu.edu.BigDataLab.util.*;
import java.io.IOException;

public class TestHdfsWrite {
    public static void test() throws IOException {
        HdfsWrite out = new HdfsWrite("/mengshangqi/a.txt");
        for(int i=0; i<300; i++) {
            out.write("test hdfswrite\n");
        }
        out.close();
        HdfsDao dao = new HdfsDao();
        System.out.println(dao.getTop100("/mengshangqi/a.txt"));
    }
}