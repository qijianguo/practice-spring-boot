package com.qijianguo.controller;

import com.qijianguo.service.model.xml.OrderResponse;
import com.qijianguo.service.model.xml.TicketRequest;
import com.qijianguo.service.model.xml.TicketResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/xml")
public class SscController {

    @PostMapping(value = "/test", consumes = { MediaType.APPLICATION_XML_VALUE }, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public TicketResponse test(@RequestBody TicketRequest ticketRequest){
        TicketResponse ticketResponse=new TicketResponse();
        List<OrderResponse> orders=new ArrayList<OrderResponse>();
        OrderResponse o=new OrderResponse();
        o.setMsg("投注成功");
        orders.add(o);
        ticketResponse.setOrderList(orders);
        return ticketResponse;
    }

}