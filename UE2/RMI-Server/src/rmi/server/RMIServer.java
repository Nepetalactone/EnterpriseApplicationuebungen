/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.server;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

/**
 *
 * @author Tobias
 */
public class RMIServer {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException {
        
        /*System.setSecurityManager(new RMISecurityManager());
        Properties props = new Properties();
        props.put("java.security.policy", "/myrmi/myrmi.policy");*/
        
        Registry registry = LocateRegistry.createRegistry(33333);
        ATMFactory factory = new ATMFactory();
        IATMFactory factoryStub = (IATMFactory) UnicastRemoteObject.exportObject(factory, 0);
        registry.rebind("Factory", factoryStub);
        
        System.out.print("RMIServer up and running");
    }
}
