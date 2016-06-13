package com.baidu.chapter5.section2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Utils {
    /**
     * 对密码进行加密
     * @param password  要加密的密码
     * @return  密文
     */
    public static String digest(String password) {
        try {
            //加密方式为MD5加密
            MessageDigest digest = MessageDigest.getInstance("MD5");
            // 把一个byte数组 转换成加密后byte数组
            byte[] bytes = digest.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                // 去掉负数
                int c = b & 0xff;  // 负数转换成正数   // 加盐
                String result = Integer.toHexString(c) + 3;// 把10进制的数 转换成16进制的数 0 255
                if (result.length() < 2) {
                    sb.append("0");// 让十六进制数 全部都是两位数
                }
                sb.append(result);
            }
            return sb.toString();  // 把加密后的密文返回回去

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // can't reach 不会发生的错误
            return "";
        }
    }
}
