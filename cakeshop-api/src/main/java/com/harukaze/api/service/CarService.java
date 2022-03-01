package com.harukaze.api.service;

import com.harukaze.api.entity.Car;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.api.vo.Result;
import com.harukaze.api.vo.param.CarParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author doki
 * @since 2021-12-23
 */
public interface CarService extends IService<Car> {

    Result getCarList(Long userId);

    Result getCount(Long userId);

    Result saveCar(CarParam carParam);

    Result removeCar(Long carId);

    void clearAll(Long userId);
}
