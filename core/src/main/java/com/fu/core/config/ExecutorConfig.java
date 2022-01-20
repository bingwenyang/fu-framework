package com.fu.core.config;

import com.fu.core.constant.GlobalConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


@Configuration
@EnableAsync
public class ExecutorConfig {

    /**
     * spring中注入了一个名字为globalExecutor的bean
     * 方法名只要在项目中唯一性，可以适当任意取（最好遵循一定的规则）
     * 使用方法：在需要加入线程池的方法上增加注解@Async("globalExecutor")就可以加入此线程池异步执行
     *
     * @return
     */
    @Bean(GlobalConstants.GLOBAL_EXECUTOR)
    public Executor globalExecutor() {
        int cores = Runtime.getRuntime().availableProcessors() * 2;
        int keepAliveTime = 60;
        int queueSize = 2048;
        MdcThreadPoolTaskExecutor executor = new MdcThreadPoolTaskExecutor();
        // 核心线程数：线程池创建时候初始化的线程数
        executor.setCorePoolSize(cores);
        // 最大线程数：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        executor.setMaxPoolSize(cores);
        // 缓冲队列：用来缓冲执行任务的队列
        executor.setQueueCapacity(queueSize);
        // 允许线程的空闲时间60秒：当超过了核心线程之外的线程在空闲时间到达之后会被销毁
        executor.setKeepAliveSeconds(keepAliveTime);
        // 线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
        executor.setThreadNamePrefix("analysis-");
        // 缓冲队列满了之后的拒绝策略：由调用线程处理（一般是主线程）
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        executor.initialize();
        return executor;
    }
}
