package cn.online.pay.client;

import lombok.extern.slf4j.Slf4j;
import cn.online.pay.api.PayService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(NFPayClientConfig.class) // 加载配置参数类
public class PayClientConfig {
    public PayClientConfig() {
        log.info("初始化支付客户端配置===================================");
    }

    @Bean
    public PayService payService(NFPayClientConfig nfPayClientConfig) {
        log.info("初始化支付客户端===================================");
        PayClient client = new PayClient();
        client.setConfig(nfPayClientConfig);
        return client;
    }
}
