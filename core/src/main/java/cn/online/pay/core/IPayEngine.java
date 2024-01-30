package cn.online.pay.core;


import cn.online.pay.api.pojo.CreateDTO;

public interface IPayEngine {
    Object createOrder(CreateDTO createDTO);

    Object callback(String appId, String xmlData);
}
