import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class copy_02 {

	/**
	 * @param args
	 * �����ŵ�һ��class�����³��ԣ���һ�������޷��ѵ�һ�����еĲ��������ڶ�����ʧ����
	 */
	public static void copy(String src,String target){
//		 InputStream in = null;
//		 OutputStream out = null;
		 try {
			 File srcfile = new File(src);
			 File targetfile = new File(target);
			 InputStream in = new FileInputStream(srcfile);
			 OutputStream out = new FileOutputStream(targetfile);
			 byte[] bytes = new byte[1024];
			 int len = -1;
			 while((len = in.read(bytes))!= -1){
				out.write(bytes, 0, len);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}
	
	public static void main(String[] args) {
		System.out.println("start copy......");
		copy("E:\\�����ļ�\\���.mp4","C:\\Users\\wolf\\Desktop\\1.mp4");
		System.out.println("copy success");
	}

}
