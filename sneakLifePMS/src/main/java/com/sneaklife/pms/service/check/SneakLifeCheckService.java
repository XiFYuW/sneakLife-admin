package com.sneaklife.pms.service.check;

import com.sneaklife.ut.exception.SneakLifeException;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/7 11:19
 */
public interface SneakLifeCheckService {

    /**
     * Test input parameters
     * @param map parameter
     * @param checkInId Detection of id
     * @throws SneakLifeException
     */
    void checkIn(Map<String,Object> map, String checkInId) throws SneakLifeException;

    /**
     * Detect login information
     * @param map parameter
     * @throws SneakLifeException
     */
    void checkLogin(Map<String,Object> map) throws SneakLifeException;
}
