package cn.online.pay.core;


import cn.online.pay.api.pojo.CreateDTO;
import com.github.binarywang.wxpay.exception.WxPayException;

public interface IPayEngine {
    Object createOrder(CreateDTO createDTO);

    Object callback(String appId, String xmlData);

    Object closeOrder(String appId, String outTradeNo) throws WxPayException;

    Object refund(String appId, String outTradeNo, Integer refundFee);
}
