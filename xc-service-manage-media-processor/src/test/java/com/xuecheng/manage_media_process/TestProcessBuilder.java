package com.xuecheng.manage_media_process;

import com.xuecheng.framework.utils.Mp4VideoUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-07-12 9:11
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestProcessBuilder {

    @Test
    public void testProcessBuilder() throws IOException {

        //创建ProcessBuilder对象
        ProcessBuilder processBuilder =new ProcessBuilder();
        //设置执行的第三方程序(命令)
//        processBuilder.command("ping","127.0.0.1");
        processBuilder.command("ipconfig");
//        processBuilder.command("java","-jar","f:/xc-service-manage-course.jar");
        //将标准输入流和错误输入流合并，通过标准输入流读取信息就可以拿到第三方程序输出的错误信息、正常信息
        processBuilder.redirectErrorStream(true);

        //启动一个进程
        Process process = processBuilder.start();
        //由于前边将错误和正常信息合并在输入流，只读取输入流
        InputStream inputStream = process.getInputStream();
        //将字节流转成字符流
        InputStreamReader reader = new InputStreamReader(inputStream,"utf-8");
       //字符缓冲区
        char[] chars = new char[1024];
        int len = -1;
        while((len = reader.read(chars))!=-1){
            String string = new String(chars,0,len);
            System.out.println(string);
        }

        inputStream.close();
        reader.close();

    }
    @Test
    public void testFFmpeg() {
        //创建ProceeBuilder对象
        ProcessBuilder processBuilder = new ProcessBuilder();
        //定义命令内容
        List<String> commands = new ArrayList<>();
        commands.add("C:\\Program Files\\ffmpeg-20180227-fa0c9d6-win64-static\\bin\\ffmpeg.exe");
        commands.add("‐i");
        commands.add("C:\\lucene.avi");
        commands.add("‐y");//覆盖输出文件
        commands.add("‐c:v");
        commands.add("libx264");
        commands.add("‐s");
        commands.add("1280x720");
        commands.add("‐pix_fmt");
        commands.add("yuv420p");
        commands.add("‐b:a");
        commands.add("63k");
        commands.add("‐b:v");
        commands.add("753k");
        commands.add("‐r");
        commands.add("18");
        commands.add("C:\\lucene.mp4");
        processBuilder.command(commands);
        //讲标准输入流和错误输入流合并，通过标准输入流读取信息
        processBuilder.redirectErrorStream(true);
        try {
            //开启进程
            Process start = processBuilder.start();
            //获取输入流
            InputStream inputStream = start.getInputStream();
            //将字节输入流转换成字符流
            InputStreamReader reader = new InputStreamReader(inputStream,"utf-8");
            char[] chars = new char[1024];
            int len = -1;
            while ((len = reader.read(chars))!=-1){
                String s = new String(chars,0,len);
                System.out.println(s);
            }
            inputStream.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试使用工具类将avi转成mp4
    @Test
    public void testProcessMp4(){
        //String ffmpeg_path, String video_path, String mp4_name, String mp4folder_path
        //ffmpeg的路径
        String ffmpeg_path = "C:\\Program Files\\ffmpeg-20180227-fa0c9d6-win64-static\\binffmpeg.exe";
        //video_path视频地址
        String video_path = "C:\\lucene.avi";
        //mp4_name mp4文件名称
        String mp4_name  ="lucene.mp4";
        //mp4folder_path mp4文件目录路径
        String mp4folder_path="C:\\";
        Mp4VideoUtil mp4VideoUtil = new Mp4VideoUtil(ffmpeg_path,video_path,mp4_name,mp4folder_path);
        //开始编码,如果成功返回success，否则返回输出的日志
        String result = mp4VideoUtil.generateMp4();
        System.out.println(result);
    }

}
