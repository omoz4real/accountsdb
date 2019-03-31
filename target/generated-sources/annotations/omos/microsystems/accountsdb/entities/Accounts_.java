package omos.microsystems.accountsdb.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import omos.microsystems.accountsdb.entities.Statements;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-31T20:12:54")
@StaticMetamodel(Accounts.class)
public class Accounts_ { 

    public static volatile SingularAttribute<Accounts, String> accountType;
    public static volatile SingularAttribute<Accounts, Integer> id;
    public static volatile SingularAttribute<Accounts, String> accountNumber;
    public static volatile ListAttribute<Accounts, Statements> statementsList;

}