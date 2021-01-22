package com.asd.asd.asd;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

/**
 * @ProjectName: extract
 * @Description:
 * @author: Ren Zhilong
 * @Date:2020-11-10上午10:49:51
 */
public class extractSameUrl {

    /**  
     * Used to extract and return the extension of a given file.
     */   
    public static String getExtension(String f) {   
        String ext = "";   
        int i = f.lastIndexOf('.');   
  
        if (i > 0 &&  i < f.length() - 1) {   
            ext = f.substring(i+1);   
        }        
        return ext;   
    }   
  
    /**  
     * Used to extract the file name without extension.
     */   
    public static String getFileName(String f) {   
        String fname = "";   
        int i = f.lastIndexOf('.');   
  
        if (i > 0 &&  i < f.length() - 1) {   
            fname = f.substring(0,i);   
        }        
        return fname;   
    }
    /**  
     * Recursively traverse all files in a folder.
     * @throws IOException 
     * @throws FileNotFoundException 
     */ 
    private static String filterStr(String regex,String data){

	    StringBuffer sb = new StringBuffer(); //sb Store the result of regular matching存放正则匹配的结果
	    Pattern p = Pattern.compile(regex); //Compile regular string 编译正则字符串
	    Matcher matcher = p.matcher(data); //Extract regular input characters 提取正则输入的字符
	    while (matcher.find()) {
	        sb.append(matcher.group() + "\r\n");
	    	}
	    return sb.toString();
		}
    
    public static void showDirectory(File file,String urlDom,String target) throws FileNotFoundException, IOException{
    	long t1 = System.currentTimeMillis();
    	File[] files = file.listFiles();
        File targetfile = new File(target);
        
        FileWriter fw = new FileWriter(targetfile,true); 
        BufferedWriter bw = new BufferedWriter(fw);
        
		String str1 = null;
		int filecount = 0;

        for(File a:files){
        	/**  
             * Here you can output all the sub-file paths under the file path one by one
             * In the future, you need to add a loop here, and use the decompression class to decompress these file paths.
             * After the decompression, the Chinese extraction program has been used to output a json file
             */ 
//            System.out.println(a.getAbsolutePath());
            filecount ++;
            System.out.println("Currently inquiring:  " + filecount + "  file......");
//          Unzip and output the file directly
            String fileName = a.getAbsolutePath();//ues GZIPInputStream Unzip GZ file
            FileInputStream Fis = new FileInputStream(fileName);
            InputStream in = new GZIPInputStream(Fis);
            Scanner sc = new Scanner(in);
            String regex = "\"url\":\"(.+?)\"";
            String regex1 = "(?<=://)[a-zA-Z\\.0-9]+(?=\\/)";
            String regex2 = "\\.[a-z]{2,5}";
//			write

            while(sc.hasNextLine()){
            	str1 = sc.nextLine();
            	String str2 = filterStr(regex, str1);
            	String str3 = filterStr(regex1, str2);
            	String str4 = filterStr(regex2, str3);
            	String[] str = str4.toString().split("\n|\r");
            	List<String> list = Arrays.asList(str);
                boolean flag = list.contains(urlDom);
                if(flag){
                	bw.write(str1);
         			bw.newLine();
                }
            }

            System.out.println("  Extract this file:  " + filecount + "  all elements" + "    Time consuming:  " + (System.currentTimeMillis() - t1)/60000 + "  min " + ((System.currentTimeMillis() - t1)/1000)%60 + " s");
            System.out.println("");	
        }

        bw.close();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
//        File file1 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\test\\ab"); 
//        showDirectory(file1,".com","E:\\paper1\\CoreOfSummerVacation\\data\\test\\extractSameUrl.json");
    	File file1 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\gzFile"); 
        showDirectory(file1,".net","E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\extractSameUrl.net.json");
        File file2 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\gzFile"); 
        showDirectory(file2,".co","E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\extractSameUrl.co.json");
        File file3 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\gzFile"); 
        showDirectory(file3,".edu","E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\extractSameUrl.edu.json");
        File file4 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\gzFile"); 
        showDirectory(file4,".gov","E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\extractSameUrl.gov.json");
        File file5 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\gzFile"); 
        showDirectory(file5,".cn","E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\extractSameUrl.cn.json");
    	
    	
//    	File file0 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\0.9"); 
//    	showDirectory(file0,".com","E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\url.org\\extractSameUrl.com0.json");
//        File file1 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\10.19"); 
//        showDirectory(file1,".com","E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\url.org\\extractSameUrl.com1.json");
//        File file2 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\20.29"); 
//        showDirectory(file2,".com","E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\url.org\\extractSameUrl.com2.json");
//        File file3 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\30.39"); 
//        showDirectory(file3,".com","E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\url.org\\extractSameUrl.com3.json");
//        File file4 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\40.49"); 
//        showDirectory(file4,".com","E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\url.org\\extractSameUrl.com4.json");
//        File file5 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\50.59"); 
//        showDirectory(file5,".com","E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\url.org\\extractSameUrl.com5.json");
//        File file6 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\60.69"); 
//        showDirectory(file6,".com","E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\url.org\\extractSameUrl.com6.json");
//        File file7 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\70.79"); 
//        showDirectory(file7,".com","E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\url.org\\extractSameUrl.com7.json");
//        File file8 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\80.89"); 
//        showDirectory(file8,".com","E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\url.org\\extractSameUrl.com8.json");
//        File file9 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\90.99"); 
//        showDirectory(file9,".com","E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\url.org\\extractSameUrl.com9.json");
        }
}
