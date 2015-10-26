/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.jpa;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pifko
 */
@Entity
@Table(name = "item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
    @NamedQuery(name = "Item.findByItemid", query = "SELECT i FROM Item i WHERE i.itemid = :itemid"),
    @NamedQuery(name = "Item.findByName", query = "SELECT i FROM Item i WHERE i.name = :name"),
    @NamedQuery(name = "Item.findByType", query = "SELECT i FROM Item i WHERE i.type = :type"),
    @NamedQuery(name = "Item.findByAmount", query = "SELECT i FROM Item i WHERE i.amount = :amount"),
    @NamedQuery(name = "Item.findByComment", query = "SELECT i FROM Item i WHERE i.comment = :comment"),
    @NamedQuery(name = "Item.findByUserId", query = "SELECT i FROM Item i WHERE i.useruserid = :userid")})
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "itemid")
    private Integer itemid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 90)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "amount")
    private String amount;
    @Size(max = 256)
    @Column(name = "comment")
    private String comment;
    @JoinColumn(name = "User_userid", referencedColumnName = "userid")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User useruserid;
    @OneToMany(mappedBy = "itemitemid", fetch = FetchType.EAGER)
    private List<Itemmovement> itemmovementList;

    public Item() {
    }

    public Item(Integer itemid) {
        this.itemid = itemid;
    }

    public Item(Integer itemid, String name, String type, String amount) {
        this.itemid = itemid;
        this.name = name;
        this.type = type;
        this.amount = amount;
    }

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUseruserid() {
        return useruserid;
    }

    public void setUseruserid(User useruserid) {
        this.useruserid = useruserid;
    }

    @XmlTransient
    public List<Itemmovement> getItemmovementList() {
        return itemmovementList;
    }

    public void setItemmovementList(List<Itemmovement> itemmovementList) {
        this.itemmovementList = itemmovementList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemid != null ? itemid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.itemid == null && other.itemid != null) || (this.itemid != null && !this.itemid.equals(other.itemid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "ejb.jpa.Item[ itemid=" + itemid + " ]";
        return itemid+" : "+this.name;
    }
    
    public Integer idaskey(){
    
        return this.itemid;
        
    }
    
}
