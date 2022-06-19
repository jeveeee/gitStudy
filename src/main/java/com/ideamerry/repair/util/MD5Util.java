package com.ideamerry.repair.util;

import java.security.MessageDigest;

/**
 * MD5加密工具
 */
public class MD5Util {

    public static String md5(String data) {
        String md5Result = null;
        if (null == data) {
            return md5Result;
        }

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes("UTF-8"));
            byte b[] = md.digest();
            int i;
            StringBuffer sb = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            md5Result = sb.toString();
        } catch (Exception e) {
        }
        return md5Result;
    }

}