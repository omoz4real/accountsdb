/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package omos.microsystems.accountsdb.session;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import omos.microsystems.accountsdb.entities.Accounts;
import omos.microsystems.accountsdb.entities.Statements;

/**
 *
 * @author omozegieaziegbe
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
//
//    public List<Accounts> findAccounts(int id, String accountNumber, String accountType) {
//        Query query = getEntityManager().createQuery("SELECT a FROM Accounts a WHERE a.id = :id OR a.accountNumber = :accountNumber OR a.accountType = :accountType");
//        query.setParameter("id", id);
//        query.setParameter("accountNumber", accountNumber);
//        query.setParameter("accountType", accountType);
//        List<Accounts> resultList = query.getResultList();
//        return resultList;
//    }

     public List<Statements> findStatements(int id, double startamount, double endamount, Date datefield, int accountId, String accountNumber, Date startdate, Date enddate) {
        Query query = getEntityManager().createQuery("SELECT s FROM Statements s WHERE s.id = :id OR s.datefield = :datefield OR s.amount BETWEEN :startamount AND :endamount OR s.datefield BETWEEN :startdate AND :enddate OR s.accountId.id = :accountId OR s.accountId.accountNumber = :accountNumber");
        query.setParameter("id", id);
        query.setParameter("datefield", datefield);
        query.setParameter("startamount", startamount);
        query.setParameter("endamount", endamount);
        query.setParameter("accountId", accountId);
        query.setParameter("accountNumber", accountNumber);
        query.setParameter("startdate", startdate, TemporalType.DATE);
        query.setParameter("enddate", enddate, TemporalType.DATE);
        List<Statements> resultList = query.getResultList();
        return resultList;
    }
}
