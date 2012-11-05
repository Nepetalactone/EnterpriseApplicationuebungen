/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atmserver;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Tobias
 */
public class ATMFactory extends UnicastRemoteObject implements IATMFactory{

    @Override
    public IATM createATM() throws RemoteException {
        return new ATM();
    }
    
    public ATMFactory() throws RemoteException, MalformedURLException{
        super();
        Naming.rebind("Factory", this);
    }
    
}
