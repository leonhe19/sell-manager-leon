package com.xisuo.sellmanager.config;

import com.xisuo.sellmanager.interceptor.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zk
 * @Description: 客户状态修改的线程池
 */
public class CustomerStateTask {

    private static Logger logger = LoggerFactory.getLogger(CustomerStateTask.class);

    private static int corePoolSize = 4;
    private static int maximumPoolSize = 8;
    private static long keepAliveTime = 30;
    private static ThreadPoolExecutor executor;

    static {
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(15);
        executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MINUTES, queue
                , new MyThreadFactory("customerState"), new MyRejected());
    }

    /**
     * 提交任务进行执行
     *
     * @param task 线程任务
     */
    public static void submitTask(Runnable task) {
        logger.info("用户:{} 执行了更改客户的状态", UserContext.getUser().getId());
        executor.execute(task);
    }

    /**
     * 自定义拒绝策略
     */
    static class MyRejected extends ThreadPoolExecutor.CallerRunsPolicy {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            logger.info("线程: {} 已经达到了拒绝策略了,当前的用户是:" + Thread.currentThread().getId(), UserContext.getUser().getId());
            super.rejectedExecution(r, e);
        }
    }

    /**
     * 自定义线程池工厂
     */
    static class MyThreadFactory implements ThreadFactory {
        final AtomicInteger threadNumber = new AtomicInteger(1);
        final String namePrefix;

        MyThreadFactory(String namePrefix) {
            this.namePrefix = namePrefix + "-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, namePrefix + threadNumber.getAndIncrement());
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

}
