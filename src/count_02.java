import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @ProjectName: extract
 * @Description:输出一个文件的所有 汉字数 + 行数
 * @author: RenZhiLong
 * @Date:2020-10-8下午3:27:01
 */
public class count_02 {
	
	// 判断一个字符是否是中文
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
	
	public static void CountChineseLine(){
		File file = new File("C:\\Users\\wolf\\Desktop\\extractsample.json");
		try {
			String name = file.getName();
			System.out.println("Name:"+name);
			InputStreamReader read = new InputStreamReader(new FileInputStream(file)); 
            BufferedReader in = new BufferedReader(read);//可用于读取指定文件     
			
			String str=null;
			String b=null;
			int i = 0;//定义一个整型变量,用于统计行数
            int c1 = 0;//定义整型变量，用于统计字符数

            while ((str = in.readLine())!= null) {//readLine()方法, 用于读取一行,只要读取内容不为空就一直执行
            	i++;//每循环一次就进行一次自增，用于统计文本行数
            	for (int j = 0; j < str.length(); j++) {//for循环的条件，当j小于该行长度时就一直循环并自增
            		b = Character.toString(str.charAt(j));//返回一个字符串对象
            		if (b.matches("[\\u4e00-\\u9fa5]")) {//if语句的条件，判断是否为汉字
                        c1++;//若为汉字则c1自增
                    }
            		}
            	}
            in.close();//关闭流
            System.out.printf("contains：");//输出提示信息
            System.out.printf(c1+" 个汉字       ");//输出汉字数
            System.out.println(i+" 行");//输出有多少行
        } catch (IOException e) {
        	e.printStackTrace();
        	}
        }
	
	
	public static void main(String[] args) {
		System.out.println("start count......");
		CountChineseLine();
		System.out.println("count success");
	}

}
