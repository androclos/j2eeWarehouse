/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.beans;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 *
 * @author Pifko
 */
public class UserSession implements HttpSessionBindingListener,Serializable {

    private static Map<UserSession,HttpSession> loggedinusers = new ConcurrentHashMap<UserSession,HttpSession>();
    
    private Integer id;

    public UserSession(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<UserSession, HttpSession> getLoggedinusers() {
        return loggedinusers;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserSession other = (UserSession) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        
        HttpSession session = loggedinusers.remove(this);
        if (session != null) {
            session.invalidate();
        }
        loggedinusers.put(this, event.getSession());  
        
        
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        
        loggedinusers.remove(this);
        
    }
    
}
