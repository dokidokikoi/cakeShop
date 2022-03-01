package com.harukaze.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.api.entity.Goods;
import com.harukaze.api.vo.GoodsVo;
import com.harukaze.api.vo.Result;
import com.harukaze.api.vo.param.GoodsPageParam;
import com.harukaze.api.vo.param.GoodsParam;
import com.harukaze.api.vo.param.PageParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author doki
 * @since 2021-12-22
 */
public interface GoodsService extends IService<Goods> {

    Result getGoodsList(GoodsPageParam pageParam, boolean isAll);

    Result getGoodsListByTypeId(Long typeId, PageParam pageParam);

    GoodsVo getGoodsById(Long goodsId);

    Result getGoodsDetailById(Long id);

    Result saveGoods(GoodsParam goodsParam);

    Result editGoods(GoodsParam goodsParam);

    Result deleteGoods(Long id);

    Result resumeGoods(Long id);
}
