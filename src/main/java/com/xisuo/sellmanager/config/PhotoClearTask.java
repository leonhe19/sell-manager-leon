package com.xisuo.sellmanager.config;

import com.xisuo.sellmanager.constant.Constant;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zk
 * @Description: 无效用户图像和无效的产品图片的清理
 * 因为去掉了用户图像和产品图像,不再进行显示,所以这个定时任务废弃了...
 */
@Deprecated
public class PhotoClearTask {


    /**
     * 储存用户文件夹下所有文件名
     */
    private static List<String> userPhoto = new LinkedList<>();

    private static List<String> productPhoto = new LinkedList<>();


    /**
     * 清理用户图像
     */
    public void clearUserPhoto() {
        List<String> allPhoto = new ArrayList<>();
        List<Boolean> collect = allPhoto.stream().map(path -> StringUtils.isNotBlank(path)).collect(Collectors.toList());
        getPath(Constant.USER_IMG_DIR);
        for (String path : userPhoto) {
            if (!collect.contains(path)) {
                new File(path).delete();
            }
        }
        userPhoto.clear();
    }


    /**
     * 根据路径过去所有的文件名
     *
     * @param path
     */
    private static void getPath(String path) {
        File dir = new File(path);
        //是文件
        if (dir.isFile()) {
            userPhoto.add(dir.getAbsolutePath());
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
                        userPhoto.add(file.getAbsolutePath());
                    } else {
                        //循环这个过程
                        getPath(file.getAbsolutePath());
                    }
                }
            }
        }
    }


}
