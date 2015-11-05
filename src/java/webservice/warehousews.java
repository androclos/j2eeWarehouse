/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import ejb.ejbs.ItemFacade;
import ejb.ejbs.UserFacade;
import ejb.ejbs.ItemmovementFacade;
import ejb.jpa.User;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.primefaces.json.JSONObject;

/**
 *
 * @author Pifko
 */
@WebService(serviceName = "warehousews")
public class warehousews {
    
    @EJB
    private ItemFacade itemfacade;

    @EJB
    private UserFacade userfacade;
        
    @EJB
    private ItemmovementFacade itemmovfacade;
    
    
    @WebMethod(operationName = "getinfo")

    public String getinfo() {
        
        Integer usernumber;
        Integer opratornumber;
        Integer itemnumber;
        Integer itemmovmenetnumber;
        
       
        JSONObject obj = new JSONObject();
        
        
        
        return obj.toString();
        
    }


    
}
