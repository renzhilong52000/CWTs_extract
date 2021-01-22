package AboutCount;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ProjectName: extract
 * @Description:
 * @author: RenZhiLong
 * @Date:2020-10-13 5:38:32
 */

public class analysis_vertical {
	
	private static String filterStr(String regex,String data){

	    StringBuffer sb = new StringBuffer(); //
	    Pattern p = Pattern.compile(regex); //
	    Matcher matcher = p.matcher(data); //
	    int i = 0;
	    while (matcher.find()) { //
	        sb.append(matcher.group() + "\r\n");
	        i++;
	        //
	    	}
	    System.out.println(i);
	    return sb.toString();
		}
	
	private static String ReadFile(String pathName) {
	    //
	    StringBuffer sb = new StringBuffer();
		try {
			sb = new StringBuffer();
			FileReader fr = new FileReader(pathName);
			BufferedReader br = new BufferedReader(fr); 
		    String line;
		    while ((line = br.readLine()) != null) {
		        sb.append(line + "\r\n");
		    }
		    System.out.println("锟斤拷取锟侥硷拷锟斤拷锟�");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return sb.toString();
	    }
	
	private static void writeFile(String pathName, String data) {
	    try {
	        //file.createNewFile();
	        File file = new File(pathName);
	        FileWriter fw = new FileWriter(file);
	        BufferedWriter bw = new BufferedWriter(fw);
	            bw.write(data);
	            bw.flush();
	            System.out.println("锟侥硷拷写锟斤拷锟斤拷锟�");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 *
	 */
	public static boolean isHave(String[] strs,String s){
		/** */
		for(int i=0;i<strs.length;i++){
			if(strs[i].indexOf(s)!=-1){//
			return true;//
			}
		}
		return false;//
	}
	
	public static int max = 0,temp1 = 0;
	public static int min = 100,temp2 = 0;
	public static float counts = 0,average = 0;
	
	public static void main(String[] args) {
	    String regex = "\\[.*?\\]]"; 
	    String data = ReadFile("C:\\Users\\wolf\\Desktop\\VertiaclExtract.json"); //1.
	    String needData = filterStr(regex, data); //2.
	    writeFile("C:\\Users\\wolf\\Desktop\\VertiaclAnslysis.json", needData); //3. 
//	    System.out.println(needData);
	    
	    //
	    String[] str = needData.toString().split("\n|\r");
	    for(int i=0 ; i<str.length ; i++){//
//	    	  System.out.println(str[i]);//
	    	  String s = "],";
	    	  int count = 1;
	    	  for(int k=0; k<str[i].length() ;){  
		            int c = -1;
		            c = str[i].indexOf(s);//
		            if(c != -1){//
			    //
		            //
		                 str[i] = str[i].substring(c + 1);  //
		                 count ++;
		                 
		                 temp1 = count;//
		                 if(temp1 > max){
		                	 max = temp1;
		                 }
		                 temp2 = count;//
		                 if(temp2 < min){
		                	 min = temp1;
		                 }
		            }else {
		                System.out.println(" This table has " + count + " Column ");
		                counts += count;
		                average = counts/str[i].length();
		                break;  
		            }  
		        }
	    	}
	    System.out.println("Vertiacl The largest column is:"+ max);
	    System.out.println("Vertiacl The smallest column is:" + min);
	    System.out.println("Vertiacl The average number of columns is:" + average);
	}
}