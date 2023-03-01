package cn.jingyouyun.openai.wxkf.constants;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2022/11/23
 */
public class GlobalConstant {

    /**
     * 数字
     */
    public static final Integer NEGATIVE_ONE = -1;
    public static final Integer ZERO = 0;
    public static final Integer ONE = 1;
    public static final Integer FIVE = 5;
    public static final Integer TEN = 10;
    public static final Integer TWENTY = 20;
    public static final Integer ONE_HUNDRED = 100;
    public static final Integer TWO_THOUSAND = 2000;

    /**
     * 同步微信消息新通知锁
     */
    public static final String ASYNC_SYNC_MESSAGE_LOCK = "OPEN_AI:KF:ASYNC_SYNC_MESSAGE_LOCK";
    /**
     * 异步处理用户信息锁
     */
    public static final String ASYNC_SYNC_USER_INFO_LOCK = "OPEN_AI:KF:ASYNC_SYNC_USER_INFO_";
    /**
     * 微信客服ACCESS_TOKEN
     */
    public static final String ACCESS_TOKEN_KEY = "OPEN_AI:KF:ACCESS_TOKEN_VALUE";
    /**
     * 微信客服ACCESS_TOKEN 锁
     */
    public static final String ACCESS_TOKEN_LOCK = "OPEN_AI:KF:ACCESS_TOKEN_LOCK";
    /**
     * 下一次获取消息起点
     */
    public static final String NEXT_CURSOR_KEY = "OPEN_AI:KF:NEXT_CURSOR_VALUE";
    /**
     * 用户信息
     */
    public static final String USER_INFO_KEY = "OPEN_AI:KF:USER_INFO_MAP";

}
