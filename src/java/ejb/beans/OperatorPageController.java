/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.beans;

import ejb.ejbs.ItemmovementFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Pifko
 */
@Named(value="OperatorPageController")
@SessionScoped
public class OperatorPageController implements Serializable{

    
    @Inject
    private LoginController logcon; //ebbol csak egy lesz es ugyan mint a loginben
    
    @EJB
    private ItemmovementFacade itemmovementfacade;
    
    public OperatorPageController() {
    }
    
    
    
}
