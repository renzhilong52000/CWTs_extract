package AboutCount;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ProjectName: extract
 * @Description:提取出所有文件中的网页  URL、域名、表标题  并统计数量
 * @author: RenZhiLong
 * @Date:2020-10-11上午11:13:51
 */
public class urlExtract_01 {
	
	private static String filterStr(String regex,String data){

	    StringBuffer sb = new StringBuffer(); //sb存放正则匹配的结果
	    Pattern p = Pattern.compile(regex); //编译正则字符串
	    Matcher matcher = p.matcher(data); //提取正则输入的字符
	    while (matcher.find()) { //发现后保存
	        sb.append(matcher.group() + "\r\n");//添加到sb
	    	}
	    return sb.toString();
		}
	
	private static String ReadFile(String pathName) {
	    //读取到的文件内容放到这个sb里
	    StringBuffer sb = new StringBuffer();
		try {
			sb = new StringBuffer();
			FileReader fr = new FileReader(pathName);
			BufferedReader br = new BufferedReader(fr); 
			
		    String line;
		    while ((line = br.readLine()) != null) {
		        sb.append(line + "\r\n");
		    }
		    System.out.println("读取文件完成");
		    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return sb.toString();
	    }
	
	private static void writeFile(String pathName, String data) throws IOException {

	    	long t1 = System.currentTimeMillis();
	        //文件不存在的话新建，存在覆盖//file.createNewFile();
	        File file = new File(pathName);
	        FileWriter fw = new FileWriter(file);
	        BufferedWriter bw = new BufferedWriter(fw);
	        
		    String[] str = data.toString().split("\n|\r");
		    
			Map<String, Integer> m = new HashMap<String, Integer>();
	        for (int i = 0; i < str.length; i++) {
				if (m.containsKey(str[i])) {//判断 key 值是否等于当前值的 key
					//若相等，则把 key 值每次加一
					m.put(str[i], m.get(str[i]) + 1);
				} else {
					//若不相等，则放入，并且设置 key 为1
					m.put(str[i], 1);
				}
			}
	        
	      //将map.entrySet()转换成list  
            List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(m.entrySet());  
            //通过比较器实现排序  
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
                //降序排序  
                public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
                    return o2.getValue().compareTo(o1.getValue());  
                }  
            });
	       
            int num = 1;  
            //将结果写入文件  
            for(Map.Entry<String, Integer> map : list) {
//	            	System.out.println(map.getKey());
                if(num <= 200) {  
                    bw.write("key: " + map.getKey() + "            value: " + map.getValue() );  
                    bw.newLine();//写入一个行分隔符
                    num++;  
                }
            }
            bw.newLine(); 
            bw.flush();
            System.out.println("文件写入完成");
            bw.write("耗时：" + (System.currentTimeMillis() - t1) + "ms");  
            System.out.println("耗时：" + (System.currentTimeMillis() - t1) + "ms");

	}
	
	/**
	 * @throws IOException 
	 *
	 */
	public static void main(String[] args) throws IOException {
//		String regex = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]"; 
	    String regex = "(?<=://)[a-zA-Z\\.0-9]+(?=\\/)"; 
	    String regex2 = "\\.[a-z]{2,5}"; 
	    String regex3 = "\"pageTitle\":\"(.+?)\""; 
	    
	    String data = ReadFile("C:\\Users\\wolf\\Desktop\\new_extract.json"); // 读取文档
	    String needData = filterStr(regex, data); 
	    String needData2 = filterStr(regex2, data); 
	    String needData3 = filterStr(regex3, data);
	    
	    writeFile("C:\\Users\\wolf\\Desktop\\URL.json", needData); // 写到某个文件中
	    writeFile("C:\\Users\\wolf\\Desktop\\URL_domain.json", needData2); 
	    writeFile("C:\\Users\\wolf\\Desktop\\pageTitle.json", needData3); 
	    System.out.println("finish");
	    
	    //以下为统计阈值部分
//		    String[] str = needData.toString().split("\n|\r");
//			Map<String, Integer> m = new HashMap<String, Integer>();
		
//			for (int i = 0; i < str.length; i++) {
//				if (m.containsKey(str[i])) {//判断 key 值是否等于当前值的 key
//					//若相等，则把 key 值每次加一
//					m.put(str[i], m.get(str[i]) + 1);
//				} else {
//					//若不相等，则放入，并且设置 key 为1
//					m.put(str[i], 1);
//				}
//			}
//			Set<String> res = m.keySet();
//			for (String im : res) {
//				System.out.println("Key：" + im + "        " + "Value：" + m.get(im));
//			}
		
	}

}