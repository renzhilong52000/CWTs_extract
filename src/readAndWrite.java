import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/**
 * @ProjectName: extract
 * @Description:
 * @author: RenZhiLong
 * @Date:2020-10-8����7:45:38
 */
public class readAndWrite {

    public static void readFile02() throws IOException {
    	File file = new File("C:\\Users\\wolf\\Desktop\\test.txt");
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        //��д����
        //BufferedReader br = new BufferedReader(new InputStreamReader(
        //        new FileInputStream("E:/phsftp/evdokey/evdokey_201103221556.txt"), "UTF-8"));
        String line="";
        String[] arrs=null;
        while ((line=br.readLine())!=null) {
            arrs=line.split(",");
            System.out.println(arrs[0] + " : " + arrs[1] + " : " + arrs[2]);
        }
        br.close();
        isr.close();
        fis.close();
    }
 
 

    public static void writeFile02() throws IOException {
        String[] arrs={
                "zhangsan,23,����",
                "lisi,30,�Ϻ�",
                "wangwu,43,����",
                "laolin,21,����",
                "ximenqing,67,����"
        };
        //д�������ַ�ʱ���������������
        FileOutputStream fos=new FileOutputStream(new File("C:\\Users\\wolf\\Desktop\\test.txt"));
        OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter  bw=new BufferedWriter(osw);
        //��д���£�
        //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
        //        new FileOutputStream(new File("E:/phsftp/evdokey/evdokey_201103221556.txt")), "UTF-8"));
 
        for(String arr:arrs){
            bw.write(arr+"\r\n");//���н�������
        }
 
        //ע��رյ��Ⱥ�˳���ȴ򿪵ĺ�رգ���򿪵��ȹر�
        bw.close();
        osw.close();
        fos.close();
    }
	
	
	
	public static void main(String[] args) {
		try {
			readFile02();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
