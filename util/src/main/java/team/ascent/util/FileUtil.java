package team.ascent.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.FileUtils;

/**
 * 基本文件函数
 * 
 */
public class FileUtil extends FileUtils {
    public static long fileCode = 0;
    private static FileOutputStream fs;

    public FileUtil() {

    }

    /**
     * 获取新的文件名称,文件名系统生成
     * 
     */
    public static synchronized String getFileCode() {

        fileCode += 1;
        String code = String.valueOf(fileCode);
        return DateUtil.format(new Date(), "yyyyMMddHHmmss") + code;

    }

    /**
     * 文件分隔符
     * 
     * @return 字符串
     */
    public static String separator() {
        return File.separator;
    }

    /**
     * 复制文件
     * 
     * @param src
     *            String 源文件路径
     * @param dst
     *            String 目标文件路径
     */
    public static void copy(File src, File dst) {
        try {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dst);
            // Transfer bytes from in to out
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建文件夹
     * 
     * @param fileName
     *            String 文件路径
     * 
     */
    public static boolean mkDir(String fileName) {
        boolean make = false;
        try {
            make = (new File(fileName)).mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return make;
    }

    /**
     * 获取文件后缀
     */

    public static String getFileExt(String fileName) {
        if (fileName == null || fileName.equals("")) {
            return "";
        }
        String[] ext = fileName.trim().split("\\.");
        
        return ext.length <=1 ? "" : ext[ext.length - 1].toLowerCase();
    }

    /**
     * 创建写入记事本
     * 
     * @param filePath
     *            String 文件路径
     * 
     * @param txt
     *            String 待写入行字串
     */
    public static void writeTxt(String filePath, String txt) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(filePath, true),
                    true);
            pw.println(txt);
            pw.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    /**
     * 读取文本文件内容
     * 
     * @param filePath
     *            String 文件路径
     * 
     * 
     */
    public static String readTxt(String filePath) {
        String rtn = null;
        File txtFile = new File(filePath);
        FileReader f=null;
        BufferedReader br=null;
        if (!txtFile.exists()) {
            return rtn;
        }

        try {
            f=new FileReader(txtFile);
            br = new BufferedReader(f);
            String s = "";
            StringBuffer sb = new StringBuffer("");
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            f.close();
            br.close();
            return sb.toString();
        } catch (IOException e) {
            e.getStackTrace();
        }finally{
            try {
                f.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return rtn;
    }

    /**
     * 读取文本文件内容
     * 
     * @param filePath
     *            String 文件路径
     * 
     * 
     */
    public static ArrayList<String> readTxtToArray(String filePath) {
        ArrayList<String> al = new ArrayList<String>();

        File txtFile = new File(filePath);
        if (!txtFile.exists()) {
            return al;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(txtFile));
            String s = "";
            // StringBuffer sb = new StringBuffer("");
            while ((s = br.readLine()) != null) {
                System.out.println(s);
                al.add(s);
                // sb.append(s + "\r\n");
            }
            br.close();
            // return sb.toString();
        } catch (IOException e) {
            e.getStackTrace();
        }
        return al;
    }


    /**
     * 删除文件
     * 
     * @param filePathAndName
     *            String 文件路径及名称 如c:/fqf.txt
     * @param fileContent
     *            String
     * @return boolean
     */
    public static void delFile(String filePathAndName) {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            java.io.File myDelFile = new java.io.File(filePath);
            myDelFile.delete();

        } catch (Exception e) {
            System.out.println("删除文件操作出错");
            e.printStackTrace();

        }

    }



    /**
     * 移动文件到指定目录
     * 
     * @param oldPath
     *            String 如：c:/fqf.txt
     * @param newPath
     *            String 如：d:/fqf.txt
     */
    public static void moveFile(String oldPath, String newPath) {
        copyFile(oldPath, newPath);
        delFile(oldPath);

    }

    /**
     * 复制单个文件
     * 
     * @param oldPath
     *            String 原文件路径 如：c:/fqf.txt
     * @param newPath
     *            String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { // 文件存在时
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件
                fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }
    
    public static int getTotalLines(String filePath) throws IOException {  
        File sourceFile = new File(filePath);
        FileReader in = new FileReader(sourceFile);  
        LineNumberReader reader = new LineNumberReader(in);  
        String s = reader.readLine();  
        int lines = 0;  
       while (s != null) {  
            lines++;  
           s = reader.readLine();  
        }  
        reader.close();  
        in.close();  
        return lines;  
    } 
    
    /**
     * 编辑参数值
     * @param param
     * @param value
     */
    public static void editParamValue(String path,String param,String value) {
        param = param + "=";
        String temp = "";
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();

            // 保存该行前面的内容
            for (int j = 1; (temp = br.readLine()) != null
                    && !temp.contains(param); j++) {
                buf = buf.append(temp);
                buf = buf.append(System.getProperty("line.separator"));
            }
            
            // 将内容插入
            buf = buf.append(param+value);

            // 保存该行后面的内容
            while ((temp = br.readLine()) != null) {
                buf = buf.append(System.getProperty("line.separator"));
                buf = buf.append(temp);
            }

            br.close();
            FileOutputStream fos = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getFileSha1(File file) {
	    if (!file.isFile()) {
	        return null;
	    }
	    MessageDigest digest = null;
	    FileInputStream in = null;
	    byte buffer[] = new byte[8192];
	    int len;
	    try {
	        digest =MessageDigest.getInstance("SHA-1");
	        in = new FileInputStream(file);
	        while ((len = in.read(buffer)) != -1) {
	            digest.update(buffer, 0, len);
	        }
	        BigInteger bigInt = new BigInteger(1, digest.digest());
	        return bigInt.toString(16);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        try {
	            in.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

}
