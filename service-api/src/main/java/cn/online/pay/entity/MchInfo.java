package cn.online.pay.entity;

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
 * @since 2024-01-31
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

    /**
     * 子商户信息
     */
    private String subMchNo;

    /**
     * 私钥key
     */
    private String privateKey;

    /**
     * 私钥content
     */
    private String privateCert;

    /**
     * 证书序号
     */
    private String certSerialNo;
}
