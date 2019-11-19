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
 * @date 2019/11/17 11:14
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component("rsaPKV")
@Configuration
@ConfigurationProperties(prefix = "rsa")
public class RsaPKV {
    private String keyAlgorithm;
    private String signatureAlgorithm;
    private String signatureName;
    private int maxDecryptBlock;
    private int maxEncryptBlock;
    private String privateKey;
    private String publicKey;
}
