package cn.online.pay.service.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayConfig {

    @PostConstruct
    public void initMchConfig() {

    }
}
