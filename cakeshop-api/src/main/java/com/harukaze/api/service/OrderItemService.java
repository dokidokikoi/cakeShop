package com.harukaze.api.service;

import com.harukaze.api.entity.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.api.vo.OrderItemVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author doki
 * @since 2021-12-23
 */
public interface OrderItemService extends IService<OrderItem> {

    List<OrderItemVo> findItemByOrderId(Long id);
}
