
/**
 * @ProjectName: extract
 * @Description:成功复制了json文件
 * @author: RenZhiLong
 * @Date:2020-10-8下午2:56:51
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class copy_03 {
	public static void copy(String src,String target){
		 File srcfile = new File(src);
		 File targetfile = new File(target);
		 
		 try {
			InputStream in = new FileInputStream(srcfile);
			OutputStream out = new FileOutputStream(targetfile);
			
			byte[] bytes = new byte[1024];
			int len = -1;
			while((len = in.read(bytes))!= -1){
				out.write(bytes, 0, len);
			}
			out.close();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}
	
	public static void main(String[] args) {
		System.out.println("start copy......");
		copy("C:\\Users\\wolf\\Desktop\\sample.json","C:\\Users\\wolf\\Desktop\\copysample.json");
		System.out.println("copy success");
	}

}
