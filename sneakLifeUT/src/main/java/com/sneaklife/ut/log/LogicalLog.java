package com.sneaklife.ut.log;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/8 13:29
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection="sneakLifeLogicalLogDB")
public class LogicalLog {

    private String sessionId;

    private Integer isDel = 0;

    private Date createDate;

    private String logText;

    private String logEx = "";

    private String logIn;

    private String logOut;
}
