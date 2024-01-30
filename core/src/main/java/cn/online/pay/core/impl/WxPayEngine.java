package cn.online.pay.core.impl;

import cn.online.pay.core.IPayEngine;
import cn.online.pay.core.config.NFPayCoreConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyV3Result;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import cn.online.pay.api.pojo.CreateDTO;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WxPayEngine implements IPayEngine {

    @Resource
    private HttpServletRequest request; //自动注入request


    @Resource
    private NFPayCoreConfig nfPayCoreConfig;

    @Resource
    private WxPayService wxPayService;

    @Override
    public Object createOrder(CreateDTO createDTO) {
        log.info("[创建订单] 渠道=微信支付");
        if (!wxPayService.switchover(createDTO.getAppId())) {
            log.info("[创建订单] 切换微信支付配置失败 appId={}", createDTO.getAppId());
            throw new RuntimeException("切换微信支付配置失败");
        }
        WxPayConfig config = wxPayService.getConfig();
        log.info("{}", config);
        try {
            WxPayUnifiedOrderV3Request request = new WxPayUnifiedOrderV3Request()
                    .setAmount(new WxPayUnifiedOrderV3Request.Amount().setTotal(createDTO.getTotal()))
                    .setAppid(createDTO.getAppId())
                    .setAttach(createDTO.getAttach())
                    .setNotifyUrl(nfPayCoreConfig.getWxPayCallbackUrl() + "/" + createDTO.getAppId())
                    .setOutTradeNo(createDTO.getOutTradeNo())
                    .setDescription(createDTO.getDescription())
                    .setPayer(new WxPayUnifiedOrderV3Request.Payer().setOpenid(createDTO.getOpenid()));
            Object res = wxPayService.createOrderV3(createDTO.getTradeType(), request);
            ObjectMapper om = new ObjectMapper();
            // 转为json
            String json = om.writeValueAsString(res);
            log.info("[创建订单成功]️ 报文={}", json);
            return res;
        } catch (WxPayException e) {
            log.error("[创建订单失败] ");
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object callback(String appId, String xmlData) {
        WxPayOrderNotifyV3Result wxPayOrderNotifyResult;
        SignatureHeader signatureHeader = new SignatureHeader();
        signatureHeader.setNonce(request.getHeader("Wechatpay-Nonce"));
        signatureHeader.setTimeStamp(request.getHeader("Wechatpay-Timestamp"));
        signatureHeader.setSerial(request.getHeader("Wechatpay-Serial"));
        signatureHeader.setSignature(request.getHeader("Wechatpay-Signature"));
        log.info("[微信支付回调] appId={} 报文={} 请求头={}", appId, xmlData, signatureHeader);

        boolean switchover = wxPayService.switchover(appId);
        if (!switchover) {
            throw new RuntimeException("切换微信支付配置失败");
        }
        try {
            wxPayOrderNotifyResult = wxPayService.parseOrderNotifyV3Result(xmlData, signatureHeader);
        } catch (WxPayException e) {
            throw new RuntimeException(e);
        }
        return wxPayOrderNotifyResult;
    }
}
