package com.ash.retrofit.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OnewayHash {
    public static String md5(String str){
        String MD5 = "";
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes("UTF-8"));
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for(int i = 0 ; i < byteData.length ; i++) sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
            MD5 = sb.toString();
        }
        catch(NoSuchAlgorithmException e) { e.printStackTrace(); MD5 = null; }
        catch (UnsupportedEncodingException e) { e.printStackTrace(); MD5 = null; }
        return MD5;
    }

    public static String sha256(String str) {
        String SHA = "";
        try{
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(str.getBytes());
            byte byteData[] = sh.digest();
            StringBuffer sb = new StringBuffer();
            for(int i = 0 ; i < byteData.length ; i++) sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
            SHA = sb.toString();
        }catch(NoSuchAlgorithmException e) { e.printStackTrace(); SHA = null; }
        String base64SHA = Base64.encodeToString(SHA.getBytes(), Base64.NO_WRAP);
        return base64SHA;
    }

    public static String sha2561(String str) {
        String SHA = "";

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            try {
                md.update(str.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e){
            }
            byte byteData1[] = md.digest();
            md.reset();
            md.update(byteData1);
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for(int i = 0 ; i < byteData.length ; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            try {
                byte[] temp =  sb.toString().getBytes("utf-8");
                SHA = Base64.encodeToString(temp, Base64.NO_WRAP);
            } catch (UnsupportedEncodingException e){
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return SHA;
    }
    public static String getSHA256Encrypt(String source, String salt) {
        return getEncrypt(source, salt.getBytes());
    }

    public static String getEncrypt(String source, byte[] salt) {

        String result = "";

        byte[] a = source.getBytes();
        byte[] bytes = new byte[a.length + salt.length];

        System.arraycopy(a, 0, bytes, 0, a.length);
        System.arraycopy(salt, 0, bytes, a.length, salt.length);

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(bytes);

            byte[] byteData = md.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
            }

            result = Base64.encodeToString(sb.toString().getBytes(), Base64.NO_WRAP);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getCurrTime() {
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("HH:mm:ss");
        String getTime = simpleDate.format(mDate);
        return getTime;
    }

}
