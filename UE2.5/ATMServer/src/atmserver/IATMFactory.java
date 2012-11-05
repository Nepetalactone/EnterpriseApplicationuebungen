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
public interface IATMFactory extends Remote{
    public IATM createATM() throws RemoteException;
}
