/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.ejbs;

import ejb.jpa.Itemmovement;
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
    
}
