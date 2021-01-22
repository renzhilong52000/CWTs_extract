package AboutCount;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * @ProjectName: extract
 * @Description:
 * @author: RenZhiLong
 * @Date:2020-10-13 5:03:09
 */
public class extract_vertical {
	
	//
	public static boolean isChinesechar(char c) {
		return c >= 0x4E00 && c <= 0x9FA5;//
	}

	
	public static boolean hasChinese(String src) {
		if (src == null) return false;
		for (char c : src.toCharArray()) {
			if (isChinesechar(c))
				return true;//
		}
		return false;
		}
	
	public static void VerExtract(String src,String target){
		 File srcfile = new File(src);
		 File targetfile = new File(target);
		 
		 try {
			InputStream in = new FileInputStream(srcfile);
			OutputStream out = new FileOutputStream(targetfile);
			InputStreamReader read = new InputStreamReader(new FileInputStream(srcfile),"utf-8"); 
            BufferedReader inn = new BufferedReader(read);//
            
            FileWriter fw = new FileWriter(targetfile); 
            BufferedWriter bw = new BufferedWriter(fw);
            
//			FileReader fr = new FileReader(srcfile); //fr
//			BufferedReader bufr = new BufferedReader(fr); //fr
			
//			byte[] bytes = new byte[1024];
//			int len = -1;
			String str=null;
//			String b=null;
            while ((str = inn.readLine())!= null) { //readLine()
            	if(str.length() == 0){
        			break;
        		}
            	
            	for (int j = 0; j < str.length(); j++) { //for
//            		b = Character.toString(str.charAt(j)); //
            		boolean status = str.contains("VERTICAL");
            		if(status){
             			bw.write(str);
             			bw.newLine();
            			break;
            		}
            	}
            }
            bw.close();
			out.close();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}
	
	public static void HorExtract(String src,String target){
		 File srcfile = new File(src);
		 File targetfile = new File(target);
		 
		 try {
			InputStream in = new FileInputStream(srcfile);
			OutputStream out = new FileOutputStream(targetfile);
			InputStreamReader read = new InputStreamReader(new FileInputStream(srcfile),"utf-8"); 
           BufferedReader inn = new BufferedReader(read);//
           
           FileWriter fw = new FileWriter(targetfile); 
           BufferedWriter bw = new BufferedWriter(fw);
			String str=null;
           while ((str = inn.readLine())!= null) { //readLine()
       		
           	if(str.length() == 0){
       			break;
       		}
           	
           	for (int j = 0; j < str.length(); j++) { //for
           		boolean status = str.contains("HORIZONTAL");
           		if(status){
            			bw.write(str);
            			bw.newLine();
           			break;
                   } 
           		}
           	}
           bw.close();
			out.close();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}
		
	public static void main(String[] args) {
		
		System.out.println("start VertiaclExtract......");
		VerExtract("C:\\Users\\wolf\\Desktop\\new_extract.json","C:\\Users\\wolf\\Desktop\\VertiaclExtract.json");
		System.out.println("VertiaclExtract success");
		
//		System.out.println("start HorizontalExtract......");
//		HorExtract("C:\\Users\\wolf\\Desktop\\extractsample.json","C:\\Users\\wolf\\Desktop\\HorizontalExtract.json");
//		System.out.println("HorizontalExtract success");
		
	}

}
