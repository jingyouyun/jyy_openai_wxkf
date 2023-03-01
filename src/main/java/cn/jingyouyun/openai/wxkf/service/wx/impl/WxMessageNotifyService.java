package cn.jingyouyun.openai.wxkf.service.wx.impl;

import cn.jingyouyun.openai.wxkf.entity.wx.dto.WxEncryptMessageNotifyDTO;
import cn.jingyouyun.openai.wxkf.entity.wx.dto.WxMessageNotifyDTO;
import cn.jingyouyun.openai.wxkf.entity.wx.dto.WxSyncMessageDTO;
import cn.jingyouyun.openai.wxkf.utils.wx.WXBizJsonMsgCrypt;
import cn.jingyouyun.openai.wxkf.utils.xml.JaxbUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 描述：微信客服消息通知
 *
 * @author zhujingchun
 * @date 2023/2/16
 */
@Slf4j
@Service
public class WxMessageNotifyService extends WxBaseService {

    /**
     * 解密通知 & 拼接请求同步消息接口参数
     *
     * @param encryptMessage
     * @return
     * @throws Exception
     */
    public WxSyncMessageDTO decodeMessageNotify(WxEncryptMessageNotifyDTO encryptMessage) throws Exception {
        // 解密
        String sEchoStr = new WXBizJsonMsgCrypt(wxKfProperties.getToken(), wxKfProperties.getEncodingAesKey(), wxKfProperties.getSCorpId())
                .VerifyURL(encryptMessage.getMsgSignature(), encryptMessage.getTimestamp(), encryptMessage.getNonce(), encryptMessage.getEncrypt());

        // XML 转对象
        WxMessageNotifyDTO wxMessageDTO = new WxMessageNotifyDTO();
        wxMessageDTO = (WxMessageNotifyDTO) JaxbUtils.xmlToObj(wxMessageDTO, sEchoStr);

        // 拼接请求同步消息接口参数
        WxSyncMessageDTO wxSyncMessageDTO = new WxSyncMessageDTO();
        wxSyncMessageDTO.setToken(wxMessageDTO.getToken());
        wxSyncMessageDTO.setOpen_kfid(wxMessageDTO.getOpenKfId());
        return wxSyncMessageDTO;
    }
}
