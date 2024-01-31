package cn.online.pay.client;

import jakarta.annotation.Resource;
import lombok.Setter;
import cn.online.pay.api.PayService;
import cn.online.pay.api.pojo.CreateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Setter
public class PayClient implements PayService {
    @Resource
    private NFPayClientConfig config;

    @Override
    public void queryOrder() {

    }

    @Override
    public Object createOrder(CreateDTO dto) {
        RestTemplate restTemplate = new RestTemplate();
        log.info("[创建订单] 入参={}", dto);
        log.info(config.getHost() + "/pay/create");
        return restTemplate.postForObject(config.getHost() + "/pay/create", dto, String.class);
    }

    @Override
    public void pay() {

    }

    @Override
    public void refund() {

    }

    @Override
    public Object callback(String appId, Object data) {
        return null;
    }

    @Override
    public Object closeOrder(String outTradeNo) {
        return null;
    }

}
