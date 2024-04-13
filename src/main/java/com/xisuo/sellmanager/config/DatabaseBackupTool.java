package com.xisuo.sellmanager.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 数据库自动备份,用于自动的备份数据库
 */
@Component
public class DatabaseBackupTool {

    private static Logger logger = LoggerFactory.getLogger(DatabaseBackupTool.class);
    private static DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM:ss");


    /**
     * 每隔2天,在每天的凌晨2点执行自动备份的任务
     */
    @Scheduled(cron = "0 0 2 1/2 * ? ")
    public void backupDatabase() {
        /**
         * window下可行了
         */
        //backup("C:\\Program Files\\MySQL\\MySQL Server 5.5\\bin","127.0.0.1","3306","root","123","test","E:\\1.sql");

        String mysqlPath = "/usr/bin";
        String mysqlIp = "192.168.184.10";
        String dataBaseName = "sell_manager";
        String resultFile = "/web/xisuo/sqlbackup/" + LocalDate.now().toString() + ".sql";
        /**
         * linux下可行了
         */
        backup(mysqlPath, mysqlIp, "3306", "root", "kea12b34c56D", dataBaseName, resultFile);
    }


    /**
     * 备份数据库 ,控制台执行命令格式 "mysql的bin目录/mysqldump --databases  -h主机ip -P端口  -u用户名 -p密码  --default-character-set=字符集  数据库名
     *
     * @param mysqlPath  mysql安装路径
     * @param mysqlIp    mysql主机ip
     * @param mysqlPort  端口
     * @param userName   用户名
     * @param password   密码
     * @param database   数据库名
     * @param resultFile 备份文件全路径
     */
    public static void backup(String mysqlPath, String mysqlIp, String mysqlPort, String userName, String password, String database, String resultFile) {
        logger.info("开始备份数据库,时间是:{}", pattern.format(LocalDateTime.now()));
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        try {
            Runtime runtime = Runtime.getRuntime();
            // 调用mysql的安装目录的命令
            /**
             * windows
             */
            /*Process process = runtime.exec("\"" + mysqlPath + File.separator + "mysqldump\" --databases -h" + mysqlIp
                    + " -P" + mysqlPort + " -u" + userName + " -p" + password
                    + " --add-drop-database --default-character-set=utf8 " + database + " --result-file=" + resultFile);*/
            /**
             * linux
             */
            Process process = runtime.exec("" + mysqlPath + File.separator + "mysqldump --databases -h" + mysqlIp
                    + " -P" + mysqlPort + " -u" + userName + " -p" + password
                    + " --add-drop-database --default-character-set=utf8 " + database + " --result-file=" + resultFile);
            // 设置导出编码为utf-8。这里必须是utf-8
            inputStream = process.getInputStream();// 控制台的输出信息作为输入流
            ErrorStreamThread errStream = new ErrorStreamThread(process.getErrorStream()); //错误流另开线程，不然会阻塞
            errStream.start();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("备份数据库完成,时间是:{}", pattern.format(LocalDateTime.now()));
    }

    private static class ErrorStreamThread extends Thread {
        private InputStream input; // 控制台errorStream

        public ErrorStreamThread(InputStream input) {
            this.input = input;
        }

        @Override
        public void run() {
            InputStreamReader inputStreamReader = null;
            BufferedReader bufferedReader = null;
            try {
                inputStreamReader = new InputStreamReader(input);
                bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.indexOf("Warning") != 0) {
                        throw new Exception(line);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}