import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class test_02 {

	/**
	 * �ֽ�������
	 */
	public static void in(){
		//1 ȷ��Ŀ���ļ�
		File file = new File("e:\\file.txt");
		//2 ����һ���ļ�����������
		try {
			InputStream in = new FileInputStream(file);
			byte[]  bytes = new byte[1024];
			StringBuilder buf = new StringBuilder();
			int len = 1;//��ʾÿ�ζ�ȡ���ֽڳ���
			//�����ݶ�ȡ���ֽ����飬�����ض�ȡ�����ֽ�������������-1ʱ����ʾ��ȡ�����ݣ�����-1ʱ��ʾ�ļ��Ѿ�����			
			while((len = in.read(bytes))!= -1){
				//���ݶ�ȡ�����ֽ����飬��ת��Ϊ�ַ������ݣ���ӵ�StringBuilder��	
				buf.append(new String(bytes,0,len));
			}
			
			System.out.println(buf);
			//�ر�������
			in.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void out(){
		File file = new File("e:\\file.txt");
		//1������һ���ļ����������
			try {
				OutputStream out = new FileOutputStream(file,true);
				//2�����������
				String info = "���������뿴��ȫ������\r\n\r\n"; //\r\n��ʾ����
				//String line = System.getProperty("line.separator");//��ȡ���з�
				//3��������д�뵽�ļ�
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
