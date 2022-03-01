package com.harukaze.api.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @PackageName: com.harukaze.api.vo
 * @ClassName: OrderItemVo
 * @Description:
 * @Author: doki
 * @Date: 24/12/2021 4:37 PM
 */
@Data
public class OrderItemVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 商品数量
     */
    private Integer amount;

    /**
     * 价格
     */
    private Double price;

    /**
     * 商品id
     */
    private GoodsVo goods;
}
