package com.fu.core.config;

import com.fu.core.trace.MdcUtils;
import com.fu.core.trace.Trace;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * 重新实现线程池的执行方法，将父类mdc添加进去
 */
public class MdcThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    @Override
    public void execute(Runnable runnable) {
        Trace trace = MdcUtils.getStoreTrace();
        super.execute(() -> MdcUtils.run(runnable, trace));
    }


    @Override
    public Future<?> submit(Runnable runnable) {
        // 获取父线程MDC中的内容，必须在run方法之前，否则等异步线程执行的时候有可能MDC里面的值已经被清空了，这个时候就会返回null
        Trace trace = MdcUtils.getStoreTrace();

        return super.submit(() -> MdcUtils.run(runnable, trace));
    }

    /**
     * spring的 @asyn 是使用的这个方法
     *
     * @param task
     * @param <T>
     * @return
     */
    @Override
    public <T> Future<T> submit(Callable<T> task) {
        // 获取父线程MDC中的内容，必须在run方法之前，否则等异步线程执行的时候有可能MDC里面的值已经被清空了，这个时候就会返回null
        Trace trace = MdcUtils.getStoreTrace();

        return super.submit(() -> MdcUtils.call(task, trace));
    }

}
