package com.ash.retrofit.util;

import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;

import javax.crypto.Cipher;


public class CipherMethod {
    private static final String TAG = "CipherMethod";
    static final int KEY_SIZE = 2048;

    public static void main0000() {
        HashMap<String, String> rsaKeyPair = createKeypairAsString();
        String publicKey = rsaKeyPair.get("publicKey");
        String privateKey = rsaKeyPair.get("privateKey");

//        Log.d(TAG, "main0000: " + "만들어진 공개키:" + publicKey);
//        Log.d(TAG, "main0000: " + "만들어진 개인키:" + privateKey);

        String plainText = "플레인 텍스트";
//        Log.d(TAG, "main0000: " + "평문: " + plainText);

        String encryptedText = encode(plainText, publicKey);
//        Log.d(TAG, "main0000: " + "암호화: " + encryptedText);

        String decryptedText = decode(encryptedText, privateKey);
//        Log.d(TAG, "main0000: " + "복호화: " + decryptedText);
    }

    /**
     * 키페어 생성
     */
    static HashMap<String, String> createKeypairAsString() {
        HashMap<String, String> stringKeypair = new HashMap<>();
        try {
            SecureRandom secureRandom = new SecureRandom();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(KEY_SIZE, secureRandom);
            KeyPair keyPair = keyPairGenerator.genKeyPair();

            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

//            String stringPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
//            String stringPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());
            String stringPublicKey = Base64.encodeToString(publicKey.getEncoded(), Base64.DEFAULT);
            String stringPrivateKey = Base64.encodeToString(privateKey.getEncoded(), Base64.DEFAULT);

            stringKeypair.put("publicKey", stringPublicKey);
            stringKeypair.put("privateKey", stringPrivateKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringKeypair;
    }

    /**
     * 암호화
     */
    public static String encode(String plainData, String stringPublicKey) {
        String encryptedData = null;
        try {
            //평문으로 전달받은 공개키를 공개키객체로 만드는 과정
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            byte[] bytePublicKey = Base64.getDecoder().decode(stringPublicKey.getBytes());
            byte[] bytePublicKey = Base64.decode(stringPublicKey, Base64.DEFAULT);
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bytePublicKey);
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

            //만들어진 공개키객체를 기반으로 암호화모드로 설정하는 과정
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            //평문을 암호화하는 과정
            byte[] byteEncryptedData = cipher.doFinal(plainData.getBytes("UTF-8"));
//            encryptedData = Base64.getEncoder().encodeToString(byteEncryptedData);
            encryptedData = Base64.encodeToString(byteEncryptedData, Base64.NO_WRAP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedData;
    }

    /**
     * 복호화
     */
    public static String decode(String encryptedData, String stringPrivateKey) {
        String decryptedData = null;
        try {
            //평문으로 전달받은 개인키를 개인키객체로 만드는 과정
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            byte[] bytePrivateKey = Base64.getDecoder().decode(stringPrivateKey.getBytes());
            byte[] bytePrivateKey = Base64.decode(stringPrivateKey, Base64.DEFAULT);
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bytePrivateKey);
            PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

            //만들어진 개인키객체를 기반으로 암호화모드로 설정하는 과정
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            //암호문을 평문화하는 과정
//            byte[] byteEncryptedData = Base64.getDecoder().decode(encryptedData.getBytes());
            byte[] byteEncryptedData = Base64.decode(encryptedData, Base64.DEFAULT);
            byte[] byteDecryptedData = cipher.doFinal(byteEncryptedData);
            decryptedData = new String(byteDecryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedData;
    }

    public static String sslGetPublicKey(String df) throws Exception {
        X509Certificate cert = null;
        ByteArrayInputStream inStream = null;
        //String df="MIID/TCCAuWgAwIBAgIBATANBgkqhkiG9w0BAQUFADBjMRQwEgYDVQQDEwtleGFtcGxlLm9yZzELMAk...";

        try {
            //String aaa = Base64Util.decode(df);
            byte[] data = df.getBytes();
            inStream = new ByteArrayInputStream(data);

            // fis = new FileInputStream(new File("C:/signCert.der"));
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X509");
            cert = (X509Certificate) certificateFactory.generateCertificate(inStream);
        } finally {
            if (inStream != null) try {inStream.close();} catch(IOException ie) {}
        }
//        Log.d(TAG, "cert : " + cert);//SHA1withRSA
//        Log.d(TAG, "--------------------------------------");
//        Log.d(TAG, "cert.getPublicKey : " + cert.getPublicKey());
        String stringPublicKey = Base64.encodeToString(cert.getPublicKey().getEncoded(), Base64.DEFAULT);

        return stringPublicKey;
    }

    private static void verifyCerts(Certificate[] certs) throws Exception {
        int n = certs.length;
        for (int i = 0; i < n - 1; i++) {
            X509Certificate cert = (X509Certificate)certs[i];
            X509Certificate issuer = (X509Certificate)certs[i + 1];
            if (cert.getIssuerX500Principal().equals(issuer.getSubjectX500Principal()) == false) {
                throw new Exception("Certificates do not chain");
            }
            cert.verify(issuer.getPublicKey());
            System.out.println("Verified: " + cert.getSubjectX500Principal());
        }
        X509Certificate last = (X509Certificate)certs[n - 1];
        // if self-signed, verify the final cert
        if (last.getIssuerX500Principal().equals(last.getSubjectX500Principal())) {
            last.verify(last.getPublicKey());
            System.out.println("Verified: " + last.getSubjectX500Principal());
        }
    }

}
