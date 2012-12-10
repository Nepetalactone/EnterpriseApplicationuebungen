/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FruitBasketBean;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tobias
 */
@Stateless
public class FruitBasketBean implements FruitBasketBeanRemote, FruitBasketBeanLocal {

    @PersistenceContext(unitName = "FruitShop_PersistenceUnit")
    private EntityManager entityManager;
    private HashMap<Integer, Fruit> content;
    private FruitShopEntity currentFruitShop;
    private boolean initialized = false;

    public FruitBasketBean() {
        content = new HashMap<Integer, Fruit>();
    }

    @Override
    public void addFruit(int fruitID) {
    }

    @Override
    public void removeFruit(int fruitID) {
    }

    @Override
    public boolean buyAllFruits() {
        return false;
    }

    @Override
    public Integer getFruitAmount() {
        return null;
    }

    @Override
    public Integer getTotalCost() {
        return null;
    }

    @Override
    public String getBasketContent() {
        return null;
    }

    @Override
    public List<String> getFruitShops() {
        if (initialized == false){
            initFruitShops();
            initialized = true;
        }
        return entityManager.createQuery("Select * from FruitShop").getResultList();
    }

    @Override
    public void changeFruitShop(int fruitShopID) {
        this.currentFruitShop = entityManager.createQuery("Select * from FruitShop where id=" + fruitShopID, FruitShopEntity.class).getSingleResult();
    }

    private void initFruitShops() {
        FruitShopEntity shop1 = new FruitShopEntity(1, "FirstShop");
        entityManager.persist(shop1);
        distributeFruits(shop1);
        FruitShopEntity shop2 = new FruitShopEntity(2, "SecondShop");
        entityManager.persist(shop1);
        distributeFruits(shop2);
        FruitShopEntity shop3 = new FruitShopEntity(3, "ThirdShop");
        entityManager.persist(shop3);
        distributeFruits(shop3);

    }

    private void distributeFruits(FruitShopEntity shop) {
        Random rand = new Random();

        Apple apple = new Apple();
        apple.setName("generic Apple");
        apple.setNumber(rand.nextInt(100));
        apple.setPrice(30);
        entityManager.persist(apple);
        shop.addFruit(apple);

        Banana banana = new Banana();
        banana.setName("generic Banana");
        banana.setNumber(rand.nextInt(100));
        banana.setPrice(35);
        entityManager.persist(banana);
        shop.addFruit(banana);

        Pear pear = new Pear();
        pear.setName("generic Pear");
        pear.setNumber(rand.nextInt(100));
        pear.setPrice(40);
        entityManager.persist(pear);
        shop.addFruit(pear);

    }
}
