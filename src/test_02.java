import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class test_02 {

	/**
	 * 字节流输入
	 */
	public static void in(){
		//1 确认目标文件
		File file = new File("e:\\file.txt");
		//2 构建一个文件输入流对象
		try {
			InputStream in = new FileInputStream(file);
			byte[]  bytes = new byte[1024];
			StringBuilder buf = new StringBuilder();
			int len = 1;//表示每次读取的字节长度
			//把数据读取到字节数组，并返回读取到的字节数，当不等于-1时，表示读取到数据，等于-1时表示文件已经读完			
			while((len = in.read(bytes))!= -1){
				//根据读取到的字节数组，再转换为字符串内容，添加到StringBuilder中	
				buf.append(new String(bytes,0,len));
			}
			
			System.out.println(buf);
			//关闭输入流
			in.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void out(){
		File file = new File("e:\\file.txt");
		//1、构造一个文件输出流对象
			try {
				OutputStream out = new FileOutputStream(file,true);
				//2、输出的内容
				String info = "看你所有想看的全部内容\r\n\r\n"; //\r\n表示换行
				//String line = System.getProperty("line.separator");//获取换行符
				//3、吧内容写入到文件
				out.write(info.getBytes());
				out.close();	
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		out();
		in();
	}

}
