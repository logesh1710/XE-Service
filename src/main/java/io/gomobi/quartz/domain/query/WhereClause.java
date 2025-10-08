package io.gomobi.quartz.domain.query;

import org.springframework.data.relational.core.sql.Where;

public class WhereClause {

    private StringBuilder selectClause;
    private StringBuilder whereClause;
    private String alias;

    private WhereClause(){}

    public static WhereClause builder(StringBuilder selectClause, String alias) {
        WhereClause whereClauseObject = new WhereClause();
        whereClauseObject.selectClause = selectClause;
        return whereClauseObject;
    }

//    public WhereClause where(){
//
//    }


//    public String build(){
//        if (whereClause == null)
//        {
//            return selectClause.toString();
//        }
//
//
//    }
}
