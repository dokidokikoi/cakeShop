package com.harukaze.api.controller.admin;

import com.harukaze.api.util.QiniuUtil;
import com.harukaze.api.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @PackageName: com.harukaze.front.controller
 * @ClassName: FileController
 * @Description:
 * @Author: doki
 * @Date: 3/12/2021 2:33 PM
 */
@Api(tags = "上传文件接口")
@RestController
@RequestMapping("/upload")
public class FileController {
    @Autowired
    private QiniuUtil qiniuUtil;

    @ApiOperation(value = "上传图片", notes = "上传图片")
    @PostMapping
    public Result upload(@RequestParam("image") MultipartFile file){
        String name = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + "." + name.split("\\.")[1];
        boolean flag = qiniuUtil.upload(file, fileName);
        if (flag) {
            return Result.succ(QiniuUtil.url + fileName, 0);
        }
        return Result.fail("上传失败");
    }
}
