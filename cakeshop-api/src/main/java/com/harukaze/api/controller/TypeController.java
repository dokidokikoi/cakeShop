package com.harukaze.api.controller;


import com.harukaze.api.service.TypeService;
import com.harukaze.api.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api(tags = "商品分类数据接口")
@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    TypeService typeService;

    @ApiOperation(value = "获取商品分类列表", notes = "获取商品分类列表")
    @GetMapping
    public Result getTypeList() {
        return typeService.findAllType();
    }
}
