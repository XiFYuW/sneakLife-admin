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
public class OperaIn extends CommonEntity {

    private String id;

    private String textName;

    private String htmlType;

    private Integer isDel;

    private Date createDate;

    private Date updateDate;

    private String field;

    private Integer isShow;

    private String menuId;

    private String rule;
}
