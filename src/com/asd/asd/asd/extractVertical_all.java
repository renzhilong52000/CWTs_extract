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
 * @Description:extract vertical
 * @author: RenZhilong
 * @Date:2020-10-31 pm 1:38:41
 */
public class extractVertical_all {
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
//			write


            while(sc.hasNextLine()){
            	str = sc.nextLine();
            	if(str.length() == 0){
        			break;
        		}
            	
            	for (int j = 0; j < str.length(); j++) {
//            		b = Character.toString(str.charAt(j)); 
            		boolean status = str.contains("HORIZONTAL");
            		if(status){
//             			bw.write(str);
//             			bw.newLine();
            			break;
            		}else{
             			bw.write(str);
             			bw.newLine();
             			break;
            		}
            	}
            }
            System.out.println("  Extract this file:  " + filecount + "  all elements" + "    Time consuming:  " + (System.currentTimeMillis() - t1)/60000 + "  min " + ((System.currentTimeMillis() - t1)/1000)%60 + " s");
//            sum += i;
        }
        
        bw.newLine();
        bw.write("time cost" + (System.currentTimeMillis() - t1) + "ms");  
        System.out.println("time cost" + (System.currentTimeMillis() - t1) + "ms");
        bw.close();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
    	File file0 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\0.9"); 
        showDirectory(file0,"E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\VERTICAL\\VERTICAL_0.9.json");
    	File file1 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\10.19"); 
        showDirectory(file1,"E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\VERTICAL\\VERTICAL_10.19.json");
        File file2 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\20.29"); 
        showDirectory(file2,"E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\VERTICAL\\VERTICAL_20.29.json");
        File file3 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\30.39"); 
        showDirectory(file3,"E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\VERTICAL\\VERTICAL_30.39.json");
        File file4 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\40.49"); 
        showDirectory(file4,"E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\VERTICAL\\VERTICAL_40.49.json");
        File file5 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\50.59"); 
        showDirectory(file5,"E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\VERTICAL\\VERTICAL_50.59.json");
        File file6 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\60.69"); 
        showDirectory(file6,"E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\VERTICAL\\VERTICAL_60.69.json");
        File file7 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\70.79"); 
        showDirectory(file7,"E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\VERTICAL\\VERTICAL_70.79.json");
        File file8 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\80.89"); 
        showDirectory(file8,"E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\VERTICAL\\VERTICAL_80.89.json");
        File file9 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\90.99"); 
        showDirectory(file9,"E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\VERTICAL\\VERTICAL_90.99.json");
//        File file1 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\test\\ab"); 
//        showDirectory(file1,"E:\\paper1\\CoreOfSummerVacation\\data\\test\\H_O_ExtractTest.json");
    }
}