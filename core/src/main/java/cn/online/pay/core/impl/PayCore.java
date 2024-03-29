package cn.online.pay.core.impl;

import cn.online.pay.pojo.CreateDTO;
import cn.online.pay.core.IPayCore;
import cn.online.pay.core.IPayEngine;
import cn.online.pay.core.enums.MchType;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class PayCore implements IPayCore {

    @Resource
    private IPayEngine wxPayEngine;

    @Resource
    private IPayEngine aliPayEngine;


    @Override
    public Object createOrder(CreateDTO createDTO) {
        return switch (createDTO.getMchType()) {
            case WX -> wxPayEngine.createOrder(createDTO);
            case ALIPAY -> aliPayEngine.createOrder(createDTO);
            default -> throw new RuntimeException("不支持的支付类型");
        };
    }

    @Override
    public Object callback(MchType mchType, String appId, String notifyData) {
        return switch (mchType) {
            case WX -> wxPayEngine.callback(appId, notifyData);
            case ALIPAY -> aliPayEngine.callback(appId, notifyData);
            default -> throw new RuntimeException("不支持的支付类型");
        };
    }

    @SneakyThrows
    @Override
    public Object closeOrder(MchType mchType, String appId, String outTradeNo) {
        return switch (mchType) {
            case WX -> wxPayEngine.closeOrder(appId, outTradeNo);
            case ALIPAY -> aliPayEngine.closeOrder(appId, outTradeNo);
            default -> throw new RuntimeException("不支持的支付类型");
        };
    }


}
