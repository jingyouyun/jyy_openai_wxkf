package cn.jingyouyun.openai.wxkf.service.wx.impl;

import cn.jingyouyun.openai.wxkf.conf.factory.WxSendMessageServiceFactory;
import cn.jingyouyun.openai.wxkf.constants.GlobalConstant;
import cn.jingyouyun.openai.wxkf.entity.wx.dto.WxSyncMessageDTO;
import cn.jingyouyun.openai.wxkf.entity.wx.vo.WxSyncMessageResultVO;
import cn.jingyouyun.openai.wxkf.entity.wx.vo.WxSyncMessageVO;
import cn.jingyouyun.openai.wxkf.service.wx.send.IWxSendMessageService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.redisson.api.RLock;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 描述：微信消息异步处理类
 *
 * @author zhujingchun
 * @date 2023/2/16
 */
@Slf4j
@Service
public class WxMessageAsyncSyncService extends WxBaseService {

    /**
     * 接收到新消息通知，同步微信消息
     *
     * @param wxSyncMessageDTO
     */
    @Async
    public void asyncSyncMessage(WxSyncMessageDTO wxSyncMessageDTO) {
        // 加锁
        RLock lock = redisson.getLock(GlobalConstant.ASYNC_SYNC_MESSAGE_LOCK);
        lock.lock();

        try {
            // 获取下一查询节点标记
            String nextCursor = null;
            Object nextCursorObj = redisUtil.get(GlobalConstant.NEXT_CURSOR_KEY);
            if (Objects.nonNull(nextCursorObj)) {
                nextCursor = String.valueOf(nextCursorObj);
            }

            while (true) {
                String accessToken = getAccessToken();
                wxSyncMessageDTO.setCursor(nextCursor);
                WxSyncMessageResultVO syncMsgVo = JSONObject.parseObject(postWxForObject(wxKfProperties.getSyncMsgUrl(accessToken), JSONObject.toJSONString(wxSyncMessageDTO)), WxSyncMessageResultVO.class);
                if (Objects.isNull(syncMsgVo) || !Objects.equals(syncMsgVo.getErrcode(), GlobalConstant.ZERO) || Objects.isNull(syncMsgVo.getNext_cursor())) {
                    log.error("调用微信查询最新消息列表错误 {}", JSONObject.toJSONString(syncMsgVo));
                    break;
                }
                nextCursor = syncMsgVo.getNext_cursor();
                List<WxSyncMessageVO> msgList = syncMsgVo.getMsg_list();
                if (CollectionUtils.isEmpty(msgList)) {
                    break;
                }

                for (WxSyncMessageVO syncMsgListVo : msgList) {
                    // 发送消息
                    IWxSendMessageService wxSendMessageService = WxSendMessageServiceFactory.getServiceByMessageType(syncMsgListVo.getMsgtype());
                    if (Objects.isNull(wxSendMessageService)) {
                        // 暂不处理
                        continue;
                    }
                    wxSendMessageService.sendMessage(syncMsgListVo);
                }

                if (Objects.equals(syncMsgVo.getHas_more(), GlobalConstant.ZERO)) {
                    break;
                }
            }
            redisUtil.set(GlobalConstant.NEXT_CURSOR_KEY, nextCursor);
        } catch (Exception e) {
            log.error("同步微信最新消息列表异常", e);
        } finally {
            lock.unlock();
        }
    }
}
