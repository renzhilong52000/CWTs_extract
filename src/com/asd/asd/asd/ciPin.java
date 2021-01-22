package com.asd.asd.asd;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

/**
 * @ProjectName: extract
 * @Description: 
 * @author: RenZhiLong
 * @Date:2020-10-22 5:43:21
 */
public class ciPin {
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
		float m = 0,n = 0,temp = 0;
        float c = 0,d = 0;
		Map<String, Integer> myTreeMap = new TreeMap<String, Integer>();
        for(File a:files){
        	/**  
             * Here you can output all the sub-file paths under the file path one by one
             * In the future, you need to add a loop here, and use the decompression class to decompress these file paths.
             * After the decompression, the Chinese extraction program has been used to output a json file
             */ 
//            System.out.println(a.getAbsolutePath());
            filecount ++;
            System.out.println("Currently inquiring:  " + filecount + "  file......");
//          unZip and output the file directly
            String fileName = a.getAbsolutePath();//ues GZIPInputStream Unzip GZ file
            FileInputStream Fis = new FileInputStream(fileName);
            InputStream in = new GZIPInputStream(Fis);
            Scanner sc = new Scanner(in);

//			write
            

            while(sc.hasNextLine()){

                str = sc.nextLine();
                String[] elements = str.split("[,.:;\"_\\s+\\W+\\d+]"); //:;\" 
              
              int count = 0;  
              //遍历数组将其存入Map<String, Integer>中  
              for(int i = 0; i < elements.length; i++) {
                  if(myTreeMap.containsKey(elements[i])) {  
                      count = myTreeMap.get(elements[i]);  
                      myTreeMap.put(elements[i], count + 1);  
                  }  
                  else {
                      myTreeMap.put(elements[i], 1);  
                  }  
              }
            }
            System.out.println("  Extract this file:  " + filecount + "  all elements" + "    Time consuming:  " + (System.currentTimeMillis() - t1)/60000 + "  min " + ((System.currentTimeMillis() - t1)/1000)%60 + " s");
//            sum += i;
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(myTreeMap.entrySet());  
        	//将map.entrySet()转换成list  
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
            //通过比较器实现排序  
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
                return o2.getValue().compareTo(o1.getValue());  
            }  
        });
        int num = 1;
        //将结果写入文件  
        for(Map.Entry<String, Integer> map : list) {
//        	System.out.println(map.getKey());
            if(num <= 500) {  
                bw.write("出现次数第" + num + "的单词为：" + map.getKey() + "   出现频率为" + map.getValue() + "次");
                bw.newLine();
                num++;  
            }
        }
        bw.newLine();
        
        for(Map.Entry<String, Integer> map : list) {
        	if(map.getKey().equals("VERTICAL")){// || map.getKey().equals("vertical")
//            	System.out.println(map.getKey());
            	bw.write("\"VERTICAL\"单词出现频率为" + map.getValue() + "次");
            	bw.newLine();
            	m = map.getValue();
            }

            if(map.getKey().equals("HORIZONTAL")){// || map.getKey().equals("horizontal")
//            	System.out.println(map.getKey());
            	bw.write("\"HORIZONTAL\"单词出现频率为" + map.getValue() + "次"); 
            	bw.newLine();
            	n = map.getValue();
            }
        }
        temp = m + n;
        c = n/temp;
        d = 1-c;

        bw.write("此次共查询:" + temp + " 个表格中水平表约占" + c);
        bw.newLine();
        bw.write("此次共查询:" + temp + " 个表格中垂直表约占" + d);
        bw.newLine();
        
        bw.write("cost times"+ (System.currentTimeMillis() - t1) + "ms");  
        System.out.println("cost times"+ (System.currentTimeMillis() - t1) + "ms");


        bw.close();
    }
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {   
//        File file1 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\GZFile"); 
//        showDirectory(file1,"E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\ciPin1.json");
        File file1 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\test\\ab"); 
        showDirectory(file1,"E:\\paper1\\CoreOfSummerVacation\\data\\test\\ciPintest.json");
    }
} 