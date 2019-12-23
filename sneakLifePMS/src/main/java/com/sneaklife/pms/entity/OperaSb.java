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
public class OperaSb extends CommonEntity {

    private String id;

    private String type;

    private Integer isDel;

    private Date createDate;

    private Date updateDate;

    private String code;

    private String icon;

    private String url;

    private String menuId;

    private Integer isShow;
}
