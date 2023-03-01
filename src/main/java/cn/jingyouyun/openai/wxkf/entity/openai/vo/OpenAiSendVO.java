package cn.jingyouyun.openai.wxkf.entity.openai.vo;

import lombok.Data;

import java.util.List;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2023/2/13
 */
@Data
public class OpenAiSendVO {

    private String id;

    private String object;

    private String created;

    private String model;

    private List<OpenAiChoicesVO> choices;

    private OpenAiUsageVO usage;
}
