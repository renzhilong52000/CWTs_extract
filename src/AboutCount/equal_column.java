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
 * @Description:��ǰ��Ҫ������
 * 1 ��дһ�������ͳ�Ƴ����С�],�� �����䴫��һ������
 * 2 ������������飬����hashMap����ͳ�ƣ�����
 * 3 ���õ���ֵ����д��һ��json�ļ�
 * @author: RenZhiLong
 * @Date:2020-10-22����2:35:42
 */
public class equal_column {
	
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
	    System.out.println("��ѯ��������   " + i);
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
	
	
	
	private static String[] insert(String[] str_c, String count1) {
		int size = str_c.length;  //��ȡ���鳤��
	    String[] tmp = new String[size + 1];  //�½���ʱ�ַ������飬��ԭ�������ϳ��ȼ�һ
	    for (int i = 0; i < size; i++){  //�ȱ�����ԭ�����ַ�������������ӵ���ʱ�ַ�������
	        tmp[i] = str_c[i];
	    }
	    tmp[size] = count1;  //������������Ҫ׷�ӵ�����
	    return tmp;  //����ƴ����ɵ��ַ�������
	}
	/**
	 *
	 */	
	
	public static void main(String[] args) throws IOException {
	    String regex = "\\[.*?\\]]"; 
	    String data = ReadFile("C:\\Users\\wolf\\Desktop\\new_extract.json"); //1. ��ȡ�ĵ�
	    String needData = filterStr(regex, data); //2. �������
	    File file = new File("C:\\Users\\wolf\\Desktop\\levelSet.json");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
  	    String[] str_c = new String[0];
	    
	    //������һ��һ�������ı��ַ���������Ҫ���ַ������зָ���ַ������飬����ͳ�ơ��Ƚ�
	    String[] str = needData.toString().split("\n|\r");
	    for(int i=0 ; i<str.length ; i++){//ͨ��str.length��ȡ�ַ������鳤��
//	    	  System.out.println(str[i]);//ѭ������ַ�������Ԫ��
	    	  String s = "],";
	    	  int count = 1;

	    	  //���], ��һ����˵����һ��
	    	  for(int k=0; k<str[i].length() ;){  
		            int c = -1;
		            c = str[i].indexOf(s);//�����S�������Ӵ�����C��ֵ����-1��
		            if(c != -1){// ���c��=-1��˵�����ڡ�
			    //�����c+1 ������ c+ s.length();������Ϊ�����str���ַ����ǡ�aaaa���� s = ��aa����������2��������ʵ������3�����ַ���  
		            //��ʣ�µ��ַ���ϴȡ���ŵ�str��  
		                 str[i] = str[i].substring(c + 1);  // �Ӵ��ڵ��Ǹ��±��һλ��ʼ
		                 count ++;
		            }else {
		            	String count1 = String.valueOf(count);
		            	str_c = insert(str_c,count1);
		            	//Ӧ�óɹ���������ַ���
		            	break;
		            }
		        }
	    	}
	    
	    //str_c[i]�����������װ�������е�Ԫ���� eg��2�е�5�е�8�е� �������������[2,5,8]
	    Map<String, Integer> m = new HashMap<String, Integer>();
        for (int i = 0; i < str_c.length; i++) {
			if (m.containsKey(str_c[i])) {//�ж� key ֵ�Ƿ���ڵ�ǰֵ�� key
				//����ȣ���� key ֵÿ�μ�һ
				m.put(str_c[i], m.get(str_c[i]) + 1);
			} else {
				//������ȣ�����룬�������� key Ϊ1
				m.put(str_c[i], 1);
			}
		}
        System.out.println(m);
        System.out.println("");	   
        
      //��map.entrySet()ת����list  
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(m.entrySet());  
        //ͨ���Ƚ���ʵ������  
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
            //��������  
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
                return o2.getValue().compareTo(o1.getValue());  
            }  
        });  
        System.out.println("ʵ�ֽ�������"); 
        System.out.println(list);
        System.out.println("");	   

        //�����д���ļ�  
        for(Map.Entry<String, Integer> map : list) {
            bw.write("key: " + map.getKey() + "            value: " + map.getValue() );
            System.out.println("key: " + map.getKey() + "            value: " + map.getValue());
            bw.newLine();//д��һ���зָ���
        }
        bw.newLine(); 
	    
	}
}








