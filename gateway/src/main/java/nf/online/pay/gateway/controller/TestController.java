package nf.online.pay.gateway.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nf.online.pay.service.service.PayService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final PayService wxPayService;

    @RequestMapping("/hello")
    public String hello() {
        log.info("[hello]");
        wxPayService.queryOrder();
        return "hello";
    }
}
