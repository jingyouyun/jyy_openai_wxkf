package cn.jingyouyun.openai.wxkf.service.wx.impl;

import cn.jingyouyun.openai.wxkf.conf.cache.RedisUtil;
import cn.jingyouyun.openai.wxkf.conf.properties.WxKfProperties;
import cn.jingyouyun.openai.wxkf.constants.GlobalConstant;
import cn.jingyouyun.openai.wxkf.entity.wx.vo.AccessTokenVO;
import cn.jingyouyun.openai.wxkf.service.wx.IWxService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2023/2/10
 */
@Slf4j
public abstract class WxBaseService implements IWxService {

    @Resource
    RedisUtil redisUtil;
    @Autowired
    RedissonClient redisson;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    public WxKfProperties wxKfProperties;

    @Override
    public String getAccessToken() {
        // 加锁
        RLock lock = redisson.getLock(GlobalConstant.ACCESS_TOKEN_LOCK);
        lock.lock();
        try {
            Object tokenObj = redisUtil.get(GlobalConstant.ACCESS_TOKEN_KEY);
            if (Objects.nonNull(tokenObj)) {
                return String.valueOf(tokenObj);
            }
            AccessTokenVO accessToken = JSONObject.parseObject(getWxForObject(wxKfProperties.getGetAccessTokenUrl()), AccessTokenVO.class);
            if (Objects.isNull(accessToken)) {
                return null;
            }
            if (StringUtils.isNotBlank(accessToken.getAccess_token())) {
                redisUtil.set(GlobalConstant.ACCESS_TOKEN_KEY, accessToken.getAccess_token(), accessToken.getExpires_in() - 120);
            }
            return accessToken.getAccess_token();
        } catch (Exception e) {
            log.error("获取 token 异常", e);
        } finally {
            lock.unlock();
        }
        return null;
    }

    @Override
    public String postWxForObject(String url, String dto) {
        log.info("发送微信请求 [URL:{}] | [请求参数:{}]", url, dto);
        String vo = restTemplate.postForObject(url, JSONObject.parseObject(dto), String.class);
        log.info("发送微信请求结果 [URL:{}] | [请求参数:{}] | [返回结果:{}]", url, dto, vo);
        return vo;
    }

    @Override
    public String getWxForObject(String url) {
        log.info("发送微信请求 [URL:{}]", url);
        String vo = restTemplate.getForObject(url, String.class);
        log.info("发送微信请求结果 [URL:{}] | [返回结果:{}]", url, vo);
        return vo;
    }
}
