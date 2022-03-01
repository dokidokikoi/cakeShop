package com.harukaze.api.controller;


import com.harukaze.api.aop.LogAnnotation;
import com.harukaze.api.entity.Goods;
import com.harukaze.api.service.GoodsService;
import com.harukaze.api.vo.GoodsVo;
import com.harukaze.api.vo.Result;
import com.harukaze.api.vo.param.GoodsPageParam;
import com.harukaze.api.vo.param.PageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author doki
 * @since 2021-12-22
 */
@Api(tags = "商品数据接口")
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @ApiOperation(value = "获取商品列表", notes = "获取商品列表")
    @ApiParam(name = "pageParam")
    @GetMapping
    public Result getGoods(GoodsPageParam pageParam) {
        return goodsService.getGoodsList(pageParam, false);
    }

    @ApiOperation(value = "根据分类获取商品列表", notes = "根据分类获取商品列表")
    @ApiParam(name = "pageParam")
    @GetMapping("/category/{typeId}")
    public Result getGoodsByTypeId(@PathVariable Long typeId, PageParam pageParam) {
        return goodsService.getGoodsListByTypeId(typeId, pageParam);
    }

    @LogAnnotation(module = "商品", operator = "获取商品详情")
    @ApiOperation(value = "获取商品信息", notes = "获取商品信息")
    @ApiParam(name = "id")
    @GetMapping("/{id}")
    public Result getGoodsById(@PathVariable Long id) {
        return goodsService.getGoodsDetailById(id);
    }

}
