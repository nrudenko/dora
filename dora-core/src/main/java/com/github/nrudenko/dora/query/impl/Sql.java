package com.github.nrudenko.dora.query.impl;

import com.github.nrudenko.dora.query.ISql;
import com.github.nrudenko.dora.query.ITable;
import com.github.nrudenko.dora.query.impl.where.And;

public class Sql {

    public static com.github.nrudenko.dora.query.ICondition and(com.github.nrudenko.dora.query.ICondition... conditions) {
        return new And(conditions);
    }

    public static com.github.nrudenko.dora.query.ICondition or(com.github.nrudenko.dora.query.ICondition... conditions) {
        return new com.github.nrudenko.dora.query.impl.where.Or(conditions);
    }

    public static ISql desc(com.github.nrudenko.dora.commons.Column column) {
        return new com.github.nrudenko.dora.query.impl.order.DESC(column);
    }

    public static ISql asc(com.github.nrudenko.dora.commons.Column column) {
        return new com.github.nrudenko.dora.query.impl.order.ASC(column);
    }

    public static ITable join(com.github.nrudenko.dora.commons.Column colLeft, com.github.nrudenko.dora.commons.Column colRight) {
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
