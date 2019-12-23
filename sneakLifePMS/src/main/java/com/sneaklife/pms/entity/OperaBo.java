package com.sneaklife.pms.entity;

import com.sneaklife.pms.entity.modal.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/19 20:24
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class OperaBo extends CommonEntity {

    private String id;

    private String textName;

    private String htmlType;

    private Integer isDel;

    private Date createDate;

    private Date updateDate;

    private String field;

    private Integer isShow;

    private String menuId;
}
