package com.ash.retrofit.util;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class util {
    public static String gen_random(int len)
    {
        String rnd = "";
        char cValue = '0';
        for(int i = 0; i < len; i++) {
            double dValue = Math.random();
            switch((int)(dValue*3)) {
                case 0:
                    cValue = (char)((dValue * 10) + 48);   // 숫자
                    break;
                case 1:
                    cValue = (char)((dValue * 26) + 65);   // 대문자
                    break;
                case 2:
                    cValue = (char)((dValue * 26) + 97);   // 대문자
                    break;
                default:
                    break;
            }
            rnd += Character.toString(cValue);
        }
        return rnd;
    }

    public static String getMACAddress(String interfaceName) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                if (interfaceName != null) {
                    if (!intf.getName().equalsIgnoreCase(interfaceName)) continue;
                }
                byte[] mac = intf.getHardwareAddress();
                if (mac==null) return "";
                StringBuilder buf = new StringBuilder();
                for (int idx=0; idx<mac.length; idx++)
                    buf.append(String.format("%02X:", mac[idx]));
                if (buf.length()>0) buf.deleteCharAt(buf.length()-1);
                return buf.toString();
            }
        } catch (Exception ex) { } // for now eat exceptions
        return "";
    }
    String macAddress = getMACAddress("wlan0");

}
