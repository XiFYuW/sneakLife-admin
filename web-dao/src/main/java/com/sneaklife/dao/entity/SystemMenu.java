package com.sneaklife.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="system_menu")
public class SystemMenu extends CommonEntity{

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
//    @GeneratedValue(generator="idGenerator")
    private String id;

    @Column(name = "tab")
    private String tab;

    @Column(name = "type")
    private String type;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "data_url")
    private String dataUrl;

    @Column(name = "page_url")
    private String pageUrl;

    @Column(name = "is_del")
    private Integer isDel;

    @Column(name = "pid")
    private String pid;

    @Column(name = "item_url")
    private String itemUrl;

    @Transient
    private List<SystemMenu> son = new ArrayList<>();

    public SystemMenu(){ super();}

    public SystemMenu(String id, String tab, String type, Date createDate, Date updateDate, String dataUrl, String pageUrl, Integer isDel, String pid, String itemUrl, List<SystemMenu> son) {
        this.id = id;
        this.tab = tab;
        this.type = type;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.dataUrl = dataUrl;
        this.pageUrl = pageUrl;
        this.isDel = isDel;
        this.pid = pid;
        this.itemUrl = itemUrl;
        this.son = son;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String struUrl) {
        this.pageUrl = pageUrl;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<SystemMenu> getSon() {
        return son;
    }

    public void setSon(List<SystemMenu> son) {
        this.son = son;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"tab\":\"")
                .append(tab).append('\"');
        sb.append(",\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"createDate\":\"")
                .append(createDate).append('\"');
        sb.append(",\"updateDate\":\"")
                .append(updateDate).append('\"');
        sb.append(",\"dataUrl\":\"")
                .append(dataUrl).append('\"');
        sb.append(",\"pageUrl\":\"")
                .append(pageUrl).append('\"');
        sb.append(",\"isDel\":")
                .append(isDel);
        sb.append(",\"pid\":\"")
                .append(pid).append('\"');
        sb.append(",\"itemUrl\":\"")
                .append(itemUrl).append('\"');
        sb.append(",\"son\":")
                .append(son);
        sb.append('}');
        return sb.toString();
    }
}
