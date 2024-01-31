package cn.online.pay.core.impl;

import cn.online.pay.api.pojo.CreateDTO;
import cn.online.pay.core.IPayEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AliPayEngine implements IPayEngine {
    @Override
    public Object createOrder(CreateDTO createDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object callback(String appId, String xmlData) {
        return null;
    }

    @Override
    public Object closeOrder(String appId, String outTradeNo) {
        return null;
    }

    @Override
    public Object refund(String appId, String outTradeNo, Integer refundFee) {
        return null;
    }
}
