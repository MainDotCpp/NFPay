package nf.online.pay.core;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitRunner {

    private final WxPayService wxPayService;

    public InitRunner() {
        System.out.println("InitRunner");
        wxPayService.getservice
    }

    public void t() {
        WxPayConfig wxPayConfig = new WxPayConfig();
        WxPayUnifiedOrderV3Request request = new WxPayUnifiedOrderV3Request();
        request.setAmount();
    }
}
