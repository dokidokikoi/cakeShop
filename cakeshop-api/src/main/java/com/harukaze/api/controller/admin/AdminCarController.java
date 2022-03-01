package com.harukaze.api.controller.admin;


import com.harukaze.api.service.CarService;
import com.harukaze.api.vo.CarVo;
import com.harukaze.api.vo.Result;
import com.harukaze.api.vo.param.CarParam;
import com.harukaze.api.vo.param.GoodsPageParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author doki
 * @since 2021-12-23
 */
@RestController
@RequestMapping("/admin/car")
public class AdminCarController {
    @Autowired
    CarService carService;

    @ApiOperation(value = "获取用户购物车列表", notes = "获取用户购物车列表")
    @ApiParam(name = "userId")
    @GetMapping("/{userId}")
    public Result getCar(@PathVariable Long userId) {
        return carService.getCarList(userId);
    }

    @ApiOperation(value = "获取用户购物车商品数", notes = "获取用户购物车商品数")
    @ApiParam(name = "userId")
    @GetMapping("/counts/{userId}")
    public Result getCarCounts(@PathVariable Long userId) {
        return carService.getCount(userId);
    }

    @ApiOperation(value = "加购物车", notes = "当前用户加购物车")
    @ApiParam(name = "carParam")
    @PostMapping
    public Result saveCar(@RequestBody CarParam carParam) {
        return carService.saveCar(carParam);
    }

    @ApiOperation(value = "删除购物车物品", notes = "删除购物车物品")
    @ApiParam(name = "carId")
    @DeleteMapping("/{carId}")
    public Result removeCar(@PathVariable Long carId) {
        return carService.removeCar(carId);
    }
}
