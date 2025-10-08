package io.gomobi.quartz.domain.query;

import io.gomobi.quartz.common.constant.Delimiter;
import io.gomobi.quartz.common.util.StringUtil;

import static io.gomobi.quartz.common.constant.Delimiter.*;

public class SelectClause {

    StringBuilder selectClause;
    StringBuilder fromClause;
    String alias;

    private SelectClause() {
    }

    public static SelectClause builder(StringBuilder fromClauseData, String alias) {
        SelectClause selectClauseObject = new SelectClause();
        selectClauseObject.fromClause = fromClauseData;
        selectClauseObject.alias = alias;
        selectClauseObject.selectClause = new StringBuilder("SELECT ");
        return selectClauseObject;
    }

    public WhereClause select(String... columnNames) {
        int selectQueryLength = columnNames.length;

        for (int i = 0; i < selectQueryLength; i++) {
            selectClause
                    .append(alias)
                    .append(DOT)
                    .append(StringUtil.camelToUpperSnake(columnNames[i]))
                    .append(i == selectQueryLength - 1
                            ? ""
                            : ","
                    );
        }

        return WhereClause.builder(selectClause.append(fromClause), alias);
    }
}
