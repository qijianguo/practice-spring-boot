package com.qijianguo.service.impl;

import com.qijianguo.service.CustomWebService;
import org.springframework.stereotype.Component;

/**接口实现
 * @作者 Administrator
 * @创建日期 2018/6/23 0023
 * @创建时间 11:26.
 */
@javax.jws.WebService(serviceName = "CustomWebService",//与前面接口一致
        targetNamespace = "http://www.CustomWebService.service.qijianguo.com",  //与前面接口一致
        endpointInterface = "com.qijianguo.service.CustomWebService")  //接口地址
@Component
public class CustomWebServiceImpl implements CustomWebService {
    @Override
    public String feedback(String feedback) {
        return "feedback!!! --->"+feedback;
    }
}