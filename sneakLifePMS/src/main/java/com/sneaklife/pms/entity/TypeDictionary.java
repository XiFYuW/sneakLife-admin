package com.sneaklife.pms.entity;

import com.sneaklife.pms.entity.modal.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/11/23 12:01
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class TypeDictionary extends CommonEntity {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer isDel;

    @NotNull
    private Date createDate;

    @NotNull
    private Date updateDate;
}
