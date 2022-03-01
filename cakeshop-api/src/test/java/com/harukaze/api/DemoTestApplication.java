package com.harukaze.api;

import cn.hutool.json.JSONUtil;
import com.harukaze.api.service.GoodsService;
import com.harukaze.api.vo.param.GoodsPageParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @PackageName: com.harukaze.api
 * @ClassName: DemoTestApplication
 * @Description:
 * @Author: doki
 * @Date: 22/12/2021 3:33 PM
 */
@SpringBootTest
public class DemoTestApplication {
    @Autowired
    GoodsService goodsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void test() {
        GoodsPageParam pageParam = new GoodsPageParam();
        pageParam.setPageNum(0);
        pageParam.setPageSize(5);
//        pageParam.setQuery("草");

        System.out.println(JSONUtil.toJsonStr(goodsService.getGoodsList(pageParam, false)));
    }

    @Test
    public void test2() {
        System.out.println(passwordEncoder.encode("12345678"));
        System.out.println(passwordEncoder.matches("12345678", "$2a$10$CwpS0vwUTfmwqGp6waWRnepUiF4KSTmTbxNh.ryGulsV8K0oRIIPy"));
    }

}
