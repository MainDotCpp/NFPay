package nf.online.pay.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nf.online.pay.service.mapper.MachInfoMapper;
import nf.online.pay.service.model.MachInfo;
import org.springframework.stereotype.Component;

@Slf4j
@Component("nfWxPayService")
@RequiredArgsConstructor
public class WxPayServiceImpl implements PayService {

    private final MachInfoMapper machInfoMapper;

    @Override
    public void queryOrder() {
        MachInfo machInfo = new MachInfo();
        machInfo.setMchNo("M" + System.currentTimeMillis());
        machInfo.setMchName("test");
        machInfo.setType((short) 1);
        machInfoMapper.insert(machInfo);
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
