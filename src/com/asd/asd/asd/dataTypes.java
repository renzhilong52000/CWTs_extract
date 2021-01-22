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
 * @author: Ren Zhilong
 * @Date:2020-11-26下午6:24:02
 */
public class dataTypes {
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

	    StringBuffer sb = new StringBuffer(); //sb存放正则匹配的结果
	    Pattern p = Pattern.compile(regex); //编译正则字符串
	    Matcher matcher = p.matcher(data); //提取正则输入的字符
	    while (matcher.find()) { //发现后保存
	        sb.append(matcher.group() + "\r\n");
	        //添加到sb
	    	}
//	    System.out.println("查询总行数：   " + i);
	    return sb.toString();
		}
    public static Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
    public static void showDirectory(File file,String target) throws FileNotFoundException, IOException{
    	long t1 = System.currentTimeMillis();
    	File[] files = file.listFiles();
        File targetfile = new File(target);
        
        FileWriter fw = new FileWriter(targetfile,true); 
        BufferedWriter bw = new BufferedWriter(fw);
        
		String str1 = null;
		int filecount = 0;
		int boolean1 = 0,int1 = 0,double1 = 0,String1 = 0;
		Pattern p1=Pattern.compile("\"(.*?)\"");

		Map<String, Integer> m = new TreeMap<String, Integer>();
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
            String regex = "\\[.*?\\]]";
//			write
            

            while(sc.hasNextLine()){
            	str1 = sc.nextLine();
            	String str2 = filterStr(regex, str1);

//            	System.out.println(str2);
//            	String[] str = str2.toString().split("\n|\r");
            	String[] str = str2.toString().split("],");

            	
            	for (int i=0; i<str.length; i++) {
//                    System.out.println(str[i]);
                    //为了把""中的内容提取出来，由于统计的时候为了方便读取的"被默认成了string类型
//                    Matcher map = p1.matcher(str[i]);
//                    ArrayList<String> list = new ArrayList<String>();
//                    while (map.find()) {
//                        list.add(map.group().trim().replace("\"",""));
//                    }
            		String st[] = str[i].toString().split("\"");
            		//目前工作可以提取出表中某一列，并且通过"将其分割开 一个一个                         
            		
            		
                    if(i != 0)
                        System.out.print(' ');
                    if ("true".equalsIgnoreCase(str[i]) || "false".equalsIgnoreCase(str[i])) {
                    	boolean1++;
                    }
                    else if(str[i].matches("[0-9]+")){
                        int1++;
                    }
                    else if(pattern.matcher(str[i]).matches()){
                    	double1++;
                    }
                    else
                    	String1++;              
                }
            	
            	
//            	for(int i=0 ; i < str.length ; i++){//通过str.length获取字符串数组长度
//                  System.out.println(str[i]);//循环输出字符串数组元素
//            	}
//      	    	    String s = "],";
//
//      	    	  //检查], 有一个就说明有一行
//      	    	    for(int k=0; k < str[i].length() ;){
//      		            int c = -1;
//      		            c = str[i].indexOf(s);//如果有S这样的子串。则C的值不是-1。
//      		            if(c != -1){// 如果c！=-1则，说明存在。
//      			    //这里的c+1 而不是 c+ s.length();这是因为。如果str的字符串是“aaaa”， s = “aa”，则结果是2个。但是实际上是3个子字符串  
//      		            //将剩下的字符冲洗取出放到str中  
//      		                 str[i] = str[i].substring(c + 1);  // 从存在的那个下标后一位开始
//      		            }else {
//      		            	//成功输出到了字符串
//      		            	break;
//      		            }
//      		        }
//      	    	}
            	
            }
        	System.out.println(m);
        	System.out.println("  Extract this file:  " + filecount + "  all elements" + "    Time consuming:  " + (System.currentTimeMillis() - t1)/60000 + "  min " + ((System.currentTimeMillis() - t1)/1000)%60 + " s");
            System.out.println("");	
//            System.out.println("  Extract this file:  " + i + "  chinese table" + "    Time consuming:  " + (System.currentTimeMillis() - t1)/1000 + "s" + "  or  " + (System.currentTimeMillis() - t1)/60000 +"  min");
//            sum += i;
        }
        bw.write("boolean：" + boolean1);
        bw.newLine(); 
	  	bw.write("int：" + int1);
        bw.newLine(); 
	    bw.write("double：" + double1);
        bw.newLine(); 
	    bw.write("String：" + String1);
	    bw.newLine();
	    bw.newLine(); 
        bw.close();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {   
//        File file1 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\gzFilesort\\gzHORIZONTAL"); 
//        showDirectory(file1,"E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\equalColumn.json");
    	File file1 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\test\\ab"); 
        showDirectory(file1,"E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\dataTypes.json");
    }
} 