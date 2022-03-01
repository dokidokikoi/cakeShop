package com.harukaze.generator.service.impl;

import com.harukaze.generator.entity.User;
import com.harukaze.generator.mapper.UserMapper;
import com.harukaze.generator.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
