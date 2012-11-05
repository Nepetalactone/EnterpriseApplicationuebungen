/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Tobias
 */
public class ATMFactory extends UnicastRemoteObject implements IATMFactory{
    
    private IATM atmStub;
    private ATM atm;
    
    @Override
    public IATM createATM() throws RemoteException {     
        return atmStub;
    }
    
    public ATMFactory() throws RemoteException{
        atm = new ATM();
        atmStub = (IATM) UnicastRemoteObject.exportObject(atm, 0);
        Registry registry = LocateRegistry.getRegistry("localhost", 33333);
        registry.rebind("ATM", atmStub);
    }
    
}
