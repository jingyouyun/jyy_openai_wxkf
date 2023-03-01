package cn.jingyouyun.openai.wxkf.entity.openai.dto;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2023/2/13
 */
@Data
public class OpenAiSendDTO {
    private String prompt;
    private String model = "text-davinci-003";
    private Integer max_tokens = 2048;
    private Integer temperature = 0;
    private Integer top_p = 1;
    private Integer frequency_penalty = 0;
    private double presence_penalty = 0.6;
    private List<String> stop = Lists.newArrayList("Human", " AI:");
}
