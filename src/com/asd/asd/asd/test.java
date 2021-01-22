package com.asd.asd.asd;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @ProjectName: extract
 * @Description:
 * @author: Ren Zhilong
 * @Date:2020-11-26обнГ7:09:16
 */
public class test {

    public static final String CHAR_PATTERN = "[^0-9]";
    public static Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
    public  static void main(String[] args){
        Scanner cin = new Scanner(System.in);
        String str = cin.nextLine();
        String str1[] = str.split(" ");
        
        for(int i = 0;i < str1.length;i++){
            if(i != 0)
                System.out.print(' ');
            if ("true".equalsIgnoreCase(str1[i]) || "false".equalsIgnoreCase(str1[i])) {
                System.out.print("boolean");
            }
            else if(str1[i].matches("[0-9]+")){
                System.out.print("int");
            }
            else if(pattern.matcher(str1[i]).matches()){
                System.out.print("double");
            }
            else
                System.out.print("String");
 
        }
 
    }
}
