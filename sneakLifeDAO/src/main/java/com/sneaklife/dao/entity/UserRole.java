package com.sneaklife.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/11 9:28
 */
@Entity
@Table(name="user_role")
public class UserRole extends CommonEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @GenericGenerator(name="idGenerator", strategy="id")
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "role_id")
    private String roleId;

    @Column(name = "is_del")
    private Integer isDel;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Transient
    private String userName;

    @Transient
    private String roleName;

    @Transient
    private String value;

    @Transient
    private String text;

    public UserRole() { }

    public UserRole(String userId, String roleId, Integer isDel, Date createDate, Date updateDate, String userName, String roleName, String value, String text) {
        this.userId = userId;
        this.roleId = roleId;
        this.isDel = isDel;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.userName = userName;
        this.roleName = roleName;
        this.value = value;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"userId\":\"")
                .append(userId).append('\"');
        sb.append(",\"roleId\":\"")
                .append(roleId).append('\"');
        sb.append(",\"isDel\":")
                .append(isDel);
        sb.append(",\"createDate\":\"")
                .append(createDate).append('\"');
        sb.append(",\"updateDate\":\"")
                .append(updateDate).append('\"');
        sb.append(",\"userName\":\"")
                .append(userName).append('\"');
        sb.append(",\"roleName\":\"")
                .append(roleName).append('\"');
        sb.append(",\"value\":\"")
                .append(value).append('\"');
        sb.append(",\"text\":\"")
                .append(text).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
