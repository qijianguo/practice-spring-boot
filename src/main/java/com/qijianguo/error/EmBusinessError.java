package com.qijianguo.error;

/**
 * @author Angus
 * @version 1.0
 * @Title: EmBusinessError
 * @Description: TODO
 * @date 2019/2/16 12:43
 */
public enum  EmBusinessError implements CommonError {

    // 10000开头是通用错误类型
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
    UNKNOW_ERROR(10002, "未知错误"),
    NOT_FOUND_RESOURCE(10003, "无对应数据"),

    /* 20000开头是用户模块错误码 */
    USER_NOT_EXIST(20001, "用户不存在"),
    USER_ALREADY_EXIST(20002, "手机号已注册，请登录"),
    USER_LOGIN_FAIL(20003, "用户名或密码错误"),
    USER_NOT_LOGIN(20004, "用户未登陆"),

    /* 30000开头为订单模块错误码*/
    STOCK_NOT_ENOUGH(30001, "库存不足"),


    ;


    private int errorCode;

    private String errorMsg;

    private EmBusinessError(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }
}
