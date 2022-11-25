package com.tty.utils;

import java.util.Random;

/**
 * @ClassName UrlUtils
 * @Description TODO
 * @Author Tommy Yeung
 * @Date 2022/11/22 上午 10:25
 **/
public class UrlUtils {
    public static String generateSeparate(int length){

        StringBuilder sb = new StringBuilder();
        Random rd = new Random();

        for (int i = 0; i < length; i++) {

            int flag = rd.nextInt(3) + 1;

            switch (flag){
                case 1:
                    sb.append(rd.nextInt(10)); // 0 - 9
                    break;
                case 2:
                    //拼接小写字母
                    sb.append((char)(rd.nextInt(26) + 97)); //a-z
                    break;
                case 3:
                    //拼接大写字母
                    sb.append((char)(rd.nextInt(26) + 65)); //a-z
                    break;
            }

        }
        return sb.toString();
    }

}
