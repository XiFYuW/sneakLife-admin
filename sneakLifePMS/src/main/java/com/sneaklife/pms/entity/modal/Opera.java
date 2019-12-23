package com.sneaklife.pms.entity.modal;

import com.sneaklife.pms.entity.OperaBo;
import com.sneaklife.pms.entity.OperaIn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Opera extends CommonEntity {

    private List<Map<String,Object>> sb = new ArrayList<>(0);

    private List<List<OperaIn>> in = new ArrayList<>(0);

    private List<List<OperaBo>> bo = new ArrayList<>(0);
}
