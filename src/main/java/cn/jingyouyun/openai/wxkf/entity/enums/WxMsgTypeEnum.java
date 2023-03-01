package cn.jingyouyun.openai.wxkf.entity.enums;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2023/2/14
 */
public enum WxMsgTypeEnum {

    /**
     * 文本消息
     */
    TEXT("text", "文本消息"),

    /**
     * 图片消息
     */
    IMAGE("image", "图片消息"),

    /**
     * 语音消息
     */
    VOICE("voice", "语音消息"),

    /**
     * 视频消息
     */
    VIDEO("video", "视频消息"),

    /**
     * 小视频消息
     */
    SHORT_VIDEO("shortvideo", "小视频消息"),

    /**
     * 地理位置消息
     */
    LOCATION("location", "地理位置消息"),

    /**
     * 链接消息
     */
    LINK("link", "链接消息");

    private String msgType;

    private String msgTypeDesc;

    WxMsgTypeEnum(String msgType, String msgTypeDesc) {
        this.msgType = msgType;
        this.msgTypeDesc = msgTypeDesc;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgTypeDesc() {
        return msgTypeDesc;
    }

    public void setMsgTypeDesc(String msgTypeDesc) {
        this.msgTypeDesc = msgTypeDesc;
    }
}
