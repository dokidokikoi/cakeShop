package com.harukaze.api.service.impl;

import com.harukaze.api.entity.Type;
import com.harukaze.api.mapper.TypeMapper;
import com.harukaze.api.service.TypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {
    @Autowired
    TypeMapper typeMapper;

    @Override
    public Result findAllType() {
        List<Type> typeList = typeMapper.selectList(null);
        return Result.succ(typeList, typeList.size());
    }
}
