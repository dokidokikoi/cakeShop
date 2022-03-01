package com.harukaze.api.controller;


import com.harukaze.api.service.UserService;
import com.harukaze.api.vo.Result;
import com.harukaze.api.vo.param.RegisterParam;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @ApiParam(name = "registerParam")
    @PostMapping("/register")
    public Result register(@RequestBody RegisterParam registerParam) {
        return userService.register(registerParam);
    }
}
