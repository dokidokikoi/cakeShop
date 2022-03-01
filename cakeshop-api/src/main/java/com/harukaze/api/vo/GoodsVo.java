package com.harukaze.api.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.harukaze.api.entity.Type;
import lombok.Data;


import java.util.List;

/**
 * @PackageName: com.harukaze.api.vo
 * @ClassName: GoodsVo
 * @Description:
 * @Author: doki
 * @Date: 22/12/2021 3:23 PM
 */
@Data
public class GoodsVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 商品名
     */
    private String name;

    /**
     * 封面
     */
    private String cover;

    /**
     * 图片1
     */
    private String image1;

    /**
     * 图片2
     */
    private String image2;

    /**
     * 价格
     */
    private Double price;

    /**
     * 介绍
     */
    private String intro;

    /**
     * 库存
     */
    private Integer stock;

    private Integer sale;

    private Long createDate;

    private Integer status;

    private List<Type> types;
}
