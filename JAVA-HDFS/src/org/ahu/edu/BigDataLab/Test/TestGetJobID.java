package org.ahu.edu.BigDataLab.Test;

/**
 * Created by Hash.meng on 16-7-15.
 */
import java.io.*;
import org.ahu.edu.BigDataLab.util.GetJobID;

class TestGetJobID {
    static void test() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/home/Hash.meng/log"));
        String str = null;
        while((str = br.readLine()) != null) {
            if(GetJobID.getId(str) != null)
                System.out.println(GetJobID.getId(str));
        }
    }
}