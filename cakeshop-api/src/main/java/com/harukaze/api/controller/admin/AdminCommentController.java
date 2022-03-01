package com.harukaze.api.controller.admin;


import com.harukaze.api.service.CommentService;
import com.harukaze.api.vo.CommentVo;
import com.harukaze.api.vo.Result;
import com.harukaze.api.vo.param.CommentParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "登录评论数据接口")
@RestController
@RequestMapping("/admin/comments")
public class AdminCommentController {
    @Autowired
    CommentService commentService;

    @ApiOperation(value = "回复评论", notes = "回复评论")
    @PostMapping
    public Result getGoodsComment(@RequestBody CommentParam commentParam) {
        return commentService.saveGoodsComment(commentParam);
    }

    @ApiOperation(value = "发表评论", notes = "发表评论")
    @PostMapping("/publish")
    public Result publishComment(@RequestBody CommentParam commentParam) {
        return commentService.publishGoodsComment(commentParam);
    }
}
