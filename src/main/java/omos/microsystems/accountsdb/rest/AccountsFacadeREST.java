/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package omos.microsystems.accountsdb.rest;

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
import omos.microsystems.accountsdb.entities.Accounts;

/**
 *
 * @author omozegieaziegbe
 */
@Stateless
@Path("accounts")
public class AccountsFacadeREST extends AbstractFacade<Accounts> {

    @PersistenceContext(unitName = "omos.microsystems_accountsdb_war_1.0PU")
    private EntityManager em;

    public AccountsFacadeREST() {
        super(Accounts.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Accounts entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Accounts entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Accounts find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({ MediaType.APPLICATION_JSON})
    public List<Accounts> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Accounts> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
    
//    @GET
//    @Path("search")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//   public List<Accounts> findAccounts(@PathParam("id") int id, @PathParam("accountNumber") String accountNumber, @PathParam("accountType") String accountType) {
//        Query query = getEntityManager().createQuery("SELECT a FROM Accounts a WHERE a.id = :id OR a.accountNumber = :accountNumber OR a.accountType = :accountType");
//        query.setParameter("id", id);
//        query.setParameter("accountNumber", accountNumber);
//        query.setParameter("accountType", accountType);
//        List<Accounts> resultList = query.getResultList();
//        return resultList;
//    }
   
}
