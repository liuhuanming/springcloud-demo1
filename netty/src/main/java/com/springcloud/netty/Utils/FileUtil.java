package com.springcloud.netty.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @description:
 * @author: Think
 * @date: 2020-05-17 22:07
 */
public class FileUtil {
    public static String UPLOAD_PATH = "G:\\upload";


    public static String save(FileInputStream fileInputStream, String fileName) {
        FileOutputStream fos = null;
        //创建通道
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        //要返回的路径
        String realPath = null, filePath = null;
        touch(UPLOAD_PATH);

        filePath = DateUtil.getCurrentDateStr() + "/" + fileName;
        realPath = UPLOAD_PATH + filePath;

        try {
            inChannel = fileInputStream.getChannel();
            outChannel = fileInputStream.getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
            inChannel.close();
            outChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return realPath;
    }

    /**
     * 如果路径不存在就新建
     *
     * @param path
     */
    public static void touch(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
