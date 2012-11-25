/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package subscriber;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author Tobias
 */
public class TopicMessageListener implements MessageListener{

    @Override
    public void onMessage(Message msg) {
        TextMessage message = (TextMessage) msg;
        try {
            System.out.println(message.getText());
        } catch (JMSException ex) {
            Logger.getLogger(TopicMessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
