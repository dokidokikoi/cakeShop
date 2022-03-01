package com.harukaze.api.mapper;

import com.harukaze.api.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author doki
 * @since 2021-12-22
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select r.* from t_user u, t_role r where u.id = #{id} and u.role_id = r.id")
    Role selectRoleByUserId(Long id);
}
