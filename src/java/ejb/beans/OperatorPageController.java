/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.beans;

import ejb.ejbs.ItemmovementFacade;
import ejb.jpa.Itemmovement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
    
    private List<Itemmovement> allmovlist;
    private String verdictreson;
    private Itemmovement selected = null;
    private String outcome = "denied";
    private String opmessage;
    
    public OperatorPageController() {
    }

    public String getOpmessage() {
        return opmessage;
    }

    public void setOpmessage(String opmessage) {
        this.opmessage = opmessage;
    }

    public List<Itemmovement> getAllmovlist() {
        if(logcon.getCurrentuser() != null)
        this.allmovlist = this.itemmovementfacade.getallitems();
        return allmovlist;
    }

    public String getVerdictreson() {
        return verdictreson;
    }

    public void setVerdictreson(String verdictreson) {
        this.verdictreson = verdictreson;
    }

    public Itemmovement getSelected() {
        return selected;
    }

    public void setSelected(Itemmovement selected) {
        this.selected = selected;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }
    
    public void setStatus(){
    
        if(selected == null){
            opmessage = "• Error: No item was selected.";
            return;
        }
    
        
        if(!selected.getVerdict().equals("pending")){
            opmessage = "• Error: Request is not in pending status.";
            return;
        }
        
        selected.setVerdict(outcome);
        selected.setVerdictreason(verdictreson);
        selected.setUseroperatorid(logcon.getCurrentuser());
        selected.setVerdictdate((new Date()).toString());

        itemmovementfacade.ediT(selected);
        
        
        opmessage = "• Status succesfully changed.";
    }
    
    
    
}
