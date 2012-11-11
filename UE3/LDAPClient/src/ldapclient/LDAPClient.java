/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ldapclient;

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

/**
 *
 * @author Tobias
 */
public class LDAPClient {
    
    InitialDirContext initContext;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    }
    
    public LDAPClient(){
        try {
            initContext = new InitialDirContext();
            Attributes attrib = initContext.getAttributes("cn=Tobias");
            System.out.println(attrib.getIDs());
        } catch (NamingException ex) {
            Logger.getLogger(LDAPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
