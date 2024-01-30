package cn.online.pay.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "nf.pay")
@Component
@Data
public class NFPayCoreConfig {
    private String wxPayCallbackUrl;
    private String aliPayCallbackUrl;
}
