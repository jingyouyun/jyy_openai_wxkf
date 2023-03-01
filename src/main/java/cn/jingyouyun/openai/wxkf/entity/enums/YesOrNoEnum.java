package cn.jingyouyun.openai.wxkf.entity.enums;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2023/2/15
 */
public enum YesOrNoEnum {
    /**
     * 删除、失效、无效状态
     */
    NO(0, "失效"),

    /**
     * 正常、有效状态
     */
    YES(1, "正常");

    private Integer status;

    private String desc;

    YesOrNoEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
