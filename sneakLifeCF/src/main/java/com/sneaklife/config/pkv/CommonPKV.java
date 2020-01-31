package com.sneaklife.config.pkv;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/3 13:34
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component("commonPKV")
@Configuration
@ConfigurationProperties(prefix = "common")
public class CommonPKV {

     private Long tokenCacheTimes;
     private String tokenKey;
     private String serverUrl;
     private String serverPathYzm;
     private String filePathYzm;
     private String userKey;
     private Long userKeySessionTimes;
     private String userCacheDir;
     private String xxlJobPath;
}
