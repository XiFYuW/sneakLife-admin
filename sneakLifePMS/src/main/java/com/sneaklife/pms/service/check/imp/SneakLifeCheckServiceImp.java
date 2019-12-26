package com.sneaklife.pms.service.check.imp;

import com.sneaklife.pkv.CommonPKV;
import com.sneaklife.pms.config.SneakLifeSystemEnum;
import com.sneaklife.pms.dao.system.authority.opera.OperaInMapper;
import com.sneaklife.pms.dao.system.authority.user.UserMapper;
import com.sneaklife.pms.service.check.SneakLifeCheckService;
import com.sneaklife.ut.check.CheckState;
import com.sneaklife.ut.check.SneakLifeCheckStateContext;
import com.sneaklife.ut.exception.SneakLifeException;
import com.sneaklife.ut.exception.SneakLifeFailureException;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.iws.RespCode;
import com.sneaklife.ut.keyless.KeyLessContext;
import com.sneaklife.ut.log.SneakLifeAnLog;
import com.sneaklife.ut.spring.SpringContextUtil;
import com.sneaklife.ut.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/7 11:19
 */
@Service
public class SneakLifeCheckServiceImp implements SneakLifeCheckService {

    private final OperaInMapper operaInMapper;

    private final UserMapper userMapper;

    private final RedisTemplate redisTemplate;

    @Autowired
    public SneakLifeCheckServiceImp(OperaInMapper operaInMapper, UserMapper userMapper, RedisTemplate redisTemplate) {
        this.operaInMapper = operaInMapper;
        this.userMapper = userMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    @SneakLifeAnLog
    public void checkIn(Map<String,Object> map, String checkInId) throws SneakLifeException {
        List<Map<String,Object>> ruleList = operaInMapper.findByCheckInId(checkInId);
        for (Map<String,Object> rule : ruleList) {
            String strRule = String.valueOf(rule.get("rule")).toUpperCase();
            String field = String.valueOf(rule.get("field"));
            String fieldValue = (String) map.get(field);
            CheckState checkState;
            try {
                checkState = CheckState.valueOf(strRule);
            } catch (IllegalArgumentException e){
                e.printStackTrace();
                throw new SneakLifeFailureException(IwsContext.respResultBody(RespCode.MSG_NOT_RULE.toValue(),
                        strRule + RespCode.MSG_NOT_RULE.toMsg()));
            }
            SneakLifeCheckStateContext sneakLifeCheckStateContext = SneakLifeCheckStateContext.singleton(checkState);
            sneakLifeCheckStateContext.handle(fieldValue, rule);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    @SneakLifeAnLog
    public void checkLogin(Map<String, Object> map) throws SneakLifeException {
        if(!IwsContext.isNotNull(map)){
            throw new SneakLifeFailureException(IwsContext.respResultBody(RespCode.MSG_LOGIN_MISS.toValue(),RespCode.MSG_LOGIN_MISS.toMsg()));
        }
        String un = String.valueOf(map.get("un"));
        String pw = String.valueOf(map.get("pw"));
        if(StringUtil.isEmpty(un)){
            throw new SneakLifeFailureException(IwsContext.respResultBody(RespCode.MSG_LOGIN_USERNAME_NULL.toValue(),RespCode.MSG_LOGIN_USERNAME_NULL.toMsg()));
        }
        if(StringUtil.isEmpty(pw)){
            throw new SneakLifeFailureException(IwsContext.respResultBody(RespCode.MSG_LOGIN_PASSWORD_NULL.toValue(),RespCode.MSG_LOGIN_PASSWORD_NULL.toMsg()));
        }
        try {
            pw = SneakLifeSystemEnum.SNEAK_LIFE_NAME.toName() + "123456";
            String pwT = KeyLessContext.digest(pw, "md5");
            map.put("pw", pwT);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new SneakLifeFailureException(IwsContext.respResultBody(RespCode.MSG_LOGIN_EN_FAIL.toValue(),RespCode.MSG_LOGIN_EN_FAIL.toMsg()));
        }
        Map<String, Object> ex = userMapper.checkExist(map);
        if(!IwsContext.isNotNull(ex)){
            throw new SneakLifeFailureException(IwsContext.respResultBody(RespCode.MSG_USER_EXIST.toValue(),RespCode.MSG_USER_EXIST.toMsg()));
        }
        final CommonPKV commonPKV = SpringContextUtil.getBean(CommonPKV.class);
        String id = String.valueOf(map.get("id"));
        redisTemplate.opsForHash().put(id, commonPKV.getUserKey(), ex);
        redisTemplate.expire(id, commonPKV.getUserKeyOverTimes(), TimeUnit.SECONDS);
    }
}
