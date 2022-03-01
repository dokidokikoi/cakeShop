package com.harukaze.api.vo.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @PackageName: com.harukaze.api.vo.param
 * @ClassName: UserParam
 * @Description:
 * @Author: doki
 * @Date: 2021/12/27 22:49
 */
@Data
public class UserParam {
    private Long id;

    /**
     * 账号
     */
    @NotBlank(message = "账户名不能为空")
    private String account;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 收货地址
     */
    @NotBlank(message = "地址不能为空")
    private String address;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    private String email;

    private String avatar;
}
