package com.sneaklife.dao.system.opera;

import com.sneaklife.dao.entity.OperaIn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperaInMapper {

    List<OperaIn> findOperaByShow();
}
