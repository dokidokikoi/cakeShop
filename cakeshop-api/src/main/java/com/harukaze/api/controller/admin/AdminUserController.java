package com.harukaze.api.controller.admin;


import com.harukaze.api.entity.User;
import com.harukaze.api.service.UserService;
import com.harukaze.api.vo.Result;
import com.harukaze.api.vo.UserVo;
import com.harukaze.api.vo.param.GoodsPageParam;
import com.harukaze.api.vo.param.PasswordParam;
import com.harukaze.api.vo.param.UserParam;
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
 * @since 2021-12-22
 */
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "获取当前用户信息", notes = "获取当前用户信息")
    @GetMapping("/currentUser")
    public Result getCurrentUser() {
        return userService.getCurrentUser();
    }

    @ApiOperation(value = "修改当前用户信息", notes = "修改当前用户信息")
    @PutMapping("/currentUser")
    public Result editCurrentUser(@RequestBody UserParam userParam) {
        return userService.editCurrentUser(userParam);
    }

    @ApiOperation(value = "修改当前用户密码", notes = "修改当前用户密码")
    @PutMapping("/currentUser/pwd")
    public Result editCurrentUserPwd(@RequestBody PasswordParam passwordParam) {
        return userService.editCurrentUserPwd(passwordParam);
    }
}
