package com.harukaze.api.vo.param;

import jdk.jfr.DataAmount;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @PackageName: com.harukaze.api.vo.param
 * @ClassName: PasswordParam
 * @Description:
 * @Author: doki
 * @Date: 24/12/2021 4:19 PM
 */
@Data
public class PasswordParam {
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}
