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
 * @Date:2020-10-24����5:37:46
 */
public class extract_end {
	
	public static void extract(String src,String target){
		 File srcfile = new File(src);
		 File targetfile = new File(target);
		 
		 try {
			InputStream in = new FileInputStream(srcfile);
			OutputStream out = new FileOutputStream(targetfile);
			InputStreamReader read = new InputStreamReader(new FileInputStream(srcfile),"utf-8"); 
            BufferedReader br = new BufferedReader(read);//�����ڶ�ȡָ���ļ�     
            
            FileWriter fw = new FileWriter(targetfile); 
            BufferedWriter bw = new BufferedWriter(fw);
			String str = null;
			String b = null;
            while ((str = br.readLine())!= null) { //readLine()����, ���ڶ�ȡһ��,ֻҪ��ȡ���ݲ�Ϊ�վ�һֱִ��
            	if(str.length() == 0){
        			break;
        		}
            	
            	for (int j = 0; j < str.length(); j++) { //forѭ������������jС�ڸ��г���ʱ��һֱѭ��������
            		b = Character.toString(str.charAt(j)); //����һ���ַ�������
            		if (b.matches("[\\u4e00-\\u9fa5]")) {//if�����������ж��Ƿ�Ϊ����
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

