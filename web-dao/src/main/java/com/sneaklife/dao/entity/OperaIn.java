package com.sneaklife.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "opera_in")
public class OperaIn extends CommonEntity {
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    private String id;

    @Column(name = "type")
    private String type;

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
    private String isShow;

    @Column(name = "menu_id")
    private String menuId;

    public OperaIn() { super();}

    public OperaIn(String id, String type, String textName, String htmlType, Integer isDel, Date createDate, Date updateDate, String field, String isShow, String menuId) {
        this.id = id;
        this.type = type;
        this.textName = textName;
        this.htmlType = htmlType;
        this.isDel = isDel;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.field = field;
        this.isShow = isShow;
        this.menuId = menuId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTextName() {
        return textName;
    }

    public void setTextName(String textName) {
        this.textName = textName;
    }

    public String getHtmlType() {
        return htmlType;
    }

    public void setHtmlType(String htmlType) {
        this.htmlType = htmlType;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"type\":\"")
                .append(type).append('\"');
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
        sb.append(",\"isShow\":\"")
                .append(isShow).append('\"');
        sb.append(",\"menuId\":\"")
                .append(menuId).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
