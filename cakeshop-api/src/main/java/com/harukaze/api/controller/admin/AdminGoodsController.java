package com.harukaze.api.controller.admin;


import com.harukaze.api.service.GoodsService;
import com.harukaze.api.vo.Result;
import com.harukaze.api.vo.param.GoodsPageParam;
import com.harukaze.api.vo.param.GoodsParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author doki
 * @since 2021-12-22
 */
@Api(tags = "商品数据后台接口")
@RestController
@RequestMapping("/admin/goods")
public class AdminGoodsController {
    @Autowired
    GoodsService goodsService;

    @PreAuthorize("hasRole('ROLE_admin')")
    @ApiOperation(value = "获取全部商品列表", notes = "获取全部商品列表")
    @ApiParam(name = "pageParam")
    @GetMapping
    public Result getGoods(GoodsPageParam pageParam) {
        return goodsService.getGoodsList(pageParam, true);
    }


    @PreAuthorize("hasRole('ROLE_admin')")
    @ApiOperation(value = "商品上架", notes = "商品上架")
    @ApiParam(name = "goodsParam")
    @PostMapping
    public Result saveGoods(@RequestBody GoodsParam goodsParam) {
        return goodsService.saveGoods(goodsParam);
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @ApiOperation(value = "商品修改", notes = "商品修改")
    @ApiParam(name = "goodsParam")
    @PutMapping
    public Result editGoods(@Validated @RequestBody GoodsParam goodsParam) {
        return goodsService.editGoods(goodsParam);
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @ApiOperation(value = "商品下架", notes = "商品下架")
    @ApiParam(name = "id")
    @DeleteMapping("/{id}")
    public Result deleteGoods(@PathVariable Long id) {
        return goodsService.deleteGoods(id);
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @ApiOperation(value = "商品恢复", notes = "商品恢复")
    @ApiParam(name = "id")
    @PutMapping("/resume/{id}")
    public Result resumeGoods(@PathVariable Long id) {
        return goodsService.resumeGoods(id);
    }

}
