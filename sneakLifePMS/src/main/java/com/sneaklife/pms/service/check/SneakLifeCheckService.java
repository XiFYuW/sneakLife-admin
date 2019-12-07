package com.sneaklife.pms.service.check;

import com.sneaklife.ut.exception.SneakLifeException;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/7 11:19
 */
public interface SneakLifeCheckService {

    /**
     * 检测输入参数
     * @param map 输入参数
     * @param checkInId 检测id
     * @throws SneakLifeException
     */
    void checkIn(Map<String,Object> map, String checkInId) throws SneakLifeException;
}
