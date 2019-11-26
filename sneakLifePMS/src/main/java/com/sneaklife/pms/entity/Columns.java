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
@Table(name = "columns")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Columns extends CommonEntity {

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    private String id;

    @Column(name = "field")
    private String field;

    @Column(name = "title")
    private String title;

    @Column(name = "is_del")
    private Integer isDel;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "align")
    private String align;

    @Column(name = "is_show")
    private Integer isShow;

    @Column(name = "menu_id")
    private String menuId;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"field\":\"")
                .append(field).append('\"');
        sb.append(",\"title\":\"")
                .append(title).append('\"');
        sb.append(",\"isDel\":")
                .append(isDel);
        sb.append(",\"createDate\":\"")
                .append(createDate).append('\"');
        sb.append(",\"updateDate\":\"")
                .append(updateDate).append('\"');
        sb.append(",\"align\":\"")
                .append(align).append('\"');
        sb.append(",\"isShow\":")
                .append(isShow);
        sb.append(",\"menuId\":\"")
                .append(menuId).append('\"');
        return sb.toString();
    }
}
