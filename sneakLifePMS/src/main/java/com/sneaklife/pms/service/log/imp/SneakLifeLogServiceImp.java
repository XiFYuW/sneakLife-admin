package com.sneaklife.pms.service.log.imp;

import com.sneaklife.pms.dao.log.SneakLifeLogJpa;
import com.sneaklife.pms.entity.SneakLifeLog;
import com.sneaklife.pms.service.log.SneakLifeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/25 10:11
 */
@Service
public class SneakLifeLogServiceImp implements SneakLifeLogService {

    @Autowired
    private SneakLifeLogJpa sneakLifeLogJpa;

    @Override
    @Async
    public void insertLog(SneakLifeLog sneakLifeLog) throws Exception {
        sneakLifeLogJpa.save(sneakLifeLog);
    }
}
