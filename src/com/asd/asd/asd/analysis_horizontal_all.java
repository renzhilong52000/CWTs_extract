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
 * @Date:2020-11-1����3:27:40
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

	    StringBuffer sb = new StringBuffer(); //sb�������ƥ��Ľ��
	    Pattern p = Pattern.compile(regex); //���������ַ���
	    Matcher matcher = p.matcher(data); //��ȡ����������ַ�
//	    int i = 0;
	    while (matcher.find()) { //���ֺ󱣴�
	        sb.append(matcher.group() + "\r\n");
//	        i++;
	        //��ӵ�sb
	    	}
//	    System.out.println("��ѯ��������   " + i);
	    return sb.toString();
		}

    private static double median(List<Integer> total) {
		double j = 0;
		//��������
	    Collections.sort(total);
	    int size = total.size();
	    if(size % 2 == 1){
	    	j = total.get((size-1)/2);}
	    else {
	    	//��0.0��Ϊ�˰�intת��double���ͣ��������2�����
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
		int ii = 0;//ͳ�Ʊ������
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
            	for(int i=0 ; i<str.length ; i++){//ͨ��str.length��ȡ�ַ������鳤��
//      	    	  System.out.println(str[i]);//ѭ������ַ�������Ԫ��
      	    	  String s = "],";
      	    	  int count = 1;

      	    	  //���], ��һ����˵����һ��
      	    	  for(int k=0; k<str[i].length();){
      		            int c = -1;
      		            c = str[i].indexOf(s);//�����S�������Ӵ�����C��ֵ����-1��
      		            if(c != -1){/***���c��=-1��˵�����ڡ�
      		            	�����c+1 ������ c+ s.length();������Ϊ�����str���ַ����ǡ�aaaa���� s = ��aa����������2����
      		            	����ʵ������3�����ַ���  ��ʣ�µ��ַ���ϴȡ���ŵ�str��  ***/
      		            	str[i] = str[i].substring(c + 1);  // �Ӵ��ڵ��Ǹ��±��һλ��ʼ
      		                 count ++;//��⵽count����˵����⵽һ�У���++  else����һ�����ж����м�����ˣ���ʼͳ��
      		            }else {
      		            	total.add(count);//Ӧ�óɹ���������ַ���
      		            	int temp1 = count;//�������ֵ 
	   		                if(temp1 > max){
	   		                	max = temp1;
	   		                }
	   		                int temp2 = count;//������Сֵ
	   		                if(temp2 < min){
	   		                	min = temp1;
	   		                }
	   		                // System.out.println("�˱���   " + count + "  ��");
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
	  	bw.write("The row median is��" + a);
        bw.newLine(); 
	  	bw.write("Horizontal the Largest row��" + max);
        bw.newLine(); 
	    bw.write("Horizontal the smallest row��" + min);
        bw.newLine(); 
	    bw.write("Horizontal average number of columns is��" + average);
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
