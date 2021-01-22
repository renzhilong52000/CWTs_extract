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
	 * 两个放到一个class里重新尝试，第一次由于无法把第一个类中的参数传给第二个类失败了
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
		copy("E:\\桌面文件\\李卜宇.mp4","C:\\Users\\wolf\\Desktop\\1.mp4");
		System.out.println("copy success");
	}

}
