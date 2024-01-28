package nf.online.pay.core.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import nf.online.pay.core.IPayCore;
import nf.online.pay.core.enums.MchType;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WxPay implements IPayCore {
    @Resource
    private WxPayService wxPayService;

    @Override
    public void createOrder(MchType mchType, String appId, String outTradeNo, String description, String openid) {
        log.info("[创建订单] 渠道=微信支付");
        wxPayService.switchover(appId);
        WxPayConfig config = wxPayService.getConfig();
        log.info("{}", config);
        try {
            WxPayUnifiedOrderV3Request request = new WxPayUnifiedOrderV3Request()
                    .setAmount(new WxPayUnifiedOrderV3Request.Amount().setTotal(1))
                    .setAppid(appId)
                    .setAttach(description)
                    .setNotifyUrl("https://leuan.top/api/v2/payNotify")
                    .setOutTradeNo(outTradeNo)
                    .setDescription(description)
                    .setPayer(new WxPayUnifiedOrderV3Request.Payer().setOpenid(openid);
            Object res = wxPayService.createOrderV3(TradeTypeEnum.JSAPI, request);
            ObjectMapper om = new ObjectMapper();
            // 转为json
            String json = om.writeValueAsString(res);
            log.info("[创建订单成功]️ 报文={}", json);
        } catch (WxPayException e) {
            log.error("[创建订单失败] ");
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
