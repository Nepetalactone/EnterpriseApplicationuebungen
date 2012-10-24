/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Interface.ISquare;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tobias
 */
public class Client {
    private static ISquare stub = null;
    private Client(){};
    
    public static void main(String[] args){
        
        try{
        
        }catch(Exception e){
            System.out.println("Malformed input");
        }
        
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 33333);
            stub = (ISquare)registry.lookup("Square");
        } catch (NotBoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AccessException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            int number = Integer.parseInt(args[0]);
            System.out.println(stub.calculateSquare(number));
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
