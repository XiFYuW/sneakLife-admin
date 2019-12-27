package com.sneaklife.pms.service.common.imp;

import com.sneaklife.config.pkv.CommonPKV;
import com.sneaklife.pms.dao.system.authority.roleFunction.RoleFunctionMapper;
import com.sneaklife.pms.service.common.RedisService;
import com.sneaklife.ut.exception.SneakLifeFailureException;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.iws.RespCode;
import com.sneaklife.ut.log.SneakLifeAnLog;
import com.sneaklife.ut.servlet.SneakLifeServlet;
import com.sneaklife.ut.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/27 14:03
 */
@Service
@SuppressWarnings("unchecked")
public class RedisServiceImp implements RedisService {

    private final RedisTemplate redisTemplate;

    private final CommonPKV commonPKV;

    private final RoleFunctionMapper roleFunctionMapper;

    @Autowired
    public RedisServiceImp(RedisTemplate redisTemplate, CommonPKV commonPKV, RoleFunctionMapper roleFunctionMapper) {
        this.redisTemplate = redisTemplate;
        this.commonPKV = commonPKV;
        this.roleFunctionMapper = roleFunctionMapper;
    }

    @Override
    @SneakLifeAnLog
    public void setLoginUserInfo(Map<String, Object> map) throws Exception{
        String id = String.valueOf(map.get("id"));
        if(!StringUtil.isEmpty(id)){
            redisTemplate.opsForHash().put(id, commonPKV.getUserKey(), map);
            redisTemplate.expire(id, commonPKV.getUserKeySessionTimes(), TimeUnit.SECONDS);
        }
    }

    @Override
    @SneakLifeAnLog
    public List<String> getLoginUserOpera() throws Exception {
        String key = getCacheId();
        HashOperations hashOperations = redisTemplate.opsForHash();
        if(!hashOperations.hasKey(key, commonPKV.getUserKey())){
            throw new SneakLifeFailureException(IwsContext.respResultBody(RespCode.MSG_LOGIN_OVERDUE.toValue(),RespCode.MSG_LOGIN_OVERDUE.toMsg()));
        }
        Map<String, Object> map = (Map<String, Object>) hashOperations.get(key, commonPKV.getUserKey());
        String roleId = String.valueOf(Objects.requireNonNull(map).get("roleId"));
        List<Map<String,Object>> roleFunctionList = roleFunctionMapper.getGroupByRoleId(roleId);
        return roleFunctionList.stream().filter(map1 -> !StringUtil.isEmpty(String.valueOf(map1.get("menuId"))))
                .map(map1 -> (String) map1.get("menuId")).collect(Collectors.toList());
    }

    @Override
    public String getCacheId() throws Exception {
        final SneakLifeServlet sneakLifeServlet = IwsContext.getSneakLifeServletObject();
        return sneakLifeServlet.getCacheSessionId();
    }
}
