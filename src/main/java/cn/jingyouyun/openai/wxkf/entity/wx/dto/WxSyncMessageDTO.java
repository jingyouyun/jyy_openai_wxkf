package cn.jingyouyun.openai.wxkf.entity.wx.dto;

import cn.jingyouyun.openai.wxkf.constants.GlobalConstant;
import lombok.Data;

/**
 * 描述：同步客服消息请求实体
 *
 * @author zhujingchun
 * @date 2023/2/15
 */
@Data
public class WxSyncMessageDTO  extends WxBaseDTO {

    /**
     * 开始位置
     */
    private String cursor;
    /**
     * token
     */
    private String token;
    /**
     * 分页条数 默认 100
     */
    private Integer limit = GlobalConstant.ONE_HUNDRED;
}
