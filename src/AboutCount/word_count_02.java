package AboutCount;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


/**
 * @ProjectName: extract
 * @Description:提取出所有单词 以及 数字出现的次数。eg：可以说明大量表格覆盖的年份分布
 * @author: RenZhiLong
 * @Date:2020-10-10上午10:58:12
 */
public class word_count_02 {

	public static void main(String[] args) throws FileNotFoundException
	{
		File file = new File("C:\\Users\\wolf\\Desktop\\new_extract.json");
//		if(!file.exists())
//		{
//			System.out.println("文件不存在");
//			return;
//		}
		Scanner scanner = new Scanner(file);
		HashMap<String, Integer> hashMap = new HashMap<String,Integer>();//单词和数量映射表
		while(scanner.hasNextLine())
		{
			String line = scanner.nextLine();
			//\w+ : 匹配所有的单词
			//\W+ : 匹配所有非单词
			String[] lineWords = line.split("\\W+");//用非单词符来做分割，分割出来的就是一个个单词
			
			Set<String> wordSet = hashMap.keySet();
			for(int i=0;i<lineWords.length;i++)
			{
				if(wordSet.contains(lineWords[i]))//如果已经有这个单词了
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
		System.out.println("统计单词：------------------------------");
		Iterator<String> iterator = hashMap.keySet().iterator();
		while(iterator.hasNext())
		{
			String word = iterator.next();
			if(hashMap.get(word) >500){
				System.out.printf("单词:%-12s 出现次数:%d\n",word,hashMap.get(word));
			}
		}
	}

}
