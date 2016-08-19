package org.ahu.edu.BigDataLab.util.File;

/**
 * Created by Hash.meng on 16-7-19.
 */

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;


public class ZipFiles {

    public static void zip(String inputFileName, String zipFileName)
            throws Exception {
        zip(zipFileName, new File(inputFileName));
    }

    private static void zip(String zipFileName, File inputFile)
            throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
                zipFileName));
        out.setEncoding("UTF-8");
        zip(out, inputFile, "");
        out.close();
    }

    private static void zip(ZipOutputStream out, File f, String base)
            throws Exception {
        if (f.isDirectory()) {
            File[] fl = f.listFiles();

            ZipEntry zipEntry = new ZipEntry(base
                    + System.getProperties().getProperty("file.separator"));
            zipEntry.setUnixMode(755);
            out.putNextEntry(zipEntry);
            base = base.length() == 0 ? "" : base
                    + System.getProperties().getProperty("file.separator");
            for (int i = 0; i < fl.length; i++) {
                zip(out, fl[i], base + fl[i].getName());
            }
        } else {
            ZipEntry zipEntry = new ZipEntry(base);
            zipEntry.setUnixMode(644);
            out.putNextEntry(zipEntry);
            FileInputStream in = new FileInputStream(f);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
        }
    }

    public static void unzip(String sourceZip, String destDir) throws Exception {
        try {
            Project p = new Project();
            Expand e = new Expand();
            e.setProject(p);
            e.setSrc(new File(sourceZip));
            e.setOverwrite(false);
            e.setDest(new File(destDir));
            e.setEncoding("GBK");
            e.execute();
        } catch (Exception e) {
            throw e;
        }
    }

    public static void unZip(String zipFile, String outputFolder)
            throws Exception {
        byte[] buffer = new byte[1024];
        String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp",
                "txt", "xls", "zip","jar", "pdf", "doc", "docx", "csv", "xls" };
        File folder = new File(outputFolder);
        if (!folder.exists()) {
            folder.mkdir();
        }

        java.util.zip.ZipInputStream zis = new java.util.zip.ZipInputStream(
                new FileInputStream(zipFile));
        java.util.zip.ZipEntry ze = null;

        while ((ze = zis.getNextEntry()) != null) {
            String fileName = ze.getName();
            File newFile = new File(outputFolder + File.separator + fileName);
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
                    .toLowerCase();

            if (Arrays.<String> asList(fileTypes).contains(fileExt)) {
                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();

            } else {
                newFile.mkdirs();
            }
        }
        zis.closeEntry();
        zis.close();
    }

    public static final int BUFFER = 1024;


    public static void compress(String baseDir, String filePath)
            throws Exception {
        List fileList = getSubFiles(new File(baseDir));
        ZipOutputStream zos = new ZipOutputStream(
                new FileOutputStream(filePath));
        ZipEntry ze = null;
        byte[] buf = new byte[BUFFER];
        int readLen = 0;
        for (int i = 0; i < fileList.size(); i++) {
            File f = (File) fileList.get(i);
            ze = new ZipEntry(getAbsFileName(baseDir, f));
            ze.setSize(f.length());
            ze.setTime(f.lastModified());
            zos.putNextEntry(ze);
            InputStream is = new BufferedInputStream(new FileInputStream(f));
            while ((readLen = is.read(buf, 0, BUFFER)) != -1) {
                zos.write(buf, 0, readLen);
            }
            is.close();
        }
        zos.close();
    }

    public static void decompress(String filePath, String destDir)
            throws Exception {
        decompress(new File(filePath), destDir);
    }


    public static void decompress(File zip, String destDir) throws Exception {
        destDir = destDir.endsWith("//") ? destDir : destDir + "//";
        byte b[] = new byte[BUFFER];
        int length;
        ZipFile zipFile;
        zipFile = new ZipFile(zip);
        Enumeration enumeration = zipFile.getEntries();
        ZipEntry zipEntry = null;

        while (enumeration.hasMoreElements()) {
            zipEntry = (ZipEntry) enumeration.nextElement();
            File loadFile = new File(destDir + zipEntry.getName());

            if (zipEntry.isDirectory()) {
                loadFile.mkdirs();
            } else {
                if (!loadFile.getParentFile().exists())
                    loadFile.getParentFile().mkdirs();

                OutputStream outputStream = new FileOutputStream(loadFile);
                InputStream inputStream = zipFile.getInputStream(zipEntry);

                while ((length = inputStream.read(b)) > 0)
                    outputStream.write(b, 0, length);
                inputStream.close();
                outputStream.close();
            }
        }
        zipFile.close();
    }

    private static String getAbsFileName(String baseDir, File realFileName) {
        File real = realFileName;
        File base = new File(baseDir);
        String ret = real.getName();
        while (true) {
            real = real.getParentFile();
            if (real == null)
                break;
            if (real.equals(base))
                break;
            else
                ret = real.getName() + "/" + ret;
        }
        return real.getName() + "/" + ret;
    }

    @SuppressWarnings("unchecked")
    private static List getSubFiles(File baseDir) {
        List ret = new ArrayList();
        File[] tmp = baseDir.listFiles();
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i].isFile())
                ret.add(tmp[i]);
            if (tmp[i].isDirectory())
                ret.addAll(getSubFiles(tmp[i]));
        }
        return ret;
    }
}