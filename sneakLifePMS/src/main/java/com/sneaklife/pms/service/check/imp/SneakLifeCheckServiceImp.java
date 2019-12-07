package com.sneaklife.pms.service.check.imp;

import com.sneaklife.pms.dao.system.authority.opera.OperaInMapper;
import com.sneaklife.pms.service.check.SneakLifeCheckService;
import com.sneaklife.ut.check.CheckState;
import com.sneaklife.ut.check.SneakLifeCheckStateContext;
import com.sneaklife.ut.exception.SneakLifeException;
import com.sneaklife.ut.exception.SneakLifeFailureException;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.iws.RespCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/7 11:19
 */
@Service
public class SneakLifeCheckServiceImp implements SneakLifeCheckService {

    @Autowired
    private OperaInMapper operaInMapper;

    @Override
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
}
