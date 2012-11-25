/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package subscriber;

import java.beans.ExceptionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Tobias
 */
public class Subscriber implements MessageListener, ExceptionListener{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            InitialContext ctx = new InitialContext();
            Topic topic = (Topic) ctx.lookup(args[1]);
            TopicConnectionFactory conFactory = (TopicConnectionFactory) ctx.lookup("topic/connectionFactory");
            TopicConnection topicCon = conFactory.createTopicConnection();
            TopicSession topicSession = topicCon.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            TopicSubscriber topicSubscriber = topicSession.createDurableSubscriber(topic, topic.getTopicName()); //Topic + name of subscription
            Subscriber asyncSubscriber = new Subscriber();
            topicSubscriber.setMessageListener(asyncSubscriber);
            topicCon.setExceptionListener(asyncSubscriber);
            topicCon.start();
        } catch (JMSException ex) {
            Logger.getLogger(Subscriber.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Subscriber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void exceptionThrown(Exception excptn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onMessage(Message msg) {
        try {
            TextMessage message = (TextMessage) msg;
            System.out.println(message.getText());
        } catch (JMSException ex) {
            Logger.getLogger(Subscriber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
