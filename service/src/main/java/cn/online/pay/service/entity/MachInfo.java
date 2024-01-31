package cn.online.pay.service.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商户信息表
 * </p>
 *
 * @author yang yang
 * @since 2024-01-31
 */
@Getter
@Setter
@TableName("t_mach_info")
public class MachInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商户号
     */
    private String mchNo;

    /**
     * 商户名称
     */
    private String mchName;

    /**
     * 商户类型，0-普通商户，1-特约商户
     */
    private Short type;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
