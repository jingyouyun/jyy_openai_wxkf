package cn.jingyouyun.openai.wxkf.service.user;

import cn.jingyouyun.openai.wxkf.conf.cache.RedisUtil;
import cn.jingyouyun.openai.wxkf.constants.GlobalConstant;
import cn.jingyouyun.openai.wxkf.entity.enums.ExceptionMsgEnum;
import cn.jingyouyun.openai.wxkf.entity.wx.vo.WxSyncMessageVO;
import cn.jingyouyun.openai.wxkf.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2023/2/20
 */
@Slf4j
@Service
public class UserService {

    @Resource
    private RedisUtil redisUtil;
    @Autowired
    private RedissonClient redisson;

    public void subMessageNum(WxSyncMessageVO wxMessageVo) {
        RLock lock = redisson.getLock(GlobalConstant.ASYNC_SYNC_USER_INFO_LOCK + wxMessageVo.getExternal_userid());
        lock.lock();
        try {
            if (!redisUtil.hHasKey(GlobalConstant.USER_INFO_KEY, wxMessageVo.getExternal_userid())) {
                // 新增
                addUser(wxMessageVo.getExternal_userid());
            }
            int num = redisUtil.hdecr(GlobalConstant.USER_INFO_KEY, wxMessageVo.getExternal_userid(), GlobalConstant.ONE).intValue();
            if (num < GlobalConstant.ONE) {
                throw new SystemException(ExceptionMsgEnum.USER_MESSAGE_BALANCE_ERROR, wxMessageVo);
            }
        } catch (SystemException e) {
            throw e;
        } catch (Exception e) {
            log.error("减少用户可用消息数量异常 {}", wxMessageVo.getExternal_userid(), e);
            throw new SystemException(ExceptionMsgEnum.USER_MESSAGE_NUM_ERROR, wxMessageVo);
        } finally {
            lock.unlock();
        }
    }

    public void addMessageNum(WxSyncMessageVO wxMessageVo) {
        RLock lock = redisson.getLock(GlobalConstant.ASYNC_SYNC_USER_INFO_LOCK + wxMessageVo.getExternal_userid());
        lock.lock();
        try {
            if (!redisUtil.hHasKey(GlobalConstant.USER_INFO_KEY, wxMessageVo.getExternal_userid())) {
                // 新增
                addUser(wxMessageVo.getExternal_userid());
            } else {
                redisUtil.hincr(GlobalConstant.USER_INFO_KEY, wxMessageVo.getExternal_userid(), GlobalConstant.ONE);
            }
        } catch (Exception e) {
            log.error("增加用户可用消息数量异常 {}", wxMessageVo.getExternal_userid(), e);
            throw new SystemException(ExceptionMsgEnum.USER_MESSAGE_NUM_ERROR, wxMessageVo);
        } finally {
            lock.unlock();
        }
    }

    private void addUser(String userId) {
        redisUtil.hincr(GlobalConstant.USER_INFO_KEY, userId, GlobalConstant.TEN);
    }
}
