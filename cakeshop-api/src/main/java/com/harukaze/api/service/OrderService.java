package com.harukaze.api.service;

import com.harukaze.api.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.api.vo.GoodsVo;
import com.harukaze.api.vo.Result;
import com.harukaze.api.vo.param.OrderParam;
import com.harukaze.api.vo.param.PageParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author doki
 * @since 2021-12-23
 */
public interface OrderService extends IService<Order> {

    Result saveOrder(Long userId);

    Result getOrders();

    Result payOrder(Long id);

    Result getAllOrders(PageParam pageParam);

    Result cancel(Long id);

    Result deleteOrderById(Long id);

    Result resume(Long id);

    Result cancelCurrentOrders(Long id);

    Result deleteCurrentOrders(Long id);
}
