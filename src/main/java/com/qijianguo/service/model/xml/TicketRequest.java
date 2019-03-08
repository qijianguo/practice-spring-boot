package com.qijianguo.service.model.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@JacksonXmlRootElement(localName ="message")
public class TicketRequest {

    @JacksonXmlElementWrapper(localName ="orderlist")
    @JacksonXmlProperty(localName ="order")
    private List<OrderRequest> orderList;

    public List<OrderRequest> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderRequest> orderList) {
        this.orderList = orderList;
    }

}