import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * @ProjectName: extract
 * @Description:����������ַ�����������
 * @author: RenZhiLong
 * @Date:2020-10-8����2:28:18
 */

public class count_01 {
	public static void CountLine(){
		File file = new File("C:\\Users\\wolf\\Desktop\\extractsample.json");
		try {
			String name = file.getName();
			System.out.println("Name:"+name);
			FileReader fr = new FileReader(file); //fr��Ϊһ��ָ������ȡ�ļ�
			BufferedReader bufr = new BufferedReader(fr); //fr��ȡ����ֵ������bufr
			int i = 0;
            
			while(bufr.readLine() != null){
				i++;//�ۼ�����
			}
			System.out.println("Lines:"+i);
			bufr.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void CountCharacter(){
		File file = new File("C:\\Users\\wolf\\Desktop\\sample.json");
		try {
			FileReader fr = new FileReader(file);
			BufferedReader bufr = new BufferedReader(fr);
			char ch;
			char fch='A';
			int countc = 0;//�ַ���
			int countw = 0; //Ӣ�ĵ�����
			while((ch = (char) bufr.read()) != (char)-1)//���ַ���ȡ�ı�����
			{
				if(ch != '\n' && ch != '\r')  //ͳ���ı����ַ���
					countc++;//�ۼ��ַ���
				if(!(ch>='a'&&ch<='z')&&!(ch>='A'&&ch<='Z')&&((fch>='a'&&fch<='z')||(fch>='A'&&fch<='Z')))
				{
					countw++;//�ۼƵ�����
				}
				fch=ch;
			}
			System.out.println("Words:"+countw);
			System.out.println("Chars:"+countc);
			bufr.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		 CountLine();	//������
		 CountCharacter();	//���� �ַ� �� ������
	}

}
