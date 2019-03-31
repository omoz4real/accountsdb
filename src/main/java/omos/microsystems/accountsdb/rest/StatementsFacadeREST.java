/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package omos.microsystems.accountsdb.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
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
@DeclareRoles({"AdminRole", "UserRole"})
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

    @RolesAllowed({"AdminRole"})
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
    
    public List<Statements> findStatementsByDate(Date firstdate, Date lastdate) throws ParseException {
        Query query = getEntityManager().createQuery("SELECT s FROM Statements s WHERE s.datefield BETWEEN :firstdate AND :lastdate ");
        query.setParameter("firstdate", firstdate, TemporalType.DATE);
        query.setParameter("lastdate", lastdate, TemporalType.DATE);
        List<Statements> stateList = query.getResultList();
        return stateList;
    }


}
