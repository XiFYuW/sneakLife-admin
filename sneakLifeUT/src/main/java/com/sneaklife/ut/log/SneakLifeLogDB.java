package com.sneaklife.ut.log;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/8 13:29
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection="sneakLifeLogDB")
public class SneakLifeLogDB {

    private String sessionId;

    private Integer isDel = 0;

    private Date createDate;

    private String logText;

    private String logEx;

    private String logIn;

    private String logOut;

    private String meModifier;

    private String meReturnType;

    private String meDeclaring;

    private String meName;

    private String meParameterType;

    private String meExceptionType;
}
