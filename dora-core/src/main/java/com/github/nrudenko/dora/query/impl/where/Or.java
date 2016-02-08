package com.github.nrudenko.dora.query.impl.where;

public class Or extends AbsCondition {
    public static final String OR = " OR ";

    public Or(com.github.nrudenko.dora.query.ICondition[] conditions) {
        super(conditions);
    }

    @Override
    public String toSql() {
        return cookSql(conditions, OR);
    }
}
