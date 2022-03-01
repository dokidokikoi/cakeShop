package com.harukaze.api.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @PackageName: com.harukaze.api.vo
 * @ClassName: UserVo
 * @Description:
 * @Author: doki
 * @Date: 23/12/2021 9:57 AM
 */
@Data
public class UserVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 角色
     */
    private Long roleId;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 邮箱
     */
    private String email;


    private String avatar;
}
