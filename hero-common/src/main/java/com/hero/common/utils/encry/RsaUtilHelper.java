package com.hero.common.utils.encry;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RsaUtilHelper {

    private static String daily_publicKey = null;
    private static String daily_privateKey = null;
    private static RSAPrivateKey dailyPrivateKey = null;
    private static RSAPublicKey dailyPublicKey = null;

    private static String readPem(String fileName) throws Exception {
        //读取pem证书
        InputStream resourceAsStream = RsaUtilHelper.class.getClassLoader().getResourceAsStream(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream));
        String s = br.readLine();
        StringBuilder publickey = new StringBuilder();
        while (s != null && s.charAt(0) != '-') {
            publickey.append(s);
            s = br.readLine();
        }
        return publickey.toString();
    }

    static {
        try {
            dailyPrivateKey = (RSAPrivateKey) HeroRsaUtil.decodePrivateKey(daily_privateKey =readPem("rsa_private_key.pem"));
            dailyPublicKey = (RSAPublicKey) HeroRsaUtil.decodePublicKey(daily_publicKey = readPem("rsa_public_key.pem"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encode(String str) {
        byte[] temp = str.getBytes();
        byte[] encode = HeroRsaUtil.encryptByPublicKey(temp, dailyPublicKey);
        return Base64.encode(encode);
    }


    public static String encodePrivate(String str) {
        byte[] temp = str.getBytes();
        byte[] encode = HeroRsaUtil.encryptByPrivateKey(temp, dailyPrivateKey);
        return Base64.encode(encode);
    }

    /**
     * 使用私钥对数据进行加密签名
     *
     * @param str 数据
     * @return 加密后的签名
     */
    public static String sign(String str) {
        try {
            java.security.Signature signet = java.security.Signature.getInstance("MD5withRSA");
            signet.initSign(dailyPrivateKey);
            signet.update(str.getBytes(Charset.forName("UTF-8")));
            byte[] signed = signet.sign();
            return Base64.encode(signed);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String decode(String bytes) {
        try {
            byte[] temp = HeroRsaUtil.decryptByPrivateKey(Base64.decode(bytes.replaceAll(" ", "+")), dailyPrivateKey);
            return new String(temp, Charset.forName("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decodePublic(String bytes) {
        try {
            byte[] temp = HeroRsaUtil.decryptByPublicKey(Base64.decode(bytes.replaceAll(" ", "+")), daily_publicKey);
            return new String(temp, Charset.forName("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用公钥判断签名是否与数据匹配
     *
     * @param data 数据
     * @param sign 签名
     * @return 是否篡改了数据
     */
    public static boolean verify(String data, String sign) {
        try {
            java.security.Signature signet = java.security.Signature.getInstance("MD5withRSA");
            signet.initVerify(dailyPublicKey);
            signet.update(data.getBytes(Charset.forName("UTF-8")));
            return signet.verify(Base64.decode(sign));
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {

        String psd = "18098973109";
        String encode = encode(psd);
        System.out.println("公钥加密的数据为:" + encode);

       /* String ip = "Aq+ZaHpEEEY/YwDsVoh4soNQ9JTBPyGFrXWaX9+gukJHdF8fN21+NW+nemdh0mxA0RLUVaXny6O0G3oE5JNBKRlk36JW2KlERG09oOYAkDgB8+1FcAJaSBGp9LVgNgD28REHGvP0Ldfi+35V1uAhvZKMpEywG2r6H9yNDBXeUfc=";
        String decode = decodePublic(ip);
        System.out.println("公钥解密公钥的数据为:" + decode);


        String psd = "123456";
        String encode = encode(psd);
        System.out.println("公钥加密的数据为:" + encode);*/
       /* String ip = "Aq+ZaHpEEEY/YwDsVoh4soNQ9JTBPyGFrXWaX9+gukJHdF8fN21+NW+nemdh0mxA0RLUVaXny6O0G3oE5JNBKRlk36JW2KlERG09oOYAkDgB8+1FcAJaSBGp9LVgNgD28REHGvP0Ldfi+35V1uAhvZKMpEywG2r6H9yNDBXeUfc=";
        String decode = decode(ip);
        System.out.println("私钥解密公钥的数据为:" + decode);

        String encodePrivate = encodePrivate(psd);
        System.out.println("私钥加密的数据为:" + encodePrivate);
        String decodePrivate = decodePublic(encodePrivate);
        System.out.println("公钥解密公钥的数据为:" + decodePrivate);



        System.out.println(decode("h7OJJCTAJ2H2nJFlDTIQlYajLlkkkPh5t/5nOCabNtl4dvUTe0kpTuAgAHw5Gzn5GhseH0wbWN9vSkEAmDeBXKLqWYJvdi+de/PMtHmy54pGfWqePtdAQYHj/a6J/sZ1H/TyDtJuDwbRuTvY/YhQkULpfHvsnXCeVRsDWBhpAt4="));

        String s = sign(psd);//私钥加密
        System.out.println("针对数据[" + psd + "]签名: " + s);
        System.out.println("签名验证结果:" + verify(psd, s));*/


    }

}
