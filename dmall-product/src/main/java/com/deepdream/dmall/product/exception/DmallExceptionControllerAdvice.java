package com.deepdream.dmall.product.exception;

import com.deepdream.common.exception.BizCodeEnum;
import com.deepdream.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangkai
 */
@RestControllerAdvice(basePackages = {
        "com.deepdream.dmall.product.controller"
})
@Slf4j
public class DmallExceptionControllerAdvice {
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public R handlerValidException(MethodArgumentNotValidException e){
//        log.error("数据校验出现异常:{},异常类型:{}",e.getMessage(),e.getClass());
        BindingResult result = e.getBindingResult();
        Map<String,String> errorMap = new HashMap<>();
        if(result.hasErrors()){
            result.getFieldErrors().forEach((item)->{
                errorMap.put(item.getField(),item.getDefaultMessage());
            });
        }
        return R.error(BizCodeEnum.VALID_EXCEPTION.getCode(),BizCodeEnum.VALID_EXCEPTION.getMsg()).put("data",errorMap);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable){
        log.error("错误 : {}",throwable.getMessage());
        throwable.printStackTrace();
        return R.error(BizCodeEnum.UNKNOW_EXCEPTION.getCode(),BizCodeEnum.UNKNOW_EXCEPTION.getMsg());
    }
}
