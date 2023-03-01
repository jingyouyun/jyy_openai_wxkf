package cn.jingyouyun.openai.wxkf.entity.wx.vo;

import lombok.Data;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2023/2/14
 */
@Data
public class AccessTokenVO extends WxBaseVO {

    private String access_token;

    private Long expires_in;
}
