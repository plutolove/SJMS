package org.ahu.edu.BigDataLab.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Hash.meng on 16-7-15.
 */
public class GetJobID {
    final static String REGEX = "(app)-([0-9]{14})-([0-9]{4})";
    public static String getId(String line) {
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(line);
        if(m.find()) {
            m.group(0);
            return m.group(0);
        }
        return null;
    }
}