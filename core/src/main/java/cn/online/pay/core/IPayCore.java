package cn.online.pay.core;


import cn.online.pay.pojo.CreateDTO;
import cn.online.pay.core.enums.MchType;
import lombok.SneakyThrows;

public interface IPayCore {
    Object createOrder(CreateDTO createDTO);

    Object callback(MchType mchType,String appId, String notifyData);

    @SneakyThrows
    Object closeOrder(MchType mchType, String appId, String outTradeNo);
}
