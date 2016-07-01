package com.lanen.test;

import java.security.Provider;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 提供加密算法，可以对输入的字符串进行加密、解密操作
 */
public class EncryptData {
    private static byte[] encryptKey;

    private static DESedeKeySpec spec;

    private static SecretKeyFactory keyFactory;

    private static SecretKey theKey;

    private static Cipher cipher;

    private static IvParameterSpec IvParameters;

    static {
        try {
            // 检测是否有 TripleDES 加密的供应程序
            // 如无，明确地安装SunJCE 供应程序
            try {
                Cipher c = Cipher.getInstance("DESede");
            } catch (Exception e) {
                System.err.println("Installling SunJCE provider.");
                Provider sunjce = new com.sun.crypto.provider.SunJCE();
                Security.addProvider(sunjce);
            }
            // 创建一个密钥
            encryptKey = "012345678901234567890123".getBytes();

            // 为上一密钥创建一个指定的 DESSede key
            spec = new DESedeKeySpec(encryptKey);

            // 得到 DESSede keys
            keyFactory = SecretKeyFactory.getInstance("DESede");

            // 生成一个 DESede 密钥对象
            theKey = keyFactory.generateSecret(spec);

            // 创建一个 DESede 密码
            cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");

            // 为 CBC 模式创建一个用于初始化的 vector 对象
            IvParameters = new IvParameterSpec(new byte[] { 12, 34, 56, 78, 90,
                    87, 65, 43 });
        } catch (Exception exc) {
            exc.printStackTrace();
            // 记录加密或解密操作错误
        }
    }

    /**
     * 加密算法
     *
     * @param password 等待加密的密码
     * @return 加密以后的密码
     * @throws Exception
     */
    public static String encrypt(String password) {
        String encrypted_password = null;
        byte[] encrypted_pwd = null;

        try {
            // 以加密模式初始化密钥
            cipher.init(Cipher.ENCRYPT_MODE, theKey, IvParameters);

            // 加密前的密码（旧）
            byte[] plainttext = password.getBytes();

            // 加密密码
            encrypted_pwd = cipher.doFinal(plainttext);

            // 转成字符串，得到加密后的密码（新）
            encrypted_password = new String(encrypted_pwd);
        } catch (Exception ex) {
            ex.printStackTrace();
            // 记录加密错误
        }
        return encrypted_password;
    }

    /**
     * 解密算法
     *
     * @param password  加过密的密码
     * @return 解密后的密码
     */
    public static String decrypt(String epassword) {
        byte[] password=epassword.getBytes();
        String decrypted_password = null;
        try {
            // 以解密模式初始化密钥
            cipher.init(Cipher.DECRYPT_MODE, theKey, IvParameters);

            // 构造解密前的密码
            byte[] decryptedPassword = password;

            // 解密密码
            byte[] decrypted_pwd = cipher.doFinal(decryptedPassword);
            // 得到结果
            decrypted_password = new String(decrypted_pwd);
        } catch (Exception ex) {
            ex.printStackTrace();
            // 记录解密错误
        }
        return decrypted_password;
    }

    public static void main(String args[]) {
        String str = "123456789";
        String e=EncryptData.encrypt(str);
        System.out.println(e);
        System.out.println(EncryptData.decrypt(e));

    }
}