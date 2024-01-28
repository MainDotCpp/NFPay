package nf.online.pay.core;

import nf.online.pay.core.enums.MchType;

public interface IPayCore {
    void createOrder(MchType mchType, String appId, String outTradeNo, String description, String openid);
}
