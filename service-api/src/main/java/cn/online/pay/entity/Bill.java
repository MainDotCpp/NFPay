package cn.online.pay.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
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
@TableName("t_bill")
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;

    private String appId;

    private String mchId;

    private String outTradeNo;

    private String transactionId;

    private String tradeType;

    private String tradeState;

    private String tradeStateDesc;

    private String bankType;

    private String attach;

    private LocalDateTime successTime;

    private String openId;

    private Long total;

    private Long payerTotal;

    private String currency;

    private String payerCurrency;

    private String sceneInfo;

    private String promotionDetails;

    private LocalDateTime createAt;

    private String notifyUrl;

    private String isNotify;

    private LocalDate notifyTime;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
}
