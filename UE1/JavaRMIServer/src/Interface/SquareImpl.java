/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.rmi.RemoteException;

/**
 *
 * @author Tobias
 */
public class SquareImpl implements ISquare{

    @Override
    public int calculateSquare(int number) throws RemoteException {
        return number*number;
    }
    
}
