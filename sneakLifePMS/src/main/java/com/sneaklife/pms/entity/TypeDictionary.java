package com.sneaklife.pms.entity;

import com.sneaklife.pms.entity.modal.CommonEntity;
import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/11/23 12:01
 */
@Entity
@Table(name = "type_dictionary")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Proxy(lazy = false)
public class TypeDictionary extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "idGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "is_del")
    @NotNull
    private Integer isDel;

    @Column(name = "create_date")
    @NotNull
    private Date createDate;

    @Column(name = "update_date")
    @NotNull
    private Date updateDate;
}
