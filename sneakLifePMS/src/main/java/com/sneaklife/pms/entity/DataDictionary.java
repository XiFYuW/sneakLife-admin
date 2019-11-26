package com.sneaklife.pms.entity;

import com.sneaklife.pms.entity.modal.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "data_dictionary")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DataDictionary extends CommonEntity {

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    private String id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "value")
    @NotNull
    private String value;

    @Column(name = "type_id")
    @NotNull
    private Long typeId;

    @Column(name = "is_del")
    @NotNull
    private Integer isDel;

    @Column(name = "create_date")
    @NotNull
    private Date createDate;

    @Column(name = "update_date")
    @NotNull
    private Date updateDate;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"value\":\"")
                .append(value).append('\"');
        sb.append(",\"typeId\":")
                .append(typeId);
        sb.append(",\"isDel\":")
                .append(isDel);
        sb.append(",\"updateDate\":\"")
                .append(updateDate).append('\"');
        sb.append(",\"createDate\":\"")
                .append(createDate).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
