package cn.jingyouyun.openai.wxkf.entity.wx.dto;

import cn.jingyouyun.openai.wxkf.constants.GlobalConstant;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2023/2/20
 */
@Data
public class WxSendMessageTextDTO {

    private String content;

    public WxSendMessageTextDTO(String request, String content) {
        if (StringUtils.isNotBlank(content) && content.length() > GlobalConstant.TWO_THOUSAND) {
            content = content.substring(GlobalConstant.TWO_THOUSAND);
        }
        if (content.startsWith("\n\n")) {
            content = content.replace("\n\n", StringUtils.EMPTY);
        }
        this.content = "问：\n" + request + "\n---------------------\n" + "答：\n" + content;
    }
}
