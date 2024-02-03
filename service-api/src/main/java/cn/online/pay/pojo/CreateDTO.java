package cn.online.pay.pojo;

import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import lombok.Data;
import cn.online.pay.core.enums.MchType;

@Data
public class CreateDTO {
    MchType mchType;
    String machId;
    String appId;
    String outTradeNo;
    String description;
    String openid;
    String notifyUrl;
    TradeTypeEnum tradeType;
    Integer total;
    String extraInfo;
    String attach;
}