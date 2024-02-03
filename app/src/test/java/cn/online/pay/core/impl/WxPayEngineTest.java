package cn.online.pay.core.impl;

import cn.hutool.core.lang.UUID;
import cn.online.pay.api.PayService;
import cn.online.pay.core.enums.MchType;
import cn.online.pay.pojo.CreateDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WxPayEngineTest {

    @Autowired
    private PayService payService;

    @Test
    void createOrder() {
        CreateDTO dto = new CreateDTO();
        dto.setMchType(MchType.WX);
        dto.setOpenid("oZGOF61avRKKMDMDZIgBnloYaXx0");
        dto.setDescription("测试商品");
        dto.setAppId("wx36e9bc0b339cc276");
        dto.setOutTradeNo(UUID.fastUUID().toString(true));
        dto.setNotifyUrl("https://leuan.top/api/v2/payNotify");
        payService.createOrder(dto);
    }
}