package AboutCount;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


/**
 * @ProjectName: extract
 * @Description:��ȡ�����е��� �Լ� ���ֳ��ֵĴ�����eg������˵��������񸲸ǵ���ݷֲ�
 * @author: RenZhiLong
 * @Date:2020-10-10����10:58:12
 */
public class word_count_02 {

	public static void main(String[] args) throws FileNotFoundException
	{
		File file = new File("C:\\Users\\wolf\\Desktop\\new_extract.json");
//		if(!file.exists())
//		{
//			System.out.println("�ļ�������");
//			return;
//		}
		Scanner scanner = new Scanner(file);
		HashMap<String, Integer> hashMap = new HashMap<String,Integer>();//���ʺ�����ӳ���
		while(scanner.hasNextLine())
		{
			String line = scanner.nextLine();
			//\w+ : ƥ�����еĵ���
			//\W+ : ƥ�����зǵ���
			String[] lineWords = line.split("\\W+");//�÷ǵ��ʷ������ָ�ָ�����ľ���һ��������
			
			Set<String> wordSet = hashMap.keySet();
			for(int i=0;i<lineWords.length;i++)
			{
				if(wordSet.contains(lineWords[i]))//����Ѿ������������
				{
					Integer number = hashMap.get(lineWords[i]);
					number++;
					hashMap.put(lineWords[i], number);
				}
				else 
				{
					hashMap.put(lineWords[i], 1);
				}
			}
			
		}
		System.out.println("ͳ�Ƶ��ʣ�------------------------------");
		Iterator<String> iterator = hashMap.keySet().iterator();
		while(iterator.hasNext())
		{
			String word = iterator.next();
			if(hashMap.get(word) >500){
				System.out.printf("����:%-12s ���ִ���:%d\n",word,hashMap.get(word));
			}
		}
	}

}
