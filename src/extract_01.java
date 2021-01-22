import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;


/**
 * @ProjectName: extract
 * @Description:
 * @author: RenZhiLong
 * @Date:2020-10-8下午8:34:45
 */
public class extract_01 {
	
	//检测是否为中文
//	public static boolean isChinesechar(char c) {
//		return c >= 0x4E00 && c <= 0x9FA5;// 根据字节码判断
//	}
//
//	
//	public static boolean hasChinese(String src) {
//		if (src == null) return false;
//		for (char c : src.toCharArray()) {
//			if (isChinesechar(c))
//				return true;//定义一个遍历字符c，让它分别等于字符串数组chars里面的各个字符，然后执行下面的语句，当c被赋值为chars里面所有字符各一次后，就会退出这个循环.
//		}
//		return false;
//		}
	
	public static void extract(String src,String target){
		 File srcfile = new File(src);
		 File targetfile = new File(target);
		 
		 try {
			InputStream in = new FileInputStream(srcfile);
			OutputStream out = new FileOutputStream(targetfile);
			InputStreamReader read = new InputStreamReader(new FileInputStream(srcfile),"utf-8"); 
            BufferedReader br = new BufferedReader(read);//可用于读取指定文件     
            
            FileWriter fw = new FileWriter(targetfile); 
            BufferedWriter bw = new BufferedWriter(fw);
            
//			FileReader fr = new FileReader(srcfile); //fr作为一个指针来读取文件
//			BufferedReader bufr = new BufferedReader(fr); //fr读取的数值缓冲在bufr
			
//			byte[] bytes = new byte[1024];
//			int len = -1;
			String str=null;
			String b=null;
            while ((str = br.readLine())!= null) { //readLine()方法, 用于读取一行,只要读取内容不为空就一直执行
        		
            	if(str.length() == 0){
        			break;
        		}
            	
            	for (int j = 0; j < str.length(); j++) { //for循环的条件，当j小于该行长度时就一直循环并自增
            		b = Character.toString(str.charAt(j)); //返回一个字符串对象
            		if (b.matches("[\\u4e00-\\u9fa5]")) {//if语句的条件，判断是否为汉字
//             			System.out.println(str);
             			bw.write(str);
             			bw.newLine();
            		//	byte[] sb = str.getBytes();
            			//out.write(sb);
//            			bw.flush();
            			break;
            			//这里检测到了一个汉字，这里为输出这一行进入下一行循环
//            			while((len = in.read(bytes))!= -1){ //copy02中 一直读整个直到读完
//                			System.out.println(b);
//                			break;
//            			}
//            			while((len = in.read(bytes))!= -1){
//            				out.write(bytes, 0, len);
//                  } 

                    } 
            	}
            }
            bw.close();
			out.close();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}
		
	public static void main(String[] args) {
		System.out.println("start extract......");
		extract("C:\\Users\\wolf\\Desktop\\CC-MAIN-20150728002301-00342-ip-10-236-191-2.ec2.internal.json","C:\\Users\\wolf\\Desktop\\new_extract.json");
		System.out.println("extract success");
	}

}
