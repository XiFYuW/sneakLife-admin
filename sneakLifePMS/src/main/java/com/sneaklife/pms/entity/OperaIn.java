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
@Table(name = "opera_in")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OperaIn extends CommonEntity {
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    private String id;

    @Column(name = "text_name")
    private String textName;

    @Column(name = "html_type")
    private String htmlType;

    @Column(name = "is_del")
    private Integer isDel;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "field")
    private String field;

    @Column(name = "is_show")
    private Integer isShow;

    @Column(name = "menu_id")
    private String menuId;

    @Column(name = "rule")
    private String rule;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"textName\":\"")
                .append(textName).append('\"');
        sb.append(",\"htmlType\":\"")
                .append(htmlType).append('\"');
        sb.append(",\"isDel\":")
                .append(isDel);
        sb.append(",\"createDate\":\"")
                .append(createDate).append('\"');
        sb.append(",\"updateDate\":\"")
                .append(updateDate).append('\"');
        sb.append(",\"field\":\"")
                .append(field).append('\"');
        sb.append(",\"isShow\":")
                .append(isShow);
        sb.append(",\"menuId\":\"")
                .append(menuId).append('\"');
        sb.append(",\"rule\":\"")
                .append(rule).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
