package com.harukaze.api.vo.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @PackageName: com.harukaze.api.vo.param
 * @ClassName: CarParam
 * @Description:
 * @Author: doki
 * @Date: 24/12/2021 10:23 AM
 */
@Data
public class CarParam {
    private Long id;
    @NotNull(message = "userId不能为空")
    private Long userId;
    @NotNull(message = "goodsId不能为空")
    private Long goodsId;
}
