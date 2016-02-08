package com.github.nrudenko.orm.query.impl.where;

import com.github.nrudenko.orm.query.ICondition;

public class And extends AbsCondition {
    public static final String AND = " AND ";

    public And(ICondition[] conditions) {
        super(conditions);
    }

    @Override
    public String toSql() {
        return cookSql(conditions, AND);
    }
}
