package nf.online.pay.service.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
    * 商户信息表
    */
@Data
@TableName(value = "t_mach_info")
public class MachInfo {
    /**
     * 商户号
     */
    @TableId(value = "mch_no", type = IdType.INPUT)
    private String mchNo;

    /**
     * 商户名称
     */
    @TableField(value = "mch_name")
    private String mchName;

    /**
     * 商户类型，0-普通商户，1-特约商户
     */
    @TableField(value = "\"type\"")
    private Short type;

    @TableField(value = "created_at")
    private Date createdAt;

    @TableField(value = "updated_at")
    private Date updatedAt;

    public static final String COL_MCH_NO = "mch_no";

    public static final String COL_MCH_NAME = "mch_name";

    public static final String COL_TYPE = "type";

    public static final String COL_CREATED_AT = "created_at";

    public static final String COL_UPDATED_AT = "updated_at";
}