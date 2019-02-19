
package localhost._8888;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "AsyncImpl", targetNamespace = "http://localhost:8888/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface AsyncImpl {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "pollForResults", targetNamespace = "http://localhost:8888/", className = "localhost._8888.PollForResults")
    @ResponseWrapper(localName = "pollForResultsResponse", targetNamespace = "http://localhost:8888/", className = "localhost._8888.PollForResultsResponse")
    @Action(input = "http://localhost:8888/AsyncImpl/pollForResultsRequest", output = "http://localhost:8888/AsyncImpl/pollForResultsResponse")
    public String pollForResults(
        @WebParam(name = "arg0", targetNamespace = "")
        List<Integer> arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "pollForResult", targetNamespace = "http://localhost:8888/", className = "localhost._8888.PollForResult")
    @ResponseWrapper(localName = "pollForResultResponse", targetNamespace = "http://localhost:8888/", className = "localhost._8888.PollForResultResponse")
    @Action(input = "http://localhost:8888/AsyncImpl/pollForResultRequest", output = "http://localhost:8888/AsyncImpl/pollForResultResponse")
    public String pollForResult(
        @WebParam(name = "arg0", targetNamespace = "")
        Integer arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.Integer
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addDataRequest", targetNamespace = "http://localhost:8888/", className = "localhost._8888.AddDataRequest")
    @ResponseWrapper(localName = "addDataRequestResponse", targetNamespace = "http://localhost:8888/", className = "localhost._8888.AddDataRequestResponse")
    @Action(input = "http://localhost:8888/AsyncImpl/addDataRequestRequest", output = "http://localhost:8888/AsyncImpl/addDataRequestResponse")
    public Integer addDataRequest(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
