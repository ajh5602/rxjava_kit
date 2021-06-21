package com.ash.retrofit.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Base64Util {
    public static String encode(String str) throws UnsupportedEncodingException {
        byte[] data = str.getBytes("UTF-8");
        return Base64.encodeToString(data, Base64.DEFAULT);
        //Android에서 base64인코딩시 개행문자 삽입하지않는 옵션.
        //return Base64.encodeToString(data, Base64.NO_WRAP);
    }

    public static String encode(byte[] digest) throws UnsupportedEncodingException {
        return Base64.encodeToString(digest, Base64.DEFAULT);
    }

    public static String decode(String str) throws UnsupportedEncodingException {
        return new String(Base64.decode(str, Base64.DEFAULT), "UTF-8");
    }

    public static String decode(byte[] digest) throws UnsupportedEncodingException {
        return new String(Base64.decode(digest, Base64.DEFAULT), "UTF-8");
    }

    public static String getURLEncode(String content) {
        try {
            return URLEncoder.encode(content, "UTF-8"); //UTF-8
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getURLDecode(String content) {
        try {
            return URLDecoder.decode(content, "UTF-8"); //UTF-8
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
