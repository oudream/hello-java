package app;

import java.io.*;
import java.math.*;
import java.util.*;
import java.text.*;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class HelloRsa
{
    private static String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCQmIxVuzxn2Je5LweL6aZxs7Y9QUBnciFS2cveEH+eKPEdQbp0KaswGAbT127g0ofoCcApyQEiV+cc40dsyD5kDn+fzzejt96rGo00bYR+8t6P57YqaPFfjjt+rVE4nWFADdt5ft6xjFM7OC1BJi3HyBm5ul9n1U8zhdHGyOXmGQIDAQAB";

    public static final String KEY_ALGORITHM = "RSA";
    private static final int MAX_DECRYPT_BLOCK = 128;

    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey)
            throws Exception
    {
        byte[] keyBytes = Base64Util.decode(publicKey.getBytes("UTF-8"));
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;

        int i = 0;
        while (inputLen - offSet > 0)
        {
            byte[] cache;
            if (inputLen - offSet > 128) {
                cache = cipher.doFinal(encryptedData, offSet, 128);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * 128;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    public static void printLn(String msg) {
//        test.InterInfo.OutInfo(msg);
        System.out.println(msg);
    }

    public static void main(String[] Args)
    {
        printLn("begin:");

        ByteArrayOutputStream outSteam = null;
        InputStream input = null;
        FileInputStream fsss = null;
//        final HashMap<String, Object> returnmap = new HashMap<String, Object>();
        //公钥解密
        try {
//            final String rootPath = System.getProperty("user.dir");
            final String rootPath = "/fff/tmp";
            printLn("获取的路径为：" + rootPath+"/license/license.txt");
            fsss = new FileInputStream(rootPath+"/license/license.txt");
            //获取文件的byte数组
            final byte[] bytess = new byte[fsss.available()];
            fsss.read(bytess);
            //base64解密，这里解密后获取的就是私钥加密后的byte
            final byte[] basebb = Base64Util.decode(bytess);
            //公钥解密
            final byte[] pubdata = decryptByPublicKey(basebb, new String(pubKey));
            final String data = new String(pubdata);
            printLn("获取解密后的文件内容为：" + data);
            final String[] datas = data.split("ygct");
            final String ywrq = datas[0];
            final String wjmax = datas[1];
//            if (mac != null && mac.equals(wjmax)) {
//                returnmap.put("check", true);
//                returnmap.put("date", ywrq);
//            } else {
//                returnmap.put("check", false);
//            }
        } catch (Exception e) {
//            returnmap.put("check", false);
        } finally {
            try { if (fsss != null) { fsss.close(); }} catch (IOException e) { printLn("关闭文件流失败，检查是否有另外的程序打开文件。");}
            try { if (outSteam != null) { outSteam.close(); }} catch (IOException e) { printLn("关闭输出流失败，检查是否有另外的程序打开文件。");}
            try { if (input != null) { input.close(); }} catch (IOException e) { printLn("关闭流失败，检查是否有另外的程序打开文件。");}
        }

        printLn("end.");
    }
}