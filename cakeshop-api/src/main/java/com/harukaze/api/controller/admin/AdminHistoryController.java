package com.harukaze.api.controller.admin;


import com.harukaze.api.entity.History;
import com.harukaze.api.service.HistoryService;
import com.harukaze.api.vo.Result;
import com.harukaze.api.vo.param.GoodsPageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
 * @since 2021-12-25
 */
@Api(tags = "历史记录数据接口")
@RestController
@RequestMapping("/admin/history")
public class AdminHistoryController{
    @Autowired
    HistoryService historyService;

    @ApiOperation(value = "获取当前用户历史记录", notes = "获取当前用户历史记录")
    @GetMapping
    public Result getGoods() {
        return historyService.getHistoryList();
    }

}
