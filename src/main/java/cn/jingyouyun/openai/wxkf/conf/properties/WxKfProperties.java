package cn.jingyouyun.openai.wxkf.conf.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信客服相关
 * @author zhujingchun
 * @date 2023-02-14
 */
@Data
@Component
@ConfigurationProperties("wx.kf")
public class WxKfProperties {

    private String token;

    private String encodingAesKey;

    private String sCorpId;

    private String getAccessTokenUrl;

    private String syncMsgUrl;
    public String getSyncMsgUrl(String accessToken) {
        return String.format(this.syncMsgUrl, accessToken);
    }

    private String sendMsgUrl;
    public String getSendMsgUrl(String accessToken) {
        return String.format(this.sendMsgUrl, accessToken);
    }
}
