package AboutCount;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: extract
 * @Description:提取出所有的元素，以及出现的次数 但是有时候会乱码。可以通过汉字说明表的偏向分析
 * 				比如：将所有出现的国家进行记录。可以看出国家在这些表中的分布概况。
 * @author: RenZhiLong
 * @Date:2020-10-10上午10:27:08
 */
public class word_count_01 {
	
	public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\wolf\\Desktop\\extractsample.json");
		FileReader fr = new FileReader(file);
        BufferedReader BR = new BufferedReader(fr);
        
        Map<String, Integer> map = new HashMap();
        
        String nextLine = "";
        while ((nextLine = BR.readLine()) != null) {
            String[] data = nextLine.split(" ");
            for (String word : data) {
                map.put(word, map.containsKey(word) ? map.get(word) + 1 : 1);
            }
        }
 
        for (String key : map.keySet()) {
        	if(map.get(key) > 300){
        		System.out.println(key + ": " + map.get(key));
        	}
        }
		System.out.println("完成输出");
    }

}
