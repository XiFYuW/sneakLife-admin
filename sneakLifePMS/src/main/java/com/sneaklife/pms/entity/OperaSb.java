package com.sneaklife.pms.entity;

import com.sneaklife.pms.entity.modal.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "opera_sb")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OperaSb extends CommonEntity {
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    private String id;

    @Column(name = "type")
    private String type;

    @Column(name = "is_del")
    private Integer isDel;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "code")
    private String code;

    @Column(name = "icon")
    private String icon;

    @Column(name = "url")
    private String url;

    @Column(name = "menu_id")
    private String menuId;

    @Column(name = "is_show")
    private Integer isShow;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"isDel\":")
                .append(isDel);
        sb.append(",\"createDate\":\"")
                .append(createDate).append('\"');
        sb.append(",\"updateDate\":\"")
                .append(updateDate).append('\"');
        sb.append(",\"code\":\"")
                .append(code).append('\"');
        sb.append(",\"icon\":\"")
                .append(icon).append('\"');
        sb.append(",\"url\":\"")
                .append(url).append('\"');
        sb.append(",\"menuId\":\"")
                .append(menuId).append('\"');
        sb.append(",\"isShow\":")
                .append(isShow);
        sb.append('}');
        return sb.toString();
    }
}
