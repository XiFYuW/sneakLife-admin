package com.sneaklife.pms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "data_dictionary")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DataDictionary extends CommonEntity {

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    private String id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "value")
    @NotNull
    private String value;

    @Column(name = "type_id",insertable = false,updatable = false)
    @NotNull
    private Long typeId;

    @Column(name = "is_del")
    @NotNull
    private Integer isDel;

    @Column(name = "create_date")
    @NotNull
    private Date createDate;

    @Column(name = "update_date")
    @NotNull
    private Date updateDate;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private TypeDictionary typeDictionary;

    @Transient
    private String tempKey;
}
