package com.beauty.usercenter.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.SecureRandom;

public class SecurityUtil {
    private final static String DES = "DES";

    private static String key1 = "MIICXAIBAAKBgQCt1/tS/3UoEwgIik3Ypd7YiQB38ZY+Nt2Zf9sn43iUzCjagcOG"
            + "COcPuIhebX/TbvX5ZUWicc7S1VeFRvqp5cJiJ1xZBLyat1H2ig8pbqCbqCJIrR"
            + "CYSp1YYu7N1Z4FVnMXjG7iBojB09Zc/mhrQpWxJ259LGFHsKgWd/Jy/QgwIDAQAB"
            + "AoGAXX0FH0FaE88IRu95pcSt7uzeX6KAFb0TtvTXOsBNnFeesMOQDojDGeU3EPD6"
            + "CQ5nIVOg+xcRJwFUW952KgcrWafiUq+ldgQJTGYgpGDqrKKyuZ+LQasWPMAqLsQN"
            + "uy+VKF+Jk4aHwurKiLHy6rblILATX1aErGFIQYnwXmJstWECQQDbrh/C1HLUmELe"
            + "pfdE2TsGm4leMMN+q5JGCN9Rpzy048+sMA029zNyp1PChg72KQnvoIwzJksq5KbT"
            + "DgDbj/Z/AkEAypXYp6WFgG0AOaJsTdawOPa6lN2LkG1b2bPiHMMfE+gxavJLe0jd"
            + "zQlW4S1zbelZjJiI3X7QdsnENqjtHk5L/QJBAIMJVkCs4OIXAKw2a0xSF3dVh5b7"
            + "l2ETjmjgGfVmwDtWOCYJc5h50+JsFPZPaNVEnXymU3qfOO8Y/Lmf/Lr7IJkCQAlz"
            + "He0MAoMwEm+dzPGAjy3JDID/KJBvGXa5NlXjICL8lLmJXc39YyM6SUwto5G/7cZq"
            + "IxA55x1zYeyuyoi8XD0CQBUh3AVlBvHp6jKkZhgOpcWUJFiZ1wBTnYl+ZQmQtbt6"
            + "bm1QjyTDdMyy5iJW+llgc4Fy/gsvTO5TeifDmwbhbuI=";

    private static String key = "adsfgqweqweqw";


    public static void main(String[] args) throws Exception {
        String data = "123456ddsadzfgvfdfgbfdfgtrgfnbfvdxscvfgbfdsafgbhjhngbvfdcsxdfvbg";
//        String key = "abc@1234";
        String ret = encrypt(data);
        System.err.println(ret);
//        System.err.println(decrypt(encrypt(data)));

    }

    /**
     * Description 根据键值进行加密
     * @param data
     * @return
     * @throws Exception
     */
    public static String encrypt(String data) throws Exception {
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
        String strs = new BASE64Encoder().encode(bt);
        return strs;
    }

    /**
     * Description 根据键值进行解密
     * @param data
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data) throws IOException,
            Exception {
        if (data == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        byte[] bt = decrypt(buf,key.getBytes());
        return new String(bt);
    }

    /**
     * Description 根据键值进行加密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }
}
