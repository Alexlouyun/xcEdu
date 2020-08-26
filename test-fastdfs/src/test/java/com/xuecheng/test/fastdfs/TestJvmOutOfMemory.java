package com.xuecheng.test.fastdfs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Alexlou on 2020/8/5.
 */
public class TestJvmOutOfMemory {
    //实现，向集合中添加100万个字符串，每个字符串由1000个UUID组成
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i <1000000; i++) {
            String str = "";
            for (int j = 0; j < 1000; j++) {
                str += UUID.randomUUID().toString();
            }
            list.add(str);
        }
        System.out.println("ok");
    }
}
