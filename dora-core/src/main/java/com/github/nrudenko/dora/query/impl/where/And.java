package com.github.nrudenko.dora.query.impl.where;

import com.github.nrudenko.dora.query.ICondition;

public class And extends AbsWhereCondition {
    public static final String AND = " AND ";

    public And(ICondition[] conditions) {
        super(conditions);
    }

    @Override
    public String toSql() {
        return cookSql(conditions, AND);
    }
}
