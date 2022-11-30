package com.salute.mall.common.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.*;
import java.util.function.Function;


@Slf4j
public class CompletableFutureUtil {

    public static final class Delayer {

        static final ScheduledThreadPoolExecutor delayer;

        static {
            delayer = new ScheduledThreadPoolExecutor(1, new DaemonThreadFactory());
            delayer.setRemoveOnCancelPolicy(true);
        }

        static ScheduledFuture<?> delay(Runnable command, long delay, TimeUnit unit) {
            return delayer.schedule(command, delay, unit);
        }

        static final class DaemonThreadFactory implements ThreadFactory {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                t.setName("CompletableFutureScheduler");
                return t;
            }
        }
    }


    public static void allOf(String errorMsg, CompletableFuture... completableFutures){
        String msg = StringUtils.isNotBlank(errorMsg)?errorMsg:"execute CompletableFutureUtil allOf error";
        CompletableFuture.allOf(completableFutures)
                .exceptionally(e->{
                    log.error(msg,e);
                    return null;
                }).join();
    }

    /**
     * @Description applyToEither
     * @author liuhu
     * @param timeout
     * @param unit
     * @param errorMsg
     * @param completableFutures
     * @date 2022/11/30 17:37
     * @return java.util.concurrent.CompletableFuture
     */
    public static CompletableFuture allOf(long timeout, TimeUnit unit,String errorMsg, CompletableFuture... completableFutures){
       String msg = StringUtils.isNotBlank(errorMsg)?errorMsg:"execute CompletableFutureUtil allOf error";
      return CompletableFuture.allOf(completableFutures)
                              .applyToEither(timeoutAfter(timeout,unit), Function.identity())
                              .exceptionally(e->{
                                    log.error(msg,e);
                                    return null;
                                });
    }


    /**
     * @Description 快速失败
     * @author liuhu
     * @param timeout
     * @param unit
     * @date 2022/11/30 17:37
     * @return java.util.concurrent.CompletableFuture<T>
     */
    public static <T> CompletableFuture<T> timeoutAfter(long timeout, TimeUnit unit) {
        CompletableFuture<T> result = new CompletableFuture<T>();
        // timeout 时间后 抛出TimeoutException 类似于sentinel / watcher
        Delayer.delayer.schedule(() -> result.completeExceptionally(new TimeoutException()), timeout, unit);
        return result;
    }

}
