package cn.jingyouyun.openai.wxkf.entity.wx.vo;

import lombok.Data;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2023/2/16
 */
@Data
public class WxSyncMessageVO {
    /**
     * 消息id
     */
    private String msgid;
    /**
     * 客服id
     */
    private String open_kfid;
    /**
     * 用户id
     */
    private String external_userid;
    /**
     * 发送消息时间
     */
    private Long send_time;
    /**
     * 消息来源。3-微信客户发送的消息 4-系统推送的事件消息 5-接待人员在企业微信客户端发送的消息
     */
    private Integer origin;
    /**
     * 消息类型
     */
    private String msgtype;
    /**
     * 消息内容
     */
    private SyncMessageTextVO text;
}
