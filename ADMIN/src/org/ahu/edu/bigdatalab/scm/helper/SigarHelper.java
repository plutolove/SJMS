package org.ahu.edu.bigdatalab.scm.helper;

import org.hyperic.sigar.Sigar;

public class SigarHelper {

    private static Sigar sigar = null;

    private SigarHelper() {
    }

    public static Sigar getInstance() {
        if (sigar == null) {
            sigar = new Sigar();
        }
        return sigar;
    }

}
