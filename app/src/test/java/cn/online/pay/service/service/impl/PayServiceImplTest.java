package cn.online.pay.service.service.impl;

import cn.hutool.core.lang.UUID;
import cn.online.pay.api.PayService;
import cn.online.pay.pojo.CreateDTO;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import jakarta.annotation.Resource;
import cn.online.pay.core.enums.MchType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PayServiceImplTest {

    @Resource
    private PayService payService;

    @Test
    void createOrder() {
        CreateDTO dto = new CreateDTO();
        dto.setMchType(MchType.WX);
        dto.setOpenid("oZGOF61avRKKMDMDZIgBnloYaXx0");
        dto.setDescription("测试商品");
        dto.setAppId("wx36e9bc0b339cc276");
        dto.setMachId("M1635456599");
        dto.setOutTradeNo(UUID.fastUUID().toString(true));
        dto.setNotifyUrl("https://leuan.top/api/v2/payNotify");
        payService.createOrder(dto);
    }


    @Test
    @DisplayName("native支付")
    void nativeCreateOrder() {
        CreateDTO dto = new CreateDTO();
        dto.setMchType(MchType.WX);
//        dto.setOpenid("oZGOF61avRKKMDMDZIgBnloYaXx0");
        dto.setDescription("测试商品");
        dto.setAppId("wx36e9bc0b339cc276");
        dto.setMachId("M1635456599");
        dto.setOutTradeNo(UUID.fastUUID().toString(true));
        dto.setNotifyUrl("https://leuan.top/api/v2/payNotify");
        dto.setTradeType(TradeTypeEnum.NATIVE);
        payService.createOrder(dto);
    }

}