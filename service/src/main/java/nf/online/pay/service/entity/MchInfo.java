package nf.online.pay.service.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author yang yang
 * @since 2024-01-27
 */
@Getter
@Setter
@TableName("t_mch_info")
public class MchInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Object id;

    /**
     * 商户号
     */
    private String mchNo;

    /**
     * 商户名称
     */
    private String mchName;

    /**
     * key
     */
    private String key;

    /**
     * key_v3
     */
    private String keyV3;

    /**
     * 类型
     */
    private String type;

    /**
     * 商户号
     */
    private String machId;

    /**
     * 关联的公众号/小程序id
     */
    private String appId;
}
