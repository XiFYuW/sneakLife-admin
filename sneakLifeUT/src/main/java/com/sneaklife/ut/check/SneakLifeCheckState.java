package com.sneaklife.ut.check;

import com.sneaklife.ut.exception.SneakLifeFailureException;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/7 11:42
 */
public interface SneakLifeCheckState {

    /**
     * 具体检测输入参数
     * @param value 输入参数
     * @param rule 规则数据
     * @throws SneakLifeFailureException
     */
    void handle(String value, Map<String, Object> rule) throws SneakLifeFailureException;

}
