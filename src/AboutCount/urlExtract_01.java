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
 * @Description:��ȡ�������ļ��е���ҳ  URL�������������  ��ͳ������
 * @author: RenZhiLong
 * @Date:2020-10-11����11:13:51
 */
public class urlExtract_01 {
	
	private static String filterStr(String regex,String data){

	    StringBuffer sb = new StringBuffer(); //sb�������ƥ��Ľ��
	    Pattern p = Pattern.compile(regex); //���������ַ���
	    Matcher matcher = p.matcher(data); //��ȡ����������ַ�
	    while (matcher.find()) { //���ֺ󱣴�
	        sb.append(matcher.group() + "\r\n");//��ӵ�sb
	    	}
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
	
	private static void writeFile(String pathName, String data) throws IOException {

	    	long t1 = System.currentTimeMillis();
	        //�ļ������ڵĻ��½������ڸ���//file.createNewFile();
	        File file = new File(pathName);
	        FileWriter fw = new FileWriter(file);
	        BufferedWriter bw = new BufferedWriter(fw);
	        
		    String[] str = data.toString().split("\n|\r");
		    
			Map<String, Integer> m = new HashMap<String, Integer>();
	        for (int i = 0; i < str.length; i++) {
				if (m.containsKey(str[i])) {//�ж� key ֵ�Ƿ���ڵ�ǰֵ�� key
					//����ȣ���� key ֵÿ�μ�һ
					m.put(str[i], m.get(str[i]) + 1);
				} else {
					//������ȣ�����룬�������� key Ϊ1
					m.put(str[i], 1);
				}
			}
	        
	      //��map.entrySet()ת����list  
            List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(m.entrySet());  
            //ͨ���Ƚ���ʵ������  
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
                //��������  
                public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
                    return o2.getValue().compareTo(o1.getValue());  
                }  
            });
	       
            int num = 1;  
            //�����д���ļ�  
            for(Map.Entry<String, Integer> map : list) {
//	            	System.out.println(map.getKey());
                if(num <= 200) {  
                    bw.write("key: " + map.getKey() + "            value: " + map.getValue() );  
                    bw.newLine();//д��һ���зָ���
                    num++;  
                }
            }
            bw.newLine(); 
            bw.flush();
            System.out.println("�ļ�д�����");
            bw.write("��ʱ��" + (System.currentTimeMillis() - t1) + "ms");  
            System.out.println("��ʱ��" + (System.currentTimeMillis() - t1) + "ms");

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
	    
	    String data = ReadFile("C:\\Users\\wolf\\Desktop\\new_extract.json"); // ��ȡ�ĵ�
	    String needData = filterStr(regex, data); 
	    String needData2 = filterStr(regex2, data); 
	    String needData3 = filterStr(regex3, data);
	    
	    writeFile("C:\\Users\\wolf\\Desktop\\URL.json", needData); // д��ĳ���ļ���
	    writeFile("C:\\Users\\wolf\\Desktop\\URL_domain.json", needData2); 
	    writeFile("C:\\Users\\wolf\\Desktop\\pageTitle.json", needData3); 
	    System.out.println("finish");
	    
	    //����Ϊͳ����ֵ����
//		    String[] str = needData.toString().split("\n|\r");
//			Map<String, Integer> m = new HashMap<String, Integer>();
		
//			for (int i = 0; i < str.length; i++) {
//				if (m.containsKey(str[i])) {//�ж� key ֵ�Ƿ���ڵ�ǰֵ�� key
//					//����ȣ���� key ֵÿ�μ�һ
//					m.put(str[i], m.get(str[i]) + 1);
//				} else {
//					//������ȣ�����룬�������� key Ϊ1
//					m.put(str[i], 1);
//				}
//			}
//			Set<String> res = m.keySet();
//			for (String im : res) {
//				System.out.println("Key��" + im + "        " + "Value��" + m.get(im));
//			}
		
	}

}