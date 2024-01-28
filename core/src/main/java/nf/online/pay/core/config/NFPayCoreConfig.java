package nf.online.pay.core.config;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import jakarta.annotation.Resource;
import lombok.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class NFPayCoreConfig {

    @Resource
    private WxPayService wxPayService;

    @Builder
    public static class InitMchConfig {
        private String type;
        private String mchId;
        private String key;
        private String v3Key;
        private String appId;
    }

    public void initMchConfig(
            String type,
            InitMchConfig initMchConfig
    ) {
        WxPayConfig config = new WxPayConfig();
        config.setSignType("MD5");
        config.setAppId(initMchConfig.appId);
        config.setMchId(initMchConfig.mchId);
        config.setMchKey(initMchConfig.key);
        config.setApiV3Key(initMchConfig.v3Key);
        config.setPrivateKeyPath("/Users/yangyang/Documents/1640579342_20240126_cert/apiclient_key.pem");
        config.setPrivateCertPath("/Users/yangyang/Documents/1640579342_20240126_cert/apiclient_cert.pem");
        wxPayService.addConfig(initMchConfig.appId, config);
    }
}
