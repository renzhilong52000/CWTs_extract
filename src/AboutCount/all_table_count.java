package AboutCount;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;


/**
 * @ProjectName: extract
 * @Description:VERTIACL（垂直表）、水平表、总体的数量计算
 * @author: RenZhiLong
 * @Date:2020-10-11上午11:11:12
 */
public class all_table_count {

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        String s;  
        String fileName1 = "C:\\Users\\wolf\\Desktop\\new_extract.json";  
        String fileName2 = "C:\\Users\\wolf\\Desktop\\result.json";  
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(fileName1));
            bw = new BufferedWriter(new FileWriter(fileName2));
            StringBuffer sb = new StringBuffer(); 
            //将文件内容存入StringBuffer中  
            while((s = br.readLine()) != null) {  
                sb.append(s);  
            }  
            //不区分大小写
            String str = sb.toString().toLowerCase();
          //分隔字符串并存入数组  (以，。空格分割)
            String[] elements = str.split("[,.:;\"_\\s\\W+\\d+]"); //:;\" 
//            String[] elements = str.split("\\W+");
            
            int count = 0;  
            Map<String, Integer> myTreeMap = new TreeMap<String, Integer>();  
            //遍历数组将其存入Map<String, Integer>中  
            for(int i = 0; i < elements.length; i++) {  
                if(myTreeMap.containsKey(elements[i])) {  
                    count = myTreeMap.get(elements[i]);  
                    myTreeMap.put(elements[i], count + 1);  
                }  
                else {
                    myTreeMap.put(elements[i], 1);  
                }  
            }              
            System.out.println("单词统计的结果请见当前目录result.json文件");  
            //将map.entrySet()转换成list  
            List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(myTreeMap.entrySet());  
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
//            	System.out.println(map.getKey());
                if(num <= 200) {  
                    bw.write("出现次数第" + num + "的单词为：" + map.getKey() + "   出现频率为" + map.getValue() + "次");  
                    bw.newLine();//写入一个行分隔符
                    num++;  
                }
            }
            bw.newLine();
//            	System.out.println(list.size());
            System.out.println("——————————开始统计行和列——————————");
            float a = 0,b = 0,temp = 0;
            float c = 0;//比例
            for(Map.Entry<String, Integer> map : list) {  
                if(map.getKey().equals("VERTICAL") || map.getKey().equals("vertical")){//word.equals("HORIZONTAL")
//                	System.out.println(map.getKey());// || map.getKey().equals("vertical")
                	bw.write("\"VERTICAL\"单词出现频率为" + map.getValue() + "次");
                	bw.newLine();
                	a = map.getValue();
                }
    
                if(map.getKey().equals("HORIZONTAL") || map.getKey().equals("horizontal")){
//                	System.out.println(map.getKey());// || map.getKey().equals("horizontal")
                	bw.write("\"HORIZONTAL\"单词出现频率为" + map.getValue() + "次"); 
                	bw.newLine();
                	b = map.getValue();
                }
            }
            temp = a + b;
            c = b/temp;

            bw.write("此次共查询: " + temp + " 个表格中水平表约占" + c);
            bw.newLine();
            
            bw.write("耗时：" + (System.currentTimeMillis() - t1) + "ms");  
            System.out.println("耗时：" + (System.currentTimeMillis() - t1) + "ms");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                br.close();
                bw.close(); 
            } catch (IOException e) {
                e.printStackTrace();
            }  
        }
    }
}