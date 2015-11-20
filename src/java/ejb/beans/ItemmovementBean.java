/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.beans;

import java.util.Date;
import javax.inject.Named;

/**
 *
 * @author Pifko
 */
public class ItemmovementBean {
    
    private Integer itemmovementid;
    private String operation;
    private String verdict;
    private Date requestdate;
    private Date verdictdate;
    private String amount;

    public Integer getItemmovementid() {
        return itemmovementid;
    }

    public void setItemmovementid(Integer itemmovementid) {
        this.itemmovementid = itemmovementid;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public Date getRequestdate() {
        return requestdate;
    }

    public void setRequestdate(Date requestdate) {
        this.requestdate = requestdate;
    }

    public Date getVerdictdate() {
        return verdictdate;
    }

    public void setVerdictdate(Date verdictdate) {
        this.verdictdate = verdictdate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    
    
}
