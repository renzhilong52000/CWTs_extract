package com.asd.asd.asd;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;


/**
 * @ProjectName: extract
 * @Description: Batch extract Chinese tables
 * @author: RenZhiLong
 * @Date:2020-10-24 am 8:30:36
 */
public class extractChinese {
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

    public static void showDirectory(File file,String target) throws FileNotFoundException, IOException{
    	long t1 = System.currentTimeMillis();
    	File[] files = file.listFiles();
        File targetfile = new File(target);
        
        FileWriter fw = new FileWriter(targetfile,true); 
        BufferedWriter bw = new BufferedWriter(fw);
        
		String str = null;
		String b = null;
		int sum = 0;
		int filecount = 0;
        for(File a:files){
        	/**  
             * Here you can output all the sub-file paths under the file path one by one
             * In the future, you need to add a loop here, and use the decompression class to decompress these file paths.
             * After the decompression, the Chinese extraction program has been used to output a json file
             */ 
//          System.out.println(a.getAbsolutePath());
            filecount ++;
            System.out.printf("Currently inquiring:  " + filecount + "  file......");
//          Unzip and output the file directly
            String fileName = a.getAbsolutePath();//ues GZIPInputStream Unzip GZ file
            FileInputStream Fis = new FileInputStream(fileName);
            InputStream in = new GZIPInputStream(Fis);
            Scanner sc = new Scanner(in);
//			write
            
			int i = 0;
            while(sc.hasNextLine()){
                str = sc.nextLine();
            	for (int j = 0; j < str.length(); j++) { 
            		b = Character.toString(str.charAt(j)); //Returns a string object
            		if (b.matches("[\\u4e00-\\u9fa5]")) {//determine whether it is a Chinese character
            			i++;
             			bw.write(str);
             			bw.newLine();
            			break;
            		}
            	}
            }
            System.out.println("  Extract this file:  " + filecount + "  all elements" + "    Time consuming:  " + (System.currentTimeMillis() - t1)/60000 + "  min " + ((System.currentTimeMillis() - t1)/1000)%60 + " s");
            sum += i;
        }
        System.out.println("total:" + sum + "table");
        bw.write("total:" + sum + "table");
        bw.close();    }

    public static void main(String[] args) throws FileNotFoundException, IOException {   
        File file = new File("E:\\paper1\\CoreOfSummerVacation\\data\\1438042987135.9\\warc"); 
        showDirectory(file,"E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\1438042987135.9.json");
    }
}  
