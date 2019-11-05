package com.sneaklife.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "opera_sb")
public class OperaSb extends CommonEntity {
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    private String id;

    @Column(name = "type")
    private String type;

    @Column(name = "text")
    private String text;

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

    public OperaSb() { super();}

    public OperaSb(String id, String type, String text, Integer isDel, Date createDate, Date updateDate, String code, String icon, String url, String menuId, Integer isShow) {
        this.id = id;
        this.type = type;
        this.text = text;
        this.isDel = isDel;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.code = code;
        this.icon = icon;
        this.url = url;
        this.menuId = menuId;
        this.isShow = isShow;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"text\":\"")
                .append(text).append('\"');
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
