package com.harukaze.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.api.entity.Goods;
import com.harukaze.api.entity.GoodsType;
import com.harukaze.api.entity.Type;
import com.harukaze.api.mapper.GoodsMapper;
import com.harukaze.api.mapper.GoodsTypeMapper;
import com.harukaze.api.mapper.TypeMapper;
import com.harukaze.api.service.GoodsService;
import com.harukaze.api.vo.GoodsVo;
import com.harukaze.api.vo.Result;
import com.harukaze.api.vo.param.GoodsPageParam;
import com.harukaze.api.vo.param.GoodsParam;
import com.harukaze.api.vo.param.PageParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author doki
 * @since 2021-12-22
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    TypeMapper typeMapper;

    @Autowired
    GoodsTypeMapper goodsTypeMapper;

    @Override
    public Result getGoodsList(GoodsPageParam pageParam, boolean isAll) {
        Page<Goods> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());
        IPage<Goods> goodsIPage = goodsMapper.selectGoodsPage(page,pageParam.getQuery(),pageParam.getType(), isAll);
        List<GoodsVo> goodsVos = copyList(goodsIPage.getRecords());

        return Result.succ(goodsVos, (int) goodsIPage.getTotal());
    }

    @Override
    public Result getGoodsListByTypeId(Long typeId, PageParam pageParam) {
        Page<Goods> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());
        IPage<Goods> goodsIPage = goodsMapper.selectGoodsPageByTypeId(page, typeId);
        List<GoodsVo> goodsVos = copyList(goodsIPage.getRecords());

        return Result.succ(goodsVos, (int) goodsIPage.getTotal());
    }

    @Override
    public GoodsVo getGoodsById(Long goodsId) {
        Goods goods = goodsMapper.selectById(goodsId);
        return copy(goods);
    }

    @Override
    public Result getGoodsDetailById(Long id) {
        GoodsVo goodsById = getGoodsById(id);
        return Result.succ(goodsById, 1);
    }

    @Transactional
    @Override
    public Result saveGoods(GoodsParam goodsParam) {
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsParam, goods);
        goods.setSale(0);
        goods.setCreateDate(System.currentTimeMillis());
        goodsMapper.insert(goods);

        GoodsType goodsType = new GoodsType();
        goodsType.setGoodsId(goods.getId());
        for (Long type : goodsParam.getTypes()) {
            goodsType.setTypeId(type);
            goodsTypeMapper.insert(goodsType);
        }
        return Result.succ(null, 0);
    }

    @Transactional
    @Override
    public Result editGoods(GoodsParam goodsParam) {
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsParam, goods);
        if (goods.getId() == null) {
            return Result.fail("商品id不能为空");
        }
        goodsMapper.updateById(goods);
        goodsTypeMapper.delete(new LambdaQueryWrapper<GoodsType>().eq(GoodsType::getGoodsId, goods.getId()));

        GoodsType goodsType = new GoodsType();
        goodsType.setGoodsId(goods.getId());
        for (Long type : goodsParam.getTypes()) {
            goodsType.setTypeId(type);
            goodsTypeMapper.insert(goodsType);
        }
        return Result.succ(null, 0);
    }

    @Override
    public Result deleteGoods(Long id) {
//        goodsMapper.delete(new LambdaQueryWrapper<Goods>().eq(Goods::getId, id));
        goodsMapper.update(null, new LambdaUpdateWrapper<Goods>().eq(Goods::getId, id).set(Goods::getStatus, 0));
        return Result.succ(null, 0);
    }

    @Override
    public Result resumeGoods(Long id) {
        goodsMapper.update(null, new LambdaUpdateWrapper<Goods>().eq(Goods::getId, id).set(Goods::getStatus, 1));
        return Result.succ(null, 0);
    }

    private List<GoodsVo> copyList(List<Goods> records) {
        List<GoodsVo> goodsVos = new ArrayList<>();
        for (Goods record : records) {
            GoodsVo goodsVo = copy(record);
            goodsVos.add(goodsVo);
        }
        return goodsVos;
    }

    private GoodsVo copy(Goods record) {
        GoodsVo goodsVo = new GoodsVo();
        List<Type> typeList = typeMapper.selectListByGoodsId(record.getId());
        BeanUtils.copyProperties(record, goodsVo);
        goodsVo.setTypes(typeList);
        return goodsVo;
    }
}
