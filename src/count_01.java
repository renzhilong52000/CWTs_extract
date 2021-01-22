import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * @ProjectName: extract
 * @Description:输出行数、字符数、单词数
 * @author: RenZhiLong
 * @Date:2020-10-8下午2:28:18
 */

public class count_01 {
	public static void CountLine(){
		File file = new File("C:\\Users\\wolf\\Desktop\\extractsample.json");
		try {
			String name = file.getName();
			System.out.println("Name:"+name);
			FileReader fr = new FileReader(file); //fr作为一个指针来读取文件
			BufferedReader bufr = new BufferedReader(fr); //fr读取的数值缓冲在bufr
			int i = 0;
            
			while(bufr.readLine() != null){
				i++;//累计行数
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
			int countc = 0;//字符数
			int countw = 0; //英文单词数
			while((ch = (char) bufr.read()) != (char)-1)//按字符读取文本内容
			{
				if(ch != '\n' && ch != '\r')  //统计文本中字符数
					countc++;//累计字符数
				if(!(ch>='a'&&ch<='z')&&!(ch>='A'&&ch<='Z')&&((fch>='a'&&fch<='z')||(fch>='A'&&fch<='Z')))
				{
					countw++;//累计单词数
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
		 CountLine();	//计算行
		 CountCharacter();	//计算 字符 和 单词数
	}

}
