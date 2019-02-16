package com.qijianguo.error;

/**
 * @author Angus
 * @version 1.0
 * @Title: CommonError
 * @Description: TODO
 * @date 2019/2/16 12:40
 */
public interface CommonError {

    public int getErrorCode();

    public String getErrorMsg();

    public CommonError setErrorMsg(String errorMsg);
}
