package com.sneaklife.dao.config.interceptor;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Interceptor;
import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JpaInterceptor extends EmptyInterceptor implements StatementInspector {

    private static Logger log = LoggerFactory.getLogger(JpaInterceptor.class);

    @Override
    public String inspect(String s) {
        log.info("【HIBERNATE】执行sql>>>【{}】",s);
        return s;
    }

}
