/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ue6client;

import FruitBasketBean.FruitBasketBeanRemote;

/**
 *
 * @author Tobias
 */
public class Main {

    private static FruitBasketBeanRemote remote;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for (String string : remote.getFruitShops()){
            System.out.println(string);
        }
    }
}
