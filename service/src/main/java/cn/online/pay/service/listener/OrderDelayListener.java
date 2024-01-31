package cn.online.pay.service.listener;

import cn.online.pay.service.entity.Order;
import cn.online.pay.service.enums.OrderStatus;
import cn.online.pay.service.service.IOrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderDelayListener {

    @Resource
    private IOrderService orderService;

    @RabbitHandler
    @RabbitListener(queues = "delay.pay.order")
    public void processDelayOrder(String msg) {
        System.out.println("[订单超时未支付]: " + msg);
        Order order = new Order();
        order.setStatus(OrderStatus.CANCEL.getCode());
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("out_trade_no", msg);
        orderService.update(order, orderQueryWrapper);
    }
}
