package com.xuecheng.manage_media;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Alexlou on 2020/7/6.
 */

public class FileTest {
    //测试文件的分块
    @Test
    public void testChunks() throws IOException {
        //源文件
        File sourceFile = new File("C:\\lucene.avi");
        //块文件目录
        String chunckFileFolder = "C:\\chunks\\";
        //先定义块文件大小
        long chunkFileSize = 1*1024*1024;
        //快数
        long chunkFileNum = (long) Math.ceil(sourceFile.length()*1.0/chunkFileSize);
        //创建读文件对象
        RandomAccessFile raf_read = new RandomAccessFile(sourceFile,"r");
        //缓冲区
        byte[] b = new byte[1024];
        for (int i = 0; i <chunkFileNum ; i++) {
            File chunkFile = new File(chunckFileFolder+i);
            //创建向块文件的写对象
            RandomAccessFile raf_write = new RandomAccessFile(chunkFile,"rw");
            int len = -1;
            while ((len = raf_read.read(b))!=-1){
                raf_write.write(b,0,len);
                if (chunkFile.length()>=chunkFileSize){
                    break;
                }
            }
            raf_write.close();
        }
        raf_read.close();

    }

    //测试文件的合并
    @Test
    public void testMergeFile(){
        //块文件路径
        String chunkFilePath = "C:\\chunks\\";
        //块文件目录
        File chunkFileFolder = new File(chunkFilePath);
        //块文件列表
        File[] files = chunkFileFolder.listFiles();

        //合并文件
        File mergeFile = new File("C:\\lucene_merge.avi");
        //
    }
    @Test
    public void testHashCode(){
        String a = "louyun";
        int hashCode = a.hashCode();
        System.out.println(hashCode);

    }
}
