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
 * @Description: ������ͳ��������ͳ��
 * @author: RenZhiLong
 * @Date:2020-10-15����2:49:03
 */
public class analysis_horizontal {
	
	private static String filterStr(String regex,String data){

	    StringBuffer sb = new StringBuffer(); //sb�������ƥ��Ľ��
	    Pattern p = Pattern.compile(regex); //���������ַ���
	    Matcher matcher = p.matcher(data); //��ȡ����������ַ�
	    int i = 0;
	    while (matcher.find()) { //���ֺ󱣴�
	        sb.append(matcher.group() + "\r\n");
	        i++;
	        //��ӵ�sb
	    	}
	    System.out.println("����ѯ: " + i + " ����");
	    return sb.toString();
		}
	
	private static String ReadFile(String pathName) {
	    //��ȡ�����ļ����ݷŵ����sb��
	    StringBuffer sb = new StringBuffer();
		try {
			sb = new StringBuffer();
			FileReader fr = new FileReader(pathName);
			BufferedReader br = new BufferedReader(fr); 
		    String line;
		    while ((line = br.readLine()) != null) {
		        sb.append(line + "\r\n");
		    }
		    System.out.println("��ȡ�ļ����");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return sb.toString();
	    }
	
	private static void writeFile(String pathName, String data) {
	    try {
	        //�ļ������ڵĻ��½������ڸ���//	        file.createNewFile();
	        File file = new File(pathName);
	        FileWriter fw = new FileWriter(file);
	        BufferedWriter bw = new BufferedWriter(fw);
	            bw.write(data);
	            bw.flush();
	            System.out.println("�ļ�д�����");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 *
	 */
	private static double median(List<Integer> total) {
		double j = 0;
		//��������
	    Collections.sort(total);
	    int size = total.size();
	    if(size % 2 == 1){
	    	j = total.get((size-1)/2);}
	    else {
	    	//��0.0��Ϊ�˰�intת��double���ͣ��������2�����
	    	j = (total.get(size/2-1) + total.get(size/2) + 0.0)/2;
	    }
		return j;
	}
//	public static boolean isHave(String[] strs,String s){
//		/*�˷�bai����������������һ����Ҫ���ҵ��ַ������飬�ڶ�����Ҫ���ҵ��ַ����ַ���
//		* */
//		for(int i=0;i<strs.length;i++){
//		if(strs[i].indexOf(s)!=-1){//ѭ�������ַ��������е�ÿ���ַ������Ƿ�������в��ҵ�����
//		return true;//���ҵ��˾ͷ����棬���ڼ�����ѯ
//		}
//		}
//		return false;//û�ҵ�����false
//		}
	
	public static int max = 0,temp1 = 0;
	public static int min = 100,temp2 = 0;
	public static float counts = 0,average = 0;
	
	public static void main(String[] args) {
	    String regex = "\\[.*?\\]]"; 
	    String data = ReadFile("C:\\Users\\wolf\\Desktop\\new_extract.json"); //1. ��ȡ�ĵ�
	    String needData = filterStr(regex, data); //2. �������
	    writeFile("C:\\Users\\wolf\\Desktop\\HorizontalAnslysis.json", needData); //3. д��ĳ���ļ���
//	        �����Ѿ����������ˮƽ��������������ͳ��ˮƽ���ж����еĹ���
//	    System.out.println(needData);
	    List<Integer> total = new ArrayList<Integer>();
	    //������һ��һ�������ı��ַ���������Ҫ���ַ������зָ���ַ������飬����ͳ�ơ��Ƚ�
	    String[] str = needData.toString().split("\n|\r");
	    for(int i=0 ; i<str.length ; i++){//ͨ��arr.length��ȡ�ַ������鳤��
//	    	  System.out.println(str[i]);//ѭ������ַ�������Ԫ��
	    	  String s = "],";
	    	  int count = 1;
	    	  
	    	  for(int k=0; k<str[i].length() ;){  
		            int c = -1;
		            c = str[i].indexOf(s);//�����S�������Ӵ�����C��ֵ����-1��
		            if(c != -1){// ���c��=-1��˵�����ڡ�
			    //�����c+1 ������ c+ s.length();������Ϊ�����str���ַ����ǡ�aaaa���� s = ��aa����������2��������ʵ������3�����ַ���  
		            //��ʣ�µ��ַ���ϴȡ���ŵ�str��  
		                 str[i] = str[i].substring(c + 1);  // �Ӵ��ڵ��Ǹ��±��һλ��ʼ
		                 count ++;
		                 
		            }else {
		            	//ͳ������
		            	total.add(count);

		            	temp1 = count;//�������ֵ 
		                 if(temp1 > max){
		                	 max = temp1;
		                 }
		                 temp2 = count;//������Сֵ
		                 if(temp2 < min){
		                	 min = temp1;
		                 }
//		            	System.out.println("�˱���   " + count + "  ��");
		                counts += count;
		                average = counts/str[i].length(); 
		                break;
		            }  
		        }
	    	}
	    System.out.println(total);
	  	double a = median(total);
	  	System.out.println("����λ����: " + a);
	    System.out.println("Horizontal(ˮƽ��)�е�����У�" + max);
	    System.out.println("Horizontal(ˮƽ��)�е���С�У�" + min);
	    System.out.println("Horizontal(ˮƽ��)�е�ƽ������" + average);
	}
}