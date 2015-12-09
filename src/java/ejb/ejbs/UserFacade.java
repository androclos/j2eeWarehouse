/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.ejbs;

import ejb.jpa.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pifko
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
    @PersistenceContext(unitName = "WarehousePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public User GetUserOnLogin(String name, String password){
    
        try{
            
            User u = (User)this.em.createNamedQuery("User.findUser").setParameter("username", name).setParameter("password", password).getSingleResult();
            return u;
            
        }
        catch(Exception e){
            
            return null;
        
        }
    
    
    }
}
