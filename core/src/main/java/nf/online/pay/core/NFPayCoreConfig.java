package nf.online.pay.core;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class NFPayCoreConfig {

    @Resource
    private WxPayService wxPayService;

    public void initMchConfig(
            String mchId,
            String v3Key
    ) {
        WxPayConfig config = new WxPayConfig();
        config.setMchId(mchId);
        config.setApiV3Key(v3Key);
        wxPayService.addConfig(mchId, config);
    }
}
