import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class test_01 {

	/**
	 * @param args
	 * 字符流：
	 * 字符输入流：Writer ，对文件的操作使用子类：FileWriter
	 * 字符输出流：Reader ，对文件的操作使用子类：FileReader
	 * 每次的操作是一个字符 所以如下
	 * 文件字符操作会自带缓存，等操作结束一次性写入
	 */
	public static void in(){     //完成读取txt中的内容并且打印输出
		File file = new File("e:\\file.txt");
		try {
			Reader in = new FileReader(file);
			char[] cs = new char[1];//这里定义了一个字符数组，可以写char[2][3][4]因为都是读的一个字符，故不会乱码
			int len = -1;
			StringBuilder buf = new StringBuilder();//没有单独构造这里直接写了。用于读取
			try {
				while((len = in.read(cs))!= -1){
					buf.append(new String(cs,0,len));
				}
				in.close();
				System.out.println(buf);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void out() {
		File file = new File("e:\\file.txt");
		try {
			Writer out = new FileWriter(file,true);
			out.write("2020年最受欢迎电影排行\r\n");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public static void main(String[] args){
		out();
		in();
	}
}
