package com.sneaklife.pkv;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/11/18 12:51
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component("ailSmsPKV")
@Configuration
@ConfigurationProperties(prefix = "ail-sms")
public class AilSmsPKV {
    private long defaultConnectTimeout;
    private long defaultReadTimeout;
    private String accessKeyId;
    private String accessKeySecret;
    private String templateCode;
}
