package nf.online.pay.service.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import nf.online.pay.core.config.NFPayCoreConfig;
import nf.online.pay.service.entity.MachInfo;
import nf.online.pay.service.entity.MchInfo;
import nf.online.pay.service.mapper.MachInfoMapper;
import nf.online.pay.service.service.IMachInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nf.online.pay.service.service.IMchInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商户信息表 服务实现类
 * </p>
 *
 * @author yang yang
 * @since 2024-01-26
 */
@Slf4j
@Service
public class MachInfoServiceImpl extends ServiceImpl<MachInfoMapper, MachInfo> implements IMachInfoService {

    @Resource
    private NFPayCoreConfig nfPayCoreConfig;
    @Resource
    private IMchInfoService mchInfoService;

    @PostConstruct
    private void initMachConfig() {
        List<MachInfo> machInfos = list();
        List<MchInfo> mchInfos = mchInfoService.list();
        log.info("[初始化商户配置] 商户数量={}", machInfos.size());
        for (MachInfo machInfo : machInfos) {
            List<MchInfo> mchList = mchInfos.stream().filter(it -> it.getMachId().equals(machInfo.getMchNo())).toList();
            for (MchInfo mchInfo : mchList) {
                nfPayCoreConfig.initMchConfig(
                        mchInfo.getType(),
                        NFPayCoreConfig.InitMchConfig.builder()
                                .appId(mchInfo.getAppId())
                                .key(mchInfo.getKey())
                                .mchId(mchInfo.getMchNo())
                                .v3Key(mchInfo.getKeyV3())
                                .build()
                );
                log.info("初始化{}({})配置 SUCCESS", machInfo.getMchName(), mchInfo.getType());
            }
        }
    }

}
