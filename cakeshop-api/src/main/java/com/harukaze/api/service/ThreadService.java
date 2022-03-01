package com.harukaze.api.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.harukaze.api.entity.Goods;
import com.harukaze.api.entity.OrderItem;
import com.harukaze.api.mapper.GoodsMapper;
import com.harukaze.api.mapper.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName: com.harukaze.api.service
 * @ClassName: ThreadService
 * @Description:
 * @Author: doki
 * @Date: 2021/12/27 16:49
 */
@Service
public class ThreadService {
    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Async("taskExecutor")
    @Transactional(rollbackFor = Exception.class)
    public void updateSaleAndStockCount(Long orderId) {
        List<OrderItem> orderItems = orderItemMapper.selectList(new LambdaQueryWrapper<OrderItem>().eq(OrderItem::getOrderId, orderId));
        for (OrderItem orderItem : orderItems) {
            Goods goods = goodsMapper.selectById(orderItem.getGoodsId());
            int sale = goods.getSale();
            int stock = goods.getStock();
            if (stock>0) {
                int update = goodsMapper.update(null, new LambdaUpdateWrapper<Goods>()
                        .eq(Goods::getId, goods.getId())
                        .eq(Goods::getSale, sale)
                        .eq(Goods::getStock, stock)
                        .set(Goods::getSale, sale + 1)
                        .set(Goods::getStock, stock - 1));
                if (update == 0) {
                    throw new RuntimeException("error");
                }
            } else {
                throw new RuntimeException("error");
            }
        }
    }

    @Async("taskExecutor")
    @Transactional(rollbackFor = Exception.class)
    public void lessSaleAndStockCount(Long orderId) {
        List<OrderItem> orderItems = orderItemMapper.selectList(new LambdaQueryWrapper<OrderItem>().eq(OrderItem::getOrderId, orderId));
        for (OrderItem orderItem : orderItems) {
            Goods goods = goodsMapper.selectById(orderItem.getGoodsId());
            int sale = goods.getSale();
            int stock = goods.getStock();
            if (stock>0) {
                int update = goodsMapper.update(null, new LambdaUpdateWrapper<Goods>()
                        .eq(Goods::getId, goods.getId())
                        .eq(Goods::getSale, sale)
                        .eq(Goods::getStock, stock)
                        .set(Goods::getSale, sale - 1)
                        .set(Goods::getStock, stock + 1));
                if (update == 0) {
                    throw new RuntimeException("error");
                }
            } else {
                throw new RuntimeException("error");
            }
        }
    }
}
