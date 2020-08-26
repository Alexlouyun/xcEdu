package com.xuecheng.test.fastdfs;

import org.csource.fastdfs.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Administrator
 * @version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class FastDFSTest {
      //上传文件
      @Test
     public void testUpload(){


          try {
              //加载fastdfs-client.propertites配置文件
              ClientGlobal.initByProperties("/config/fastdfs-client.properties");
              //定义trackerClient，用于请求TrackerServer
              TrackerClient trackerClient = new TrackerClient();
              //连接Tracker
              TrackerServer trackerServer = trackerClient.getConnection();
              //获取storage
              StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
              //创建storageCilent
              StorageClient1 storageClient1 = new StorageClient1(trackerServer,storageServer);
              //向storestorage服务器上传文件
              //本地文件的路径
              String filepath = "c:/login-logo.png";
              //上传文件成功后拿到文件的id
              String fileId = storageClient1.upload_file1(filepath, "png", null);
              System.out.println(fileId);
          } catch (Exception e) {
            e.printStackTrace();
          }
      }

      //下载文件
      @Test
    public void testDownload(){

          //加载fastdfs-client.propertites配置文件
          try {
              ClientGlobal.initByProperties("/config/fastdfs-client.properties");
              //定义trackerClient，用于请求TrackerServer
              TrackerClient trackerClient = new TrackerClient();
              //连接Tracker
              TrackerServer trackerServer = trackerClient.getConnection();
              //获取storage
              StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
              //创建storageCilent
              StorageClient1 storageClient1 = new StorageClient1(trackerServer,storageServer);
              //文件下载
              //文件id
              String fileId = "group1/M00/01/22";
              //根据文件id下载得到文件的字节数组
              byte[] bytes = storageClient1.download_file1(fileId);
              //使用文件输出流保存文件到C盘
              FileOutputStream fileOutputStream = new FileOutputStream(new File("c:/fastDFSDownload/login-logo.png"));
              fileOutputStream.write(bytes);

          } catch (Exception e) {
              e.printStackTrace();
          }

      }


}
