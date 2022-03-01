package com.harukaze.api.controller.admin;


import com.harukaze.api.service.OrderService;
import com.harukaze.api.vo.GoodsVo;
import com.harukaze.api.vo.OrderVo;
import com.harukaze.api.vo.Result;
import com.harukaze.api.vo.param.OrderParam;
import com.harukaze.api.vo.param.PageParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author doki
 * @since 2021-12-23
 */
@RestController
@RequestMapping("/admin/orders")
public class AdminOrderController {
    @Autowired
    OrderService orderService;

    @ApiOperation(value = "提交订单", notes = "提交订单")
    @ApiParam(name = "orderList")
    @PostMapping("/{userId}")
    public Result saveOrder(@PathVariable Long userId) {
        return orderService.saveOrder(userId);
    }

    @ApiOperation(value = "查询当前用户订单", notes = "查询当前用户订单")
    @GetMapping("/current")
    public Result getOrders() {
        return orderService.getOrders();
    }

    @ApiOperation(value = "取消当前用户订单", notes = "取消当前用户订单")
    @ApiParam(name = "id")
    @PutMapping("/current/cancel/{id}")
    public Result cancelCurrentOrders(@PathVariable Long id) {
        return orderService.cancelCurrentOrders(id);
    }

    @ApiOperation(value = "删除当前用户订单", notes = "删除当前用户订单")
    @ApiParam(name = "id")
    @DeleteMapping("/current/{id}")
    public Result deleteCurrentOrders(@PathVariable Long id) {
        return orderService.deleteCurrentOrders(id);
    }

    @ApiOperation(value = "付款", notes = "付款")
    @ApiParam(name = "id")
    @PutMapping("/current/{id}")
    public Result payOrder(@PathVariable Long id) {
        return orderService.payOrder(id);
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @ApiOperation(value = "查询所有用户订单", notes = "查询所有用户订单")
    @ApiParam(name = "pageParam")
    @GetMapping
    public Result getAllOrders(PageParam pageParam) {
        return orderService.getAllOrders(pageParam);
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @ApiOperation(value = "取消订单", notes = "取消订单")
    @ApiParam(name = "id")
    @PutMapping("/cancel/{id}")
    public Result cancelOrders(@PathVariable Long id) {
        return orderService.cancel(id);
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @ApiOperation(value = "恢复订单", notes = "恢复订单")
    @ApiParam(name = "id")
    @PutMapping("/resume/{id}")
    public Result resumeOrders(@PathVariable Long id) {
        return orderService.resume(id);
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @ApiOperation(value = "删除订单", notes = "删除订单")
    @ApiParam(name = "id")
    @DeleteMapping("/{id}")
    public Result deleteOrders(@PathVariable Long id) {
        return orderService.deleteOrderById(id);
    }
}
