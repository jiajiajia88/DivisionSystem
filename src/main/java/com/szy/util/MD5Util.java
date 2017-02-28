package com.szy.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by shizhouyong on 2017/1/24.
 */
public class MD5Util {

    public static String md5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] byteDigest = MessageDigest.getInstance("MD5").digest(str.getBytes("utf-8"));
        int i;
        StringBuilder buf = new StringBuilder("");
        for (byte aByteDigest : byteDigest) {
            i = aByteDigest;
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
        // return Base64.getEncoder().encodeToString(MessageDigest.getInstance("MD5").digest(str.getBytes("utf-8")));
    }
}