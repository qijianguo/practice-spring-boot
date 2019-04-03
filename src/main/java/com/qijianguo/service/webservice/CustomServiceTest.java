package com.qijianguo.service.webservice;

import com.qijianguo.service.webservice.server.CustomWebService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class CustomServiceTest {

    public static void main(String[] args) throws MalformedURLException {
        //wsdl网络路径
        URL url = new URL("http://localhost:8080/services/CustomWebService?wsdl");
        //服务描述中服务端点的限定名称  两个参数分别为 命名空间 服务名
        QName qName = new QName("http://www.CustomWebService.service.qijianguo.com", "CustomWebService");
        //创建服务对象
        Service service = Service.create(url, qName);
        //获得Hiservice的实现类对象
        CustomWebService hiService = service.getPort(new QName("http://www.CustomWebService.service.qijianguo.com","CustomWebServiceImplPort"), CustomWebService.class);
        //调用WebService方法
        System.out.println(hiService.feedback("xiaoming"));
    }
}
