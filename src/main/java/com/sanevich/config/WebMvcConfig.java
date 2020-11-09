package com.sanevich.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(-1);
        ThreadPoolTaskExecutor t = new ThreadPoolTaskExecutor();
        t.setThreadNamePrefix("DownloadZipTaskExecutor-");
        t.setCorePoolSize(0);
        t.setMaxPoolSize(100);
        t.setQueueCapacity(50);
        t.setAllowCoreThreadTimeOut(true);
        t.setKeepAliveSeconds(120);
        t.setRejectedExecutionHandler((r, executor) -> {

        });
        t.initialize();
        configurer.setTaskExecutor(t);
    }
}
