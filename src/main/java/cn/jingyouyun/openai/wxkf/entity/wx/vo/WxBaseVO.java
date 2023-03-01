package cn.jingyouyun.openai.wxkf.entity.wx.vo;

import lombok.Data;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2023/3/1
 */
@Data
public class WxBaseVO {
    /**
     * 编码
     */
    private Integer errcode;
    /**
     * 信息
     */
    private String errmsg;
}
