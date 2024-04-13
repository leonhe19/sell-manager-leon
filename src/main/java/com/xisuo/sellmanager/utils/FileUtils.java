package com.xisuo.sellmanager.utils;

import com.xisuo.sellmanager.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zk
 * @Description: 文件上传的工具类
 * @date 2018-07-27 11:31
 */
public class FileUtils {

    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * @Param flag  user用户图像
     *              product  产品图片
     * 处理上传的文件,返回文件保存的路径
     */
    public static String handleUploadFile(MultipartFile file,String flag) {
        if (file == null) {
            throw new IllegalArgumentException("上传的文件不能为空");
        }
        // 获取物理路径,创建目录
        StringBuilder targetDirectory = new StringBuilder();
        if ("user".equals(flag)) {
            targetDirectory.append(Constant.USER_IMG_DIR);
        } else {
            targetDirectory.append(Constant.PRODUCT_IMG_DIR);
        }
        targetDirectory.append(yearQuarter());
        File tmpFile = new File(targetDirectory.toString());
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        // 上传的文件名
        String tmpFileName = file.getOriginalFilename();
        int index = tmpFileName.lastIndexOf('.');
        // 文件后缀名
        String suffix = "";
        if ((index > -1) && (index < (tmpFileName.length() - 1))) {
            suffix = tmpFileName.substring(index + 1);
        }
        // 匹配文件的格式
        String targetFileName = null;
        List<String> list = Stream.of("jpg", "bmp", "png", "gif").collect(Collectors.toList());
        if (list.contains(suffix)) {
            // 重命名上传的文件名
            targetFileName = StrUtil.renameFileName(tmpFileName);
            // 保存的新文件
            File target = new File(targetDirectory.toString(), targetFileName);
            try {
                // 保存文件
                logger.info("正在上传的文件的名字是:" + tmpFileName);
                org.apache.commons.io.FileUtils.copyInputStreamToFile(file.getInputStream(), target);
                logger.info("保存后的文件的名字是:" + targetFileName);

            } catch (IOException e) {
                logger.error("保存文件的名字是:{}  出错原因是:{}", targetFileName, e.getCause());
                throw new RuntimeException(e.getCause());
            }
            return targetDirectory + "/" + targetFileName;
        } else {
            return "上传的文件只能是jpg, bmp, png, gif 格式的";
        }

    }

    /**
     * 201901 201902....
     * 获取文件夹的名字,按照季度来进行划分,因为考虑到用户可能很少.不想建立那么多文件夹
     */
    private static String yearQuarter() {
        LocalDate now = LocalDate.now();
        StringBuilder sb = new StringBuilder();
        sb.append(now.getYear());
        int tmp = 0;
        int value = now.getMonthValue();
        if (value <= 3) {
            tmp = 1;
        } else if (value <= 6) {
            tmp = 2;
        } else if (value <= 9) {
            tmp = 3;
        } else {
            tmp = 4;
        }
        sb.append("0");
        sb.append(tmp);
        sb.append("/");
        return sb.toString();
    }


}
