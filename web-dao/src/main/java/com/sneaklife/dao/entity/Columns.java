package com.sneaklife.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "columns")
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

    public Columns() { super();}

    public Columns(String id, String field, String title, Integer isDel, Date createDate, Date updateDate, String align, Integer isShow, String menuId) {
        this.id = id;
        this.field = field;
        this.title = title;
        this.isDel = isDel;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.align = align;
        this.isShow = isShow;
        this.menuId = menuId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
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
