package com.harukaze.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author doki
 * @since 2021-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单项数量
     */
    private Integer amount;

    /**
     * 总价
     */
    private Double total;

    /**
     * 创建时间
     */
    private Long createDate;

    /**
     * 付款状态
     */
    private Integer status;

    /**
     * 付款类别
     */
    private Integer paytype;

    /**
     * 收货人
     */
    private String name;

    /**
     * 收货人手机号
     */
    private String phone;

    /**
     * 收件地址
     */
    private String address;

    /**
     * 订单用户
     */
    private Long userId;


}
