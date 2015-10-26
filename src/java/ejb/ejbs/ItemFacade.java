/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.ejbs;

import ejb.jpa.Item;
import ejb.jpa.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pifko
 */
@Stateless
public class ItemFacade extends AbstractFacade<Item> {
    @PersistenceContext(unitName = "WarehousePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemFacade() {
        super(Item.class);
    }
    
    public void ediT(Item t){
    
        this.edit(t);
    
    }
    
    public List<Item> getitems(User u){
    
        return this.em.createNamedQuery("Item.findByUserId").setParameter("userid", u).getResultList();
    
    }
    
}
