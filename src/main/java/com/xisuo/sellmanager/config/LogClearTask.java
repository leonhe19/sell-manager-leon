package com.xisuo.sellmanager.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志文件的清理
 */
@Component
public class LogClearTask {

    /**
     * 储存文件夹下所有文件路径
     */
    private static List<String> fileList = new ArrayList<>();

    //一个月的事件,毫秒
    private static int TIME = 1000 * 3600 * 24 * 30;


    /**
     * 每个月的1 11 21 号的 凌晨1点 执行这个任务
     */
    //@Scheduled(cron = "0 0 1 1,11,21 * ? ")
    public static void main(String[] args) {
        getPath("");
        delete();
        //清空
        fileList.clear();

    }

    /**
     * 根据路径过去所有的文件对象
     *
     * @param path
     */
    private static void getPath(String path) {
        File dir = new File(path);
        //是文件
        if (dir.isFile()) {
            fileList.add(dir.getAbsolutePath());
        } else {
            //是文件夹
            File[] files = dir.listFiles();
            if (files.length == 0) {
                //删除空的文件夹
                dir.delete();
            } else {
                for (File file : files) {
                    boolean file1 = file.isFile();
                    if (file1) {
                        //添加文件路径
                        fileList.add(file.getAbsolutePath());
                    } else {
                        //循环这个过程
                        getPath(file.getAbsolutePath());
                    }
                }
            }
        }
    }


    /**
     * 根据一个固定的事件,删除文件
     */
    public static void delete() {
        for (String path : fileList) {
            File file = new File(path);
            long modified = file.lastModified();
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - modified > TIME) {
                file.delete();
            }
        }
    }


}