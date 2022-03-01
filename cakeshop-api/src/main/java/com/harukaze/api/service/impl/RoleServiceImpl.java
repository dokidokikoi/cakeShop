package com.harukaze.api.service.impl;

import com.harukaze.api.entity.Role;
import com.harukaze.api.mapper.RoleMapper;
import com.harukaze.api.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.api.util.UserThreadLocal;
import com.harukaze.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Override
    public Result findCurrentRole() {
        Role role = roleMapper.selectRoleByUserId(UserThreadLocal.get().getId());
        return Result.succ(role, 1);
    }
}
