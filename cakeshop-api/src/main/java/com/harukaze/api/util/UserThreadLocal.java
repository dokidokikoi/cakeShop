package com.harukaze.api.util;

import com.harukaze.api.vo.UserVo;

/**
 * @PackageName: com.harukaze.front.util
 * @ClassName: UserThreadLocal
 * @Description:
 * @Author: doki
 * @Date: 27/11/2021 7:31 PM
 */
public class UserThreadLocal {
    private static final ThreadLocal<UserVo> LOCAL = new ThreadLocal<>();

    private UserThreadLocal() {
    }

    public static UserVo get() {
        return LOCAL.get();
    }

    public static void set(UserVo userVo) {
        LOCAL.set(userVo);
    }

    public static void remove() {
        LOCAL.remove();
    }

}
