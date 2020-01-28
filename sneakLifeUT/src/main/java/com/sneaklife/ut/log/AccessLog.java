package com.sneaklife.ut.log;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/1/28 14:02
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection="sneakLifeAccessLogDB")
public class AccessLog {

    private String ip;

    private String browser;

    private String address;

    private String accessInterface;

    private long accessTime;

    private String logEx = "";

    private Integer isDel = 0;

    private Date createDate;
}
