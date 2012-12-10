/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FruitBasketBean;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Tobias
 */
@Remote
public interface FruitBasketBeanRemote {

    void addFruit(int fruitID);

    void removeFruit(int fruitID);

    boolean buyAllFruits();

    Integer getFruitAmount();

    Integer getTotalCost();

    String getBasketContent();
    
    void changeFruitShop(int fruitShopID);
    
    List<String> getFruitShops();
    
}
