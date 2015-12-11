/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import ejb.beans.LoginController;
import ejb.jpa.User;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pifko
 */
@WebFilter("/*")
public class LoginFilter implements Filter{

    @Inject
    private LoginController logcon;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        String url = req.getRequestURL().toString();

        if(req.getRequestURI().contains("warehousews")){
        
           chain.doFilter(req, resp);
           return;
        
        }
        
        
        if(!(req.getRequestURI().contains("faces")) || !(req.getRequestURI().toString().contains("Warehouse"))){
        
           resp.sendRedirect("faces/index.xhtml");
           return;
        
        }
        
        if((req.getRequestURI().contains("operatorcalendar.xhtml") || url.contains("operatorcalendar.xhtml"))){
            
            
            if(logcon.getCurrentuser() == null){
               resp.sendRedirect("index.xhtml");
               return;
            }
        
            if(!logcon.getCurrentuser().getRole().equals("operator")){
           
                resp.sendRedirect("newjsf.xhtml");
                return;
           }   
        
        }
        
        if((req.getRequestURI().contains("itemmovpage.xhtml") || url.contains("itemmovpage.xhtml"))){
            
            
            if(logcon.getCurrentuser() == null){
               resp.sendRedirect("index.xhtml");
               return;
            }
        
            if(logcon.getCurrentuser().getRole().equals("operator")){
           
                resp.sendRedirect("operatorpage.xhtml");
                return;
           }   
        
        }
        
        if((req.getRequestURI().contains("newjsf.xhtml") || url.contains("newjsf.xhtml"))){
            
            
            if(logcon.getCurrentuser() == null){
               resp.sendRedirect("index.xhtml");
               return;
            }
        
            if(logcon.getCurrentuser().getRole().equals("operator")){
           
                resp.sendRedirect("operatorpage.xhtml");
                return;
           }   
        
        }

        if((req.getRequestURI().contains("operatorpage.xhtml") || url.contains("operatorpage.xhtml"))){

           if(logcon.getCurrentuser() == null){
           
                resp.sendRedirect("index.xhtml");
                return;
           } 
           
           if(!logcon.getCurrentuser().getRole().equals("operator")){
           
                resp.sendRedirect("newjsf.xhtml");
                return;
           }   
        }
        
       chain.doFilter(req, resp);
        
    }

    @Override
    public void destroy() {
        
    }
    
}
