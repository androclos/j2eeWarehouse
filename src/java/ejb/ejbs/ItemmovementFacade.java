/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.ejbs;

import ejb.jpa.Item;
import ejb.jpa.Itemmovement;
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
public class ItemmovementFacade extends AbstractFacade<Itemmovement> {
    @PersistenceContext(unitName = "WarehousePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemmovementFacade() {
        super(Itemmovement.class);
    }
    
    public void persisT(Itemmovement item){
    
        this.create(item);
    
    }
    
    public List<Itemmovement> getitems(User u){
    
        return this.em.createNamedQuery("Itemmovement.findByOwner").setParameter("userid", u).getResultList();
    
    }
    
    public void removE(Itemmovement e){
    
        this.remove(e);
    
    }
    
    public List<Itemmovement> getallitems(){
    
        return this.findAll();
    
    }
    
        public void ediT(Itemmovement t){
    
        this.edit(t);
    
    }
}
