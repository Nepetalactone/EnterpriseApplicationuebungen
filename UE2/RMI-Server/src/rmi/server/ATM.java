/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.server;

import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.rmi.RemoteException;

/**
 *
 * @author Tobias
 */
public class ATM extends UnicastRemoteObject implements IATM{
    
    HashMap<Integer, Account> balance;
    
    public ATM()throws RemoteException{
        balance = new HashMap();
        balance.put(1, new Account(0));
        balance.put(2, new Account(100));
        balance.put(3, new Account(500));
    }
    
    public float getBalance(int accountNo){
        return balance.get(accountNo).getBalance();
    }

    @Override
    public void deposit(int accountNo, float amount) {
        balance.get(accountNo).setBalance(balance.get(accountNo).getBalance() + amount);
    }

    @Override
    public void withdraw(int accountNo, float amount) {
        balance.get(accountNo).setBalance(balance.get(accountNo).getBalance() - amount);
    }
    
}
