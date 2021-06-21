package com.ash.retrofit;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/* NAME : AES256 암호화
 *  FUNC : AES256 암호화를 수행한다.
 *  DATE : 2020-04-14
 *  AUTHOR : 안재현
 * */
public class SecurityUtils {
    /* NAME : AES256 암호화
     *  FUNC : AES256 암호화를 수행한다.
     *  DATE : 2020-04-14
     *  AUTHOR : 안재현
     * */

    public static String getEncryptKey() {
        return encryptKey;
    }

    public void setEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
    }

    public static byte[] ivBytes = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    /*  NAME : AES_Eecode
     *  FUNC : AES256 암호화 공통
     *  DATE : 2020-04-21
     *  AUTHOR : 안재현
     * */
    public static String AES_Encode(String str,String v) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        byte[] textBytes = str.getBytes("UTF-8");
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(v.getBytes("UTF-8"), "AES");
        Cipher cipher = null;
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);

        return Base64.encodeToString(cipher.doFinal(textBytes), 0);
    }

    /*  NAME : AES_Decode
     *  FUNC : AES256 복호화 공통
     *  DATE : 2020-04-21
     *  AUTHOR : 안재현
     * */
    public static String AES_Decode(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        byte[] textBytes = Base64.decode(str, 0);
        //byte[] textBytes = str.getBytes("UTF-8");
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
        return new String(cipher.doFinal(textBytes), "UTF-8");
    }

    /*  NAME : Base64_encode
     *  FUNC : BASE64 인코딩용
     *  DATE : 2020-04-21
     *  AUTHOR : 안재현
     * */
    public static String Base64_encode(byte[] digest) throws UnsupportedEncodingException {
        return Base64.encodeToString(digest, Base64.DEFAULT);
    }

    /*  NAME : Base64_decode
     *  FUNC : BASE64 디코딩용
     *  DATE : 2020-04-21
     *  AUTHOR : 안재현
     * */
    public static String Base64_decode(String text) throws UnsupportedEncodingException {
        return new String(Base64.decode(text, Base64.NO_WRAP));
    }

    /*  NAME : getURLDecode
     *  FUNC : URL 문자열 암호화 중 깨질 떄 사용
     *  DATE : 2020-04-21
     *  AUTHOR : 안재현
     * */
    public static String getURLEncode(String content) {
        try {
            return URLEncoder.encode(content, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*  NAME : getURLDecode
     *  FUNC : URL 복호화시 깨질 때 사용
     *  DATE : 2020-04-21
     *  AUTHOR : 안재현
     * */
    public static String getURLDecode(String content) {
        try {
            return URLDecoder.decode(content, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String AES_Decodes(String str, String decKey) {
        String rnv = "";
        try {
            byte[] textBytes = Base64.decode(str, 0);
            //byte[] textBytes = str.getBytes("UTF-8");
            AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            SecretKeySpec newKey = new SecretKeySpec(decKey.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
            return new String(cipher.doFinal(textBytes), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
