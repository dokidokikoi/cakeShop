package com.harukaze.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.api.entity.Role;
import com.harukaze.api.entity.User;
import com.harukaze.api.vo.Result;
import com.harukaze.api.vo.UserVo;
import com.harukaze.api.vo.param.PasswordParam;
import com.harukaze.api.vo.param.RegisterParam;
import com.harukaze.api.vo.param.UserParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author doki
 * @since 2021-12-22
 */
public interface UserService extends IService<User> {

    Result getCurrentUser();

    User findUserByUsername(String username);

    Role findRole(Long id);

    UserVo findUserById(Long id);

    UserVo getUserById(Long userId);

    Result editCurrentUser(UserParam userParam);

    Result editCurrentUserPwd(PasswordParam passwordParam);

    Result register(RegisterParam registerParam);
}
