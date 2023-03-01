package cn.jingyouyun.openai.wxkf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 描述：文本消息处理
 *
 * @author zhujingchun
 * @date 2023/2/10
 */
@EnableAsync(proxyTargetClass=true)
@SpringBootApplication
public class JyyOpenaiWxkfApplication {

    public static void main(String[] args) {
        SpringApplication.run(JyyOpenaiWxkfApplication.class, args);
    }

}
