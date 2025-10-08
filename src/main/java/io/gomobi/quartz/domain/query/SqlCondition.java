package io.gomobi.quartz.domain.query;

import lombok.Getter;

@Getter
public enum SqlCondition {

    IN("%s IN (%s)"),
    NOT_IN("%s NOT IN (%s)"),
    EQUAL("%s = %s"),
    NOT_EQUAL("%s != %s"),
    LIKE("%s LIKE %s"),
    NOT_LIKE("%s NOT LIKE %s"),
    BETWEEN("%s BETWEEN %s AND %s"),
    NOT_BETWEEN("%s NOT BETWEEN %s AND %s"),
    GREATER_THAN("%s > %s"),
    GREATER_THAN_OR_EQUAL("%s >= %s"),
    LESS_THAN("%s < %s"),
    LESS_THAN_OR_EQUAL("%s <= %s"),
    IS_NULL("%s IS NULL"),
    IS_NOT_NULL("%s IS NOT NULL"),
    STARTS_WITH("%s LIKE %s"),
    ENDS_WITH("%s LIKE %s"),
    CONTAINS("%s LIKE %s"),
    EXISTS("%s EXISTS (%s)"),
    NOT_EXISTS("NOT EXISTS (%s)");

    private final String template;

    SqlCondition(String template) {
        this.template = template;
    }

}
