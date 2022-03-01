package com.harukaze.api.controller.admin;


import com.harukaze.api.service.RoleService;
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
@Api(tags = "角色数据接口")
@RestController
@RequestMapping("/admin/role")
public class AdminRoleController {
    @Autowired
    RoleService roleService;

    @ApiOperation(value = "查询当前用户角色", notes = "查询当前用户角色")
    @GetMapping("/current")
    public Result getCurrentRole() {
        return roleService.findCurrentRole();
    }
}
