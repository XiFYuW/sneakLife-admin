package com.sneaklife.service.system;

import com.sneaklife.dao.entity.modal.TableOpera;

import java.util.List;
import java.util.Map;

public interface OperaService {

    TableOpera buildOperaBody(Map<String,Object> map, boolean is);

    List<Map<String,Object>> buildOperaTreeGrid(Map<String,Object> map);

    void clean();
}
