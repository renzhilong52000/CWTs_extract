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
 * @Description:当前主要任务是
 * 1 、写一个类可以统计出所有“],” 并将其传入一个数组
 * 2 、调用这个数组，导入hashMap进行统计，计数
 * 3 、得到的值进行写入一个json文件
 * @author: RenZhiLong
 * @Date:2020-10-22下午2:35:42
 */
public class equal_column {
	
	private static String filterStr(String regex,String data){

	    StringBuffer sb = new StringBuffer(); //sb存放正则匹配的结果
	    Pattern p = Pattern.compile(regex); //编译正则字符串
	    Matcher matcher = p.matcher(data); //提取正则输入的字符
	    int i = 0;
	    while (matcher.find()) { //发现后保存
	        sb.append(matcher.group() + "\r\n");
	        i++;
	        //添加到sb
	    	}
	    System.out.println("查询总行数：   " + i);
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
	
	
	
	private static String[] insert(String[] str_c, String count1) {
		int size = str_c.length;  //获取数组长度
	    String[] tmp = new String[size + 1];  //新建临时字符串数组，在原来基础上长度加一
	    for (int i = 0; i < size; i++){  //先遍历将原来的字符串数组数据添加到临时字符串数组
	        tmp[i] = str_c[i];
	    }
	    tmp[size] = count1;  //在最后添加上需要追加的数据
	    return tmp;  //返回拼接完成的字符串数组
	}
	/**
	 *
	 */	
	
	public static void main(String[] args) throws IOException {
	    String regex = "\\[.*?\\]]"; 
	    String data = ReadFile("C:\\Users\\wolf\\Desktop\\new_extract.json"); //1. 读取文档
	    String needData = filterStr(regex, data); //2. 正则查找
	    File file = new File("C:\\Users\\wolf\\Desktop\\levelSet.json");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
  	    String[] str_c = new String[0];
	    
	    //这里获得一个一个完整的表（字符串），需要将字符串进行分割成字符串数组，进行统计、比较
	    String[] str = needData.toString().split("\n|\r");
	    for(int i=0 ; i<str.length ; i++){//通过str.length获取字符串数组长度
//	    	  System.out.println(str[i]);//循环输出字符串数组元素
	    	  String s = "],";
	    	  int count = 1;

	    	  //检查], 有一个就说明有一行
	    	  for(int k=0; k<str[i].length() ;){  
		            int c = -1;
		            c = str[i].indexOf(s);//如果有S这样的子串。则C的值不是-1。
		            if(c != -1){// 如果c！=-1则，说明存在。
			    //这里的c+1 而不是 c+ s.length();这是因为。如果str的字符串是“aaaa”， s = “aa”，则结果是2个。但是实际上是3个子字符串  
		            //将剩下的字符冲洗取出放到str中  
		                 str[i] = str[i].substring(c + 1);  // 从存在的那个下标后一位开始
		                 count ++;
		            }else {
		            	String count1 = String.valueOf(count);
		            	str_c = insert(str_c,count1);
		            	//应该成功输出到了字符串
		            	break;
		            }
		        }
	    	}
	    
	    //str_c[i]这个数组里面装的是所有的元素数 eg：2列的5列的8列的 存在数组里就是[2,5,8]
	    Map<String, Integer> m = new HashMap<String, Integer>();
        for (int i = 0; i < str_c.length; i++) {
			if (m.containsKey(str_c[i])) {//判断 key 值是否等于当前值的 key
				//若相等，则把 key 值每次加一
				m.put(str_c[i], m.get(str_c[i]) + 1);
			} else {
				//若不相等，则放入，并且设置 key 为1
				m.put(str_c[i], 1);
			}
		}
        System.out.println(m);
        System.out.println("");	   
        
      //将map.entrySet()转换成list  
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(m.entrySet());  
        //通过比较器实现排序  
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
            //降序排序  
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
                return o2.getValue().compareTo(o1.getValue());  
            }  
        });  
        System.out.println("实现降序排序："); 
        System.out.println(list);
        System.out.println("");	   

        //将结果写入文件  
        for(Map.Entry<String, Integer> map : list) {
            bw.write("key: " + map.getKey() + "            value: " + map.getValue() );
            System.out.println("key: " + map.getKey() + "            value: " + map.getValue());
            bw.newLine();//写入一个行分隔符
        }
        bw.newLine(); 
	    
	}
}








