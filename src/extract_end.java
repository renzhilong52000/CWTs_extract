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
 * @Date:2020-10-24下午5:37:46
 */
public class extract_end {
	
	public static void extract(String src,String target){
		 File srcfile = new File(src);
		 File targetfile = new File(target);
		 
		 try {
			InputStream in = new FileInputStream(srcfile);
			OutputStream out = new FileOutputStream(targetfile);
			InputStreamReader read = new InputStreamReader(new FileInputStream(srcfile),"utf-8"); 
            BufferedReader br = new BufferedReader(read);//可用于读取指定文件     
            
            FileWriter fw = new FileWriter(targetfile); 
            BufferedWriter bw = new BufferedWriter(fw);
			String str = null;
			String b = null;
            while ((str = br.readLine())!= null) { //readLine()方法, 用于读取一行,只要读取内容不为空就一直执行
            	if(str.length() == 0){
        			break;
        		}
            	
            	for (int j = 0; j < str.length(); j++) { //for循环的条件，当j小于该行长度时就一直循环并自增
            		b = Character.toString(str.charAt(j)); //返回一个字符串对象
            		if (b.matches("[\\u4e00-\\u9fa5]")) {//if语句的条件，判断是否为汉字
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
		System.out.println("start extract......");
		extract("E:\\paper1\\CoreOfSummerVacation\\data\\a\\test1.json.gz","E:\\paper1\\CoreOfSummerVacation\\data\\a\\ce.gz");
		System.out.println("extract success");
	}

}

