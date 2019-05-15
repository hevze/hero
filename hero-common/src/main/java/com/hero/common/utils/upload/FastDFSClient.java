package com.hero.common.utils.upload;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import java.io.IOException;

/**
 * 类说明 ：
 *
 * @ClassName FastDFSClient
 * @Author hwz.hs
 * @Date 2018/9/14 13:38
 * @Version v_1.0
 */

public class FastDFSClient {

    private TrackerServer trackerServer;

    // 配置文件路径

    public FastDFSClient() {
    }

    public FastDFSClient(String configLocation) throws Exception{
        // 加载配置文件
        ClientGlobal.init(configLocation);
        // 创建 tracker客户端
        TrackerClient trackerClient = new TrackerClient();
        // 获取tracker连接
        trackerServer = trackerClient.getConnection();
    }

    /**
     * 获取文件扩展名
     * @param fileName
     * @return
     */
    public static String getFileExtension(String fileName){
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 上传文件
     * @param data
     * @param fileName
     * @return
     * @throws MyException
     * @throws IOException
     */
    public String uploadFile(byte[] data,String fileName) throws IOException, MyException{
        // 查看storage客户端
        StorageClient1 storageClient = new StorageClient1(trackerServer, null);

        // 上传
        return storageClient.upload_file1(data, getFileExtension(fileName), null);
    }
}
