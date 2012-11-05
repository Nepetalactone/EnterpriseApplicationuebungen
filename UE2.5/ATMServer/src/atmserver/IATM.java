/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atmserver;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Tobias
 */
public interface IATM extends Remote{
    public void deposit(int accountNo, float amount) throws RemoteException;
    public void withdraw(int accountNo, float amount) throws RemoteException;
    public float getBalance(int accountNo) throws RemoteException;
}
