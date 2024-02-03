package cn.online.pay.core.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    WAITING_PAY((short) 0, "待支付"),
    PAY_SUCCESS((short) 1, "支付成功"),
    PAY_FAIL((short) 2, "支付失败"),
    REFUNDING((short) 3, "退款中"),
    REFUND_SUCCESS((short) 4, "退款成功"),
    REFUND_FAIL((short) 5, "退款失败"),
    CANCEL((short) 6, "已取消");


    private final Short code;
    private final String desc;
}
