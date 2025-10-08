package io.gomobi.quartz.domain.query;

public class FromClause {

    StringBuilder fromClause;

    private FromClause(){
    }

    public static  SelectClause builder(String tableName){
        FromClause fromClauseObject = new FromClause();
        fromClauseObject.fromClause = new StringBuilder(" FROM %s %s".formatted(tableName, tableName.charAt(0)));
        return SelectClause.builder(fromClauseObject.fromClause, tableName.substring(0,1));
    }
}
