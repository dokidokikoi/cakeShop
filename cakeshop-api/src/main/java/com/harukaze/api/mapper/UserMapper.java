package com.harukaze.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.harukaze.api.entity.Role;
import com.harukaze.api.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author doki
 * @since 2021-12-22
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select("select t_role.* from t_user, t_role where t_user.id = #{id} AND t_user.role_id = t_role.id")
    Role selectRole(Long id);
}
