package com.sneaklife.pms.entity;

import com.sneaklife.pms.entity.modal.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/6 14:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleFunction extends CommonEntity {

    private Long id;

    private String menuId;

    private String roleId;

    private Integer isDel;

    private Date createDate;

    private Date updateDate;
}
