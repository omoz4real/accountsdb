/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package omos.microsystems.accountsdb.backingbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import omos.microsystems.accountsdb.entities.Accounts;
import omos.microsystems.accountsdb.entities.Statements;
import omos.microsystems.accountsdb.rest.AccountsFacadeREST;
import omos.microsystems.accountsdb.rest.StatementsFacadeREST;
import omos.microsystems.accountsdb.session.AccountsFacade;
import omos.microsystems.accountsdb.session.StatementsFacade;

/**
 *
 * @author omozegieaziegbe
 */
@Named(value = "accountsbean")
@RequestScoped
public class accountsbean implements Serializable {

    private Accounts account = new Accounts();
    private List<Accounts> accounts;
    private List<Accounts> findCustomersByAccount;

    private Statements statement = new Statements();
    private List<Statements> statements;
    private List<Statements> findStatementsByAccount;

    int id;

    int accountId;
    String accountNumber;
    String accountType;

    int statementId;
    double amount;
    Date datefield;
    
    double startamount;
    double endamount;
    
    Date startdate;
    Date enddate;

    @PersistenceContext
    EntityManager em;

    @EJB
    private AccountsFacade accountsfacade;
    @EJB
    private AccountsFacadeREST accountsfacaderest;

    @EJB
    private StatementsFacade statementsfacade;
    @EJB
    private StatementsFacadeREST statementsfacaderest;

    /**
     * Creates a new instance of accountsbean
     */
    @PostConstruct
    public void init() {
        accounts = accountsfacade.findAll();
        accounts = accountsfacaderest.findAll();
        account = accountsfacade.find(id);
        account = accountsfacaderest.find(id);
        account = new Accounts();

        statements = statementsfacaderest.findAll();
        statements = statementsfacade.findAll();
        statement = statementsfacaderest.find(id);
        statement = statementsfacade.find(id);
        statement = new Statements();
    }

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }

    public List<Accounts> getAccounts() {
        accounts = accountsfacaderest.findAll();
        return accounts;
    }

//    public void setAccounts(List<Accounts> accounts) {
//        this.accounts = accounts;
//    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getStatementId() {
        return statementId;
    }

    public void setStatementId(int statementId) {
        this.statementId = statementId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDatefield() {
        return datefield;
    }

    public void setDatefield(Date datefield) {
        this.datefield = datefield;
    }

    public String showDetails(Statements statement) {
        this.statement = statement;
        return "viewaccount";
    }

    public Statements getStatement() {
        return statement;
    }

    public void setStatement(Statements statement) {
        this.statement = statement;
    }

    public List<Statements> getStatements() {
        statements = statementsfacade.findAll();
        return statements;
    }

    public double getStartamount() {
        return startamount;
    }

    public void setStartamount(double startamount) {
        this.startamount = startamount;
    }

    public double getEndamount() {
        return endamount;
    }

    public void setEndamount(double endamount) {
        this.endamount = endamount;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    
//    public void setStatements(List<Statements> statements) {
//        this.statements = statements;
//    }
//    public StatementsFacade getStatementsfacade() {
//        return statementsfacade;
//    }
//
//    public void setStatementsfacade(StatementsFacade statementsfacade) {
//        this.statementsfacade = statementsfacade;
//    }
//
//    public StatementsFacadeREST getStatementsfacaderest() {
//        return statementsfacaderest;
//    }
//
//    public void setStatementsfacaderest(StatementsFacadeREST statementsfacaderest) {
//        this.statementsfacaderest = statementsfacaderest;
//    }
//    public String getAccountName() {
//        try {
//            return em.createNamedQuery("Accounts.findById", Accounts.class).setParameter("id", accountId).getSingleResult().getAccountType();
//        } catch (NoResultException e) {
//            return "";
//        }
//    }
//    public List<Accounts> findAccounts() {
////        Query query = getEntityManager().createQuery("SELECT a FROM Accounts a WHERE e.courseDate BETWEEN :startDate AND :endDate order " + "by e.courseDate");
//        Query query = em.createQuery("SELECT a FROM Accounts a WHERE a.id = :accountId AND a.accountNumber = :accountNumber AND a.accountType = :accountType");
//        query.setParameter("id", accountId);
//        query.setParameter("accountNumber", accountNumber);
//        query.setParameter("accountType", accountType);
//        List<Accounts> resultList = query.getResultList();
//        return resultList;
//    }
//    public String getAllAcccounts() {
//        try {
//            List<Accounts> list = em.createNamedQuery("Accounts.findAll", Accounts.class)
//                    //                    .setParameter("accountId", accountId)
//                    .setParameter("accountNumber", accountNumber)
//                    .setParameter("accountType", accountType)
//                    .getResultList();
//            System.out.println("This are the details" + list.get(0));
//            if (list.isEmpty()) {
//                return "none";
//            }
//
//            return list
//                    .get(0)
//                    .getId().toString();
//        } catch (NoResultException e) {
//            return "none";
//        }
//    }
//    public List<Accounts> findCustomers() {
//        List<Accounts> cust = null;
//        try {
//            cust = accountsfacade.findAccounts(accountId, accountNumber, accountType);
//            System.out.println("This are the details" + cust.get(0));
//        } catch (ArrayIndexOutOfBoundsException exception) {
//            System.out.println("This is the Exception" + exception + " " + 3);
//        }
//        return cust;
//
//    }
    public List<Statements> findStatements() {
        List<Statements> stat = null;
        try {
            stat = statementsfacade.findStatements(statementId, startamount, endamount, datefield, accountId, accountNumber);

        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("This is the Exception" + exception + " " + 3);
        }
        return stat;
    }

//    public List<Accounts> getFindCustomersByAccount() {
//        findCustomersByAccount = accountsfacade.findAccounts(accountId, accountNumber, accountType);
//        return findCustomersByAccount;
//    }
//    public void setFindCustomersByAccount(List<Accounts> findCustomersByAccount) {
//        this.findCustomersByAccount = findCustomersByAccount;
//    }
    public List<Statements> getFindStatementsByAccount() {
        findStatementsByAccount = statementsfacade.findStatements(statementId, startamount, endamount, datefield, accountId, accountNumber);
        return findStatementsByAccount;
    }

    public void setFindStatementsByAccount(List<Statements> findStatementsByAccount) {
        this.findStatementsByAccount = findStatementsByAccount;
    }


}
