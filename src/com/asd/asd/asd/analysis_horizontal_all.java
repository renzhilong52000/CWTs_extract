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
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

/**
 * @ProjectName: extract
 * @Description:
 * @author: RenZhilong
 * @Date:2020-11-1下午3:27:40
 */
public class analysis_horizontal_all {

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
//	    int i = 0;
	    while (matcher.find()) { //发现后保存
	        sb.append(matcher.group() + "\r\n");
//	        i++;
	        //添加到sb
	    	}
//	    System.out.println("查询总行数：   " + i);
	    return sb.toString();
		}

    private static double median(List<Integer> total) {
		double j = 0;
		//集合排序
	    Collections.sort(total);
	    int size = total.size();
	    if(size % 2 == 1){
	    	j = total.get((size-1)/2);}
	    else {
	    	//加0.0是为了把int转成double类型，否则除以2会算错
	    	j = (total.get(size/2-1) + total.get(size/2) + 0.0)/2;
	    }
		return j;
	}
    
	public static int max = 0;
	public static int min = 100;
	public static float counts = 0,average = 0;
	
    public static void showDirectory(File file,String target) throws FileNotFoundException, IOException{
    	long t1 = System.currentTimeMillis();
    	File[] files = file.listFiles();
        File targetfile = new File(target);
        
        FileWriter fw = new FileWriter(targetfile,true); 
        BufferedWriter bw = new BufferedWriter(fw);
        
		String str1 = null;
		int filecount = 0;
		List<Integer> total = new ArrayList<Integer>();
		int ii = 0;//统计表格数量
//		Map<String, Integer> m = new TreeMap<String, Integer>();
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
            	ii++;
            	String str2 = filterStr(regex, str1);
            	String[] str = str2.toString().split("\n|\r");
            	for(int i=0 ; i<str.length ; i++){//通过str.length获取字符串数组长度
//      	    	  System.out.println(str[i]);//循环输出字符串数组元素
      	    	  String s = "],";
      	    	  int count = 1;

      	    	  //检查], 有一个就说明有一行
      	    	  for(int k=0; k<str[i].length();){
      		            int c = -1;
      		            c = str[i].indexOf(s);//如果有S这样的子串。则C的值不是-1。
      		            if(c != -1){/***如果c！=-1则，说明存在。
      		            	这里的c+1 而不是 c+ s.length();这是因为。如果str的字符串是“aaaa”， s = “aa”，则结果是2个。
      		            	但是实际上是3个子字符串  将剩下的字符冲洗取出放到str中  ***/
      		            	str[i] = str[i].substring(c + 1);  // 从存在的那个下标后一位开始
      		                 count ++;//检测到count就是说明检测到一列，就++  else就是一个表有多少列检测完了，开始统计
      		            }else {
      		            	total.add(count);//应该成功输出到了字符串
      		            	int temp1 = count;//检测出最大值 
	   		                if(temp1 > max){
	   		                	max = temp1;
	   		                }
	   		                int temp2 = count;//检测出最小值
	   		                if(temp2 < min){
	   		                	min = temp1;
	   		                }
	   		                // System.out.println("此表有   " + count + "  行");
	   		                counts += count;
//	   		                average = counts/str[i].length(); 
	   		                break;
      		            }
      		        }
      	    	}
            	
            }
            
            average = counts/ii;
            System.out.println("  Extract this file:  " + filecount + "  all elements" + "    Time consuming:  " + (System.currentTimeMillis() - t1)/60000 + "  min " + ((System.currentTimeMillis() - t1)/1000)%60 + " s");
            System.out.println("");	
        }
        double a = median(total);
	  	bw.write("The row median is：" + a);
        bw.newLine(); 
	  	bw.write("Horizontal the Largest row：" + max);
        bw.newLine(); 
	    bw.write("Horizontal the smallest row：" + min);
        bw.newLine(); 
	    bw.write("Horizontal average number of columns is：" + average);
        bw.newLine(); 
        bw.close();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {   
        File file1 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\gzFilesort\\gzHORIZONTAL"); 
        showDirectory(file1,"E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\Statistics about the columns and rows\\ColumnsHorizontalTables(attributes).json");
        File file2 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\gzFilesort\\gzVERTICAL"); 
        showDirectory(file2,"E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\Statistics about the columns and rows\\ColumnsVerticalTables(entities).json");
//    	File file1 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\test\\ab"); 
//        showDirectory(file1,"E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\StaElementsTest.json");
    }
} 
