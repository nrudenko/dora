package com.github.nrudenko.orm.query.impl;

import com.github.nrudenko.orm.commons.Column;
import com.github.nrudenko.orm.query.ICondition;
import com.github.nrudenko.orm.query.ISql;
import com.github.nrudenko.orm.query.ITable;
import com.github.nrudenko.orm.query.impl.order.ASC;
import com.github.nrudenko.orm.query.impl.order.DESC;
import com.github.nrudenko.orm.query.impl.where.And;
import com.github.nrudenko.orm.query.impl.where.Or;

public class Sql {

    public static ICondition and(ICondition... conditions) {
        return new And(conditions);
    }

    public static ICondition or(ICondition... conditions) {
        return new Or(conditions);
    }

    public static ISql desc(Column column) {
        return new DESC(column);
    }

    public static ISql asc(Column column) {
        return new ASC(column);
    }

    public static ITable join(Column colLeft, Column colRight) {
        return new Join(colLeft, colRight);
    }


    ///////////////////////////////////////////////////////////////////////////
    // Misc
    ///////////////////////////////////////////////////////////////////////////

    public static String preparePlaceHolders(int count) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append(i > 0 ? ",?" : "?");
        }
        return result.toString();
    }
}
