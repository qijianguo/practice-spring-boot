package com.qijianguo.intercepter;

import com.qijianguo.commons.ExceptionEnum;
import com.qijianguo.dataobject.Result;
import com.qijianguo.error.BusinessException;
import com.qijianguo.error.EmBusinessError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Result handleBusinessException(BusinessException e) {
        log.error(e.getMessage(), e);
        return Result.fail(e.getErrorCode(), e.getErrorMsg());
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Result handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        FieldError fieldError = e.getFieldError();
        String defaultMessage = fieldError.getDefaultMessage();
        return Result.fail(ExceptionEnum._400.getCode(), defaultMessage);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.fail(EmBusinessError.UNKNOW_ERROR.getErrorCode(), EmBusinessError.UNKNOW_ERROR.getErrorMsg());
    }
}
