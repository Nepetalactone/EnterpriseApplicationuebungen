/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atmserver;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tobias
 */
public class ATMServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, MalformedURLException{
        try {
            
            Registry registry = LocateRegistry.createRegistry(1099);
            ATMFactory factory = new ATMFactory();
            System.out.println("Server up and running");
        } catch (RemoteException ex) {
            Logger.getLogger(ATMServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
