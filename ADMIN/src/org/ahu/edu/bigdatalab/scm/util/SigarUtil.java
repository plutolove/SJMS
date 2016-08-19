package org.ahu.edu.bigdatalab.scm.util;

import org.ahu.edu.bigdatalab.scm.helper.SigarHelper;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class SigarUtil {
    public final static String DISK_TYPE = "disk_type"; //磁盘的格式NTFS,FAT
    public final static String DISK_TOTAL = "disk_total";
    public final static String DISK_USED = "disk_used";
    public final static String DISK_FREE = "disk_free";

    public final static String OS_VENDOR_NAME = "vendor_name"; //供应商名称
    public final static String OS_VENDOR = "vendor";
    public final static String OS_ARCH = "arch";    //构架
    public final static String OS_VERSION = "version";    //版本号
    public final static String OS_NAME = "name";
    public final static String OS_JAVA_VERSION = "java_version";


    public static String[] getCpuInfo() {
        Sigar sigar = SigarHelper.getInstance();
        String[] result = new String[3];
        try {
            CpuInfo info = sigar.getCpuInfoList()[0];
            result[0] = info.getModel();
            result[1] = sigar.getCpuInfoList().length + "核";
            result[2] = info.getMhz() + "Mhz";
            return result;
        } catch (SigarException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取磁盘信息
     *
     * @return
     */
    public static Map<String, String> getDiskInfo() {
        Map<String, String> diskInfo = new HashMap<>();

        Sigar sigar = SigarHelper.getInstance();
        FileSystem disk;
        FileSystemUsage usage;
        try {
            if (sigar == null) {
                TestUtil.log("IS NULL");
                sigar = new Sigar();
            }

            disk = sigar.getFileSystemList()[0];
            usage = sigar.getFileSystemUsage(disk.getDirName());

            diskInfo.put(DISK_TYPE, disk.getSysTypeName());
            diskInfo.put(DISK_TOTAL, String.valueOf(usage.getTotal() / 1024 / 1024));
            diskInfo.put(DISK_USED, String.valueOf(usage.getUsed() / 1024 / 1024));
            diskInfo.put(DISK_FREE, String.valueOf(usage.getFree() / 1024 / 1024));
            diskInfo.put(DISK_TOTAL, String.valueOf(usage.getTotal() / 1024 / 1024));
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return diskInfo;
    }

    public static Map<String, String> getOSInfo() {
        Map<String, String> osInfo = new HashMap<>();

        Properties props = System.getProperties();

        OperatingSystem OS = OperatingSystem.getInstance();
        osInfo.put(OS_VENDOR_NAME, OS.getVendorName());
        osInfo.put(OS_VENDOR, OS.getVendor());
        osInfo.put(OS_ARCH, props.getProperty("os.arch"));
        osInfo.put(OS_VERSION, OS.getVersion());
        osInfo.put(OS_NAME, props.getProperty("os.name"));
        osInfo.put(OS_JAVA_VERSION, props.getProperty("java.version"));
        return osInfo;
    }

}
