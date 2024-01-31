package cn.online.pay.service.service.impl;

import cn.online.pay.service.mapper.MachInfoMapper;
import cn.online.pay.service.service.IMachInfoService;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import cn.online.pay.service.entity.MachInfo;
import cn.online.pay.service.entity.MchInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.online.pay.service.service.IMchInfoService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

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


    private final IMchInfoService mchInfoService;
    private final WxPayService wxPayService;
    private final MachInfoMapper machInfoMapper;

    public MachInfoServiceImpl(IMchInfoService mchInfoService, WxPayService wxPayService, MachInfoMapper machInfoMapper) {
        this.mchInfoService = mchInfoService;
        this.wxPayService = wxPayService;
        this.machInfoMapper = machInfoMapper;
        initMachConfig();
    }


    private void initMachConfig() {
        List<MachInfo> machInfos = machInfoMapper.selectList(null);
        List<MchInfo> mchInfos = mchInfoService.list();
        log.info("[初始化商户配置] 配置数量={}", mchInfos.size());
        log.info(wxPayService.toString());
        Map<String, WxPayConfig> configMap = mchInfos.stream().map(mch -> {
            WxPayConfig config = new WxPayConfig();
            config.setMchId(mch.getMchNo());
            config.setAppId(mch.getAppId());
            config.setMchKey(mch.getKey());
            config.setSubMchId(mch.getSubMchNo());
            config.setApiV3Key(mch.getKeyV3());
            config.setPrivateKeyContent(mch.getPrivateKey().getBytes(StandardCharsets.UTF_8));
            config.setPrivateCertContent(mch.getPrivateCert().getBytes(StandardCharsets.UTF_8));
            return config;
        }).collect(Collectors.toMap(WxPayConfig::getAppId, config -> config));
        wxPayService.setMultiConfig(configMap);
    }

}
