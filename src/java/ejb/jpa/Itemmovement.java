/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Pifko
 */
@Entity
@Table(name = "itemmovement")
@NamedQueries({
    @NamedQuery(name = "Itemmovement.findAll", query = "SELECT i FROM Itemmovement i"),
    @NamedQuery(name = "Itemmovement.findByItemmovementid", query = "SELECT i FROM Itemmovement i WHERE i.itemmovementid = :itemmovementid"),
    @NamedQuery(name = "Itemmovement.findByOwner", query = "SELECT i FROM Itemmovement i WHERE i.userrequesterid = :userid"),
    @NamedQuery(name = "Itemmovement.findByOperation", query = "SELECT i FROM Itemmovement i WHERE i.operation = :operation"),
    @NamedQuery(name = "Itemmovement.findByVerdict", query = "SELECT i FROM Itemmovement i WHERE i.verdict = :verdict"),
    @NamedQuery(name = "Itemmovement.findByAmount", query = "SELECT i FROM Itemmovement i WHERE i.amount = :amount"),
    @NamedQuery(name = "Itemmovement.findByRequestdate", query = "SELECT i FROM Itemmovement i WHERE i.requestdate = :requestdate"),
    @NamedQuery(name = "Itemmovement.findByVerdictdate", query = "SELECT i FROM Itemmovement i WHERE i.verdictdate = :verdictdate"),
    @NamedQuery(name = "Itemmovement.findByMovementdate", query = "SELECT i FROM Itemmovement i WHERE i.movementdate = :movementdate"),
    @NamedQuery(name = "Itemmovement.findByItemmovementcol", query = "SELECT i FROM Itemmovement i WHERE i.itemmovementcol = :itemmovementcol"),
    @NamedQuery(name = "Itemmovement.findByVerdictreason", query = "SELECT i FROM Itemmovement i WHERE i.verdictreason = :verdictreason")})
public class Itemmovement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "itemmovementid")
    private Integer itemmovementid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "operation")
    private String operation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "verdict")
    private String verdict;
    @Size(max = 45)
    @Column(name = "amount")
    private String amount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "requestdate")
    private String requestdate;
    @Size(max = 45)
    @Column(name = "verdictdate")
    private String verdictdate;
    @Size(max = 45)
    @Column(name = "movementdate")
    private String movementdate;
    @Size(max = 45)
    @Column(name = "itemmovementcol")
    private String itemmovementcol;
    @Size(max = 245)
    @Column(name = "verdictreason")
    private String verdictreason;
    @JoinColumn(name = "User_operatorid", referencedColumnName = "userid")
    @ManyToOne(fetch = FetchType.EAGER)
    private User useroperatorid;
    @JoinColumn(name = "User_requesterid", referencedColumnName = "userid")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User userrequesterid;
    @JoinColumn(name = "Item_itemid", referencedColumnName = "itemid")
    @ManyToOne(fetch = FetchType.EAGER)
    private Item itemitemid;

    public Itemmovement() {
    }

    public Itemmovement(Integer itemmovementid) {
        this.itemmovementid = itemmovementid;
    }

    public Itemmovement(Integer itemmovementid, String operation, String verdict, String requestdate) {
        this.itemmovementid = itemmovementid;
        this.operation = operation;
        this.verdict = verdict;
        this.requestdate = requestdate;
    }

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRequestdate() {
        return requestdate;
    }

    public void setRequestdate(String requestdate) {
        this.requestdate = requestdate;
    }

    public String getVerdictdate() {
        return verdictdate;
    }

    public void setVerdictdate(String verdictdate) {
        this.verdictdate = verdictdate;
    }

    public String getMovementdate() {
        return movementdate;
    }

    public void setMovementdate(String movementdate) {
        this.movementdate = movementdate;
    }

    public String getItemmovementcol() {
        return itemmovementcol;
    }

    public void setItemmovementcol(String itemmovementcol) {
        this.itemmovementcol = itemmovementcol;
    }

    public String getVerdictreason() {
        return verdictreason;
    }

    public void setVerdictreason(String verdictreason) {
        this.verdictreason = verdictreason;
    }

    public User getUseroperatorid() {
        return useroperatorid;
    }

    public void setUseroperatorid(User useroperatorid) {
        this.useroperatorid = useroperatorid;
    }

    public User getUserrequesterid() {
        return userrequesterid;
    }

    public void setUserrequesterid(User userrequesterid) {
        this.userrequesterid = userrequesterid;
    }

    public Item getItemitemid() {
        return itemitemid;
    }

    public void setItemitemid(Item itemitemid) {
        this.itemitemid = itemitemid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemmovementid != null ? itemmovementid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itemmovement)) {
            return false;
        }
        Itemmovement other = (Itemmovement) object;
        if ((this.itemmovementid == null && other.itemmovementid != null) || (this.itemmovementid != null && !this.itemmovementid.equals(other.itemmovementid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.jpa.Itemmovement[ itemmovementid=" + itemmovementid + " ]";
    }
    
}
