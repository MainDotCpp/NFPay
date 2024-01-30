package cn.online.pay.service.service.impl;

import cn.online.pay.service.entity.Order;
import cn.online.pay.service.mapper.OrderMapper;
import cn.online.pay.service.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yang yang
 * @since 2024-01-28
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
