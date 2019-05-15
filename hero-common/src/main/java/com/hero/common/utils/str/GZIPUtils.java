package com.hero.common.utils.str;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPUtils  {
    public static final String GZIP_ENCODE_UTF_8 = "UTF-8";
    public static final String GZIP_ENCODE_ISO_8859_1 = "ISO-8859-1";


    public static byte[] compress(String str, String encoding) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(encoding));
            gzip.close();
        } catch ( Exception e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    public static byte[] compress(String str) throws IOException {
        return compress(str, GZIP_ENCODE_UTF_8);
    }

    public static byte[] uncompress(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    public static String uncompressToString(byte[] bytes, String encoding) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            return out.toString(encoding);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String uncompressToString(byte[] bytes) {
        return uncompressToString(bytes, GZIP_ENCODE_UTF_8);
    }

    public static void main(String[] args) throws IOException {
        String s = "{\"results\":{\"jvm-gc\":{\"fullGcCollectionCount\":2,\"fullGcCollectionTime\":143,\"spanFullGcCollectionCount\":0,\"spanFullGcCollectionTime\":0,\"spanYongGcCollectionCount\":0,\"spanYongGcCollectionTime\":0,\"yongGcCollectionCount\":36,\"yongGcCollectionTime\":583},\"jvm-info\":{\"freeMemory\":\"188m\",\"load\":-1,\"maxMemory\":\"2702m\",\"pid\":\"55172\",\"totalMemory\":\"418m\"},\"jvm-memory\":{\"edenSpaceCommitted\":214433792,\"edenSpaceInit\":50331648,\"edenSpaceMax\":1049624576,\"edenSpaceUsed\":128574400,\"heapMemoryCommitted\":438829056,\"heapMemoryInit\":199229440,\"heapMemoryMax\":2833776640,\"heapMemoryUsed\":241628288,\"nonHeapMemoryCommitted\":61276160,\"nonHeapMemoryInit\":2555904,\"nonHeapMemoryMax\":-1,\"nonHeapMemoryUsed\":59025272,\"oldGenCommitted\":219676672,\"oldGenInit\":133169152,\"oldGenMax\":2125463552,\"oldGenUsed\":108660704,\"permGenCommitted\":0,\"permGenInit\":0,\"permGenMax\":0,\"permGenUsed\":0,\"survivorCommitted\":4718592,\"survivorInit\":7864320,\"survivorMax\":4718592,\"survivorUsed\":4393184},\"jvm-thread\":{\"daemonThreadCount\":28,\"deadLockedThreadCount\":0,\"threadCount\":48,\"totalStartedThreadCount\":111},\"jvm-thread-pool\":{\"ack-timer\":{\"activeCount(workingThread)\":0,\"corePoolSize\":1,\"maxPoolSize\":2147483647,\"poolSize(workThread)\":0,\"queueSize(blockedTask)\":0},\"conn-worker\":{\"activeCount(workingThread)\":8,\"poolSize(workThread)\":8,\"queueSize(blockedTask)\":0},\"event-bus\":{\"activeCount(workingThread)\":0,\"corePoolSize\":1,\"maxPoolSize\":16,\"poolSize(workThread)\":1,\"queueSize(blockedTask)\":0},\"mq\":{\"activeCount(workingThread)\":0,\"corePoolSize\":1,\"maxPoolSize\":4,\"poolSize(workThread)\":0,\"queueSize(blockedTask)\":0}}},\"timestamp\":1555405634113}";
        System.out.println("字符串长度："+s.length());
        System.out.println("压缩后：："+compress(s).length + " ---->" + compress(s));
        System.out.println("解压后："+uncompress(compress(s)).length + "--->" + uncompress(compress(s)));
        System.out.println("解压字符串后：："+uncompressToString(compress(s)).length() + "----> "  + uncompressToString(compress(s)));
    }
}