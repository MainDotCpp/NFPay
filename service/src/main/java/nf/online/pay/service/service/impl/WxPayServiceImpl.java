package nf.online.pay.service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nf.online.pay.service.mapper.MachInfoMapper;
import nf.online.pay.service.service.IMachInfoService;
import nf.online.pay.service.service.PayService;
import org.springframework.stereotype.Component;

@Slf4j
@Component("nfWxPayService")
@RequiredArgsConstructor
public class WxPayServiceImpl implements PayService {

    private final IMachInfoService machInfoService;

    @Override
    public void queryOrder() {
    }

    @Override
    public void createOrder() {
    }

    @Override
    public void pay() {
    }

    @Override
    public void refund() {

    }

    @Override
    public void callback() {

    }
}
