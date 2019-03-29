package omos.microsystems.accountsdb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import omos.microsystems.accountsdb.entities.Accounts;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-29T18:40:24")
@StaticMetamodel(Statements.class)
public class Statements_ { 

    public static volatile SingularAttribute<Statements, Accounts> accountId;
    public static volatile SingularAttribute<Statements, Double> amount;
    public static volatile SingularAttribute<Statements, Date> datefield;
    public static volatile SingularAttribute<Statements, Integer> id;

}