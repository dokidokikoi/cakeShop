package com.harukaze.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.harukaze.api.entity.Goods;
import com.harukaze.api.entity.Order;
import com.harukaze.api.entity.OrderItem;
import com.harukaze.api.entity.User;
import com.harukaze.api.mapper.OrderItemMapper;
import com.harukaze.api.mapper.OrderMapper;
import com.harukaze.api.mapper.UserMapper;
import com.harukaze.api.service.CarService;
import com.harukaze.api.service.OrderItemService;
import com.harukaze.api.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.api.service.ThreadService;
import com.harukaze.api.util.UserThreadLocal;
import com.harukaze.api.vo.*;
import com.harukaze.api.vo.param.OrderParam;
import com.harukaze.api.vo.param.PageParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CarService carService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    ThreadService threadService;

    @Override
    @Transactional
    public Result saveOrder(Long userId) {
        User user = userMapper.selectById(userId);
        Result result = carService.getCarList(userId);
        carService.clearAll(userId);
        List<CarVo> carList = (List) result.getData();
        List<GoodsVo> goodsVoList = new ArrayList<>();
        for (CarVo carVo : carList) {
            GoodsVo goods = carVo.getGoods();
            if (goods.getStatus() == 1 && goods.getStock() > 0) {
                goodsVoList.add(goods);
            }
        }
        if (goodsVoList.size() == 0) {
            return Result.fail("无商品");
        }
        Order order = new Order();
        order.setName(user.getNickname());
        order.setAddress(user.getAddress());
        order.setPhone(user.getPhone());
        order.setUserId(user.getId());
        order.setCreateDate(System.currentTimeMillis());
        order.setAmount(goodsVoList.size());
        order.setPaytype(0);
        order.setStatus(0);
        orderMapper.insert(order);

        Double total = 0.0;
        for (GoodsVo goodsVo : goodsVoList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setGoodsId(goodsVo.getId());
            orderItem.setOrderId(order.getId());
            orderItem.setPrice(goodsVo.getPrice());
            orderItemMapper.insert(orderItem);
            total += goodsVo.getPrice();
        }

        orderMapper.update(null, new LambdaUpdateWrapper<Order>().eq(Order::getId, order.getId()).set(Order::getTotal, total));
        threadService.updateSaleAndStockCount(order.getId());
        return Result.succ(null, 0);
    }

    @Override
    public Result getOrders() {
        UserVo userVo = UserThreadLocal.get();
        List<Order> orders = orderMapper.selectList(new LambdaQueryWrapper<Order>().eq(Order::getUserId, userVo.getId()).orderByDesc(Order::getCreateDate));
        List<OrderVo> orderVos = copyList(orders);

        return Result.succ(orderVos, orderVos.size());
    }

    @Transactional
    @Override
    public Result payOrder(Long id) {
        Order order = orderMapper.selectOne(new LambdaQueryWrapper<Order>().eq(Order::getId, id).eq(Order::getUserId, UserThreadLocal.get().getId()));
        if (order != null) {
            order.setStatus(1);
            orderMapper.updateById(order);
            return Result.succ(null, 0);
        }
        return Result.fail("想太多");
    }

    @Override
    public Result getAllOrders(PageParam pageParam) {
        Page<Order> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());
        IPage<Order> orderIPage = orderMapper.selectPage(page, new LambdaQueryWrapper<Order>().orderByDesc(Order::getCreateDate));
        List<OrderVo> orderVos = copyList(orderIPage.getRecords());
        return Result.succ(orderVos, (int) orderIPage.getTotal());
    }

    @Override
    public Result cancel(Long id) {
        orderMapper.update(null, new LambdaUpdateWrapper<Order>().eq(Order::getId, id).set(Order::getStatus, 3));
        threadService.lessSaleAndStockCount(id);
        return Result.succ(null, 0);
    }

    @Transactional
    @Override
    public Result deleteOrderById(Long id) {
        Order order = orderMapper.selectById(id);
        if (order.getStatus() == 0) {
            threadService.lessSaleAndStockCount(id);
        }
        orderItemMapper.delete(new LambdaQueryWrapper<OrderItem>().eq(OrderItem::getOrderId, id));
        orderMapper.deleteById(id);
        return Result.succ(null, 0);
    }

    @Override
    public Result resume(Long id) {
        orderMapper.update(null, new LambdaUpdateWrapper<Order>().eq(Order::getId, id).set(Order::getStatus, 0));
        return Result.succ(null, 0);
    }

    @Override
    public Result cancelCurrentOrders(Long id) {
        Order order = orderMapper.selectOne(new LambdaQueryWrapper<Order>().eq(Order::getId, id).eq(Order::getUserId, UserThreadLocal.get().getId()));
        if (order != null) {
            return cancel(id);
        }
        return Result.fail("想太多");
    }

    @Override
    public Result deleteCurrentOrders(Long id) {
        Order order = orderMapper.selectOne(new LambdaQueryWrapper<Order>().eq(Order::getId, id).eq(Order::getUserId, UserThreadLocal.get().getId()));
        if (order != null) {
            return deleteOrderById(id);
        }
        return Result.fail("想太多");
    }

    private List<OrderVo> copyList(List<Order> orders) {
        List<OrderVo> orderVos = new ArrayList<>();
        for (Order order : orders) {
            OrderVo orderVo = copy(order);
            orderVos.add(orderVo);
        }
        return orderVos;
    }

    private OrderVo copy(Order order) {
        OrderVo orderVo = new OrderVo();
        BeanUtils.copyProperties(order, orderVo);
        List<OrderItemVo> orderItemVos = orderItemService.findItemByOrderId(order.getId());
        orderVo.setOrderItemVos(orderItemVos);
        orderVo.setUser(UserThreadLocal.get());
        return orderVo;
    }
}
