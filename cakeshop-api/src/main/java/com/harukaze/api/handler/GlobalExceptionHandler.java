package com.harukaze.api.handler;


import com.harukaze.api.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @PackageName: com.harukaze.front.handler
 * @ClassName: GlobalExceptionHandler
 * @Description:
 * @Author: doki
 * @Date: 19/12/2021 11:07 AM
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e) {
        System.out.println(e);
        return Result.fail(e.getMessage());
    }
}
