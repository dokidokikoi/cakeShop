package com.harukaze.api.vo.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @PackageName: com.harukaze.api.vo.param
 * @ClassName: GoodsParam
 * @Description:
 * @Author: doki
 * @Date: 2021/12/27 12:44
 */
@Data
public class GoodsParam {
    private Long id;

    /**
     * 商品名
     */
    @NotBlank(message = "商品名不能为空")
    private String name;

    /**
     * 封面
     */
    @NotBlank(message = "封面不能为空")
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
    @NotNull(message = "价格不能为空")
    private Double price;

    /**
     * 介绍
     */
    @NotBlank(message = "简介不能为空")
    private String intro;

    /**
     * 库存
     */
    @NotNull(message = "库存不能为空")
    private Integer stock;

    private Integer sale;

//    @NotNull(message = "分类不能为空")
    private List<Long> types;
}
