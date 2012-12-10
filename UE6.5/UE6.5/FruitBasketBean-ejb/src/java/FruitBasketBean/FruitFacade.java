/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FruitBasketBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tobias
 */
@Stateless
public class FruitFacade extends AbstractFacade<Fruit> {
    @PersistenceContext(unitName = "FruitBasketBean-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FruitFacade() {
        super(Fruit.class);
    }
    
}
