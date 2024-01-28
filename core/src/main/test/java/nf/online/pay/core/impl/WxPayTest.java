package nf.online.pay.core.impl;

import jakarta.annotation.Resource;
import nf.online.pay.core.IPayCore;
import nf.online.pay.core.enums.MchType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WxPayTest {

    @Resource
    private IPayCore wxPay;
    @Test
    void createOrder() {
        wxPay.createOrder(MchType.WX, "wx36e9bc0b339cc276", "1231234223354555", "测试商品", "oZGOF61avRKKMDMDZIgBnloYaXx0");
    }
}