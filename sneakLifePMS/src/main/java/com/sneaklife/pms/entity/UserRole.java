package com.sneaklife.pms.entity;

import com.sneaklife.pms.entity.modal.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/11 9:28
 */
@Entity
@Table(name="user_role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
