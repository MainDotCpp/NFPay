package nf.online.pay.core.config;

import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WxPayServiceConfig {
    @Bean
    public WxPayService wxPayService() {
        return new WxPayServiceImpl();
    }
}
