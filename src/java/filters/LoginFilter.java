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
@WebFilter("/faces/*")
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
        //LoginController a = (LoginController)req.getSession(false).getAttribute("LoginController");
        //User u = a.getCurrentuser();
        
        String url = req.getRequestURL().toString();
   
        
        /*if(!req.getRequestURI().contains("xhtml") && logcon.getCurrentuser() == null)
            chain.doFilter(req, resp); 
            //resp.sendRedirect(req.getContextPath());
            
        else if(logcon.getCurrentuser() == null)
            resp.sendRedirect(req.getContextPath()+"/");
        else
            chain.doFilter(req, resp);*/
        
        
        if(!(req.getRequestURI().contains("faces")) || !(req.getRequestURI().contains("faces"))){
        
           resp.sendRedirect("faces/index.xhtml");
           return;
        
        }
        
        if((req.getRequestURI().contains("newjsf.xhtml") || url.contains("newjsf.xhtml")) && logcon.getCurrentuser() == null){
            //resp.sendRedirect(req.getContextPath()+ "/faces/index.xhtml");
           resp.sendRedirect("index.xhtml");
           return;
            
       }

       chain.doFilter(req, resp);
        
    }

    @Override
    public void destroy() {
        
    }
    
}
