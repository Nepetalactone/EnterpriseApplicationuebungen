/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ue65client;

import FruitBasketBean.FruitBasketBeanRemote;
import javax.ejb.EJB;

/**
 *
 * @author Tobias
 */
public class Main {

    @EJB
    private static FruitBasketBeanRemote remote;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(remote.sayHello());
        for (String string : remote.getFruitShops()){
            System.out.println(string);
        }
        
        remote.changeFruitShopByName("FirstShop");
        
        System.out.println("BasketContent1: " + remote.getBasketContent());
        
        System.out.println("FruitAmount1: " + remote.getFruitAmount());
        
        System.out.println("TotalCost1: " + remote.getTotalCost());
        
        System.out.println("CurrentShop: " + remote.getCurrentShopName());
        
        remote.addFruitByName("generic Apple");
        
        remote.addFruitByName("generic Banana");
        
        System.out.println("BasketContent2: " + remote.getBasketContent());
        
        System.out.println("FruitAmount2: " + remote.getFruitAmount());
        
        System.out.println("TotalCost2: " + remote.getTotalCost());
        
        System.out.println("CurrentShop: " + remote.getCurrentShopName());
        
        remote.removeFruitByName("generic Banana");
        
        remote.changeFruitShopByName("SecondShop");
        
        remote.addFruitByName("generic Apple");
        
        remote.addFruitByName("generic Apple");
        
        System.out.println("BasketContent3: " + remote.getBasketContent());
        
        System.out.println("FruitAmount3: " + remote.getFruitAmount());
        
        System.out.println("TotalCost3: " + remote.getTotalCost());
        
        System.out.println("CurrentShop: " + remote.getCurrentShopName());
        
        remote.changeFruitShopByName("ThirdName");
        
        System.out.println("BasketContent4: " + remote.getBasketContent());
        
        System.out.println("FruitAmount4: " + remote.getFruitAmount());
        
        System.out.println("TotalCost4: " + remote.getTotalCost());
        
        System.out.println("CurrentShop: " + remote.getCurrentShopName());
        
        remote.getAvailableFruits();
        
        remote.buyAllFruits();
        
        System.out.println("BasketContent5: " + remote.getBasketContent());
        
        System.out.println("FruitAmount5: " + remote.getFruitAmount());
        
        System.out.println("TotalCost5: " + remote.getTotalCost());
        
        System.out.println("CurrentShop: " + remote.getCurrentShopName());
    }
}
