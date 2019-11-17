package com.sneaklife.pms.config.dao;

import org.hibernate.EmptyInterceptor;
import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SneakLifeJpaInterceptor extends EmptyInterceptor implements StatementInspector {

    private static Logger log = LoggerFactory.getLogger(SneakLifeJpaInterceptor.class);

    @Override
    public String inspect(String s) {
        log.info("【HIBERNATE】执行sql>>>【{}】",s);
        return s;
    }

}
