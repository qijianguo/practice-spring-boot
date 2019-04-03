
package com.qijianguo.service.webservice.server;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.qijianguo.service.webservice.server package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FeedbackResponse_QNAME = new QName("http://www.CustomWebService.service.qijianguo.com", "feedbackResponse");
    private final static QName _Feedback_QNAME = new QName("http://www.CustomWebService.service.qijianguo.com", "feedback");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.qijianguo.service.webservice.server
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Feedback }
     * 
     */
    public Feedback createFeedback() {
        return new Feedback();
    }

    /**
     * Create an instance of {@link FeedbackResponse }
     * 
     */
    public FeedbackResponse createFeedbackResponse() {
        return new FeedbackResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FeedbackResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.CustomWebService.service.qijianguo.com", name = "feedbackResponse")
    public JAXBElement<FeedbackResponse> createFeedbackResponse(FeedbackResponse value) {
        return new JAXBElement<FeedbackResponse>(_FeedbackResponse_QNAME, FeedbackResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Feedback }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.CustomWebService.service.qijianguo.com", name = "feedback")
    public JAXBElement<Feedback> createFeedback(Feedback value) {
        return new JAXBElement<Feedback>(_Feedback_QNAME, Feedback.class, null, value);
    }

}
