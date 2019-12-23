package com.sneaklife.pms.entity;

import com.sneaklife.pms.entity.modal.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/11 9:28
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRole extends CommonEntity {

    private Integer id;

    private String userId;

    private String roleId;

    private Integer isDel;

    private Date createDate;

    private Date updateDate;

    private String userName;

    private String roleName;

    private String value;

    private String text;

}
