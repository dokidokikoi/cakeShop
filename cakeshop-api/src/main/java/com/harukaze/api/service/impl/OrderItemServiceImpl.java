package com.harukaze.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.harukaze.api.entity.OrderItem;
import com.harukaze.api.mapper.OrderItemMapper;
import com.harukaze.api.service.GoodsService;
import com.harukaze.api.service.OrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.api.vo.OrderItemVo;
import com.harukaze.api.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author doki
 * @since 2021-12-23
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {
    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    GoodsService goodsService;

    @Override
    public List<OrderItemVo> findItemByOrderId(Long id) {
        List<OrderItem> orderItems = orderItemMapper.selectList(new LambdaQueryWrapper<OrderItem>().eq(OrderItem::getOrderId, id));
        List<OrderItemVo> orderItemVos = copyList(orderItems);

        return orderItemVos;
    }

    private List<OrderItemVo> copyList(List<OrderItem> orderItems) {
        List<OrderItemVo> orderItemVos = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            OrderItemVo orderItemVo = copy(orderItem);
            orderItemVos.add(orderItemVo);
        }
        return orderItemVos;
    }

    private OrderItemVo copy(OrderItem orderItem) {
        OrderItemVo orderItemVo = new OrderItemVo();
        BeanUtils.copyProperties(orderItem, orderItemVo);
        orderItemVo.setGoods(goodsService.getGoodsById(orderItem.getGoodsId()));
        return orderItemVo;
    }
}
