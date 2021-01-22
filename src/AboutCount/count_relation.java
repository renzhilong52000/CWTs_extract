package AboutCount;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @ProjectName: extract
 * @Description:��ѯ��JSON�ĵ���relation�����˶��ٴΣ��ж��ٸ����ǹ�ϵ��
 * @author: RenZhiLong
 * @Date:2020-10-10����3:22:02
 */
public class count_relation {
	 
	 public static int count(String filename, String target)
	    throws FileNotFoundException, IOException {
	   FileReader fr = new FileReader(filename);
	   BufferedReader br = new BufferedReader(fr);
	   StringBuilder sb = new StringBuilder();
	   while (true) {
	    String line = br.readLine();
	    if (line == null) {
	     break;
	    }
	    sb.append(line);
	   }
	   String result = sb.toString();
	   
	   int count = 0;
	   int index = 0;
	   
	   while (true) {
	    index = result.indexOf(target, index + 1);
	    if (index > 0) {
	     count++;
	    } else {
	     break;
	    }
	   }
	   br.close();
	   return count;
	  }
	 
	 public static void main(String[] args) {
	   try {
	    System.out.println(count("C:\\Users\\wolf\\Desktop\\extractsample.json", "relation"));
	   } catch (FileNotFoundException e) {
	    e.printStackTrace();
	   } catch (IOException e) {
	    e.printStackTrace();
	   }
	  }
	 
	}