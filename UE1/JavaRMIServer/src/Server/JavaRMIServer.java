/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Interface.ISquare;
import Interface.SquareImpl;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tobias
 */
public class JavaRMIServer {

    public JavaRMIServer() {
    }

    ;
    
    public static void main(String[] args) {
        try {
            SquareImpl square = new SquareImpl();
            ISquare stub = (ISquare) UnicastRemoteObject.exportObject(square, 0);

            Registry registry = LocateRegistry.createRegistry(33333);
            registry.rebind("Square", stub);

            System.out.println("Square Server is ready to listen...");

        } catch (RemoteException ex) {
            Logger.getLogger(JavaRMIServer.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
