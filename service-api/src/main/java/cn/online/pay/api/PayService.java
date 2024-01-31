package cn.online.pay.api;


import cn.online.pay.api.pojo.CreateDTO;

public interface PayService {

    void queryOrder();

    Object createOrder(CreateDTO dto);

    void pay();

    void refund();

    Object callback(String appId,Object data);

    Object closeOrder( String outTradeNo);
}
