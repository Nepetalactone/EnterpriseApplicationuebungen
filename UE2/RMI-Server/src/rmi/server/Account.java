/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.server;

/**
 *
 * @author Tobias
 */
public class Account {
    private float balance;
    
    public Account(float balance){
        this.balance = balance;
    }
    
    public float getBalance(){
        return balance;
    }
    
    public void setBalance(float balance){
        this.balance = balance;
    }
}
