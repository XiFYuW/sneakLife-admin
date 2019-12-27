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
 * @date 2019/11/17 19:29
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component("aesPKV")
@Configuration
@ConfigurationProperties(prefix = "aes")
public class AesPKV {
    private String keyAlgorithm;
    private String signatureName;
    private String charset;
}
