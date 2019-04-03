package com.qijianguo.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
/**
 * WebService接口
 * @作者 Administrator
 * @创建日期 2018/6/23 0023
 * @创建时间 11:21.
 */
@javax.jws.WebService(name = "CustomWebService", // 暴露服务名称
        targetNamespace = "http://www.CustomWebService.service.qijianguo.com")   //命名空间,一般是接口的包名倒序
public interface CustomWebService {
    @WebMethod
    @WebResult(name = "String",targetNamespace = "")
    public String feedback(@WebParam(name = "feedback") String feedback);
}