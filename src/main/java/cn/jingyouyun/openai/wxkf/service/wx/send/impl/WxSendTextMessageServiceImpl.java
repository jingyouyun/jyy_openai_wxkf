package cn.jingyouyun.openai.wxkf.service.wx.send.impl;

import cn.jingyouyun.openai.wxkf.entity.enums.WxMsgTypeEnum;
import cn.jingyouyun.openai.wxkf.entity.wx.dto.WxSendMessageDTO;
import cn.jingyouyun.openai.wxkf.entity.wx.dto.WxSendMessageTextDTO;
import cn.jingyouyun.openai.wxkf.entity.wx.vo.WxSyncMessageVO;
import cn.jingyouyun.openai.wxkf.service.openai.OpenAiSendMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述：文本消息处理
 *
 * @author zhujingchun
 * @date 2023/2/10
 */
@Slf4j
@Service
public class WxSendTextMessageServiceImpl extends WxSendMessageBaseService {
    
    @Autowired
    private OpenAiSendMessageService openAiSendMessageService;

    @Override
    public String messageType() {
        return WxMsgTypeEnum.TEXT.getMsgType();
    }

    @Override
    public WxSendMessageDTO createWxSendMessageDTO(WxSyncMessageVO wxMessageVo) {
        String text = openAiSendMessageService.sendMessage(wxMessageVo);
        WxSendMessageDTO wxSendMessageDTO = new WxSendMessageDTO();
        wxSendMessageDTO.setTouser(wxMessageVo.getExternal_userid());
        wxSendMessageDTO.setOpen_kfid(wxMessageVo.getOpen_kfid());
        wxSendMessageDTO.setMsgtype(messageType());
        wxSendMessageDTO.setText(new WxSendMessageTextDTO(wxMessageVo.getText().getContent(), text));
        return wxSendMessageDTO;
    }
}
