package cn.jingyouyun.openai.wxkf.service.wx;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2023/2/10
 */
public interface IWxService {

    /**
     * 获取微信Token
     * @return
     */
    String getAccessToken();

    /**
     * 调用微信POST请求
     * @param url
     * @param dto
     * @return
     */
    String postWxForObject(String url, String dto);

    /**
     * 调用微信GET请求
     * @param url
     * @return
     */
    String getWxForObject(String url);
}
