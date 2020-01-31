package com.sneaklife.ut.check;

import com.sneaklife.ut.date.DateUtil;
import com.sneaklife.ut.exception.SneakLifeFailureException;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.iws.RespCode;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/1/30 11:08
 */
public class SneakLifeStateCheckDATERANGE implements SneakLifeCheckState{

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Override
    public void handle(String value, Map<String, Object> rule) throws SneakLifeFailureException {

    }

    @Override
    public Map<String,Object> internal(String value) throws SneakLifeFailureException {
        String[] values = value.split(",");
        if (values.length > 2) {
            throw new SneakLifeFailureException(IwsContext.respResultBody(RespCode.MSG_DATE_RANGE_COUNT_ERR.toValue(), RespCode.MSG_DATE_RANGE_COUNT_ERR.toMsg()));
        }
        LocalDateTime start = DateUtil.strToLocalDateTime(values[0], PATTERN);
        LocalDateTime end = DateUtil.strToLocalDateTime(values[1], PATTERN);
        System.out.println(start.compareTo(end));
        if (start.isAfter(end)) {
            throw new SneakLifeFailureException(IwsContext.respResultBody(RespCode.MSG_DATE_RANGE_ERR.toValue(), RespCode.MSG_DATE_RANGE_ERR.toMsg()));
        }
        Map<String,Object> map = new HashMap<>();
        map.put("startTime", start);
        map.put("endTime", end);
        return map;
    }
}
