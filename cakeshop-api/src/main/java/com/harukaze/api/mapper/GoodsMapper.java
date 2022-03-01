package com.harukaze.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.harukaze.api.entity.Goods;
import com.harukaze.api.vo.param.GoodsPageParam;
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
public interface GoodsMapper extends BaseMapper<Goods> {

    IPage<Goods> selectGoodsPage(Page<Goods> page, String query, String type, boolean isAll);

    IPage<Goods> selectGoodsPageByTypeId(Page<Goods> page, Long typeId);
}
