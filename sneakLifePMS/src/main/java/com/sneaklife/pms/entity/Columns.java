package com.sneaklife.pms.entity;

import com.sneaklife.pms.entity.modal.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Columns extends CommonEntity {

    private String id;

    private String field;

    private String title;

    private Integer isDel;

    private Date createDate;

    private Date updateDate;

    private String align;

    private Integer isShow;

    private String menuId;
}
