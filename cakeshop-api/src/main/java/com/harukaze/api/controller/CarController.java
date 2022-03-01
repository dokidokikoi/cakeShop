package com.harukaze.api.controller;


import com.harukaze.api.service.CarService;
import com.harukaze.api.vo.Result;
import com.harukaze.api.vo.param.GoodsPageParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author doki
 * @since 2021-12-23
 */
@RestController
@RequestMapping("/car")
public class CarController {
}
