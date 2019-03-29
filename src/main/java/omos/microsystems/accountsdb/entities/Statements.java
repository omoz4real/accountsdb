/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package omos.microsystems.accountsdb.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author omozegieaziegbe
 */
@Entity
@Table(catalog = "accountsdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Statements.findAll", query = "SELECT s FROM Statements s")
    , @NamedQuery(name = "Statements.findById", query = "SELECT s FROM Statements s WHERE s.id = :id")
    , @NamedQuery(name = "Statements.findByDatefield", query = "SELECT s FROM Statements s WHERE s.datefield = :datefield")
    , @NamedQuery(name = "Statements.findByAmount", query = "SELECT s FROM Statements s WHERE s.amount = :amount")})
public class Statements implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datefield;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private double amount;
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Accounts accountId;

    public Statements() {
    }

    public Statements(Integer id) {
        this.id = id;
    }

    public Statements(Integer id, Date datefield, double amount) {
        this.id = id;
        this.datefield = datefield;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatefield() {
        return datefield;
    }

    public void setDatefield(Date datefield) {
        this.datefield = datefield;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Accounts getAccountId() {
        return accountId;
    }

    public void setAccountId(Accounts accountId) {
        this.accountId = accountId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Statements)) {
            return false;
        }
        Statements other = (Statements) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "omos.microsystems.accountsdb.entities.Statements[ id=" + id + " ]";
    }
    
}
