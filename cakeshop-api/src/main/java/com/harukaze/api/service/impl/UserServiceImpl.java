package com.harukaze.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.api.entity.Role;
import com.harukaze.api.entity.User;
import com.harukaze.api.mapper.UserMapper;
import com.harukaze.api.service.UserService;
import com.harukaze.api.util.UserThreadLocal;
import com.harukaze.api.vo.Result;
import com.harukaze.api.vo.UserVo;
import com.harukaze.api.vo.param.PasswordParam;
import com.harukaze.api.vo.param.RegisterParam;
import com.harukaze.api.vo.param.UserParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author doki
 * @since 2021-12-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Result getCurrentUser() {
        return Result.succ(UserThreadLocal.get(), 1);
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getAccount, username));
    }

    @Override
    public Role findRole(Long id) {
        return userMapper.selectRole(id);
    }

    @Override
    public UserVo findUserById(Long id) {
        User user = userMapper.selectById(id);
        UserVo userVo = copy(user);
        return userVo;
    }

    @Override
    public UserVo getUserById(Long userId) {
        User user = userMapper.selectById(userId);
        return copy(user);
    }

    @Override
    public Result editCurrentUser(UserParam userParam) {
        UserVo userVo = UserThreadLocal.get();
        if (userVo.getId() != userParam.getId()){
            return Result.fail("无法修改其他用户信息");
        }
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        userMapper.updateById(user);
        return Result.succ(null, 0);
    }

    @Override
    public Result editCurrentUserPwd(PasswordParam passwordParam) {
        UserVo userVo = UserThreadLocal.get();
        User user = userMapper.selectById(userVo.getId());
        if (passwordEncoder.matches(passwordParam.getOldPassword(), user.getPassword())) {
            userMapper.update(null, new LambdaUpdateWrapper<User>().eq(User::getId, userVo.getId()).set(User::getPassword, passwordEncoder.encode(passwordParam.getNewPassword())));
            return Result.succ(null, 0);
        }
        return Result.fail("密码不正确");
    }

    @Override
    public Result register(RegisterParam registerParam) {
        User user = new User();
        user.setAccount(registerParam.getUsername());
        user.setNickname(registerParam.getUsername());
        User user1 = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getAccount, user.getAccount()));
        if (user1 != null) {
            return Result.fail("账户名重复");
        }
        user.setPassword(passwordEncoder.encode(registerParam.getPassword()));
        user.setEmail(registerParam.getEmail());
        user.setRoleId(2L);
        userMapper.insert(user);
        return Result.succ(null, 0);
    }

    private UserVo copy(User user) {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }
}
