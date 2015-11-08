/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import ejb.ejbs.ItemFacade;
import ejb.ejbs.UserFacade;
import ejb.ejbs.ItemmovementFacade;
import ejb.jpa.Item;
import ejb.jpa.Itemmovement;
import ejb.jpa.User;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.primefaces.json.JSONException;
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

    public String getinfo() throws JSONException {
        
        Integer usernumber = 0;
        Integer opratornumber = 0;
        Integer itemnumber = 0;
        Integer itemmovmenetnumber = 0;
        Integer pendingrequests = 0;
        
        List<Item> items = itemfacade.findAll();
        List<User> users = userfacade.findAll();
        List<Itemmovement> movs = itemmovfacade.findAll();
        
        for(User u : users){
        
            if(u.getRole().equals("user"))
                usernumber++;
            else
                opratornumber++;
            
        }
        
        itemnumber = items.size();
        itemmovmenetnumber = movs.size();

        for(Itemmovement i : movs){
        
            if(i.getVerdict().equals("pending"))
                pendingrequests++;
            
        }
       
       
        JSONObject obj = new JSONObject();
        obj.put("Number of users", usernumber);
        obj.put("Number of operators", opratornumber);
        obj.put("Number of items", itemnumber);
        obj.put("Number of movement requests", itemmovmenetnumber);
        obj.put("Number of pending movement requests", pendingrequests);
        
        
        return obj.toString();
        
    }


    
}
