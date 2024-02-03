package cn.online.pay.service;

import cn.online.pay.pojo.QueryOrderDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.online.pay.entity.Order;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yang yang
 * @since 2024-01-28
 */
public interface IOrderService extends IService<Order> {
    List<Order> list(QueryOrderDTO queryOrderDTO);

    List<Order> listByOpenId(String machId, String openId);

    List<Order> listByTradeNos(String machId, List<String> tradeNos);
}
