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
 * @Description:VERTIACL����ֱ����ˮƽ���������������
 * @author: RenZhiLong
 * @Date:2020-10-11����11:11:12
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
            //���ļ����ݴ���StringBuffer��  
            while((s = br.readLine()) != null) {  
                sb.append(s);  
            }  
            //�����ִ�Сд
            String str = sb.toString().toLowerCase();
          //�ָ��ַ�������������  (�ԣ����ո�ָ�)
            String[] elements = str.split("[,.:;\"_\\s\\W+\\d+]"); //:;\" 
//            String[] elements = str.split("\\W+");
            
            int count = 0;  
            Map<String, Integer> myTreeMap = new TreeMap<String, Integer>();  
            //�������齫�����Map<String, Integer>��  
            for(int i = 0; i < elements.length; i++) {  
                if(myTreeMap.containsKey(elements[i])) {  
                    count = myTreeMap.get(elements[i]);  
                    myTreeMap.put(elements[i], count + 1);  
                }  
                else {
                    myTreeMap.put(elements[i], 1);  
                }  
            }              
            System.out.println("����ͳ�ƵĽ�������ǰĿ¼result.json�ļ�");  
            //��map.entrySet()ת����list  
            List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(myTreeMap.entrySet());  
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
//            	System.out.println(map.getKey());
                if(num <= 200) {  
                    bw.write("���ִ�����" + num + "�ĵ���Ϊ��" + map.getKey() + "   ����Ƶ��Ϊ" + map.getValue() + "��");  
                    bw.newLine();//д��һ���зָ���
                    num++;  
                }
            }
            bw.newLine();
//            	System.out.println(list.size());
            System.out.println("����������������������ʼͳ���к��С�������������������");
            float a = 0,b = 0,temp = 0;
            float c = 0;//����
            for(Map.Entry<String, Integer> map : list) {  
                if(map.getKey().equals("VERTICAL") || map.getKey().equals("vertical")){//word.equals("HORIZONTAL")
//                	System.out.println(map.getKey());// || map.getKey().equals("vertical")
                	bw.write("\"VERTICAL\"���ʳ���Ƶ��Ϊ" + map.getValue() + "��");
                	bw.newLine();
                	a = map.getValue();
                }
    
                if(map.getKey().equals("HORIZONTAL") || map.getKey().equals("horizontal")){
//                	System.out.println(map.getKey());// || map.getKey().equals("horizontal")
                	bw.write("\"HORIZONTAL\"���ʳ���Ƶ��Ϊ" + map.getValue() + "��"); 
                	bw.newLine();
                	b = map.getValue();
                }
            }
            temp = a + b;
            c = b/temp;

            bw.write("�˴ι���ѯ: " + temp + " �������ˮƽ��Լռ" + c);
            bw.newLine();
            
            bw.write("��ʱ��" + (System.currentTimeMillis() - t1) + "ms");  
            System.out.println("��ʱ��" + (System.currentTimeMillis() - t1) + "ms");
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