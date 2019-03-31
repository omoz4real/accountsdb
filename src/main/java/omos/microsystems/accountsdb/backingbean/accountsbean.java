/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package omos.microsystems.accountsdb.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import omos.microsystems.accountsdb.entities.Accounts;
import omos.microsystems.accountsdb.entities.Statements;
import omos.microsystems.accountsdb.rest.AccountsFacadeREST;
import omos.microsystems.accountsdb.rest.StatementsFacadeREST;

/**
 *
 * @author omozegieaziegbe
 */
@Named(value = "accountsbean")
@DeclareRoles({"AdminRole", "UserRole"})
@RequestScoped
public class accountsbean implements Serializable {

    private Accounts account = new Accounts();
    private List<Accounts> accounts;
    private List<Accounts> findCustomersByAccount;

    private Statements statement = new Statements();
    private List<Statements> statements;
    private List<Statements> findStatementsByAccount;

    private List<Statements> findStateByAccount;

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

    String name;

    @PersistenceContext
    EntityManager em;

    @EJB
    private AccountsFacadeREST accountsfacaderest;

    @EJB
    private StatementsFacadeREST statementsfacaderest;

    /**
     * Creates a new instance of accountsbean
     */
    @PostConstruct
    public void init() {
        accounts = accountsfacaderest.findAll();
        account = accountsfacaderest.find(id);
        account = new Accounts();

        statements = statementsfacaderest.findAll();
        statement = statementsfacaderest.find(id);
        statement = new Statements();
//        invalidateUsers();
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
        statements = statementsfacaderest.findAll();
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

    public String getName() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        name = context.getUserPrincipal().getName();
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @RolesAllowed({"AdminRole"})
    public List<Statements> findStatements() {
        List<Statements> stat = null;
        try {
            stat = statementsfacaderest.findStatements(statementId, startamount, endamount, datefield, accountId, accountNumber, startdate, enddate);

        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("This is the Exception" + exception);
        }
        return stat;
    }

    public List<Statements> getFindStatementsByAccount() {
        findStatementsByAccount = statementsfacaderest.findStatements(statementId, startamount, endamount, datefield, accountId, accountNumber, startdate, enddate);
        return findStatementsByAccount;
    }

    public void setFindStatementsByAccount(List<Statements> findStatementsByAccount) {
        this.findStatementsByAccount = findStatementsByAccount;
    }


//    public String invalidateUsers() {
//
//        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//        name = context.getUserPrincipal().getName();
//        HttpServletRequest request = (HttpServletRequest) context.getRequest();
//        HttpSession session = request.getSession(false);
//        if (name != null) {
//            session.setAttribute("name", name);
//        } else {
//            FacesContext contexts = FacesContext.getCurrentInstance();
//        FacesMessage message = new FacesMessage("This User is already Logged In.");
//        contexts.addMessage("", message);
//        contexts.getExternalContext().getFlash().setKeepMessages(true);
//        session.invalidate();
//        return "/faces/login.xhtml?faces-redirect=true";
//        }
//
//        System.out.println("This is the name " + name);
//        return name;
//    }

    public void logout() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession httpSession = (HttpSession) ec.getSession(false);
        httpSession.invalidate();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml?faces-redirect=true");
    }

    public List<Statements> findState() throws ParseException {
        List<Statements> stat = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateInString = "2019-01-01";
            String dateInString2 = "2019-03-03";
            Date firstdate = sdf.parse(dateInString);
            Date seconddate = sdf.parse(dateInString2);
            stat = statementsfacaderest.findStatementsByDate(new Date("2019/01/01"), new Date("2019/05/03"));

        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("This is the Exception" + exception);
        }
        return stat;
    }

//    public List<Statements> getFindStateByAccount() {
//        findStatementsByAccount = statementsfacaderest.findStatements(statementId, startamount, endamount, datefield, accountId, accountNumber, startdate, enddate);
//        return findStatementsByAccount ;
//    }
//
//    public void setFindStateByAccount(List<Statements> findStatementsByAccount) {
//        this.findStatementsByAccount = findStatementsByAccount;
//    }
    public List<Statements> getFindStateByAccount() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = "2019-01-01";
        String dateInString2 = "2019-03-03";
        Date firstdate = sdf.parse(dateInString);
        Date seconddate = sdf.parse(dateInString2);
        findStateByAccount = statementsfacaderest.findStatementsByDate(firstdate, seconddate);
        return findStateByAccount;
    }

    public void setFindStateByAccount(List<Statements> findStateByAccount) {
        this.findStateByAccount = findStateByAccount;
    }
}
