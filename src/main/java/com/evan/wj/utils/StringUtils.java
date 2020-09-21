package com.evan.wj.utils;

import java.util.Random;

//工具类：生成指定长度随机字符串的方法
public class StringUtils {
    public static String  getRandomString(int length){
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
