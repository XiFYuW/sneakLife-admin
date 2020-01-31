package com.sneaklife.pms.service.system.job.imp;

import com.sneaklife.config.pkv.CommonPKV;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.CommonService;
import com.sneaklife.pms.service.system.job.SystemJobService;
import com.sneaklife.ut.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/1/31 17:19
 */
@Service
public class SystemJobServiceImp extends CommonService implements SystemJobService {

    private final CommonPKV commonPKV;

    @Autowired
    public SystemJobServiceImp(CommonPKV commonPKV) {
        this.commonPKV = commonPKV;
    }


    @Override
    public void insert(Map<String, Object> map) throws Exception {

    }

    @Override
    public Map<String, Object> getData(Map<String, Object> map, PageInfo pageInfo) throws Exception {
        String xxlJob = commonPKV.getXxlJobPath();
        map.clear();
        map.put("xxlJobPath", xxlJob);
        return map;
    }

    @Override
    public TableOpera buildData(Map<String, Object> map) throws Exception {
        return null;
    }

    @Override
    public void update(Map<String, Object> map) throws Exception {

    }

    @Override
    public void delete(Map<String, Object> map) throws Exception {

    }
}
