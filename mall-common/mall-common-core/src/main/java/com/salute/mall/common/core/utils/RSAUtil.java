package com.salute.mall.common.core.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Encoder;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RSAUtil {

    /**
     * 类型
     */
    public static final String ENCRYPT_TYPE = "RSA";

    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";


    private static final String PUBLIC_KEY_VALUE = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDNn1BTAHhhREL3kHeJwYeeezRrFkxX2e6SxrA7\n" +
            "jFofRyYtlF5kCqeAc1H7BHeHNQ3ZQgq/Qw8BhyFQD7RVGD6orgJc21vkVYX9mTTxkNWyna5SYnKl\n" +
            "DGqRSAVPxqOQno6xb8OwbQ3C5H4uhLdYbay5Dd86V3klw31fVgcWqThRbwIDAQAB";


    private static final String PRIVATE_KEY_VALUE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAM2fUFMAeGFEQveQd4nBh557NGsW\n" +
            "TFfZ7pLGsDuMWh9HJi2UXmQKp4BzUfsEd4c1DdlCCr9DDwGHIVAPtFUYPqiuAlzbW+RVhf2ZNPGQ\n" +
            "1bKdrlJicqUMapFIBU/Go5CejrFvw7BtDcLkfi6Et1htrLkN3zpXeSXDfV9WBxapOFFvAgMBAAEC\n" +
            "gYBDpP2P0L/Jf+Nn/ibVMs2i3XVJJXTIEfHiZ2rirDGZyJZOuFi1IKwqHcYChl2sJVABATD36t+s\n" +
            "6P+UjxYMSIWT8THPnR/cBY6J8WTYoYQzc3/hsstGjvo5smgLqQNwb0Y2NlTAU2tnPJOOxniFZDr5\n" +
            "NCzxJaEU9zxv5azUKvFeAQJBAPN04c52JoRdRNMfbEtHZcYq6cWPvGaH/J8NIYfYIYoTCtSdXkMM\n" +
            "VNCdztmOKg6H+AjGwcbDOyOjRrL/5BiHR+ECQQDYN2jTJjoAJim38eAB3XakLcKh3J+SyFryF3Se\n" +
            "75uxhMy9BrR6OnzlfODtSGMhTN7baqtzJFwicpEPWz4P0YNPAkEAliBEQklvVP4iu8HLyNv9MWcz\n" +
            "AsWw2tUOfZHeVGN9wyQc8KEeCGBpSL+XV2aoHo/P7rGJuqSXKlG7/hN3t3xsYQJBAKb+PLcASkG9\n" +
            "N/F7HbEzFCP/t9h95r1eg2eBmC/9DfoQgHTqTdAthjNUvQBnLTEWBvXesCbRCVwNsl3y8b8T6V8C\n" +
            "QQCP6kE7DtcXYq62wJn4mbVSTQjVMtJXWzblRrJ6bRQbtMN/gbqBMmdudL+IyaJhoH+ky/OOnjcD\n" +
            "wPEdQGq3VCuo";

    /**
     * @Description 公钥加密
     * @author liuhu
     * @param content
     * @param publicKey
     * @date 2022/6/2 15:50
     * @return java.lang.String
     */
    public static String encrypt(String content, PublicKey publicKey) {
        try{
            RSA rsa = new RSA(null,publicKey);
            return rsa.encryptBase64(content, KeyType.PublicKey);
        }catch (Exception e){
            log.error("RSA加密异常,content:{}",content,e);
        }
        return null;
    }

   /**
    * @Description 公钥加密
    * @author liuhu
    * @param content
    * @param publicKey
    * @date 2022/6/2 15:50
    * @return java.lang.String
    */
    public static String encrypt(String content, String publicKey) {
        try{
            RSA rsa = new RSA(null,publicKey);
            return rsa.encryptBase64(content, KeyType.PublicKey);
        }catch (Exception e){
            log.error("RSA加密异常,content:{}",content,e);
        }
        return null;
    }

    /**
     * @Description 公钥加密
     * @author liuhu
     * @param content
     * @date 2022/6/2 15:50
     * @return java.lang.String
     */
    public static String encrypt(String content) {
        try{
            RSA rsa = new RSA(null,PUBLIC_KEY_VALUE);
            return rsa.encryptBase64(content, KeyType.PublicKey);
        }catch (Exception e){
            log.error("RSA加密异常,content:{}",content,e);
        }
        return null;
    }


    /**
     * @Description 私钥解密
     * @author liuhu
     * @param content
     * @param privateKey
     * @date 2022/6/2 15:50
     * @return java.lang.String
     */
    public static String decrypt(String content, PrivateKey privateKey) {
        try {
            RSA rsa = new RSA(privateKey,null);
            return rsa.decryptStr(content, KeyType.PrivateKey);
        } catch (Exception e){
            log.error("RSA解密异常,content:{}",content,e);
        }
        return null;
    }

    /**
     * @Description 私钥解密
     * @author liuhu
     * @param content
     * @param privateKey
     * @date 2022/6/2 15:50
     * @return java.lang.String
     */
    public static String decrypt(String content, String privateKey) {
        try {
            RSA rsa = new RSA(privateKey,null);
            return rsa.decryptStr(content, KeyType.PrivateKey);
        } catch (Exception e){
            log.error("RSA解密异常,content:{}",content,e);
        }
        return null;
    }

    /**
     * @Description 私钥解密
     * @author liuhu
     * @param content
     * @date 2022/6/2 15:50
     * @return java.lang.String
     */
    public static String decrypt(String content) {
        try {
            RSA rsa = new RSA(PRIVATE_KEY_VALUE,null);
            return rsa.decryptStr(content, KeyType.PrivateKey);
        } catch (Exception e){
            log.error("RSA解密异常,content:{}",content,e);
        }
        return null;
    }



    /**
     * @Description 获取 公钥私钥  获取一次就可以了
     * @author liuhu
     * @date 2022/6/2 15:49
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    public static Map<String,String> generateKeyPair() {
        try {
            KeyPair pair = SecureUtil.generateKeyPair(ENCRYPT_TYPE);
            PrivateKey privateKey = pair.getPrivate();
            PublicKey publicKey = pair.getPublic();
            // 获取 公钥和私钥 的 编码格式（通过该 编码格式 可以反过来 生成公钥和私钥对象）
            byte[] pubEncBytes = publicKey.getEncoded();
            byte[] priEncBytes = privateKey.getEncoded();

            // 把 公钥和私钥 的 编码格式 转换为 Base64文本 方便保存
            String pubEncBase64 = new BASE64Encoder().encode(pubEncBytes);
            String priEncBase64 = new BASE64Encoder().encode(priEncBytes);
            Map<String, String> map = new HashMap<String, String>(2);
            map.put(PUBLIC_KEY,pubEncBase64);
            map.put(PRIVATE_KEY,priEncBase64);

            return map;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        String content = "helloWorld";
//        String encrypt = RsaUtils.encrypt(content, PUBLIC_KEY_VALUE);
//        String decrypt = RsaUtils.decrypt(encrypt, PRIVATE_KEY_VALUE);
//        System.out.println("原始内容："+content);
//        System.out.println("加密内容："+encrypt);
//        System.out.println("解密内容："+decrypt);


        String content = "helloWorld";
        String encrypt = RSAUtil.encrypt(content);
        String decrypt = RSAUtil.decrypt(encrypt);
        System.out.println("原始内容："+content);
        System.out.println("加密内容："+encrypt);
        System.out.println("解密内容："+decrypt);

    }



  
}