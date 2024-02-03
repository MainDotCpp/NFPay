package cn.online.pay.gateway.controller;

import cn.online.pay.api.PayService;
import cn.online.pay.pojo.CreateDTO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PayController {

    @Resource
    private PayService payService;

    @RequestMapping("/create")
    public Object create(@RequestBody CreateDTO dto) {
        return payService.createOrder(dto);
    }

}
