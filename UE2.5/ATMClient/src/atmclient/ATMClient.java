/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atmclient;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tobias
 */
public class ATMClient {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            
            Registry registry = LocateRegistry.getRegistry(1099);
            IATMFactory factory = (IATMFactory) registry.lookup("Factory");
            IATM atmStub = factory.createATM();
            
            // get initial account balance
            System.out.println("Initial Balances");
            System.out.println("Balance(1): " + atmStub.getBalance(1));
            System.out.println("Balance(2): " + atmStub.getBalance(2));
            System.out.println("Balance(3): " + atmStub.getBalance(3));
            System.out.println();
            // make €1000 depoist in account 1 and get new balance
            System.out.println("Depositting(1): 1000 ");
            atmStub.deposit(1, 1000);
            System.out.println("Balance(1): " + atmStub.getBalance(1));
            // make €100 withdrawal from account 2 and get new balance
            System.out.println("Withdrawing(2): 100 ");
            atmStub.withdraw(2, 100);
            System.out.println("Balance(2): " + atmStub.getBalance(2));
            // make €500 deposit in account 3 and get new balance
            System.out.println("Depositting(3): 500 ");
            atmStub.deposit(3, 500);
            System.out.println("Balance(3): " + atmStub.getBalance(3));
            // get final account balance
            System.out.println();
            System.out.println("Final Balances");
            System.out.println("Balance(1): " + atmStub.getBalance(1));
            System.out.println("Balance(2): " + atmStub.getBalance(2));
            System.out.println("Balance(3): " + atmStub.getBalance(3));
            
        } catch (NotBoundException | RemoteException ex) {
            Logger.getLogger(ATMClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
