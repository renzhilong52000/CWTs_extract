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
 * @Description:For horizontal tables,
 * 				having the same columns means having the same attributes
 * @author: RenZhiLong
 * @Date:2020-10-25����12:33:49
 */
public class equalColumn {
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
	    int i = 0;
	    while (matcher.find()) { //���ֺ󱣴�
	        sb.append(matcher.group() + "\r\n");
	        i++;
	        //��ӵ�sb
	    	}
//	    System.out.println("��ѯ��������   " + i);
	    return sb.toString();
		}
    
    private static String[] insert(String[] str_c, String count1) {
		int size = str_c.length;  //��ȡ���鳤��
	    String[] tmp = new String[size + 1];  //�½���ʱ�ַ������飬��ԭ�������ϳ��ȼ�һ
	    for (int i = 0; i < size; i++){  //�ȱ�����ԭ�����ַ�������������ӵ���ʱ�ַ�������
	        tmp[i] = str_c[i];
	    }
	    tmp[size] = count1;  //������������Ҫ׷�ӵ�����
	    return tmp;  //����ƴ����ɵ��ַ�������
	}
    
    public static void showDirectory(File file,String target) throws FileNotFoundException, IOException{
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
            String[] str_c = new String[0];
            String regex = "\\[.*?\\]]"; 
//			write
            

            while(sc.hasNextLine()){
            	str1 = sc.nextLine();
            	String str2 = filterStr(regex, str1);
            	String[] str = str2.toString().split("\n|\r");
            	for(int i=0 ; i<str.length ; i++){//ͨ��str.length��ȡ�ַ������鳤��
//      	    	  System.out.println(str[i]);//ѭ������ַ�������Ԫ��
      	    	  String s = "],";
      	    	  int count = 1;

      	    	  //���], ��һ����˵����һ��
      	    	  for(int k=0; k<str[i].length() ;){
      		            int c = -1;
      		            c = str[i].indexOf(s);//�����S�������Ӵ�����C��ֵ����-1��
      		            if(c != -1){// ���c��=-1��˵�����ڡ�
      			    //�����c+1 ������ c+ s.length();������Ϊ�����str���ַ����ǡ�aaaa���� s = ��aa����������2��������ʵ������3�����ַ���  
      		            //��ʣ�µ��ַ���ϴȡ���ŵ�str��  
      		                 str[i] = str[i].substring(c + 1);  // �Ӵ��ڵ��Ǹ��±��һλ��ʼ
      		                 count ++;
      		            }else {
      		            	String count1 = String.valueOf(count);
      		            	str_c = insert(str_c,count1);
      		            	//�ɹ���������ַ���
      		            	break;
      		            }
      		        }
      	    	}
            	
            }
            for (int j = 0; j < str_c.length; j++) {
					if (m.containsKey(str_c[j])) {//�ж� key ֵ�Ƿ���ڵ�ǰֵ�� key
						//����ȣ���� key ֵÿ�μ�һ
						m.put(str_c[j], m.get(str_c[j]) + 1);
					} else {
						//������ȣ�����룬�������� key Ϊ1
						m.put(str_c[j], 1);
					}
		          }
        	System.out.println(m);
        	System.out.println("  Extract this file:  " + filecount + "  all elements" + "    Time consuming:  " + (System.currentTimeMillis() - t1)/60000 + "  min " + ((System.currentTimeMillis() - t1)/1000)%60 + " s");
            System.out.println("");	
//            System.out.println("  Extract this file:  " + i + "  chinese table" + "    Time consuming:  " + (System.currentTimeMillis() - t1)/1000 + "s" + "  or  " + (System.currentTimeMillis() - t1)/60000 +"  min");
//            sum += i;
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(m.entrySet());  
        //ͨ���Ƚ���ʵ������  
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
            //��������  
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
                return o2.getValue().compareTo(o1.getValue());  
            }  
        });
        
        System.out.println("ʵ�ֽ�������"); 
        System.out.println(list);
        System.out.println("");
        
        for(Map.Entry<String, Integer> map : list){
            bw.write("key: " + map.getKey() + "            value: " + map.getValue() );
//            System.out.println("key: " + map.getKey() + "            value: " + map.getValue());
            bw.newLine();//д��һ���зָ���
        }
        bw.newLine(); 
        bw.close();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {   
//        File file1 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\gzFilesort\\gzHORIZONTAL"); 
//        showDirectory(file1,"E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\equalColumn.json");
    	File file1 = new File("E:\\paper1\\CoreOfSummerVacation\\data\\test\\ab"); 
        showDirectory(file1,"E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\equalColumntest.json");
    }
} 