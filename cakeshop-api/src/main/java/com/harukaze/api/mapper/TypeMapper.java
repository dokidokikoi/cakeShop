package com.harukaze.api.mapper;

import com.harukaze.api.entity.Type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author doki
 * @since 2021-12-22
 */
public interface TypeMapper extends BaseMapper<Type> {

    @Select("select t.* from t_type t, t_goods_type gt where gt.goods_id = #{id} and gt.type_id = t.id")
    List<Type> selectListByGoodsId(Long id);
}
