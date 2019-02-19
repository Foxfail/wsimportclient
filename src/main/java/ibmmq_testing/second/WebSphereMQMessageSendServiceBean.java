package ibmmq_testing.second;



import com.ibm.mq.jms.MQDestination;
import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.jms.*;


/**
 * User: Nuwan.N.Panane
 */
@Stateless(name = "WebSphereMQMessageSendService")
public class WebSphereMQMessageSendServiceBean {


    private static Logger logger = Logger.getLogger(WebSphereMQMessageSendServiceBean.class);
    private Connection connection = null;
    private Session session = null;
    private MessageProducer producer = null;


    public static void main(String[] args) {
        BasicConfigurator.configure();
        WebSphereMQMessageSendServiceBean webSphereMQMessageSendServiceBean = new WebSphereMQMessageSendServiceBean();
        webSphereMQMessageSendServiceBean.sendMessage("message", "id");
        webSphereMQMessageSendServiceBean.cleanUp();
    }

    public boolean sendMessage(String xmlMsg, String instrumentId) {
        boolean success = false;
        String destinationType = "QUEUE";
        String destinationName = "PRO.QDX.QDX.MSG.QDC";
        String channel = "PROQDIB.SVRCONN";
        String hostName = "localhost";
        Integer port = 1414;
        String queueManager = "REFDMQ01";
        String uid = "nuwan";
        String provider = "com.ibm.msg.client.wmq";
        try {
            JmsFactoryFactory jmsFactoryFactory = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
            JmsConnectionFactory jmsConnectionFactory = jmsFactoryFactory.createConnectionFactory();
            // Set the properties
            jmsConnectionFactory.setStringProperty(WMQConstants.WMQ_HOST_NAME, hostName);
            jmsConnectionFactory.setIntProperty(WMQConstants.WMQ_PORT, port);
            jmsConnectionFactory.setStringProperty(WMQConstants.WMQ_CHANNEL, channel);
            jmsConnectionFactory.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
            jmsConnectionFactory.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, queueManager);


            // Create JMS objects
            connection = jmsConnectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = null;
            if (destinationType.equals("QUEUE")) {
                destination = session.createQueue(destinationName);
            } else {
                destination = session.createTopic(destinationName);
            }
            producer = session.createProducer(destination);
            //connection.setExceptionListener(this);
            connection.start();
            TextMessage message = session.createTextMessage();
            // If WMQ_MESSAGE_BODY is set to WMQ_MESSAGE_BODY_MQ, no additional header is added to the message body.
            ((MQDestination) destination).setMessageBodyStyle(WMQConstants.WMQ_MESSAGE_BODY_MQ);
            ((MQDestination) destination).setMQMDWriteEnabled(true);
            message.setText(xmlMsg);
            message.setJMSCorrelationID(instrumentId);
            producer.send(message);
            success = true;
            logger.info("WebSphereMQMessageSender.sendMessage: Sent message:\n" + message);
        } catch (JMSException e) {
            logger.error("WebSphereMQMessageSender.sendMessage: JMSException while sending message to QDIB", e);
            success = false;
            recordFailure(e);
        } catch (Exception e) {
            logger.error("WebSphereMQMessageSender.sendMessage: Exception while sending message to QDIB", e);
            success = false;
        } finally {
            cleanUp();
        }
        return success;
    }


    /**
     * Record this run as failure.
     *
     * @param ex exception
     */
    private void recordFailure(Exception ex) {
        if (ex != null) {
            if (ex instanceof JMSException) {
                processJMSException((JMSException) ex);
            } else {
                logger.error("WebSphereMQMessageSender.recordFailure: " + ex);
            }
        }
        logger.error("WebSphereMQMessageSender.recordFailure: FAILURE");
    }


    /**
     * Process a JMSException and any associated inner exceptions.
     *
     * @param jmsex jmsex
     */
    private void processJMSException(JMSException jmsex) {
        logger.error(jmsex);
        Throwable innerException = jmsex.getLinkedException();
        if (innerException != null) {
            logger.error("WebSphereMQMessageSender.processJMSException: Inner exception(s):");
        }
        while (innerException != null) {
            logger.error(innerException);
            innerException = innerException.getCause();
        }
    }


    /**
     * Release resources
     */
    private void cleanUp() {
        if (producer != null) {
            try {
                producer.close();
            } catch (JMSException jmsex) {
                logger.error("WebSphereMQMessageSender. cleanUp: Producer could not be closed.");
                recordFailure(jmsex);
            }
        }
        if (session != null) {
            try {
                session.close();
            } catch (JMSException jmsex) {
                logger.error("WebSphereMQMessageSender. cleanUp: Session could not be closed.");
                recordFailure(jmsex);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (JMSException jmsex) {
                logger.error("WebSphereMQMessageSender. cleanUp: Connection could not be closed.");
                recordFailure(jmsex);
            }
        }
    }
}