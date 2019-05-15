package com.hero.common.utils.encry;


import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.Console;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

/**
 * 类说明 ：
 *
 * @ClassName encryptionUtils
 * @Author hwz.hs
 * @Date 2018/9/25 14:42
 * @Version v_1.0
 */
public class EncryptionUtils {


    private static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";
    private static  final String ENCRY_KEY = "acoin666";

    /**
     * DES算法，加密
     *
     * @param data 待加密字符串
     * @return 加密后的字节数组，一般结合Base64编码使用
     */
    public static String encode(String data)
    {
        try {
            return encode(ENCRY_KEY, data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * DES算法，加密
     *
     * @param data 待加密字符串
     * @return 加密后的字节数组，一般结合Base64编码使用
     */
    public static String encode(String data,String encry_key)
    {
        try {
            return encode(encry_key, data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * DES算法，加密
     *
     * @param data 待加密字符串
     * @param key 加密私钥，长度不能够小于8位
     * @return 加密后的字节数组，一般结合Base64编码使用
     */
    private static String encode(String key,byte[] data) throws Exception
    {
        try
        {
            DESKeySpec dks = new DESKeySpec(key.getBytes());

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            //key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.ENCRYPT_MODE, secretKey,paramSpec);

            byte[] bytes = cipher.doFinal(data);

            return new String(Base64.encode(bytes));
        } catch (Exception e)
        {
            throw new Exception(e);
        }
    }

    /**
     * DES算法，解密
     *
     * @param data 待解密字符串
     * @param key 解密私钥，长度不能够小于8位
     * @return 解密后的字节数组
     * @throws Exception 异常
     */
    private static byte[] decode(String key,byte[] data) throws Exception
    {
        try
        {
            SecureRandom sr = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            //key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.DECRYPT_MODE, secretKey,paramSpec);
            return cipher.doFinal(data);
        } catch (Exception e)
        {
            throw new Exception(e);
        }
    }

    /**
     * 获取编码后的值
     * @param data
     * @return
     * @throws Exception
     */
    public static String decodeValue(String data)
    {
        byte[] datas;
        String value = null;
        try {

            datas = decode(ENCRY_KEY, Base64.decode(data));

            value = new String(datas);
        } catch (Exception e) {
            value = "";
        }
        return value;
    }

    /**
     * 获取编码后的值
     * @param data
     * @return
     * @throws Exception
     */
    public static String decodeValue(String data,String encry_key)
    {
        byte[] datas;
        String value = null;
        try {

            datas = decode(encry_key, Base64.decode(data));

            value = new String(datas);
        } catch (Exception e) {
            value = "";
        }
        return value;
    }


    public static void main1(String[] args) {
       /* String pwd = null;
        String depwd = null;
        String entryPwd = null;*/
        int n = 1;
        while(true){
            try {
                Console cons = System.console();
                if(cons != null){
                    System.out.println();
                    // 输入地址私钥，后面的格式参数省略
                    String str = cons.readLine("地址私钥: ");
                    // 输入密码， 后面的格式参数省略
                    char[] pwd = cons.readPassword("解密密码: ");
                    String depwd = EncryptionUtils.decodeValue(str, new String(pwd));
                    if(StringUtils.isEmpty(depwd)){
                        System.out.println("获取地址失败,核对解密密码是否错误.");
                    }else {
                        System.out.println("可用地址：" + depwd);
                    }
                    String ifContiniu = cons.readLine("是否继续y/n: ");
                    if(ifContiniu.equals("n")){
                        System.exit(0);
                    }
                }


                /*Scanner sc = new Scanner(System.in);
                System.out.println("请输入地址私钥...");
                pwd = sc.next();
                System.out.println(pwd);
                System.out.println("请输入解密密钥");
                entryPwd = sc.next
                System.out.println(entryPwd);
                depwd = EncryptionUtils.decodeValue(pwd, entryPwd);
                if(StringUtils.isEmpty(depwd)){
                    System.out.println("输入解密密钥错误!");
                }
                System.out.println("密文：" + pwd + "  解密：" + depwd);
                System.out.println("是否继续:y/n");*/

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        try {
            String pwd = "{\"results\":{\"jvm-gc\":{\"fullGcCollectionCount\":2,\"fullGcCollectionTime\":143,\"spanFullGcCollectionCount\":0,\"spanFullGcCollectionTime\":0,\"spanYongGcCollectionCount\":0,\"spanYongGcCollectionTime\":0,\"yongGcCollectionCount\":36,\"yongGcCollectionTime\":583},\"jvm-info\":{\"freeMemory\":\"188m\",\"load\":-1,\"maxMemory\":\"2702m\",\"pid\":\"55172\",\"totalMemory\":\"418m\"},\"jvm-memory\":{\"edenSpaceCommitted\":214433792,\"edenSpaceInit\":50331648,\"edenSpaceMax\":1049624576,\"edenSpaceUsed\":128574400,\"heapMemoryCommitted\":438829056,\"heapMemoryInit\":199229440,\"heapMemoryMax\":2833776640,\"heapMemoryUsed\":241628288,\"nonHeapMemoryCommitted\":61276160,\"nonHeapMemoryInit\":2555904,\"nonHeapMemoryMax\":-1,\"nonHeapMemoryUsed\":59025272,\"oldGenCommitted\":219676672,\"oldGenInit\":133169152,\"oldGenMax\":2125463552,\"oldGenUsed\":108660704,\"permGenCommitted\":0,\"permGenInit\":0,\"permGenMax\":0,\"permGenUsed\":0,\"survivorCommitted\":4718592,\"survivorInit\":7864320,\"survivorMax\":4718592,\"survivorUsed\":4393184},\"jvm-thread\":{\"daemonThreadCount\":28,\"deadLockedThreadCount\":0,\"threadCount\":48,\"totalStartedThreadCount\":111},\"jvm-thread-pool\":{\"ack-timer\":{\"activeCount(workingThread)\":0,\"corePoolSize\":1,\"maxPoolSize\":2147483647,\"poolSize(workThread)\":0,\"queueSize(blockedTask)\":0},\"conn-worker\":{\"activeCount(workingThread)\":8,\"poolSize(workThread)\":8,\"queueSize(blockedTask)\":0},\"event-bus\":{\"activeCount(workingThread)\":0,\"corePoolSize\":1,\"maxPoolSize\":16,\"poolSize(workThread)\":1,\"queueSize(blockedTask)\":0},\"mq\":{\"activeCount(workingThread)\":0,\"corePoolSize\":1,\"maxPoolSize\":4,\"poolSize(workThread)\":0,\"queueSize(blockedTask)\":0}}},\"timestamp\":1555405634113}";
            String encodePwd = EncryptionUtils.encode(pwd);
            System.out.println(encodePwd);
            System.out.println(EncryptionUtils.decodeValue(encodePwd));


            // 输入地址私钥，后面的格式参数省略
            String str = "mllNY7Oimyo=";
            // 输入密码， 后面的格式参数省略
            String depwd = EncryptionUtils.decodeValue(str);
            System.out.println("公钥地址：1FixYJucR6rqYNsYwEc5e7CgWzYE2raTV7  ---->  私钥地址" + depwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
