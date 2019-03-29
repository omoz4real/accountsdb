/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package omos.microsystems.accountsdb.rest;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import omos.microsystems.accountsdb.entities.Statements;

/**
 *
 * @author omozegieaziegbe
 */
@Stateless
@Path("statements")
public class StatementsFacadeREST extends AbstractFacade<Statements> {

    @PersistenceContext(unitName = "omos.microsystems_accountsdb_war_1.0PU")
    private EntityManager em;

    public StatementsFacadeREST() {
        super(Statements.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Statements entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Statements entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Statements find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Statements> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Statements> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Statements> findStatements(int id, double amount, Date datefield, int accountId, String accountNumber) {
        Query query = getEntityManager().createQuery("SELECT s FROM Statements s WHERE s.id = :id OR s.datefield = :datefield OR s.amount = :amount OR s.accountId.id = :accountId OR s.accountId.accountNumber = :accountNumber");
        query.setParameter("id", id);
        query.setParameter("datefield", datefield);
        query.setParameter("amount", amount);
        query.setParameter("accountId", accountId);
        query.setParameter("accountNumber", accountNumber);
        List<Statements> resultList = query.getResultList();
        return resultList;
    }
    
}
