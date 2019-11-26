package com.sneaklife.pms.entity;

import com.sneaklife.pms.entity.modal.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/6 14:30
 */
@Entity
@Table(name="role_function")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleFunction extends CommonEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="idGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "menu_id")
    private String menuId;

    @Column(name = "role_id")
    private String roleId;

    @Column(name = "is_del")
    private Integer isDel;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;
}
