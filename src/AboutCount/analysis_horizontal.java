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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ProjectName: extract
 * @Description: 仿照列统计做出行统计
 * @author: RenZhiLong
 * @Date:2020-10-15下午2:49:03
 */
public class analysis_horizontal {
	
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
	    System.out.println("共查询: " + i + " 个表");
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
	
	private static void writeFile(String pathName, String data) {
	    try {
	        //文件不存在的话新建，存在覆盖//	        file.createNewFile();
	        File file = new File(pathName);
	        FileWriter fw = new FileWriter(file);
	        BufferedWriter bw = new BufferedWriter(fw);
	            bw.write(data);
	            bw.flush();
	            System.out.println("文件写入完成");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 *
	 */
	private static double median(List<Integer> total) {
		double j = 0;
		//集合排序
	    Collections.sort(total);
	    int size = total.size();
	    if(size % 2 == 1){
	    	j = total.get((size-1)/2);}
	    else {
	    	//加0.0是为了把int转成double类型，否则除以2会算错
	    	j = (total.get(size/2-1) + total.get(size/2) + 0.0)/2;
	    }
		return j;
	}
//	public static boolean isHave(String[] strs,String s){
//		/*此方bai法有两个参数，第一个是要查找的字符串数组，第二个是要查找的字符或字符串
//		* */
//		for(int i=0;i<strs.length;i++){
//		if(strs[i].indexOf(s)!=-1){//循环查找字符串数组中的每个字符串中是否包含所有查找的内容
//		return true;//查找到了就返回真，不在继续查询
//		}
//		}
//		return false;//没找到返回false
//		}
	
	public static int max = 0,temp1 = 0;
	public static int min = 100,temp2 = 0;
	public static float counts = 0,average = 0;
	
	public static void main(String[] args) {
	    String regex = "\\[.*?\\]]"; 
	    String data = ReadFile("C:\\Users\\wolf\\Desktop\\new_extract.json"); //1. 读取文档
	    String needData = filterStr(regex, data); //2. 正则查找
	    writeFile("C:\\Users\\wolf\\Desktop\\HorizontalAnslysis.json", needData); //3. 写到某个文件中
//	        这里已经完成了所有水平表的输出。下面是统计水平表有多少行的过程
//	    System.out.println(needData);
	    List<Integer> total = new ArrayList<Integer>();
	    //这里获得一个一个完整的表（字符串），需要将字符串进行分割成字符串数组，进行统计、比较
	    String[] str = needData.toString().split("\n|\r");
	    for(int i=0 ; i<str.length ; i++){//通过arr.length获取字符串数组长度
//	    	  System.out.println(str[i]);//循环输出字符串数组元素
	    	  String s = "],";
	    	  int count = 1;
	    	  
	    	  for(int k=0; k<str[i].length() ;){  
		            int c = -1;
		            c = str[i].indexOf(s);//如果有S这样的子串。则C的值不是-1。
		            if(c != -1){// 如果c！=-1则，说明存在。
			    //这里的c+1 而不是 c+ s.length();这是因为。如果str的字符串是“aaaa”， s = “aa”，则结果是2个。但是实际上是3个子字符串  
		            //将剩下的字符冲洗取出放到str中  
		                 str[i] = str[i].substring(c + 1);  // 从存在的那个下标后一位开始
		                 count ++;
		                 
		            }else {
		            	//统计众数
		            	total.add(count);

		            	temp1 = count;//检测出最大值 
		                 if(temp1 > max){
		                	 max = temp1;
		                 }
		                 temp2 = count;//检测出最小值
		                 if(temp2 < min){
		                	 min = temp1;
		                 }
//		            	System.out.println("此表有   " + count + "  行");
		                counts += count;
		                average = counts/str[i].length(); 
		                break;
		            }  
		        }
	    	}
	    System.out.println(total);
	  	double a = median(total);
	  	System.out.println("行中位数是: " + a);
	    System.out.println("Horizontal(水平表)中的最大行：" + max);
	    System.out.println("Horizontal(水平表)中的最小行：" + min);
	    System.out.println("Horizontal(水平表)中的平均行数" + average);
	}
}