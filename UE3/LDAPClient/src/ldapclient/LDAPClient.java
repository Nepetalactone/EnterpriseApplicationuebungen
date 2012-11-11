/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ldapclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.*;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchResult;

/**
 *
 * @author Tobias
 */
public class LDAPClient {

    InitialDirContext initContext;
    Hashtable env;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            LDAPClient client = new LDAPClient(args);
        } catch (IOException ex) {
            Logger.getLogger(LDAPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LDAPClient(String[] args) throws IOException {
        try {
            env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://" + args[0] + ":10389/o=home");
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, "cn=Tobias, ou=users, o=home");
            env.put(Context.SECURITY_CREDENTIALS, "asdf");
            initContext = new InitialDirContext(env);

            String choice = "help";
            boolean first = true;
            while (!choice.equalsIgnoreCase("exit")) {
                if (first == false) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                    choice = in.readLine();
                }
                first = false;

                switch (choice) {
                    case ("help"):
                        showHelp();
                        break;

                    case ("searchUsers"):
                        searchUsers();
                        break;

                    case ("searchMachines"):
                        searchMachines();
                        break;

                    default:
                        System.out.println("Didn't understand input");
                }
            }
            Attributes attrib = initContext.getAttributes("cn=Tobias, ou=users");
            System.out.println(attrib.get("sn").toString());
        } catch (NamingException ex) {
            Logger.getLogger(LDAPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showHelp() {
        System.out.println("help = shows the available commands\n"
                + "searchUsers = search for the surnames of all users\n"
                + "searchMachine = search for the common names of the devices in the environment\n"
                + "exit = close the program");
    }

    private void searchMachines() {
        try {
            DirContext context = new InitialDirContext(env);
            NamingEnumeration asdf = initContext.list("ou=machines");
            while(asdf.hasMore()){
                SearchResult sr = (SearchResult) asdf.next();
                Attributes attr = sr.getAttributes();
                System.out.println(attr.get("cn"));
            }
            Attributes attrib = initContext.getAttributes("ou=users");
            
        } catch (NamingException ex) {
            Logger.getLogger(LDAPClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void searchUsers() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
