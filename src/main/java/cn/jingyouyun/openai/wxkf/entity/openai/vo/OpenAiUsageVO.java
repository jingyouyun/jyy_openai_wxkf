package cn.jingyouyun.openai.wxkf.entity.openai.vo;

import lombok.Data;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2023/2/13
 */
@Data
public class OpenAiUsageVO {
    private Integer prompt_tokens;
    private Integer completion_tokens;
    private Integer total_tokens;
}
