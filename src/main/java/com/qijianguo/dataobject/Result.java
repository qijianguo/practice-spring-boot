package com.qijianguo.dataobject;

import com.qijianguo.commons.ExceptionEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

/**
 * @author Angus
 */
@Data
@NoArgsConstructor
public class Result {

    private String status;

    private Integer code;

    private String message;

    private Object result;

    private Result create(String status, Integer code, String message, Object result) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.result = result;
        return this;
    }

    public static Result success() {
        return new Result().create("ok", ExceptionEnum._200.getCode(), ExceptionEnum._200.getMessage(), null);
    }

    public static Result success(Object result) {
        return new Result().create("ok", ExceptionEnum._200.getCode(), ExceptionEnum._200.getMessage(), result);
    }

    public static Result success(Integer code, String message, Object result) {
        return new Result().create("ok", code, message, result);
    }

    public static Result fail(Integer code, String message) {
        return new Result().create("fail", code, message, Arrays.asList());
    }
}
