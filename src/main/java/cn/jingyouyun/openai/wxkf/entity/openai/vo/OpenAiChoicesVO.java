package cn.jingyouyun.openai.wxkf.entity.openai.vo;

import lombok.Data;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2023/2/13
 */
@Data
public class OpenAiChoicesVO {

    private String text;
    private Integer index;
    private String logprobs;
    private String finish_reason;
}
