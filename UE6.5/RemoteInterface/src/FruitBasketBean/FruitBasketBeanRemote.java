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

    Integer getTotalCost();

    Boolean buyAllFruits();

    Integer getFruitAmount();

    String getBasketContent();

    List<String> getFruitShops();
    
     void changeFruitShop(int fruitShopID);
    
}
