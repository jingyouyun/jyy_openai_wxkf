package cn.jingyouyun.openai.wxkf.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2023/2/16
 */
@Configuration
public class AsyncConfig {

    /**
     * U8C 预生成
     * @return
     */
    @Bean("getVoucherDetailAsync")
    public AsyncTaskExecutor getVoucherDetail() {
        ThreadPoolTaskExecutor asyncTaskExecutor = new ThreadPoolTaskExecutor();
        asyncTaskExecutor.setMaxPoolSize(4);
        asyncTaskExecutor.setCorePoolSize(4);
        asyncTaskExecutor.setThreadNamePrefix("async-task-thread-pool-");
        asyncTaskExecutor.initialize();
        return asyncTaskExecutor;
    }
}
