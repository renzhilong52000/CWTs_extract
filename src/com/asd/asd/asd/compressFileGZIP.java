package com.asd.asd.asd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * @ProjectName: extract
 * @Description:
 * @author: RenZhiLong
 * @Date:2020-10-25 12:17:19
 */
public class compressFileGZIP {   
private static void doCompressFile(String inFileName) {   
    
        try {   
          
            System.out.println("Creating the GZIP output stream.");   
            String outFileName = inFileName + ".gz";   
            GZIPOutputStream out = null;   
            try {   
                out = new GZIPOutputStream(new FileOutputStream(outFileName));   
            } catch(FileNotFoundException e) {   
                System.err.println("Could not create file: " + outFileName);   
                System.exit(1);   
            }   
                      
    
            System.out.println("Opening the input file.");   
            FileInputStream in = null;   
            try {   
                in = new FileInputStream(inFileName);   
            } catch (FileNotFoundException e) {   
            System.err.println("File not found. " + inFileName);   
                System.exit(1);   
            }   
  
            System.out.println("Transfering bytes from input file to GZIP Format.");   
            byte[] buf = new byte[1024];   
            int len;   
            while((len = in.read(buf)) > 0) {   
                out.write(buf, 0, len);   
            }   
            in.close();   
  
            System.out.println("Completing the GZIP file");   
            out.finish();   
            out.close();   
          
        } catch (IOException e) {   
            e.printStackTrace();   
            System.exit(1);   
        }   
  
    }   
  
    /**  
     * Sole entry point to the class and application.  
     * @param args Array of String arguments.  
     */   
    public static void main(String[] args) { 
    	int i = 0;
    	String str0="E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\域名提取\\extractSameUrl_cn.json";
    	String str1="E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\域名提取\\extractSameUrl_co.json";
    	String str2="E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\域名提取\\extractSameUrl_edu.json";
    	String str3="E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\域名提取\\extractSameUrl_gov.json";
    	String str4="E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\域名提取\\extractSameUrl_net.json";
//    	String str5="E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\域名提取\\url_org\\extractSameUrl_org5.json";
//    	String str6="E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\域名提取\\url_org\\extractSameUrl_org6.json";
//    	String str7="E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\域名提取\\url_org\\extractSameUrl_org7.json";
//    	String str8="E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\域名提取\\url_org\\extractSameUrl_org8.json";
//    	String str9="E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\域名提取\\url_org\\extractSameUrl_org9.json";
    	//    	String str10="E:\\paper1\\CoreOfSummerVacation\\data\\allStatistics\\extractSameUrl\\domainCountry\\extractSameUrl.tw.json";
    	//	    String str6="E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\HORIZONTAL\\Horizontal_60.69.117369.json";
//	    String str7="E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\HORIZONTAL\\Horizontal_70.79.127170.json";
//	    String str8="E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\HORIZONTAL\\Horizontal_80.89.124839.json";
//	    String str9="E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\HORIZONTAL\\Horizontal_90.99.128151.json";
//	    String str10="E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\VERTICAL\\VERTICAL_0.9.json";
//	    String str11="E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\VERTICAL\\VERTICAL_10.19.json";
//	    String str12="E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\VERTICAL\\VERTICAL_20.29.json";
//	    String str13="E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\VERTICAL\\VERTICAL_30.39.json";
//	    String str14="E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\VERTICAL\\VERTICAL_40.49.json";
//	    String str15="E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\VERTICAL\\VERTICAL_50.59.json";
//	    String str16="E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\VERTICAL\\VERTICAL_60.69.json";
//	    String str17="E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\VERTICAL\\VERTICAL_70.79.json";
//	    String str18="E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\VERTICAL\\VERTICAL_80.89.json";
//	    String str19="E:\\paper1\\CoreOfSummerVacation\\data\\GZFilesort\\VERTICAL\\VERTICAL_90.99.json";
//	    String str20="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\20.14136.json";
//	    String str21="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\21.13773.json";
//	    String str22="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\22.13734.json";
//	    String str23="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\23.13861.json";
//	    String str24="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\24.13933.json";
//	    String str25="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\25.14204.json";
//	    String str26="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\26.13904.json";
//	    String str27="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\27.14252.json";
//	    String str28="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\28.12948.json";
//	    String str29="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\29.11085.json";
//	    String str30="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\30.13608.json";
//	    String str31="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\31.14269.json";
//	    String str32="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\32.11032.json";
//	    String str33="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\33.13531.json";
//	    String str34="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\34.13171.json";
//	    String str35="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\35.13351.json";
//	    String str36="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\36.11593.json";
//	    String str37="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\37.13734.json";
//	    String str38="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\38.14243.json";
//	    String str39="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\39.14200.json";
//	    String str40="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\40.14091.json";
//	    String str41="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\41.13584.json";
//	    String str42="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\42.14125.json";
//	    String str43="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\43.13763.json";
//	    String str44="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\44.13834.json";
//	    String str45="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\45.14175.json";
//	    String str46="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\46.13645.json";
//	    String str47="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\47.13830.json";
//	    String str48="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\48.13351.json";
//	    String str49="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\49.14074.json";
//	    String str50="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\50.13429.json";
//	    String str51="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\51.13689.json";
//	    String str52="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\52.13784.json";
//	    String str53="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\53.14112.json";
//	    String str54="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\54.13335.json";
//	    String str56="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\56.13536.json";
//	    String str57="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\57.13831.json";
//	    String str58="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\58.11191.json";
//	    String str59="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\59.13950.json";
//	    String str60="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\60.14223.json";
//	    String str61="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\61.13943.json";
//	    String str62="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\62.14318.json";
//	    String str63="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\63.1631.json";
//	    String str64="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\64.14187.json";
//	    String str65="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\65.13818.json";
//	    String str66="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\66.14115.json";
//	    String str67="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\67.13700.json";
//	    String str68="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\68.14304.json";
//	    String str69="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\69.13660.json";
//	    String str70="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\70.13674.json";
//	    String str71="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\71.13817.json";
//	    String str72="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\72.14192.json";
//	    String str73="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\73.13726.json";
//	    String str74="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\74.13391.json";
//	    String str75="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\75.13883.json";
//	    String str76="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\76.13833.json";
//	    String str77="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\77.13504.json";
//	    String str78="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\78.14093.json";
//	    String str79="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\79.13669.json";
//	    String str80="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\80.13038.json";
//	    String str81="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\81.13474.json";
//	    String str82="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\82.13532.json";
//	    String str83="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\83.12918.json";
//	    String str84="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\84.13850.json";
//	    String str85="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\85.14098.json";
//	    String str86="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\86.13783.json";
//	    String str87="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\87.13879.json";
//	    String str88="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\88.13283.json";
//	    String str89="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\89.13866.json";
//	    String str90="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\90.13527.json";
//	    String str91="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\91.14304.json";
//	    String str92="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\92.14028.json";
//	    String str93="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\93.14373.json";
//	    String str94="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\94.13547.json";
//	    String str95="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\95.14223.json";
//	    String str96="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\96.13450.json";
//	    String str97="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\97.13908.json";
//	    String str98="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\98.13814.json";
//	    String str99="E:\\paper1\\CoreOfSummerVacation\\data\\chineseData\\99.13867.json";

        doCompressFile(str0);
        System.out.println(i++);	    
        doCompressFile(str1);
        System.out.println(i++);
        doCompressFile(str2);
        System.out.println(i++);
        doCompressFile(str3);
        System.out.println(i++);
        doCompressFile(str4);
        System.out.println(i++);
//        doCompressFile(str5);
//        System.out.println(i++);
//        doCompressFile(str6);
//        System.out.println(i++);
//        doCompressFile(str7);
//        System.out.println(i++);
//        doCompressFile(str8);
//        System.out.println(i++);
//        doCompressFile(str9);
//        System.out.println(i++);
//        doCompressFile(str10);
//        System.out.println(i++);
//        doCompressFile(str11);
//        System.out.println(i++);
//        doCompressFile(str12);
//        System.out.println(i++);
//        doCompressFile(str13);
//        System.out.println(i++);
//        doCompressFile(str14);  
//        System.out.println(i++);
//        doCompressFile(str15);
//        System.out.println(i++);
//        doCompressFile(str16);  
//        System.out.println(i++);
//        doCompressFile(str17);
//        System.out.println(i++);
//        doCompressFile(str18);  
//        System.out.println(i++);
//        doCompressFile(str19);
//        System.out.println(i++);
//        doCompressFile(str20);
//        System.out.println(i++);
//        doCompressFile(str21);
//        System.out.println(i++);
//        doCompressFile(str22);
//        System.out.println(i++);
//        doCompressFile(str23);
//        System.out.println(i++);
//        doCompressFile(str24);  
//        System.out.println(i++);
//        doCompressFile(str25);
//        System.out.println(i++);
//        doCompressFile(str26);  
//        System.out.println(i++);
//        doCompressFile(str27);
//        System.out.println(i++);
//        doCompressFile(str28);  
//        System.out.println(i++);
//        doCompressFile(str29);
//        System.out.println(i++);
//        doCompressFile(str30);
//        System.out.println(i++);
//        doCompressFile(str31);
//        System.out.println(i++);
//        doCompressFile(str32);
//        System.out.println(i++);
//        doCompressFile(str33);
//        System.out.println(i++);
//        doCompressFile(str34);  
//        System.out.println(i++);
//        doCompressFile(str35);
//        System.out.println(i++);
//        doCompressFile(str36);  
//        System.out.println(i++);
//        doCompressFile(str37);
//        System.out.println(i++);
//        doCompressFile(str38);  
//        System.out.println(i++);
//        doCompressFile(str39);
//        System.out.println(i++);
//        doCompressFile(str40);
//        System.out.println(i++);
//        doCompressFile(str41);
//        System.out.println(i++);
//        doCompressFile(str42);
//        System.out.println(i++);
//        doCompressFile(str43);
//        System.out.println(i++);
//        doCompressFile(str44);  
//        System.out.println(i++);
//        doCompressFile(str45);
//        System.out.println(i++);
//        doCompressFile(str46);  
//        System.out.println(i++);
//        doCompressFile(str47);
//        System.out.println(i++);
//        doCompressFile(str48);  
//        System.out.println(i++);
//        doCompressFile(str49);
//        System.out.println(i++);
//        doCompressFile(str50);
//        System.out.println(i++);
//        doCompressFile(str51);
//        System.out.println(i++);
//        doCompressFile(str52);
//        System.out.println(i++);
//        doCompressFile(str53);
//        System.out.println(i++);
//        doCompressFile(str54);  
//        System.out.println(i++);
//        System.out.println(i++);
//        doCompressFile(str56);  
//        System.out.println(i++);
//        doCompressFile(str57);
//        System.out.println(i++);
//        doCompressFile(str58);  
//        System.out.println(i++);
//        doCompressFile(str59);
//        System.out.println(i++);
//        doCompressFile(str60);
//        System.out.println(i++);
//        doCompressFile(str61);
//        System.out.println(i++);
//        doCompressFile(str62);
//        System.out.println(i++);
//        doCompressFile(str63);
//        System.out.println(i++);
//        doCompressFile(str64);  
//        System.out.println(i++);
//        doCompressFile(str65);
//        System.out.println(i++);
//        doCompressFile(str66);  
//        System.out.println(i++);
//        doCompressFile(str67);
//        System.out.println(i++);
//        doCompressFile(str68);  
//        System.out.println(i++);
//        doCompressFile(str69);
//        System.out.println(i++);
//        doCompressFile(str70);
//        System.out.println(i++);
//        doCompressFile(str71);
//        System.out.println(i++);
//        doCompressFile(str72);
//        System.out.println(i++);
//        doCompressFile(str73);
//        System.out.println(i++);
//        doCompressFile(str74);  
//        System.out.println(i++);
//        doCompressFile(str75);
//        System.out.println(i++);
//        doCompressFile(str76);  
//        System.out.println(i++);
//        doCompressFile(str77);
//        System.out.println(i++);
//        doCompressFile(str78);  
//        System.out.println(i++);
//        doCompressFile(str79);
//        System.out.println(i++);
//        doCompressFile(str80);
//        System.out.println(i++);
//        doCompressFile(str81);
//        System.out.println(i++);
//        doCompressFile(str82);
//        System.out.println(i++);
//        doCompressFile(str83);
//        System.out.println(i++);
//        doCompressFile(str84);  
//        System.out.println(i++);
//        doCompressFile(str85);
//        System.out.println(i++);
//        doCompressFile(str86);  
//        System.out.println(i++);
//        doCompressFile(str87);
//        System.out.println(i++);
//        doCompressFile(str88);  
//        System.out.println(i++);
//        doCompressFile(str89);
//        System.out.println(i++);
//        doCompressFile(str90);
//        System.out.println(i++);
//        doCompressFile(str91);
//        System.out.println(i++);
//        doCompressFile(str92);
//        System.out.println(i++);
//        doCompressFile(str93);
//        System.out.println(i++);
//        doCompressFile(str94);  
//        System.out.println(i++);
//        doCompressFile(str95);
//        System.out.println(i++);
//        doCompressFile(str96);  
//        System.out.println(i++);
//        doCompressFile(str97);
//        System.out.println(i++);
//        doCompressFile(str98);  
//        System.out.println(i++);
//        doCompressFile(str99);
//        System.out.println(i++);
    }   
} 