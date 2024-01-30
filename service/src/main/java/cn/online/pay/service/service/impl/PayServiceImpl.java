package cn.online.pay.service.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.online.pay.api.PayService;
import cn.online.pay.core.IPayCore;
import cn.online.pay.core.enums.MchType;
import cn.online.pay.service.entity.Bill;
import cn.online.pay.service.entity.MchInfo;
import cn.online.pay.service.entity.Order;
import cn.online.pay.service.mapper.MchInfoMapper;
import cn.online.pay.service.mapper.OrderMapper;
import cn.online.pay.service.service.IBillService;
import cn.online.pay.service.service.IMachInfoService;
import cn.online.pay.service.service.IMchInfoService;
import cn.online.pay.service.service.IOrderService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyV3Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import cn.online.pay.api.pojo.CreateDTO;
import cn.online.pay.service.enums.OrderStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class PayServiceImpl implements PayService {
    @Resource
    private IPayCore payCore;
    @Resource
    private IOrderService orderService;

    @Resource
    private IMchInfoService mchInfoService;

    @Resource
    private IBillService billService;


    @Override
    public void queryOrder() {

    }

    @Override
    public Object createOrder(CreateDTO dto) {
        log.info("[创建订单] 入参={}", dto);
        Order order = new Order();
        order.setAttach(dto.getDescription());
        order.setDescription(dto.getDescription());
        order.setMachId(dto.getMachId());
        order.setNotifyUrl(dto.getNotifyUrl());
        order.setAppId(dto.getAppId());
        order.setOutTradeNo(dto.getOutTradeNo());
        order.setTotalFee(dto.getTotal());
        order.setOpenId(dto.getOpenid());
        order.setMachId(dto.getMachId());
        order.setStatus(OrderStatus.WAITING_PAY.getCode());
        orderService.save(order);
        return payCore.createOrder(dto);
    }

    @Override
    public void pay() {

    }

    @Override
    public void refund() {

    }

    @Override
    public Object callback(String appId, Object data) {
        log.info("[支付回调] 入参={}", data);
        LambdaQueryWrapper<MchInfo> ew = new LambdaQueryWrapper<>();
        ew.eq(MchInfo::getAppId, appId);
        MchInfo mchInfo = mchInfoService.getOne(ew);
        MchType mchType = MchType.valueOf(mchInfo.getType());
        WxPayOrderNotifyV3Result notifyData = (WxPayOrderNotifyV3Result) payCore.callback(mchType, appId, data.toString());
        WxPayOrderNotifyV3Result.DecryptNotifyResult result = notifyData.getResult();
        log.info("[支付回调] 解密后报文={}", notifyData);

        // 查询订单
        LambdaQueryWrapper<Order> orderEw = new LambdaQueryWrapper<>();
        orderEw.eq(Order::getOutTradeNo, result.getOutTradeNo());
        Order order = orderService.getOne(orderEw);

        // TODO: 2024/1/30 检查是否有重复推送


        // 更新订单状态
        if (!result.getTradeState().equals("SUCCESS")) {
            log.info("[支付回调] 支付失败 appId={} outTradeNo={}", appId, result.getOutTradeNo());
            order.setStatus(OrderStatus.PAY_FAIL.getCode());
            return null;
        }

        order.setStatus(OrderStatus.PAY_SUCCESS.getCode());
        orderService.updateById(order);
        // 异步通知业务系统

        // 创建支付流水
        Bill bill = new Bill();
        bill.setAppId(result.getAppid());
        bill.setMchId(result.getMchid());
        bill.setOutTradeNo(result.getOutTradeNo());
        bill.setTransactionId(result.getTransactionId());
        bill.setTradeType(result.getTradeType());
        bill.setTradeState(result.getTradeState());
        bill.setTradeStateDesc(result.getTradeStateDesc());
        bill.setBankType(result.getBankType());
        bill.setAttach(result.getAttach());
        LocalDateTime successTime = DateUtil.parseLocalDateTime(result.getSuccessTime(), "yyyy-MM-dd'T'HH:mm:ssXXX");
        bill.setSuccessTime(successTime);
        bill.setOpenId(result.getPayer().getOpenid());
        bill.setTotal(result.getAmount().getTotal().longValue());
        bill.setPayerTotal(result.getAmount().getPayerTotal().longValue());
        bill.setCurrency(result.getAmount().getCurrency());
        bill.setPayerCurrency(result.getAmount().getPayerCurrency());
        if (result.getSceneInfo() != null) {
            bill.setSceneInfo(result.getSceneInfo().toString());
        }
        if (result.getPromotionDetails() != null) {
            bill.setPromotionDetails(result.getPromotionDetails().toString());
        }
        billService.save(bill);

        return data;
    }

}