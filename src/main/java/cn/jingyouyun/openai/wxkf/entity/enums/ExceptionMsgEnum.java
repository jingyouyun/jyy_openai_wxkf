package cn.jingyouyun.openai.wxkf.entity.enums;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2023/2/16
 */
public enum ExceptionMsgEnum {
    /**
     * 系统异常
     */
    SYSTEM_ERROR(1000001, "系统未知异常，请重试！"),
    /**
     * 用户相关 - 处理余额失败
     */
    USER_MESSAGE_NUM_ERROR(2000001, "处理余额失败，请重试！"),
    USER_MESSAGE_BALANCE_ERROR(2000002, "可用余额不足~"),
    /**
     * OPENAI相关
     */
    OPEN_AI_SYSTEM_MESSAGE_ERROR(3000001, "OPENAI系统异常，请重试！"),
    OPEN_AI_MESSAGE_ERROR(3000002, "解答失败，请重试！"),
    /**
     * 微信消息相关
     */
    WX_SEND_MESSAGE_ERROR(4000001, "微信消息发送失败！"),
    ;

    private Integer code;

    private String message;

    ExceptionMsgEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
