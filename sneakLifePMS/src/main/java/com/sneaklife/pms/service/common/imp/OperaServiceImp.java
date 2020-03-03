package com.sneaklife.pms.service.common.imp;

import com.sneaklife.config.SneakLifeSystemEnum;
import com.sneaklife.pms.dao.system.authority.opera.ColumnsMapper;
import com.sneaklife.pms.dao.system.authority.opera.OperaBoMapper;
import com.sneaklife.pms.dao.system.authority.opera.OperaInMapper;
import com.sneaklife.pms.dao.system.authority.opera.OperaSbMapper;
import com.sneaklife.pms.dao.system.authority.roleFunction.RoleFunctionMapper;
import com.sneaklife.pms.entity.Opera;
import com.sneaklife.pms.entity.Table;
import com.sneaklife.pms.entity.TableOpera;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.common.RedisService;
import com.sneaklife.ut.exception.SneakLifeFailureException;
import com.sneaklife.ut.interfaces.ResultParameter;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.iws.RespCode;
import com.sneaklife.ut.log.LogicalLogAn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author https://github.com/XiFYuW
 */
@Service
public class OperaServiceImp implements OperaService {

    private final ColumnsMapper columnsMapper;

    private final OperaInMapper operaInMapper;

    private final OperaSbMapper operaSbMapper;

    private final OperaBoMapper operaBoMapper;

    private final RedisService redisService;

    private final RoleFunctionMapper roleFunctionMapper;

    private AtomicInteger s = new AtomicInteger(1);

    @Autowired
    public OperaServiceImp(ColumnsMapper columnsMapper, OperaInMapper operaInMapper, OperaSbMapper operaSbMapper,
                           OperaBoMapper operaBoMapper, RedisService redisService, RoleFunctionMapper roleFunctionMapper) {
        this.columnsMapper = columnsMapper;
        this.operaInMapper = operaInMapper;
        this.operaSbMapper = operaSbMapper;
        this.operaBoMapper = operaBoMapper;
        this.redisService = redisService;
        this.roleFunctionMapper = roleFunctionMapper;
    }

    @Override
    @LogicalLogAn
    public TableOpera buildOperaBody(Map<String, Object> map, boolean is) throws Exception{
        List<String> userInfoList = redisService.getLoginUserOpera();
        List<Map<String,Object>> columnsList = getOperaBodyColumns(userInfoList, map);
        Table table = new Table(columnsList);
        List<Map<String,Object>> operaSbList = getOperaBodySb(userInfoList, map);
        List<Map<String,Object>> operaInList = getOperaBodyIn(userInfoList, map);
        List<Map<String,Object>> operaBoList = getOperaBodyBo(userInfoList, map);
        Opera opera = new Opera(operaSbList, displayOpera(operaInList, 2, is), displayOpera(operaBoList, 3, is));
        return new TableOpera(table,opera);
    }

    /**
     * 获取功能字段数据
     * @param userInfoList 登录用户功能
     * @param map 条件参数
     * @return 功能字段数据
     */
    private List<Map<String,Object>> getOperaBodyColumns(List<String> userInfoList, Map<String, Object> map){
        List<Map<String,Object>> columnsList = columnsMapper.findColumns(map);
        return columnsList.stream().filter(columns -> userInfoList.contains(String.valueOf(columns.get("id")))).collect(Collectors.toList());
    }

    /**
     * 获取功能按钮数据
     * @param userInfoList 登录用户功能
     * @param map 条件参数
     * @return 功能按钮数据
     */
    private List<Map<String,Object>> getOperaBodySb(List<String> userInfoList, Map<String, Object> map){
        List<Map<String,Object>> operaSbList = operaSbMapper.findOperaSb(map);
        return operaSbList.stream().filter(sb -> userInfoList.contains(sb.get("id").toString())).collect(Collectors.toList());
    }

    /**
     * 获取功能输入字段数据
     * @param userInfoList 登录用户功能
     * @param map 条件参数
     * @return 功能输入字段数据
     */
    private List<Map<String,Object>> getOperaBodyIn(List<String> userInfoList, Map<String, Object> map){
        List<Map<String,Object>> operaInList = operaInMapper.findOperaIn(map);
        return operaInList.stream().filter(in -> userInfoList.contains(String.valueOf(in.get("id")))).collect(Collectors.toList());
    }

    /**
     * 获取功能查询字段数据
     * @param userInfoList 登录用户功能
     * @param map 条件参数
     * @return 功能查询字段数据
     */
    private List<Map<String,Object>> getOperaBodyBo(List<String> userInfoList, Map<String, Object> map){
        List<Map<String,Object>> operaBoList = operaBoMapper.findOperaBo(map);
        return operaBoList.stream().filter(bo -> userInfoList.contains(String.valueOf(bo.get("id")))).collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Object>> buildSpecificRoleFunction(Map<String, Object> map) throws Exception {
        isOperaMenuId(map);
        List<String> spOperaList = redisService.getSpOpera(map);
        List<Map<String, Object>> operaColumnsTreeList = buildOperaColumnsTree(map, spOperaList);
        List<Map<String, Object>> operaSbTreeList = buildOperaSbTree(map, spOperaList);
        List<Map<String, Object>> operaInTreeList = buildOperaInTree(map, spOperaList);
        List<Map<String, Object>> operaBoTreeList = buildOperaBoTree(map, spOperaList);
        return Stream.of(operaColumnsTreeList, operaSbTreeList, operaInTreeList, operaBoTreeList).flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    /**
     * 检测是否保存所选择的功能
     * @param map 条件参数
     * @throws Exception 异常提示信息
     */
    private void isOperaMenuId(Map<String, Object> map) throws Exception {
        List<String> operaList = redisService.getOpera(map);
        String menuId = String.valueOf(map.get("menuId"));
        if(!operaList.contains(menuId)){
            throw new SneakLifeFailureException(IwsContext.respResultBody(RespCode.MSG_SAVE_CHECKED_OPERA.toValue(),
                    RespCode.MSG_SAVE_CHECKED_OPERA.toMsg()));
        }
    }

    /**
     * 构建功能字段树
     * @param map 条件参数
     * @param operaList 功能列表
     * @return 功能字段树数据
     */
    private List<Map<String, Object>> buildOperaColumnsTree(Map<String, Object> map, List<String> operaList) {
        List<Map<String,Object>> columnsList = columnsMapper.findColumns(map);
        return getData(operaList, columnsList, SneakLifeSystemEnum.OPERA_COLUMNS.toName(), SneakLifeSystemEnum.OPERA_COLUMNS_VALUE.toName(), columns -> {
            Map<String,Object> map1 = new HashMap<>();
            map1.put("id",  columns.get("id"));
            map1.put("textName",columns.get("title"));
            return map1;
        });
    }

    /**
     * 构建功能按钮
     * @param map 条件参数
     * @param operaList 功能列表
     * @return 功能按钮数据
     */
    private List<Map<String, Object>> buildOperaSbTree(Map<String, Object> map, List<String> operaList) {
        List<Map<String,Object>> operaSbList = operaSbMapper.findOperaSb(map);
        return getData(operaList, operaSbList, SneakLifeSystemEnum.OPERA_SB.toName(), SneakLifeSystemEnum.OPERA_SB_VALUE.toName(), operaSb -> {
            Map<String,Object> map1 = new HashMap<>();
            map1.put("id",  String.valueOf(operaSb.get("id")));
            map1.put("textName", String.valueOf(operaSb.get("codeName")));
            return map1;
        });
    }

    /**
     * 构建功能输入字段
     * @param map 条件参数
     * @param operaList 功能列表
     * @return 功能输入字段数据
     */
    private List<Map<String, Object>> buildOperaInTree(Map<String, Object> map, List<String> operaList){
        List<Map<String,Object>> operaInList = operaInMapper.findOperaIn(map);
        return getData(operaList, operaInList, SneakLifeSystemEnum.OPERA_IN.toName(), SneakLifeSystemEnum.OPERA_IN_VALUE.toName(), new ResultParameter<Map<String, Object>>() {
            @Override
            public Map<String, Object> getMap(Map<String, Object> map) {
                return defaultMap(map);
            }
        });
    }

    /**
     * 构建功能查询字段
     * @param map 条件参数
     * @param operaList 功能列表
     * @return 功能查询字段数据
     */
    private List<Map<String, Object>> buildOperaBoTree(Map<String, Object> map, List<String> operaList){
        List<Map<String, Object>> operaBoList = operaBoMapper.findOperaBo(map);
        return getData(operaList, operaBoList, SneakLifeSystemEnum.OPERA_BO.toName(), SneakLifeSystemEnum.OPERA_BO_VALUE.toName(), new ResultParameter<Map<String, Object>>() {
            @Override
            public Map<String, Object> getMap(Map<String, Object> map) {
                return defaultMap(map);
            }
        });
    }

    /**
     * 获取功能（‘columns’，‘operaSb’，‘operaIn’，‘operaBo’）数据
     * @param operaList 功能列表
     * @param list 数据库里已有的功能，此时还没有授权，只是存在于库里
     * @param treeViewId id
     * @param name 名称
     * @param resultParameter 类型转换Map接口
     * @param <T> 数据项类型
     * @return 功能数据
     */
    private <T> List<Map<String, Object>> getData(List<String> operaList, List<T> list, String treeViewId, String name, ResultParameter<T> resultParameter){
        AtomicBoolean ab = new AtomicBoolean(false);
        AtomicInteger pid = new AtomicInteger(s.getAndAdd(1));
        List<Map<String, Object>> data = list.stream().map(operaBo -> {
            Map<String,Object> map = resultParameter.getMap(operaBo);
            return getTreeMap(operaList, ab, pid, String.valueOf(map.get("id")), String.valueOf(map.get("textName")));
        }).collect(Collectors.toList());
        List<Map<String, Object>> temp = specificTitle(treeViewId, name, pid, ab);
        return Stream.of(temp, data).flatMap(Collection::stream).distinct().sorted(Comparator.comparing(x -> String.valueOf(x.get("id")))).collect(Collectors.toList());
    }

    /**
     * 获取树形的节点
     * @param operaList 功能列表
     * @param ab 是否勾选
     * @param pid 父序列
     * @param id 序列
     * @param textName 名称
     * @return 数据项
     */
    private Map<String, Object> getTreeMap(List<String> operaList, AtomicBoolean ab, AtomicInteger pid, String id, String textName) {
        if(operaList.contains(id)){
            ab.set(true);
            return buildOperaItem(id, textName, s.getAndIncrement(), pid.get(), true);
        }else {
            return buildOperaItem(id, textName, s.getAndIncrement(), pid.get(), false);
        }
    }

    /**
     * 具体功能的标题
     * @param treeViewId id
     * @param name 名称
     * @param ai pid
     * @param ab 是否勾选
     * @return list
     */
    private List<Map<String, Object>> specificTitle(String treeViewId, String name, AtomicInteger ai, AtomicBoolean ab){
        List<Map<String, Object>> list = new ArrayList<>();
        if(ab.get()){
            list.add(buildOperaItem(treeViewId, name, ai.get(), ai.get() - ai.get(), true));
        }else {
            list.add(buildOperaItem(treeViewId, name, ai.get(), ai.get() - ai.get(), false));
        }
        return list;
    }

    /**
     * 构建前端字段
     * @param treeViewId id
     * @param name 名称
     * @param id 序列
     * @param pid 父序列
     * @param check 是否勾选
     * @return 数据项
     */
    private Map<String,Object> buildOperaItem(String treeViewId, String name, int id, int pid, boolean check){
        Map<String,Object> item = new HashMap<>();
        item.put("check",check);
        item.put("name",name);
        item.put("treeViewId",treeViewId);
        item.put("id",id);
        item.put("pid",pid);
        return item;
    }

    @Override
    @LogicalLogAn
    public List<Map<String, Object>> getSelectsKyByMenuId(String menuId, String htmlType) {
        return operaInMapper.getSelectsKyByMenuId(menuId, htmlType);
    }

    /**
     * 处理前端显示的功能输入
     * @param list 数据列表
     * @param gn 步距
     * @param t 是否启用
     * @return List
     */
    private <E> List<List<E>> displayOpera(List<E> list, int gn, boolean t){
        List<List<E>> data = new LinkedList<>();
        if(t){
            data.add(list);
            return data;
        }
        // The processing measure is 2
        List<E> temp = new ArrayList<>();
        boolean odd = list.size() % gn != 0;
        for (int i = 1; i <= list.size(); i++) {
            E operaIn = list.get(i - 1);
            if(i % gn != 0){
                temp.add(operaIn);
            }else {
                temp.add(operaIn);
                data.add(temp);
                temp = new ArrayList<>();
            }
        }
        // Odd Numbers, let's do the last one
        if(odd){
            data.add(temp);
        }
        return data;
    }

    @Override
    public void updateSpecificRoleFunction(Map<String, Object> map) throws Exception {
        List<String> operaList = redisService.getSpOpera(map);
        List<String> checkedList = filterCheckedList(map);
        String roleId = String.valueOf(map.get("roleId"));
        List<String> noOperaList = checkedList.stream().filter(x -> !operaList.contains(x)).collect(Collectors.toList());
        if (!noOperaList.isEmpty()) {
            roleFunctionMapper.insertSpBatch(noOperaList, roleId);
        }
        List<String> noCheckedList = operaList.stream().filter(x -> !checkedList.contains(x)).collect(Collectors.toList());
        if (!noCheckedList.isEmpty()) {
            roleFunctionMapper.deleteSpBatch(noCheckedList, roleId);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void updateRoleFunction(Map<String, Object> map) throws Exception {
        List<String> operaList = redisService.getOpera(map);
        List<String> checkedList = (List<String>) map.get("up");
        String roleId = String.valueOf(map.get("roleId"));
        List<String> noOperaList = checkedList.stream().filter(x -> !operaList.contains(x)).collect(Collectors.toList());
        if (!noOperaList.isEmpty()) {
            roleFunctionMapper.insertBatch(noOperaList, roleId);
        }
        List<String> noCheckedList = operaList.stream().filter(x -> !checkedList.contains(x)).collect(Collectors.toList());
        if (!noCheckedList.isEmpty()) {
            roleFunctionMapper.deleteBatch(noCheckedList, roleId);
        }
    }

    /**
     * 过滤：‘SneakLifeSystemEnum.OPERA_COLUMNS’，‘SneakLifeSystemEnum.OPERA_SB’，
     * ‘SneakLifeSystemEnum.OPERA_IN’，‘SneakLifeSystemEnum.OPERA_BO’
     * @param map 条件参数
     * @return List<String>
     */
    @SuppressWarnings("unchecked")
    private List<String> filterCheckedList(Map<String, Object> map){
        List<String> checkedList = (List<String>) map.get("up");
        return checkedList.stream().filter(x ->
                !(Objects.equals(x, SneakLifeSystemEnum.OPERA_COLUMNS.toName()) ||
                Objects.equals(x, SneakLifeSystemEnum.OPERA_SB.toName()) ||
                Objects.equals(x, SneakLifeSystemEnum.OPERA_IN.toName()) ||
                Objects.equals(x, SneakLifeSystemEnum.OPERA_BO.toName()))
        ).collect(Collectors.toList());
    }
}
