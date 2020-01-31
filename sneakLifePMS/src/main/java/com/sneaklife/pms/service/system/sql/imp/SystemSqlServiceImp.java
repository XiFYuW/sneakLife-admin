package com.sneaklife.pms.service.system.sql.imp;

import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.CommonService;
import com.sneaklife.pms.service.system.sql.SystemSqlService;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.page.PageInfo;
import com.sneaklife.ut.servlet.SneakLifeServlet;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/1/26 12:58
 */
@Service
public class SystemSqlServiceImp extends CommonService implements SystemSqlService {

    @Override
    public void insert(Map<String, Object> map) throws Exception {

    }

    @Override
    public Map<String, Object> getData(Map<String, Object> map, PageInfo pageInfo) throws Exception {
        SneakLifeServlet sneakLifeServlet = IwsContext.getSneakLifeServletObject();
        String serverPath = sneakLifeServlet.getServerPath();
        map.clear();
        map.put("serverPath", serverPath + "druid/");
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
