package nf.online.pay.service.config;

import jakarta.annotation.PostConstruct;
import nf.online.pay.service.service.PayService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;

@Configuration
public class PayConfig {


    @PostConstruct
    public void initMchConfig() {

    }
}
