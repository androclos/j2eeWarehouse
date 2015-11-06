/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.beans;

import ejb.ejbs.ItemFacade;
import ejb.ejbs.ItemmovementFacade;
import ejb.jpa.Item;
import ejb.jpa.Itemmovement;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author Pifko
 */
@Named(value="UserPageController")
@SessionScoped
public class UserPageController implements Serializable{
    
    @Inject
    private LoginController logcon; //ebbol csak egy lesz es ugyan mint a loginben
    
    @Inject
    private ItemBean itembean;

    @EJB
    private ItemFacade itemfacade;
    
    @EJB
    private ItemmovementFacade itemmovementfacade;
    
    private String selecteditem;
    private List<Item> itemlsit = null;
    private Item selected;
    private String changedcomment;

    
    /*-----------------------------------------*/
    private String selectedoperation = "1";
    private String newitemname = null;
    private String itemamount = null;
    private Date movementdate;
    private String newitemmessage;
    private String movementopmessage;
    private List<Itemmovement> itemmovlist = null;
    private Itemmovement selectedmov;

    public String getMovementopmessage() {
        return movementopmessage;
    }

    public void setMovementopmessage(String movementopmessage) {
        this.movementopmessage = movementopmessage;
    }

    public Itemmovement getSelectedmov() {
        return selectedmov;
    }

    public void setSelectedmov(Itemmovement selectedmov) {
        this.selectedmov = selectedmov;
    }
    
    public List<Itemmovement> getItemmovlist() {
        if(logcon.getCurrentuser() != null)
        this.itemmovlist = this.itemmovementfacade.getitems(this.logcon.getCurrentuser());
        return itemmovlist;
    }

    public void setItemmovlist(List<Itemmovement> itemmovlist) {
        this.itemmovlist = itemmovlist;
    }

    
    public String getNewitemmessage() {
        return newitemmessage;
    }

    public void setNewitemmessage(String newitemmessage) {
        this.newitemmessage = newitemmessage;
    }

    
    public String getNewitemname() {
        return newitemname;
    }

    public void setNewitemname(String newitemname) {
        this.newitemname = newitemname;
    }

    public String getItemamount() {
        return itemamount;
    }

    public void setItemamount(String itemamount) {
        this.itemamount = itemamount;
    }

    public Date getMovementdate() {
        return movementdate;
    }

    public void setMovementdate(Date movementdate) {
        this.movementdate = movementdate;
    }
     
    
    public String getSelectedoperation() {
        return selectedoperation;
    }

    public void setSelectedoperation(String selectedoperation) {
        this.selectedoperation = selectedoperation;
    }
    
    
    
    public String getChangedcomment() {
        return changedcomment;
    }

    public void setChangedcomment(String changedcomment) {
        this.changedcomment = changedcomment;
    }

    public Item getSelected() {
        return selected;
    }

    public void setSelected(Item selected) {
        this.selected = selected;
    }

    public List<Item> getItemlsit() {
        if(logcon.getCurrentuser() != null)
        this.itemlsit = this.itemfacade.getitems(this.logcon.getCurrentuser());
        return itemlsit;
    }


    
    public void setItemlsit(List<Item> itemlsit) {
        this.itemlsit = itemlsit;
    }

    public void updatecomment(){
      
        this.selected.setComment(changedcomment);
        this.itemfacade.ediT(selected);
        this.itemlsit = this.logcon.userItems();

    }
    
    
    public void setSelecteditem(String selecteditem) {
        this.selecteditem = selecteditem;
    }


    public String getSelecteditem() {

        return selecteditem;
    
    }
    
    public List<Item> returnUserItemList(){

        return this.logcon.userItems();
    
    }

    public ItemBean getItembean() {
        return itembean;
    }
    
    public void mas(){
    
    this.selecteditem = "ynasgem";
    
    }

    public void addItemMovement(){
    
        switch(selectedoperation){
        
            case "1": {
            
                if(newitemname.equals(null) || itemamount.equals(null) || newitemname.isEmpty() || itemamount.isEmpty()){
                    newitemmessage = "• Error: Emtpy name or amount field.";
                    return;
                }
                
                Itemmovement newitemmov = new Itemmovement();
                
                newitemmov.setOperation("addnewitem");
                newitemmov.setVerdict("pending");
                newitemmov.setUserrequesterid(logcon.getCurrentuser());
                newitemmov.setAmount(itemamount);
                newitemmov.setRequestdate((new Date()).toString());
                newitemmov.setMovementdate(movementdate.toString());
                
                itemmovementfacade.persisT(newitemmov);
                newitemmessage = "Request added";
                break;
            }
            case "2": {
                   
                if(selected == null || itemamount.equals(null)){
                    newitemmessage = "• Error: No item selected or empty amount field.";
                    return;
                }
                
                Itemmovement newitemmov = new Itemmovement();
                
                newitemmov.setOperation("addtoexistingitem");
                newitemmov.setVerdict("pending");
                newitemmov.setUserrequesterid(logcon.getCurrentuser());
                newitemmov.setAmount(itemamount);
                newitemmov.setRequestdate((new Date()).toString());
                newitemmov.setMovementdate(movementdate.toString());
                newitemmov.setItemitemid(selected);
                
                itemmovementfacade.persisT(newitemmov);
                newitemmessage = "Request added";
                break;
            }
            case "3": {
            
                if(selected == null || itemamount.equals(null)){
                    newitemmessage = "• Error: No item selected or empty amount field.";
                    return;
                }
                
                Itemmovement newitemmov = new Itemmovement();
                
                newitemmov.setOperation("takeoutitem");
                newitemmov.setVerdict("pending");
                newitemmov.setUserrequesterid(logcon.getCurrentuser());
                newitemmov.setAmount(itemamount);
                newitemmov.setRequestdate((new Date()).toString());
                newitemmov.setMovementdate(movementdate.toString());
                newitemmov.setItemitemid(selected);
                
                itemmovementfacade.persisT(newitemmov);
                newitemmessage = "Request added";
                break;
            }
            default: {break;}

        }

    }
    
    public void itemMovmentRedirect() throws IOException{
    
         FacesContext facesContext=FacesContext.getCurrentInstance();
         ExternalContext externalContext=facesContext.getExternalContext();
         
         externalContext.redirect("itemmovpage.xhtml");
    
    }
    
    public void itemListRedirect() throws IOException{
    
         FacesContext facesContext=FacesContext.getCurrentInstance();
         ExternalContext externalContext=facesContext.getExternalContext();
         
         externalContext.redirect("newjsf.xhtml");
    
    }
    
    public void deleteItemMovement(){
    
        if(selectedmov == null){
            movementopmessage = "No request was selected.";
            return;
        }
    
        if(!selectedmov.getVerdict().equals("pending")){
            movementopmessage = "Request is not pending, you cant delete it.";
            return;
        }
        
        itemmovementfacade.removE(selectedmov);
        movementopmessage = "Request is deleted.";
        
    }
    
}
