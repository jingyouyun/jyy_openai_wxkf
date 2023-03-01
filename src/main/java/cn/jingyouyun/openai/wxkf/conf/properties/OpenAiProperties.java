package cn.jingyouyun.openai.wxkf.conf.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2023/2/14
 */
@Data
@Component
@ConfigurationProperties("openai")
public class OpenAiProperties {

    private String url;

    private String key;
}
