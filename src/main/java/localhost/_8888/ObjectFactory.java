
package localhost._8888;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the localhost._8888 package. 
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

    private final static QName _AddDataRequest_QNAME = new QName("http://localhost:8888/", "addDataRequest");
    private final static QName _AddDataRequestResponse_QNAME = new QName("http://localhost:8888/", "addDataRequestResponse");
    private final static QName _PollForResultResponse_QNAME = new QName("http://localhost:8888/", "pollForResultResponse");
    private final static QName _PollForResult_QNAME = new QName("http://localhost:8888/", "pollForResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: localhost._8888
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddDataRequestResponse }
     * 
     */
    public AddDataRequestResponse createAddDataRequestResponse() {
        return new AddDataRequestResponse();
    }

    /**
     * Create an instance of {@link PollForResultResponse }
     * 
     */
    public PollForResultResponse createPollForResultResponse() {
        return new PollForResultResponse();
    }

    /**
     * Create an instance of {@link PollForResult }
     * 
     */
    public PollForResult createPollForResult() {
        return new PollForResult();
    }

    /**
     * Create an instance of {@link AddDataRequest }
     * 
     */
    public AddDataRequest createAddDataRequest() {
        return new AddDataRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddDataRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://localhost:8888/", name = "addDataRequest")
    public JAXBElement<AddDataRequest> createAddDataRequest(AddDataRequest value) {
        return new JAXBElement<AddDataRequest>(_AddDataRequest_QNAME, AddDataRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddDataRequestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://localhost:8888/", name = "addDataRequestResponse")
    public JAXBElement<AddDataRequestResponse> createAddDataRequestResponse(AddDataRequestResponse value) {
        return new JAXBElement<AddDataRequestResponse>(_AddDataRequestResponse_QNAME, AddDataRequestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PollForResultResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://localhost:8888/", name = "pollForResultResponse")
    public JAXBElement<PollForResultResponse> createPollForResultResponse(PollForResultResponse value) {
        return new JAXBElement<PollForResultResponse>(_PollForResultResponse_QNAME, PollForResultResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PollForResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://localhost:8888/", name = "pollForResult")
    public JAXBElement<PollForResult> createPollForResult(PollForResult value) {
        return new JAXBElement<PollForResult>(_PollForResult_QNAME, PollForResult.class, null, value);
    }

}
