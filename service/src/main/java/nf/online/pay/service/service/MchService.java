package nf.online.pay.service.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import nf.online.pay.core.NFPayCoreConfig;
import nf.online.pay.service.mapper.MachInfoMapper;
import nf.online.pay.service.model.MachInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MchService {

    @Resource
    private NFPayCoreConfig nfPayCoreConfig;

    @Resource
    private MachInfoMapper machInfoMapper;

    private void loadMchConfig() {
        List<MachInfo> machInfos = machInfoMapper.selectList(null);
        log.info("[初始化商户配置] 商户数量={}",machInfos.size());
        for (MachInfo machInfo : machInfos) {
        }
    }

}
