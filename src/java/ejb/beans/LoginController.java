/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.beans;

import ejb.ejbs.*;
import ejb.jpa.Item;
import ejb.jpa.User;
import java.io.IOException;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pifko
 */
@Named(value="LoginController")
@SessionScoped
public class LoginController implements Serializable {
    
    @EJB
    private UserFacade userfacade;
    
    @Inject
    private UserBean userbean;

    private User currentuser = null; //loggedin user
    
    public UserBean getUserbean() {
        return userbean;
    }

    public User getCurrentuser() {
        return currentuser;
    }

    
    public String login(){
    
    
        User s = this.userfacade.GetUserOnLogin(this.userbean.getUsername(), this.userbean.getPassword());
        
        if(s != null){
        
            
            UserSession a = new UserSession(s.getUserid());
            
            FacesContext facesContext=FacesContext.getCurrentInstance();
            ExternalContext externalContext=facesContext.getExternalContext();
            externalContext.getSessionMap().put(s.getUsername(), a);
            
            
            
            currentuser = s;
            clearUserBean();
            setUserBean(currentuser);
            

            if(currentuser.getRole().equals("operator"))
                return "/operatorpage?faces-redirect=true";
            else
                return "/newjsf?faces-redirect=true";
        }
        
        faceMessage("• There is no such user or the password is wrong.");
        return "index.xhtml";
    }
    
    public void checkUser() throws IOException{ //van e login
    
        if(this.currentuser == null){
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.redirect(ec.getRequestContextPath() + "/faces/index.xhtml");
        }
        
    
    }
    
    public void logout(){
    
        try {
            FacesContext facesContext=FacesContext.getCurrentInstance();
            ExternalContext externalContext=facesContext.getExternalContext();
            
            externalContext.getSessionMap().remove(this.userbean.getUsername());
            clearUserBean();
            currentuser = null;
            
            
            externalContext.redirect("index.xhtml");
            
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public List<Item> userItems(){
    
        
        this.userfacade.refresh(currentuser);
        this.currentuser = this.userfacade.find(this.currentuser.getUserid());
        return this.currentuser.getItemList();
    
    }
    
    public void faceMessage(String s){
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("loginform:loginerror",new FacesMessage(s));
    
    }
    
    public void clearUserBean(){
    
        this.userbean.setEmail(null);
        this.userbean.setPassword(null);
        this.userbean.setUserid(null);
        this.userbean.setUsername(null);
    }
    
    public void setUserBean(User s){
    
        this.userbean.setEmail(s.getEmail());
        this.userbean.setUserid(s.getUserid());
        this.userbean.setUsername(s.getUsername());

    }
    
    
    
    
}
