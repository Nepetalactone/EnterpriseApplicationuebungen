/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FruitBasketBean;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Tobias
 */
@Stateful
public class FruitBasketBean implements FruitBasketBeanRemote, FruitBasketBeanLocal {

    @PersistenceContext(unitName = "FruitBasketBean-ejbPU")
    private EntityManager entityManager;
    private HashMap<Integer, Fruit> content;
    private FruitShopEntity currentFruitShop;
    private boolean initialized = false;
    private AbstractFacade facade;

    public FruitBasketBean() {
        content = new HashMap<Integer, Fruit>();
    }

    @Override
    public String sayHello() {
        return "hello";
    }

    @Override
    public void addFruit(int fruitID) {
        Fruit fruit = entityManager.find(Fruit.class, fruitID);
        currentFruitShop.removeFruit(fruitID);
        content.put(fruitID, fruit);
    }

    @Override
    public void removeFruit(int fruitID) {
        Fruit fruit = content.remove(fruitID);
        currentFruitShop.addFruit(fruit);
    }

    @Override
    public Boolean buyAllFruits() {
        content.clear();
        return true;
    }

    @Override
    public Integer getFruitAmount() {
        int amount = 0;

        for (Fruit fruit : content.values()) {
            amount = amount + fruit.number;
        }
        return amount;
    }

    @Override
    public Integer getTotalCost() {
        int totalCost = 0;

        if (content.isEmpty() == true) {
            return 0;
        }

        for (Fruit f : content.values()) {
            totalCost = totalCost + (f.number * f.price);
        }

        return totalCost;
    }

    @Override
    public String getBasketContent() {
        StringBuilder builder = new StringBuilder();

        for (Fruit fruit : content.values()) {
            builder.append(fruit.number + "x " + fruit.name + " " + fruit.price + "\n");
        }

        return builder.toString();
    }

    @Override
    public List<String> getFruitShops() {

        if (initialized == false) {
            initFruitShops();
            initialized = true;
        }/*
         facade = new FruitShopEntityFacade();

         List<FruitShopEntity> list = facade.findAll();

         List<String> stringList = new LinkedList<String>();

         for (FruitShopEntity ent : list) {
         stringList.add(ent.getName());
         }
        
         return stringList;*/

        List<Object> objectlist = entityManager.createQuery("Select e from FruitShopEntity e").getResultList();
        List<String> list = new LinkedList();

        for (Object object : objectlist) {
            FruitShopEntity fs = (FruitShopEntity) object;
            list.add(fs.getName());
        }
        //List<String> list = entityManager.createQuery("Select name from FRUIT_SHOP").getResultList();
        return list;
    }
    
    private List<FruitShopEntity> getFruitShopObjects() {

        List<FruitShopEntity> list = entityManager.createQuery("Select e from FruitShopEntity e").getResultList();

        return list;
    }

    @Override
    public void changeFruitShop(int fruitShopID) {
        this.currentFruitShop = entityManager.find(FruitShopEntity.class, fruitShopID);
    }

    private void initFruitShops() throws PersistenceException {
        FruitShopEntity shop1 = new FruitShopEntity("FirstShop");
        entityManager.persist(shop1);
        distributeFruits(shop1);
        FruitShopEntity shop2 = new FruitShopEntity("SecondShop");
        entityManager.persist(shop2);
        distributeFruits(shop2);
        FruitShopEntity shop3 = new FruitShopEntity("ThirdShop");
        entityManager.persist(shop3);
        distributeFruits(shop3);
    }

    private void distributeFruits(FruitShopEntity shop) throws PersistenceException {
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
    
    

    @Override
    public void removeFruitByName(String name) {
        Collection<Fruit> fruitColl = content.values();
        
        for (Fruit fruit : fruitColl){
            if (fruit.name.equals(name)){
                content.remove(fruit.id);
                currentFruitShop.addFruit(fruit);
            }
        }
    }

    @Override
    public void addFruitByName(String fruitName) {
        boolean fruitExists = false;
        if (currentFruitShop != null) {

            if (currentFruitShop.getFruits() != null) {
                List<Fruit> fruits = currentFruitShop.getFruits();

                for (Fruit fruit : fruits) {
                    if (fruit.name.equalsIgnoreCase(fruitName)) {
                        currentFruitShop.removeFruit(fruit.getId());

                        for (Fruit contentFruit : content.values()) {
                            if (contentFruit.name.equalsIgnoreCase(fruitName)) {
                                contentFruit.setNumber(contentFruit.getNumber() + 1);
                                content.put(contentFruit.id, contentFruit);
                                fruitExists = true;
                            }
                        }
                        if (fruitExists == false) {
                            fruit.setNumber(1);
                            content.put(fruit.getId(), fruit);
                            return;
                        }
                    }
                }
            }
        }
    }

    @Override
    public String getAvailableFruits() {
        
        if (currentFruitShop == null){
            return null;
        }
        
        StringBuilder builder = new StringBuilder();
        
        List<Fruit> fruits = currentFruitShop.getFruits();
        
        for (Fruit fruit : fruits){
            builder.append(fruit.number + "x " + fruit.name);
        }
        return builder.toString();
    }

    @Override
    public String getCurrentShopName() {
        if (currentFruitShop == null){
            return null;
        }
        return currentFruitShop.getName();
    }

    @Override
    public void changeFruitShopByName(String fruitShopName) {
        List<FruitShopEntity> shops = getFruitShopObjects();
        
        for (FruitShopEntity fs : shops){
            if (fs.getName().equalsIgnoreCase(fruitShopName)){
                this.currentFruitShop = fs;
            }
        }
    }
    
    
}
