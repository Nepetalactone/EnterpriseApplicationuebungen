/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package publisher;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;


/**
 *
 * @author Tobias
 */
public class Publisher {

    @Resource(mappedName = "topic/ConnectionFactory")
    private static TopicConnectionFactory connectionFactory;
    @Resource(mappedName = "topic/topic0")
    private static Topic topic;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MessageProducer messageProducer;
        TextMessage textMessage;
        try {
            Connection connection = connectionFactory.createTopicConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(topic);
            textMessage = session.createTextMessage();
            textMessage.setText("asdfasdf");
            messageProducer.send(textMessage);
            System.out.println("asdfasdf");
            messageProducer.close();
            session.close();
            connection.close();
        } catch (JMSException ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
