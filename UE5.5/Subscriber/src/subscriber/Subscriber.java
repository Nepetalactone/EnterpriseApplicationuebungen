/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package subscriber;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;

/**
 *
 * @author Tobias
 */
public class Subscriber {

        @Resource(mappedName = "topic/ConnectionFactory")
        private static TopicConnectionFactory connectionFactory;
        @Resource(mappedName = "topic/topic0")
        private static Topic topic;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JMSException {
        Connection connection;
        MessageConsumer messageConsumer;
        
        connection = connectionFactory.createTopicConnection();
        connection.setClientID("tobi");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        messageConsumer = session.createDurableSubscriber(topic, "topic0");
        messageConsumer.setMessageListener(new TopicMessageListener());
        connection.start();
        
        while (true){
            
        }
    }
}
