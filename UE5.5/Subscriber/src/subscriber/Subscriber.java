/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package subscriber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        System.out.println("Connection established. Enter 'q' to quit");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String quit = null;
        try {
            quit = reader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Subscriber.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while (quit != "q"){
            try {
                quit = reader.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Subscriber.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        messageConsumer.close();
        System.out.println("MessageConsumer closed");
        session.close();
        System.out.println("Session closed");
        connection.close();
        System.out.println("Connection closed");
    }
}
