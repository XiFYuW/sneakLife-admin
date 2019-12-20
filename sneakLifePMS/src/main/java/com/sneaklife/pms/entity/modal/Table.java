package com.sneaklife.pms.entity.modal;

import com.sneaklife.pms.entity.Columns;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Table extends CommonEntity {

    private List<Columns> columns;

}
