package com.fx.fastdfs;

import com.fx.utils.FastDFSClient;
import org.csource.fastdfs.*;
import org.junit.Test;

public class TestFastDFS {

    @Test
    public void uploadFile() throws Exception{
        ClientGlobal.init("/Users/fx/IdeaProjects/FXMall/FXMall-manager-web/src/main/resources/resource/client.conf");
        TrackerClient trackerClient=new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer=null;
        StorageClient storageClient=new StorageClient(trackerServer,storageServer);
        String[] strings = storageClient.upload_file("/Users/fx/Downloads/DestCropImage.png", "png", null);
        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    public void testFastDFSClient() throws Exception{
        FastDFSClient fastDFSClient=new FastDFSClient("/Users/fx/IdeaProjects/FXMall/FXMall-manager-web/src/main/resources/resource/client.conf");
        String string = fastDFSClient.uploadFile("/Users/fx/Downloads/Snip20170827_1.png");
        System.out.println(string);
    }
}
