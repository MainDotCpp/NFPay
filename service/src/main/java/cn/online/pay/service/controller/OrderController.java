package cn.online.pay.service.controller;

import cn.online.pay.entity.Order;
import cn.online.pay.service.IOrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yang yang
 * @since 2024-01-28
 */
@Controller
@RequestMapping("/service/order")
public class OrderController {

    @Resource
    private IOrderService orderService;

    List<Order> list() {
        return orderService.list();
    }

}
