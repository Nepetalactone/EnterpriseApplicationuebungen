/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Tobias
 */
public interface ISquare extends Remote{
    public int calculateSquare(int number) throws RemoteException;
}
