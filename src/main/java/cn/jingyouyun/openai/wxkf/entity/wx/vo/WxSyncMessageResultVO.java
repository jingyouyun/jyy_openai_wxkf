package cn.jingyouyun.openai.wxkf.entity.wx.vo;

import lombok.Data;

import java.util.List;

/**
 * 描述：同步客服消息返回实体
 *
 * @author zhujingchun
 * @date 2023/2/15
 */
@Data
public class WxSyncMessageResultVO extends WxBaseVO {

    /**
     * 下次查询开始位置
     */
    private String next_cursor;
    /**
     * 消息列表
     */
    private List<WxSyncMessageVO> msg_list;
    /**
     * 是否还有消息 0否 1是
     */
    private Integer has_more;
}
