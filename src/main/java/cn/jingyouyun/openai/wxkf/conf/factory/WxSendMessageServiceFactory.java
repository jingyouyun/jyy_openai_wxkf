package cn.jingyouyun.openai.wxkf.conf.factory;

import cn.jingyouyun.openai.wxkf.service.wx.send.IWxSendMessageService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息
 *
 * @author: zhujingchun
 * @data: 2023/2/10
 */
@Component
public class WxSendMessageServiceFactory implements ApplicationContextAware {

    private static Map<String, IWxSendMessageService> wxSendMessageServiceMap;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, IWxSendMessageService> map = applicationContext.getBeansOfType(IWxSendMessageService.class);
        wxSendMessageServiceMap = new HashMap<>(map.size());
        for (String key : map.keySet()) {
            IWxSendMessageService iService = map.get(key);
            if(iService.messageType() == null) {
                continue;
            }
            wxSendMessageServiceMap.put(iService.messageType(), iService);
        }
    }

    /**
     * 根据messageType获取
     *
     * @param messageType
     * @param <T>
     * @return
     */
    public static <T extends IWxSendMessageService> T getServiceByMessageType(String messageType) {
        return (T) wxSendMessageServiceMap.get(messageType);
    }

    /**
     * 获取所有service
     *
     * @return
     */
    public static Map<String, IWxSendMessageService> getAllService() {
        return wxSendMessageServiceMap;
    }

}
