package cn.jingyouyun.openai.wxkf.service.wx.send.impl;

import cn.jingyouyun.openai.wxkf.entity.enums.ExceptionMsgEnum;
import cn.jingyouyun.openai.wxkf.entity.wx.dto.WxSendMessageDTO;
import cn.jingyouyun.openai.wxkf.entity.wx.dto.WxSendMessageTextDTO;
import cn.jingyouyun.openai.wxkf.entity.wx.vo.WxSyncMessageVO;
import cn.jingyouyun.openai.wxkf.exception.SystemException;
import cn.jingyouyun.openai.wxkf.service.user.UserService;
import cn.jingyouyun.openai.wxkf.service.wx.impl.WxBaseService;
import cn.jingyouyun.openai.wxkf.service.wx.send.IWxSendMessageService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2023/2/10
 */
@Slf4j
public abstract class WxSendMessageBaseService extends WxBaseService implements IWxSendMessageService {

    @Autowired
    private UserService userService;

    /**
     * 默认发送微信消息
     *
     * @param wxMessageVo
     */
    //@Async
    @Override
    public void sendMessage(WxSyncMessageVO wxMessageVo) {
        WxSendMessageDTO wxSendMessageDTO;
        try {
            // 减少用户余额
            userService.subMessageNum(wxMessageVo);
            wxSendMessageDTO = createWxSendMessageDTO(wxMessageVo);
        } catch (SystemException e) {
            wxSendMessageDTO = new WxSendMessageDTO();
            wxSendMessageDTO.setTouser(wxMessageVo.getExternal_userid());
            wxSendMessageDTO.setMsgtype(wxMessageVo.getMsgtype());
            wxSendMessageDTO.setText(new WxSendMessageTextDTO(wxMessageVo.getText().getContent(), e.getExceptionMsgEnum().getMessage()));
            wxSendMessageDTO.setOpen_kfid(wxMessageVo.getOpen_kfid());
            if (!Objects.equals(e.getExceptionMsgEnum().getCode(), ExceptionMsgEnum.USER_MESSAGE_BALANCE_ERROR.getCode())) {
                userService.addMessageNum(wxMessageVo);
            }
        } catch (Exception e) {
            wxSendMessageDTO = new WxSendMessageDTO();
            wxSendMessageDTO.setTouser(wxMessageVo.getExternal_userid());
            wxSendMessageDTO.setMsgtype(wxMessageVo.getMsgtype());
            wxSendMessageDTO.setText(new WxSendMessageTextDTO(wxMessageVo.getText().getContent(), ExceptionMsgEnum.SYSTEM_ERROR.getMessage()));
            wxSendMessageDTO.setOpen_kfid(wxMessageVo.getOpen_kfid());
            userService.addMessageNum(wxMessageVo);
        }
        String dto = JSONObject.toJSONString(wxSendMessageDTO);
        postWxForObject(wxKfProperties.getSendMsgUrl(getAccessToken()), dto);
    }
}
