package cn.jingyouyun.openai.wxkf.entity.wx.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 描述：解密前微信客服新消息通知
 *
 * @author zhujingchun
 * @date 2023/2/16
 */
@Data
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.NONE)
public class WxEncryptMessageNotifyDTO {

    @XmlElement(name = "ToUserName")
    private String toUserName;

    @XmlElement(name = "Encrypt")
    private String encrypt;

    @XmlElement(name = "AgentID")
    private String agentId;

    /**
     * 签名
     */
    private String msgSignature;
    /**
     * 时间戳
     */
    private String timestamp;
    /**
     * 随机数
     */
    private String nonce;
}
