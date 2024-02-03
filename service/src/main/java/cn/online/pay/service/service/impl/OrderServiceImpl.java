package cn.online.pay.service.service.impl;

import cn.online.pay.entity.Order;
import cn.online.pay.pojo.QueryOrderDTO;
import cn.online.pay.service.mapper.OrderMapper;
import cn.online.pay.service.IOrderService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yang yang
 * @since 2024-01-28
 */
@Service
@DubboService
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Override
    public List<Order> list(QueryOrderDTO queryOrderDTO) {
        LambdaQueryWrapper<Order> ew = new LambdaQueryWrapper<>();
        if (!queryOrderDTO.getMachNo().isBlank()) ew.eq(Order::getMachId, queryOrderDTO.getMachNo());
        if (!queryOrderDTO.getOpenId().isBlank()) ew.eq(Order::getOpenId, queryOrderDTO.getOpenId());
        if (!queryOrderDTO.getOutTradeNo().isBlank()) ew.eq(Order::getOutTradeNo, queryOrderDTO.getOutTradeNo());
        if (queryOrderDTO.getOrderStatus() != null) ew.eq(Order::getStatus, queryOrderDTO.getOrderStatus().getCode());
        return list(ew);
    }
}
