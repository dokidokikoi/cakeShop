package com.harukaze.api.controller;


import com.harukaze.api.service.CommentService;
import com.harukaze.api.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @since 2021-12-23
 */
@Api(tags = "评论数据接口")
@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @ApiOperation(value = "获取商品评论", notes = "获取商品评论")
    @GetMapping("/{id}")
    public Result getGoodsComment(@PathVariable Long id) {
        return commentService.findGoodsComment(id);
    }
}
