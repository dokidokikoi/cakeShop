package com.harukaze.api.service;

import com.harukaze.api.entity.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.api.vo.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author doki
 * @since 2021-12-22
 */
public interface TypeService extends IService<Type> {

    Result findAllType();
}
