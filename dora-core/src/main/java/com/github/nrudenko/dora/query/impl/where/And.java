package com.github.nrudenko.dora.query.impl.where;

public class And extends AbsCondition {
    public static final String AND = " AND ";

    public And(com.github.nrudenko.dora.query.ICondition[] conditions) {
        super(conditions);
    }

    @Override
    public String toSql() {
        return cookSql(conditions, AND);
    }
}
