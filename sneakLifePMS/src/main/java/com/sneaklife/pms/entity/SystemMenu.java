package com.sneaklife.pms.entity;

import com.sneaklife.pms.entity.modal.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemMenu extends CommonEntity {

    private String id;

    private String tab;

    private String name;

    private Date createDate;

    private Date updateDate;

    private String dataUrl;

    private Integer isDel;

    private String pid;

    private String itemUrl;

    private String pageUrl;

    private List<SystemMenu> son = new ArrayList<>();

}
