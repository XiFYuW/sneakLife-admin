package com.sneaklife.ut.check;

import com.sneaklife.ut.exception.SneakLifeException;
import com.sneaklife.ut.exception.SneakLifeFailureException;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.iws.RespCode;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/7 11:43
 */
public class SneakLifeStateCheckISNOTNULL implements SneakLifeCheckState {

    @Override
    public void handle(String value, Map<String, Object> rule) throws SneakLifeFailureException {
        String textName = String.valueOf(rule.get("textName"));
        if (StringUtils.isEmpty(value)) {
            throw new SneakLifeFailureException(IwsContext.respResultBody(RespCode.MSG_IS_NOT_NULL.toValue(),
                    textName + RespCode.MSG_IS_NOT_NULL.toMsg()));
        }
    }
}
