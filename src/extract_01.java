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
 * @Date:2020-10-8����8:34:45
 */
public class extract_01 {
	
	//����Ƿ�Ϊ����
//	public static boolean isChinesechar(char c) {
//		return c >= 0x4E00 && c <= 0x9FA5;// �����ֽ����ж�
//	}
//
//	
//	public static boolean hasChinese(String src) {
//		if (src == null) return false;
//		for (char c : src.toCharArray()) {
//			if (isChinesechar(c))
//				return true;//����һ�������ַ�c�������ֱ�����ַ�������chars����ĸ����ַ���Ȼ��ִ���������䣬��c����ֵΪchars���������ַ���һ�κ󣬾ͻ��˳����ѭ��.
//		}
//		return false;
//		}
	
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
            
//			FileReader fr = new FileReader(srcfile); //fr��Ϊһ��ָ������ȡ�ļ�
//			BufferedReader bufr = new BufferedReader(fr); //fr��ȡ����ֵ������bufr
			
//			byte[] bytes = new byte[1024];
//			int len = -1;
			String str=null;
			String b=null;
            while ((str = br.readLine())!= null) { //readLine()����, ���ڶ�ȡһ��,ֻҪ��ȡ���ݲ�Ϊ�վ�һֱִ��
        		
            	if(str.length() == 0){
        			break;
        		}
            	
            	for (int j = 0; j < str.length(); j++) { //forѭ������������jС�ڸ��г���ʱ��һֱѭ��������
            		b = Character.toString(str.charAt(j)); //����һ���ַ�������
            		if (b.matches("[\\u4e00-\\u9fa5]")) {//if�����������ж��Ƿ�Ϊ����
//             			System.out.println(str);
             			bw.write(str);
             			bw.newLine();
            		//	byte[] sb = str.getBytes();
            			//out.write(sb);
//            			bw.flush();
            			break;
            			//�����⵽��һ�����֣�����Ϊ�����һ�н�����һ��ѭ��
//            			while((len = in.read(bytes))!= -1){ //copy02�� һֱ������ֱ������
//                			System.out.println(b);
//                			break;
//            			}
//            			while((len = in.read(bytes))!= -1){
//            				out.write(bytes, 0, len);
//                  } 

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
		extract("C:\\Users\\wolf\\Desktop\\CC-MAIN-20150728002301-00342-ip-10-236-191-2.ec2.internal.json","C:\\Users\\wolf\\Desktop\\new_extract.json");
		System.out.println("extract success");
	}

}
