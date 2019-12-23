package com.sneaklife.pms.entity;

import com.sneaklife.pms.entity.modal.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/11 9:32
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends CommonEntity {

    private Integer id;

    private String uuid;

    private String name;

    private Integer isDel;

    private Date createDate;

    private Date updateDate;

}
