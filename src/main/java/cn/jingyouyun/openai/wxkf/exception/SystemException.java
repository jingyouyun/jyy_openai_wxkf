package cn.jingyouyun.openai.wxkf.exception;

import cn.jingyouyun.openai.wxkf.entity.enums.ExceptionMsgEnum;
import cn.jingyouyun.openai.wxkf.entity.wx.vo.WxSyncMessageVO;
import lombok.Getter;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2023/2/16
 */
@SuppressWarnings("serial")
@Getter
public class SystemException extends RuntimeException {

    private ExceptionMsgEnum exceptionMsgEnum;
    private WxSyncMessageVO wxMessageVo;

    public SystemException(ExceptionMsgEnum exceptionMsgEnum, WxSyncMessageVO wxMessageVo) {
        this.exceptionMsgEnum = exceptionMsgEnum;
        this.wxMessageVo = wxMessageVo;
    }

    public SystemException(ExceptionMsgEnum exceptionMsgEnum) {
        this.exceptionMsgEnum = exceptionMsgEnum;
    }
}
