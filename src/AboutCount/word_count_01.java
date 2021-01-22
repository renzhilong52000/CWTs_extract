package AboutCount;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: extract
 * @Description:��ȡ�����е�Ԫ�أ��Լ����ֵĴ��� ������ʱ������롣����ͨ������˵�����ƫ�����
 * 				���磺�����г��ֵĹ��ҽ��м�¼�����Կ�����������Щ���еķֲ��ſ���
 * @author: RenZhiLong
 * @Date:2020-10-10����10:27:08
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
		System.out.println("������");
    }

}
