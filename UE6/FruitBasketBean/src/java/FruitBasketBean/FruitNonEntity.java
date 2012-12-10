/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FruitBasketBean;

/**
 *
 * @author Tobias
 */
public class FruitNonEntity {
    private int id;
    private int price;
    private int number;
    private String name;

    public FruitNonEntity(int id, int price, int number, String name) {
        this.id = id;
        this.price = price;
        this.number = number;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
