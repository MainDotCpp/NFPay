package cn.online.pay.service.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author yang yang
 * @since 2024-01-31
 */
@Getter
@Setter
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 商户号
     */
    private String machId;

    /**
     * appId
     */
    private String appId;

    private String description;

    private String attach;

    private String openId;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 回调地址
     */
    private String notifyUrl;

    /**
     * 回调时间
     */
    private LocalDateTime notifyTime;

    /**
     * 是否通知
     */
    private Boolean isNotify;

    /**
     * 订单状态
     */
    private Short status;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    private Integer id;

    /**
     * 订单金额
     */
    private Integer totalFee;
}
