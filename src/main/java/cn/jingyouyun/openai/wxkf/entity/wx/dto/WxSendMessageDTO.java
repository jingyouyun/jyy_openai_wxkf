package cn.jingyouyun.openai.wxkf.entity.wx.dto;

import lombok.Data;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2023/2/20
 */
@Data
public class WxSendMessageDTO extends WxBaseDTO {
    /**
     * 发送到指定的用户id
     */
    private String touser;
    /**
     * 消息类型
     */
    private String msgtype;
    /**
     * 消息内容
     */
    private WxSendMessageTextDTO text;
}
