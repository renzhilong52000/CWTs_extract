import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @ProjectName: extract
 * @Description:���һ���ļ������� ������ + ����
 * @author: RenZhiLong
 * @Date:2020-10-8����3:27:01
 */
public class count_02 {
	
	// �ж�һ���ַ��Ƿ�������
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
	
	public static void CountChineseLine(){
		File file = new File("C:\\Users\\wolf\\Desktop\\extractsample.json");
		try {
			String name = file.getName();
			System.out.println("Name:"+name);
			InputStreamReader read = new InputStreamReader(new FileInputStream(file)); 
            BufferedReader in = new BufferedReader(read);//�����ڶ�ȡָ���ļ�     
			
			String str=null;
			String b=null;
			int i = 0;//����һ�����ͱ���,����ͳ������
            int c1 = 0;//�������ͱ���������ͳ���ַ���

            while ((str = in.readLine())!= null) {//readLine()����, ���ڶ�ȡһ��,ֻҪ��ȡ���ݲ�Ϊ�վ�һֱִ��
            	i++;//ÿѭ��һ�ξͽ���һ������������ͳ���ı�����
            	for (int j = 0; j < str.length(); j++) {//forѭ������������jС�ڸ��г���ʱ��һֱѭ��������
            		b = Character.toString(str.charAt(j));//����һ���ַ�������
            		if (b.matches("[\\u4e00-\\u9fa5]")) {//if�����������ж��Ƿ�Ϊ����
                        c1++;//��Ϊ������c1����
                    }
            		}
            	}
            in.close();//�ر���
            System.out.printf("contains��");//�����ʾ��Ϣ
            System.out.printf(c1+" ������       ");//���������
            System.out.println(i+" ��");//����ж�����
        } catch (IOException e) {
        	e.printStackTrace();
        	}
        }
	
	
	public static void main(String[] args) {
		System.out.println("start count......");
		CountChineseLine();
		System.out.println("count success");
	}

}
