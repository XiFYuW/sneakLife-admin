package com.sneaklife.dao.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/25 10:17
 */
@Entity
@Table(name="log")
public class SneakLifeLog extends CommonEntity{

    @Id
    @GeneratedValue(generator="idGenerator")
    private Integer id;

    @Column(name = "is_del")
    private Integer isDel;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "log_text")
    private String logText;

    @Column(name = "log_ex")
    private String logEx;

    @Column(name = "log_in")
    private String logIn;

    @Column(name = "log_out")
    private String logOut;

    public SneakLifeLog() {
    }

    public SneakLifeLog(Integer isDel, Date createDate, Date updateDate, String logText, String logEx, String logIn, String logOut) {
        this.isDel = isDel;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.logText = logText;
        this.logEx = logEx;
        this.logIn = logIn;
        this.logOut = logOut;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getLogText() {
        return logText;
    }

    public void setLogText(String logText) {
        this.logText = logText;
    }

    public String getLogEx() {
        return logEx;
    }

    public void setLogEx(String logEx) {
        this.logEx = logEx;
    }

    public String getLogIn() {
        return logIn;
    }

    public void setLogIn(String logIn) {
        this.logIn = logIn;
    }

    public String getLogOut() {
        return logOut;
    }

    public void setLogOut(String logOut) {
        this.logOut = logOut;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"isDel\":")
                .append(isDel);
        sb.append(",\"createDate\":\"")
                .append(createDate).append('\"');
        sb.append(",\"updateDate\":\"")
                .append(updateDate).append('\"');
        sb.append(",\"logText\":\"")
                .append(logText).append('\"');
        sb.append(",\"logEx\":\"")
                .append(logEx).append('\"');
        sb.append(",\"logIn\":\"")
                .append(logIn).append('\"');
        sb.append(",\"logOut\":\"")
                .append(logOut).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
