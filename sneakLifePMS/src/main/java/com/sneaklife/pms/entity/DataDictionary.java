package com.sneaklife.pms.entity;

import com.sneaklife.pms.entity.modal.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class DataDictionary extends CommonEntity {

    private String id;

    @NotNull
    private String name;

    @NotNull
    private String value;

    @NotNull
    private Long typeId;

    @NotNull
    private Integer isDel;

    @NotNull
    private Date createDate;

    @NotNull
    private Date updateDate;
}
