package com.asd.asd.asd;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ProjectName: extract
 * @Description:
 * @author: Ren Zhilong
 * @Date:2020-11-27ÏÂÎç6:41:24
 */
public class test2{
	 
    public static void main(String[] args) {
        String str = "this is \"Tom\" and \"Eric\"£¬ this is \"Bruce lee\", he is a chinese, name is \"ÀîĞ¡Áú\"¡£";
 
        Pattern p1=Pattern.compile("\"(.*?)\"");
 
        Matcher m = p1.matcher(str);
 
        ArrayList<String> list = new ArrayList<String>();
        while (m.find()) {
            list.add(m.group().trim().replace("\"","")+" ");
        }
        System.out.println(list.toString());
    }
}
