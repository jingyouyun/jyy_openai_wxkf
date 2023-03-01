package cn.jingyouyun.openai.wxkf.entity.wx.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 描述：解密后的微信客服新消息通知
 *
 * @author zhujingchun
 * @date 2023/2/14
 */
@Data
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.NONE)
public class WxMessageNotifyDTO {

    /**
     * 开发者微信号
     */
    @XmlElement(name = "ToUserName")
    private String toUserName;

    /**
     * 消息创建时间 （整型）
     */
    @XmlElement(name = "CreateTime")
    private Long createTime;

    /**
     * 消息类型，文本为text
     */
    @XmlElement(name = "MsgType")
    private String msgType;

    /**
     * event
     */
    @XmlElement(name = "Event")
    private String event;

    /**
     * token
     */
    @XmlElement(name = "Token")
    private String token;

    /**
     * 客服id
     */
    @XmlElement(name = "OpenKfId")
    private String openKfId;
}
