package com.hero.common.utils.upload;

import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 类说明 ：
 *
 * @ClassName TestFastDfs
 * @Author hwz.hs
 * @Date 2018/9/12 15:52
 * @Version v_1.0
 */
public class FastdfsUpload {

        private static Logger log = LoggerFactory.getLogger(FastdfsUpload.class);

        private final static String FASTDFS_CONFIG = "fastdfs.properties";
        private TrackerClient trackerClient = null;
        private TrackerServer trackerServer = null;
        private StorageClient storageClient = null;
        public FastdfsUpload() throws Exception {
            try {
                ClientGlobal.init(FASTDFS_CONFIG);
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception(e.getMessage());
            }
            System.out.println("ClientGlobal.configInfo(): " + ClientGlobal.configInfo());
        }

        /**
         * 避免文件冲突，每次实例化一个StorageClient
         * */
        private StorageClient getStorageClient() throws Exception {
            try {
                if(trackerClient==null) {
                    trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
                }
                if(trackerServer==null) {
                    trackerServer = trackerClient.getConnection();
                }
                StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
                if (storageServer == null) {
                    throw new IllegalStateException("getStoreStorage return null");
                }
                if(storageClient == null) {
                    storageClient = new StorageClient(trackerServer, storageServer);
                }
                return storageClient;
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception(e.getMessage());
            }
        }

        /**
         * 向FASTDFS上传文件
         * */
        synchronized	public String uploadByFastDFS(MultipartFile file) throws Exception{
            try {
                StorageClient storageClient = this.getStorageClient();
                String extName = getFileType(file.getOriginalFilename());
                String r []= storageClient.upload_file(file.getBytes(), extName,null);
                if(r!=null) {
                    return r[0] + "/" + r[1];
                }else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception(e.getMessage());
            } finally {
            }
        }

        /**
         * 重FASTDFS上下载文件，得到byte数组
         * */
        synchronized public byte[] downloadByFastDFS(String fullPath) throws  Exception{
            try {
                StorageClient storageClient = this.getStorageClient();
                PathInfo storePath = praseFromUrl(fullPath);
                byte[] buffer = storageClient.download_file(storePath.getGroupName(), storePath.getPath());
                return buffer;
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception(e.getMessage());
            }
        }
        /**
         * 解析文件路径
         */
        public PathInfo praseFromUrl(String filePath) {
            int pos = filePath.indexOf("group");
            String groupAndPath = filePath.substring(pos);
            pos = groupAndPath.indexOf("/");
            String group = groupAndPath.substring(0, pos);
            String path = groupAndPath.substring(pos + 1);
            PathInfo r = new PathInfo(group, path);
            return r;
        }

        public static String getFileType(String filePath){
            int index = filePath.lastIndexOf(".");
            String res = filePath.substring(index+1);
            return res;
        }

        public static String getFileType(File file){
            return getFileType(file.getAbsolutePath());
        }

        public boolean isFileType(MultipartFile file){
            boolean flag=false;
            String fileType = FastdfsUpload.getFileType(file.getOriginalFilename());
            if("jpg".equals(fileType)||"jpeg".equals(fileType)||"png".equals(fileType)){
                flag=true;
            }else{
                flag=false;
            }
            return flag;
        }

        public Map<String,MultipartFile> getMapIsNull(Map<String,MultipartFile> map){
            Map<String,MultipartFile> remap=new HashMap<String, MultipartFile>();
            for(String item:map.keySet()){
                if(!map.get(item).isEmpty()){
                    remap.put(item, map.get(item));
                }
            }
            return remap;
        }

        /**
         * 封裝fastDfs文件路徑信息
         * */
        class PathInfo{
            private String groupName;
            private String path;
            public PathInfo(String groupName,String path) {
                this.groupName = groupName;
                this.path = path;
            }
            public String getGroupName() {
                return groupName;
            }
            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }
            public String getPath() {
                return path;
            }
            public void setPath(String path) {
                this.path = path;
            }
        }
}
