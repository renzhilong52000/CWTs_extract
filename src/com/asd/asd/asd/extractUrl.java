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
 * @author: RenZhilong
 * @Date:2020-11-1����5:03:58
 */
public class extractUrl {

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

	    StringBuffer sb = new StringBuffer(); //sb�������ƥ��Ľ��
	    Pattern p = Pattern.compile(regex); //���������ַ���
	    Matcher matcher = p.matcher(data); //��ȡ����������ַ�
	    while (matcher.find()) { //���ֺ󱣴�
	        sb.append(matcher.group() + "\r\n");
	        //��ӵ�sb
	    	}
	    return sb.toString();
		}
    
    public static void showDirectory(File file,String reg,String target) throws FileNotFoundException, IOException{
    	long t1 = System.currentTimeMillis();
    	File[] files = file.listFiles();
        File targetfile = new File(target);
        
        FileWriter fw = new FileWriter(targetfile,true); 
        BufferedWriter bw = new BufferedWriter(fw);
        
		String str1 = null;
		int filecount = 0;

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
            String regex = reg; 
//			write

            while(sc.hasNextLine()){
            	str1 = sc.nextLine();
            	String str2 = filterStr(regex, str1);
            	String[] str = str2.toString().split("\n|\r");
            	
            	for (int i = 0; i < str.length; i++) {
    				if (m.containsKey(str[i])) {//�ж� key ֵ�Ƿ���ڵ�ǰֵ�� key
    					//����ȣ���� key ֵÿ�μ�һ
    					m.put(str[i], m.get(str[i]) + 1);
    				} else {
    					//������ȣ�����룬�������� key Ϊ1
    					m.put(str[i], 1);
    				}
    			}
            }

            System.out.println("  Extract this file:  " + filecount + "  all elements" + "    Time consuming:  " + (System.currentTimeMillis() - t1)/60000 + "  min " + ((System.currentTimeMillis() - t1)/1000)%60 + " s");
            System.out.println("");	
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(m.entrySet());  
        //ͨ���Ƚ���ʵ������  
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
            //��������  
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
                return o2.getValue().compareTo(o1.getValue());  
            }  
        });
        
        System.out.println("Sorting in descending order..."); 
        
        for(Map.Entry<String, Integer> map : list){
            bw.write("key: " + map.getKey() + "            value: " + map.getValue() );
            bw.newLine();//д��һ���зָ���
        }
        bw.newLine(); 
        bw.close();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {   
        File file1 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\gzFile"); 
        showDirectory(file1,"(?<=://)[a-zA-Z\\.0-9]+(?=\\/)","E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractUrl.json");
//    	File file1 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\test\\ab"); 
//        showDirectory(file1,"(?<=://)[a-zA-Z\\.0-9]+(?=\\/)","E:\\paper1\\CoreOfSummerVacation\\data\\test\\extractUrlTest.json");
    }
} 