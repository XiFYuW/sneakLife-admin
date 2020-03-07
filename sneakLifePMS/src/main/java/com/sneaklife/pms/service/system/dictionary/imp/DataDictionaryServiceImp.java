package com.sneaklife.pms.service.system.dictionary.imp;

import com.sneaklife.config.cache.SneakLifeAuthorityManagementCacheEvict;
import com.sneaklife.pms.dao.system.dictionary.DataDictionaryMapper;
import com.sneaklife.pms.dao.system.dictionary.TypeDictionaryMapper;
import com.sneaklife.pms.entity.TableOpera;
import com.sneaklife.pms.service.common.CommonService;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.dictionary.DataDictionaryService;
import com.sneaklife.ut.excel.ExcelTool;
import com.sneaklife.ut.exception.SneakLifeSuccessfulException;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.log.LogicalLogAn;
import com.sneaklife.ut.page.PageInfo;
import com.sneaklife.ut.date.DateUtil;
import com.sneaklife.ut.servlet.SneakLifeServlet;
import com.sneaklife.ut.string.StringUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author https://github.com/XiFYuW
 */
@Service
@CacheConfig(cacheNames = "SneakLifeAuthorityManagement")
public class DataDictionaryServiceImp extends CommonService implements DataDictionaryService {

    private final DataDictionaryMapper dataDictionaryMapper;

    private final TypeDictionaryMapper typeDictionaryMapper;

    private final OperaService operaService;

    public DataDictionaryServiceImp(DataDictionaryMapper dataDictionaryMapper, TypeDictionaryMapper typeDictionaryMapper, OperaService operaService) {
        this.dataDictionaryMapper = dataDictionaryMapper;
        this.typeDictionaryMapper = typeDictionaryMapper;
        this.operaService = operaService;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @LogicalLogAn
    public Map<String,Object> getData(Map<String, Object> map, PageInfo pageInfo) throws Exception {
        return super.findAllPage(dataDictionaryMapper, map, pageInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable
    @LogicalLogAn
    public TableOpera buildData(Map<String, Object> map) throws Exception{
        return operaService.buildOperaBody(map, false);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @LogicalLogAn
    public void insert(Map<String, Object> map) throws Exception {
        String value = String.valueOf(map.get("value"));
        if (StringUtil.isEmpty(value)) {
            map.put("value", DateUtil.getMilli());
        }
        insert(dataDictionaryMapper, map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @LogicalLogAn
    public void update(Map<String, Object> map) throws Exception {
        update(dataDictionaryMapper, map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @LogicalLogAn
    public void delete(Map<String, Object> map) throws Exception {
        delete(dataDictionaryMapper, map);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @LogicalLogAn
    public Map<String, Object> getByType(Map<String, Object> map) {
        String express = String.valueOf(map.get("express"));
        String menuId = String.valueOf(map.get("menuId"));
        Map<String, Object> item = buildSelectKey(express, menuId);
        map.clear();
        map.put("title", "select data");
        map.put("data", item);
        return map;
    }

    @Override
    public void exportDataDictionary() throws Exception{
        List<Map<String,Object>> data = new ArrayList<>(dataDictionaryMapper.findAllPage(new HashMap<>()));
        SneakLifeServlet sneakLifeServlet = IwsContext.getSneakLifeServletObject();
        sneakLifeServlet.setExcelHeaders("数据字典详情表.xlsx");
        OutputStream outputStream = sneakLifeServlet.getOutputStream();
        ExcelTool tool = new ExcelTool(outputStream);
        tool.createSheet("xxx", 1502, data.size());
        tool.setColumnView(new int[] {0, 1, 2, 3, 4}, new int[] {30, 30, 30, 30, 30});
        tool.fillTitle(data,"数据字典详情表",2);
        tool.fillHeader(data,3);
        tool.fillcontent(data,4);
        tool.close();
        outputStream.flush();
        outputStream.close();
    }

    private Map<String, Object> buildSelectKey(String express, String menuId) {
        Map<String, Object> data = new HashMap<>();
        List<Map<String, Object>> selectKey = operaService.getSelectsKyByMenuId(menuId, "selects");
        String[] types = express.split(",");
        for (String type : types) {
            String[] match = type.split(":");
            for (Map<String, Object> select : selectKey) {
                if (match[0].equals(String.valueOf(select.get("id")))) {
                    String field = String.valueOf(select.get("field"));
                    String va = match[1];
                    if ("*".equals(va)) {
                        List<Map<String, Object>> item = typeDictionaryMapper.getIdName();
                        data.put(field, item);
                    } else {
                        List<Map<String, Object>> item = dataDictionaryMapper.getNameValueByTypeId(Long.parseLong(va));
                        data.put(field, item);
                    }
                }
            }
        }
        return data;
    }

}
