package cn.jingyouyun.openai.wxkf.service.wx.send;

import cn.jingyouyun.openai.wxkf.entity.wx.dto.WxSendMessageDTO;
import cn.jingyouyun.openai.wxkf.entity.wx.vo.WxSyncMessageVO;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2023/2/10
 */
public interface IWxSendMessageService {

    /**
     * 消息处理类型 cn.jingyouyun.openai.wxkf.entity.enums.WxMsgTypeEnum
     * @return
     */
    String messageType();

    /**
     * 生成发送微信消息实体
     * *实现此方法，支持更多的消息回复*
     * 示例：cn.jingyouyun.openai.wxkf.service.wx.send.impl.WxSendTextMessageServiceImpl
     * @param wxMessageVo
     * @return
     */
    WxSendMessageDTO createWxSendMessageDTO(WxSyncMessageVO wxMessageVo);

    /**
     * 发送微信消息
     * @param wxMessageVo
     * @return
     */
    void sendMessage(WxSyncMessageVO wxMessageVo);
}
