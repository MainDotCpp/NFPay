package cn.online.pay.service.controller;

import cn.online.pay.api.PayService;
import cn.online.pay.core.IPayEngine;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("callback")
@RestController
public class CallbackController {

    @Resource
    private IPayEngine wxPayEngine;

    @Resource
    private PayService payService;


    @RequestMapping("wx/{appId}")
    public Object wxPayCallback(
            @PathVariable String appId,
            @RequestBody String notifyData
    ) {
        log.info("[微信支付回调原始数据]: {}", notifyData);
        return payService.callback(appId, notifyData);
    }
}
