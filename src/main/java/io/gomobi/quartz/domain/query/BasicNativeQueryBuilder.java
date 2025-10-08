package io.gomobi.quartz.domain.query;

import io.gomobi.quartz.common.util.StringUtil;

import static io.gomobi.quartz.common.constant.Delimiter.*;

public final class BasicNativeQueryBuilder {

    public static final String SELECT = "SELECT";
    public static final String FROM = "FROM";
    public static final String WHERE = "WHERE";
    public static final String ORDER_BY = "ORDER BY";
    public static final String AND = "AND";
    public static final String OR = "OR";
    public static final String LIMIT = "LIMIT";
    public static final String OFFSET = "OFFSET";

    private StringBuffer selectData;
    private StringBuffer fromData;
    private StringBuffer whereData;
    private StringBuffer orderByData;

    private Class<?> tableType;
    private String tableName;
    private String alias;

    public static BasicNativeQueryBuilder fromBuilder(Class<?> classType) {
        BasicNativeQueryBuilder builder = new BasicNativeQueryBuilder();
        builder.tableType = classType;
        builder.tableName = StringUtil.camelToUpperSnake(classType.getSimpleName());
        builder.alias = builder.tableName.substring(0, 1);
        builder.fromData = new StringBuffer(FROM)
                .append(SPACE)
                .append(builder.tableName)
                .append(SPACE)
                .append(builder.alias)
                .append(SPACE);
        return builder;
    }

    public BasicNativeQueryBuilder select() {
        String[] columnNames = EntityUtil.extractSelectAttributes(tableType);

        if (selectData == null) {
            this.selectData = new StringBuffer(SELECT)
                    .append(SPACE);
        }

        selectAndOrderByAttribute(selectData, columnNames);
        return this;
    }

    public BasicNativeQueryBuilder where(String entityAttributeName, SqlCondition condition, Object... values) {
        if (whereData == null) {
            this.whereData = new StringBuffer(WHERE).append(SPACE);
        }
        String columnName = StringUtil.camelToUpperSnake(entityAttributeName);

        return switch (condition){
            case EQUAL, NOT_EQUAL,
                 LIKE, NOT_LIKE,
                 GREATER_THAN, GREATER_THAN_OR_EQUAL,
                 LESS_THAN, LESS_THAN_OR_EQUAL -> whereSingleValue(columnName, condition, values[0]);
            case IN, NOT_IN,
                 EXISTS, NOT_EXISTS -> whereMultipleValues(columnName, condition, values);
            case BETWEEN, NOT_BETWEEN -> whereTwoValue(columnName, condition, values[0], values[1]);
            default -> this;
        };
    }

    private BasicNativeQueryBuilder whereTwoValue(String columnName, SqlCondition condition, Object lowerBound, Object upperBound) {
        String formatted = condition.getTemplate()
                .formatted(columnName, StringUtil.quoteAnObject(lowerBound), StringUtil.quoteAnObject(upperBound));

        whereData.append(alias).append(DOT)
                .append(formatted)
                .append(SPACE);
        return this;
    }

    private BasicNativeQueryBuilder whereMultipleValues(String columnName, SqlCondition condition, Object[] values) {
        String singleQuotedValues = StringUtil.arrayToStringWithDelimiterWithQuote(COMMA, values);
        String formatted = condition.getTemplate().formatted(
                columnName,
                singleQuotedValues
        );

        whereData.append(alias)
                .append(DOT)
                .append(formatted)
                .append(SPACE);

        return this;
    }

    private BasicNativeQueryBuilder whereSingleValue(String columnName, SqlCondition condition, Object value) {
        String formatted = condition.getTemplate()
                .formatted(
                        columnName,
                        StringUtil.quoteAnObject(value)
                );
        whereData.append(alias).append(DOT).append(formatted).append(SPACE);

        return this;
    }

    public BasicNativeQueryBuilder and(){
        whereData.append(SPACE).append(AND).append(SPACE);
        return this;
    }

    public BasicNativeQueryBuilder or(){
        whereData.append(SPACE).append(OR).append(SPACE);
        return this;
    }

    public BasicNativeQueryBuilder orderBy(SortOrder sortOrder, String... entityAttributeNames)
    {
        if (orderByData == null)
            this.orderByData = new StringBuffer(ORDER_BY).append(SPACE);

        for (int i = 0; i < entityAttributeNames.length; i++) {
            String columnName = entityAttributeNames[i];
            entityAttributeNames[i] = StringUtil.camelToUpperSnake(columnName);
        }

        selectAndOrderByAttribute(orderByData, entityAttributeNames);
        orderByData.append(SPACE).append(sortOrder);
        return this;
    }

    public BasicNativeQueryBuilder limit(int limit){
        orderByData.append(SPACE)
                .append(LIMIT)
                .append(SPACE)
                .append(limit);

        return this;
    }

    public BasicNativeQueryBuilder offset(int offset){
        orderByData.append(SPACE)
                .append(OFFSET)
                .append(SPACE)
                .append(offset);

        return this;
    }

    public String build(){
        return selectData
                .append(NEWLINE)
                .append(fromData)
                .append(NEWLINE)
                .append(whereData == null? "" : whereData)
                .append(NEWLINE)
                .append(orderByData == null || whereData == null ? "" : orderByData)
                .toString();
    }

    public enum SortOrder{
        ASC, DESC
    }

    private void selectAndOrderByAttribute(StringBuffer data,String... columnNames){
        int totalColumn = columnNames.length;
        for (int i = 0; i < totalColumn; i++) {
            data.append(alias)
                    .append(DOT)
                    .append(columnNames[i])
                    .append(i == totalColumn - 1 ? SPACE : COMMA)
                    .append(SPACE);
        }
    }
}
