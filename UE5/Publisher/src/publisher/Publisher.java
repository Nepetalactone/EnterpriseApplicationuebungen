/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package publisher;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Tobias
 */
public class Publisher {

    
    @Resource(mappedName = "topic/ConnectionFactory")
    private static TopicConnectionFactory conFactory;
    @Resource(mappedName = "topic/topic0")
    private static Topic topic;
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            InitialContext ctx = new InitialContext();
            Topic topic = (Topic) ctx.lookup("topic/topic0");
            conFactory = (TopicConnectionFactory) ctx.lookup("topic/ConnectionFactory"); 
            TopicConnection topicCon = conFactory.createTopicConnection();
            TopicSession topicSession = topicCon.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            TopicPublisher topicPublisher = topicSession.createPublisher(topic);
            topicPublisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            TextMessage message = topicSession.createTextMessage();
            message.setText(args[1]);
            topicPublisher.publish(message);
            System.out.println("published message: " + message.getText());
            topicCon.close();
        } catch (NamingException ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
