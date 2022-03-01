package com.harukaze.api.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @PackageName: com.harukaze.api.vo
 * @ClassName: CarVo
 * @Description:
 * @Author: doki
 * @Date: 23/12/2021 11:43 PM
 */
@Data
public class CarVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 创建时间
     */
    private Long createDate;

    /**
     * 用户
     */
    private UserVo user;

    /**
     * 商品
     */
    private GoodsVo goods;
}
