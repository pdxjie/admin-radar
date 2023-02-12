package com.pdx.radar.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 派大星
 * @since 2023-02-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("r_log")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 日志类型
     */
    private Integer type;

    /**
     * 目标ID
     */
    private String targetId;

    /**
     * 修改之前的值
     */
    private String oldValue;

    /**
     * 修改之后的值
     */
    private String newValue;

    /**
     * 操作人
     */
    private String operator;

    private Date createTime;

    /**
     * 操作IP
     */
    private String operateIp;


}
