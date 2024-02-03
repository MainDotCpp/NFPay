package cn.online.pay.pojo;

import cn.online.pay.core.enums.OrderStatus;
import lombok.Data;

@Data
public class QueryOrderDTO {
    String machNo;
    String openId;
    String outTradeNo;
    OrderStatus orderStatus;
}
