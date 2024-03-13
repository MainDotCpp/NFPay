package cn.online.pay.client;

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDubboConfig
@ConditionalOnProperty(prefix = "nf.pay", name = "host", havingValue = "true")
public class DubboConfig {
}
