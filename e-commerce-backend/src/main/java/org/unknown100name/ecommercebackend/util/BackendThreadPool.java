package org.unknown100name.ecommercebackend.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import static common.ConstUtil.*;

/**
 * @author unknown100name
 * @since 2022.03.13
 *
 * @description 用于推荐系统的线程池
 */
@Configuration
@EnableAsync
public class BackendThreadPool {

    @Bean(name = "HttpSender")
    @Primary
    public ThreadPoolTaskExecutor httpSender() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(HTTP_SENDER_CORE_POOL_SIZE);
        // 设置最大线程数
        executor.setMaxPoolSize(HTTP_SENDER_MAX_POOL_SIZE);
        // 设置队列容量
        executor.setQueueCapacity(HTTP_SENDER_CAPACITY_SIZE);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(HTTP_SENDER_ACTIVE_TIME);
        // 设置默认线程名称
        executor.setThreadNamePrefix(HTTP_SENDER_NAME);
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(HTTP_SENDER_REJECT_POLICY);
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(HTTP_SENDER_SHUTDOWN_POLICY);
        return executor;

    }
}
