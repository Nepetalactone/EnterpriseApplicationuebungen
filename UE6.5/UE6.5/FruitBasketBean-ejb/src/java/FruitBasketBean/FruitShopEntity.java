/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FruitBasketBean;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;

/**
 *
 * @author Tobias
 */
@Entity
@Table(name = "FRUIT_SHOP")
public class FruitShopEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @OneToMany
    private List<Fruit> fruits;
    private String name;

    public FruitShopEntity(int id, String name) {
        this.id = id;
        this.name = name;
        fruits = new LinkedList<Fruit>();
    }
    
    public FruitShopEntity(String name){
        this.name = name;
        fruits = new LinkedList<Fruit>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Fruit> getFruits() {
        return fruits;
    }

    public void setFruits(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    public FruitShopEntity() {
        fruits = new LinkedList<Fruit>();
    }

    public void addFruit(Fruit fruit) {

        if (fruits == null) {
            fruits = new LinkedList<Fruit>();
            fruits.add(fruit);
        } else {
            for (Fruit f : fruits) {
                if (f.getName().equalsIgnoreCase(fruit.getName())) {
                    f.setNumber(f.getNumber() + 1);
                    return;
                }
            }
            fruits.add(fruit);
        }
    }

    public Fruit getFruit(int fruitID) {
        for (Fruit fruit : fruits) {
            if (fruit.getId() == fruitID) {
                return fruit;
            }
        }
        return null;
    }

    public void removeFruit(int fruitID) {
        for (Fruit fruit : fruits) {
            if (fruit.getId() == fruitID) {
                fruit.number = fruit.number - 1;
            }
        }
    }
    
    public void removeAllFruits(){
        fruits.clear();
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FruitShopEntity)) {
            return false;
        }
        FruitShopEntity other = (FruitShopEntity) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FruitBasketBean.FruitShopEntity[ id=" + id + " ]";
    }
}
