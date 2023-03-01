package cn.jingyouyun.openai.wxkf.controller;

import cn.jingyouyun.openai.wxkf.conf.properties.WxKfProperties;
import cn.jingyouyun.openai.wxkf.entity.wx.dto.WxEncryptMessageNotifyDTO;
import cn.jingyouyun.openai.wxkf.entity.wx.dto.WxSyncMessageDTO;
import cn.jingyouyun.openai.wxkf.service.wx.impl.WxMessageAsyncSyncService;
import cn.jingyouyun.openai.wxkf.service.wx.impl.WxMessageNotifyService;
import cn.jingyouyun.openai.wxkf.utils.wx.WXBizJsonMsgCrypt;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2023/2/20
 */
@Slf4j
@RestController
@RequestMapping("/message")
public class WxMessageController {

    @Autowired
    private WxKfProperties wxKfProperties;
    @Autowired
    private WxMessageNotifyService wxMessageNotifyService;
    @Autowired
    private WxMessageAsyncSyncService wxMessageAsyncSyncService;

    @GetMapping
    public String test(@RequestParam("msg_signature") String msg_signature, @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce, @RequestParam("echostr") String echostr) throws Exception {
        log.info("接受到微信客服请求：{} {} {} {}", msg_signature, timestamp, nonce, echostr);
        WXBizJsonMsgCrypt wxcpt = new WXBizJsonMsgCrypt(wxKfProperties.getToken(), wxKfProperties.getEncodingAesKey(), wxKfProperties.getSCorpId());
        String sEchoStr = wxcpt.VerifyURL(msg_signature, timestamp,
                nonce, echostr);
        log.info("接受到微信客服请求：{} {} {} {} | 解密 {}", msg_signature, timestamp, nonce, echostr, sEchoStr);
        return sEchoStr;
    }

    @PostMapping(consumes = "text/xml", produces = "text/xml;charset=utf-8")
    public String kfMessage(@RequestParam("msg_signature") String msgSignature, @RequestParam("timestamp") String timestamp,
                            @RequestParam("nonce") String nonce, @RequestBody WxEncryptMessageNotifyDTO wxEncryptMessageNotifyDTO) throws Exception {
        log.info("接收到微信客服消息 {} {} {} {}", msgSignature, timestamp, nonce, JSONObject.toJSONString(wxEncryptMessageNotifyDTO));
        wxEncryptMessageNotifyDTO.setMsgSignature(msgSignature);
        wxEncryptMessageNotifyDTO.setTimestamp(timestamp);
        wxEncryptMessageNotifyDTO.setNonce(nonce);
        WxSyncMessageDTO wxSyncMessageDTO = wxMessageNotifyService.decodeMessageNotify(wxEncryptMessageNotifyDTO);
        log.info("接受到微信客服请求：{} {} {} {} | 解密 {}", msgSignature, timestamp, nonce, wxEncryptMessageNotifyDTO.getEncrypt(), JSONObject.toJSONString(wxSyncMessageDTO));
        wxMessageAsyncSyncService.asyncSyncMessage(wxSyncMessageDTO);
        return "SUCCESS";
    }
}
