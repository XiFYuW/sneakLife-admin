package com.sneaklife.pms.entity;

import com.sneaklife.pms.entity.modal.CommonEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/25 10:17
 */
@Entity
@Table(name="log")
public class SneakLifeLog extends CommonEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @GenericGenerator(name="idGenerator", strategy="id")
    @Column(name = "id")
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

    @Column(name = "me_modifier")
    private String meModifier;

    @Column(name = "me_return_type")
    private String meReturnType;

    @Column(name = "me_declaring")
    private String meDeclaring;

    @Column(name = "me_name")
    private String meName;

    @Column(name = "me_parameter_type")
    private String meParameterType;

    @Column(name = "me_exception_type")
    private String meExceptionType;

    public SneakLifeLog() {
    }

    public SneakLifeLog(Integer isDel, Date createDate, Date updateDate, String logText, String logEx, String logIn, String logOut, String meModifier, String meReturnType, String meDeclaring, String meName, String meParameterType, String meExceptionType) {
        this.isDel = isDel;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.logText = logText;
        this.logEx = logEx;
        this.logIn = logIn;
        this.logOut = logOut;
        this.meModifier = meModifier;
        this.meReturnType = meReturnType;
        this.meDeclaring = meDeclaring;
        this.meName = meName;
        this.meParameterType = meParameterType;
        this.meExceptionType = meExceptionType;
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

    public String getMeModifier() {
        return meModifier;
    }

    public void setMeModifier(String meModifier) {
        this.meModifier = meModifier;
    }

    public String getMeReturnType() {
        return meReturnType;
    }

    public void setMeReturnType(String meReturnType) {
        this.meReturnType = meReturnType;
    }

    public String getMeDeclaring() {
        return meDeclaring;
    }

    public void setMeDeclaring(String meDeclaring) {
        this.meDeclaring = meDeclaring;
    }

    public String getMeName() {
        return meName;
    }

    public void setMeName(String meName) {
        this.meName = meName;
    }

    public String getMeParameterType() {
        return meParameterType;
    }

    public void setMeParameterType(String meParameterType) {
        this.meParameterType = meParameterType;
    }

    public String getMeExceptionType() {
        return meExceptionType;
    }

    public void setMeExceptionType(String meExceptionType) {
        this.meExceptionType = meExceptionType;
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
        sb.append(",\"meModifier\":\"")
                .append(meModifier).append('\"');
        sb.append(",\"meReturnType\":\"")
                .append(meReturnType).append('\"');
        sb.append(",\"meDeclaring\":\"")
                .append(meDeclaring).append('\"');
        sb.append(",\"meName\":\"")
                .append(meName).append('\"');
        sb.append(",\"meParameterType\":\"")
                .append(meParameterType).append('\"');
        sb.append(",\"meExceptionType\":\"")
                .append(meExceptionType).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
