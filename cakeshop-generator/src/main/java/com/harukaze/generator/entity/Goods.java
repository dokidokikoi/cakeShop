package com.harukaze.generator.entity;

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
 * @since 2021-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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


}
