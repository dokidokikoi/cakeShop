package com.harukaze.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.harukaze.api.entity.Car;
import com.harukaze.api.entity.Goods;
import com.harukaze.api.mapper.CarMapper;
import com.harukaze.api.service.CarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.api.service.GoodsService;
import com.harukaze.api.service.UserService;
import com.harukaze.api.util.UserThreadLocal;
import com.harukaze.api.vo.CarVo;
import com.harukaze.api.vo.Result;
import com.harukaze.api.vo.param.CarParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author doki
 * @since 2021-12-23
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {
    @Autowired
    CarMapper carMapper;

    @Autowired
    GoodsService goodsService;

    @Autowired
    UserService userService;

    @Override
    public Result getCarList(Long userId) {
        List<Car> cars = carMapper.selectList(new LambdaQueryWrapper<Car>().eq(Car::getUserId, userId));
        List<CarVo> carVos = copyList(cars);
        return Result.succ(carVos, carVos.size());
    }

    @Override
    public Result getCount(Long userId) {
        return Result.succ(carMapper.selectCount(new LambdaQueryWrapper<Car>().eq(Car::getUserId, userId)), 0);
    }

    @Override
    public Result saveCar(CarParam carParam) {
        Car car = new Car();
        BeanUtils.copyProperties(carParam, car);
        car.setCreateDate(System.currentTimeMillis());
        carMapper.insert(car);
        return Result.succ(null,0);
    }

    @Override
    public Result removeCar(Long carId) {
        Car car = carMapper.selectOne(new LambdaQueryWrapper<Car>().eq(Car::getId, carId).eq(Car::getUserId, UserThreadLocal.get().getId()));
        if (car == null) {
            return Result.fail("错误");
        }
        carMapper.deleteById(carId);
        return Result.succ(null, 0);
    }

    @Override
    public void clearAll(Long userId) {
        carMapper.delete(new LambdaQueryWrapper<Car>().eq(Car::getUserId, userId));
    }

    private List<CarVo> copyList(List<Car> cars) {
        List<CarVo> carVos = new ArrayList<>();
        for (Car car : cars) {
            CarVo carVo = copy(car);
            carVos.add(carVo);
        }
        return carVos;
    }

    private CarVo copy(Car car) {
        CarVo carVo = new CarVo();
        BeanUtils.copyProperties(car, carVo);
        carVo.setGoods(goodsService.getGoodsById(car.getGoodsId()));
        carVo.setUser(userService.getUserById(car.getUserId()));
        return carVo;
    }
}
