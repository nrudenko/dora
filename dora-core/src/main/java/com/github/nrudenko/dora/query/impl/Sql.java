package com.github.nrudenko.dora.query.impl;

import com.github.nrudenko.dora.commons.Column;
import com.github.nrudenko.dora.query.ICondition;
import com.github.nrudenko.dora.query.ISql;
import com.github.nrudenko.dora.query.ITable;
import com.github.nrudenko.dora.query.impl.order.Asc;
import com.github.nrudenko.dora.query.impl.order.Desc;
import com.github.nrudenko.dora.query.impl.where.And;
import com.github.nrudenko.dora.query.impl.where.Or;

public class Sql {

    public static ICondition and(ICondition... conditions) {
        return new And(conditions);
    }

    public static ICondition or(ICondition... conditions) {
        return new Or(conditions);
    }

    public static ISql desc(Column column) {
        return new Desc(column);
    }

    public static ISql asc(Column column) {
        return new Asc(column);
    }

    public static ITable join(Column colLeft, Column colRight) {
        return new Join(colLeft, colRight);
    }

}
