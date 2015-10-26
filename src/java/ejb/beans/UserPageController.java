/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.beans;

import ejb.ejbs.ItemFacade;
import ejb.jpa.Item;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIInput;
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
    
    
    private String selecteditem;
    private List<Item> itemlsit = null;
    private Item selected;
    private String changedcomment;
    private String selectedoperation = "1";

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
        //this.itemlsit = this.logcon.userItems();
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

    
}
