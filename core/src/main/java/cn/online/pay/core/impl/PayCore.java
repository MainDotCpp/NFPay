package cn.online.pay.core.impl;

import cn.online.pay.api.pojo.CreateDTO;
import cn.online.pay.core.IPayCore;
import cn.online.pay.core.IPayEngine;
import cn.online.pay.core.enums.MchType;
import jakarta.annotation.Resource;
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


}
