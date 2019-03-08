package com.qijianguo.service.model.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.annotation.XmlElement;

public class OrderRequest {

    public String lotteryType;

    public String phase;

    @JacksonXmlProperty(localName = "lotterytype")
    public String getLotteryType() {
        return lotteryType;
    }

    public void setLotteryType(String lotteryType) {
        this.lotteryType = lotteryType;
    }

    @JacksonXmlProperty(localName = "phase")
    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }
}