package com.harukaze.api.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.harukaze.api.controller.admin.AdminOrderController;
import lombok.Data;

import java.util.List;

/**
 * @PackageName: com.harukaze.api.vo
 * @ClassName: OrderVo
 * @Description:
 * @Author: doki
 * @Date: 24/12/2021 4:35 PM
 */
@Data
public class OrderVo {
    @JsonSerialize(using = ToStringSerializer.class)
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
    private UserVo user;

    private List<OrderItemVo> orderItemVos;
}
