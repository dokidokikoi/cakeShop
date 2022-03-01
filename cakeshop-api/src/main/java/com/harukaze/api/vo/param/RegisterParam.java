package com.harukaze.api.vo.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @PackageName: com.harukaze.api.vo.param
 * @ClassName: UserParam
 * @Description:
 * @Author: doki
 * @Date: 2021/12/27 17:09
 */
@Data
public class RegisterParam {
    @NotBlank(message = "用户账号不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "邮箱不能为空")
    private String email;
}
