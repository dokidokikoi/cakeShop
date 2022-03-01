package com.harukaze.api.vo.param;

import com.harukaze.api.controller.admin.AdminOrderController;
import com.harukaze.api.vo.GoodsVo;
import com.harukaze.api.vo.OrderVo;
import com.harukaze.api.vo.UserVo;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @PackageName: com.harukaze.api.vo.param
 * @ClassName: OrderParam
 * @Description:
 * @Author: doki
 * @Date: 24/12/2021 1:59 PM
 */
@Data
public class OrderParam {
    private UserVo user;
    @NotNull(message = "商品不能为空")
    private List<GoodsVo> goodsVos;
}
