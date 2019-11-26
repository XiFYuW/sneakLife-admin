package com.sneaklife.pms.entity.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TableOpera extends CommonEntity {

    private Table table;

    private Opera opera;

}
