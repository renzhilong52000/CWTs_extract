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
	 * �ַ�����
	 * �ַ���������Writer �����ļ��Ĳ���ʹ�����ࣺFileWriter
	 * �ַ��������Reader �����ļ��Ĳ���ʹ�����ࣺFileReader
	 * ÿ�εĲ�����һ���ַ� ��������
	 * �ļ��ַ��������Դ����棬�Ȳ�������һ����д��
	 */
	public static void in(){     //��ɶ�ȡtxt�е����ݲ��Ҵ�ӡ���
		File file = new File("e:\\file.txt");
		try {
			Reader in = new FileReader(file);
			char[] cs = new char[1];//���ﶨ����һ���ַ����飬����дchar[2][3][4]��Ϊ���Ƕ���һ���ַ����ʲ�������
			int len = -1;
			StringBuilder buf = new StringBuilder();//û�е�����������ֱ��д�ˡ����ڶ�ȡ
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
			out.write("2020�����ܻ�ӭ��Ӱ����\r\n");
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
