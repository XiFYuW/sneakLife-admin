package com.sneaklife.pms.entity;

import com.sneaklife.pms.entity.modal.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="system_menu")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SystemMenu extends CommonEntity {

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    private String id;

    @Column(name = "tab")
    private String tab;

    @Column(name = "name")
    private String name;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "data_url")
    private String dataUrl;

    @Column(name = "is_del")
    private Integer isDel;

    @Column(name = "pid")
    private String pid;

    @Column(name = "item_url")
    private String itemUrl;

    @Column(name = "page_url")
    private String pageUrl;

    @Transient
    private List<SystemMenu> son = new ArrayList<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"tab\":\"")
                .append(tab).append('\"');
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"createDate\":\"")
                .append(createDate).append('\"');
        sb.append(",\"updateDate\":\"")
                .append(updateDate).append('\"');
        sb.append(",\"dataUrl\":\"")
                .append(dataUrl).append('\"');
        sb.append(",\"isDel\":")
                .append(isDel);
        sb.append(",\"pid\":\"")
                .append(pid).append('\"');
        sb.append(",\"itemUrl\":\"")
                .append(itemUrl).append('\"');
        sb.append(",\"pageUrl\":\"")
                .append(pageUrl).append('\"');
        sb.append(",\"son\":")
                .append(son);
        sb.append('}');
        return sb.toString();
    }
}
