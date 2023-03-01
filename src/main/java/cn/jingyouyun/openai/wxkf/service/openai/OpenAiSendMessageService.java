package cn.jingyouyun.openai.wxkf.service.openai;

import cn.jingyouyun.openai.wxkf.conf.RestConfig;
import cn.jingyouyun.openai.wxkf.conf.properties.OpenAiProperties;
import cn.jingyouyun.openai.wxkf.constants.GlobalConstant;
import cn.jingyouyun.openai.wxkf.entity.enums.ExceptionMsgEnum;
import cn.jingyouyun.openai.wxkf.entity.openai.dto.OpenAiSendDTO;
import cn.jingyouyun.openai.wxkf.entity.openai.vo.OpenAiChoicesVO;
import cn.jingyouyun.openai.wxkf.entity.openai.vo.OpenAiSendVO;
import cn.jingyouyun.openai.wxkf.entity.wx.vo.WxSyncMessageVO;
import cn.jingyouyun.openai.wxkf.exception.SystemException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2023/2/20
 */
@Slf4j
@Service
public class OpenAiSendMessageService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OpenAiProperties openAiProperties;

    /**
     * 发送消息到openAI
     * @param wxMessageVo
     * @return
     */
    public String sendMessage(WxSyncMessageVO wxMessageVo) {
        OpenAiSendDTO openAiSendDTO = new OpenAiSendDTO();
        openAiSendDTO.setPrompt(wxMessageVo.getText().getContent());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(RestConfig.HEADERS_AUTHORIZATION, RestConfig.HEADERS_BEARER + openAiProperties.getKey());

        String paramDto = JSONObject.toJSONString(openAiSendDTO);
        HttpEntity<String> httpEntity = new HttpEntity<>(paramDto, headers);

        OpenAiSendVO openAiSendVO;
        try {
            log.info("调用 openai：{}", paramDto);
            openAiSendVO = restTemplate.postForEntity(openAiProperties.getUrl(), httpEntity, OpenAiSendVO.class).getBody();
            log.info("调用 openai 返回值：{}", JSONObject.toJSONString(openAiSendVO));
        } catch (Exception e) {
            log.error("调用 openai 异常 {}", JSONObject.toJSONString(wxMessageVo), e);
            throw new SystemException(ExceptionMsgEnum.OPEN_AI_SYSTEM_MESSAGE_ERROR, wxMessageVo);
        }

        if (Objects.isNull(openAiSendVO)) {
            throw new SystemException(ExceptionMsgEnum.OPEN_AI_MESSAGE_ERROR, wxMessageVo);
        }
        List<OpenAiChoicesVO> choices = openAiSendVO.getChoices();
        if (CollectionUtils.isEmpty(choices)) {
            throw new SystemException(ExceptionMsgEnum.OPEN_AI_MESSAGE_ERROR, wxMessageVo);
        }
        return openAiSendVO.getChoices().get(GlobalConstant.ZERO).getText();
    }
}
