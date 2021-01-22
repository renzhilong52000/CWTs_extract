import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;




public class copy_01 {

	/**
	 * @param args
	 * 文件（如txt文本文件）的复制：将一个文件中的内容复制到另外一个文件。
	 * 给定源文件A，利用输入流读取A文件的内容，利用输出流，将读取的源文件写到B文件中。
	 */
	public static void in(){
		File file = new File("e:\\file.txt");
		try {
			InputStream in = new FileInputStream(file);
			byte[]  bytes = new byte[1024];
			StringBuilder buf = new StringBuilder();
			int len = 1;
			while((len = in.read(bytes))!= -1){
				buf.append(new String(bytes,0,len));
			}
			System.out.println(buf);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void out(){
		File file = new File("e:\\copy.txt");
		String buf = null;
		try {
			OutputStream out = new FileOutputStream(file,true);
			out.write(buf.getBytes());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void copy(String src,String target){
		 
	}
	
	public static void main(String[] args) {
//		String buf = null;
		in();
		out();
	}

}
