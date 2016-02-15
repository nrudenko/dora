package com.github.nrudenko.dora.query.impl.where;

import com.github.nrudenko.dora.query.ICondition;

public class Or extends AbsWhereCondition {
    public static final String OR = " OR ";

    public Or(ICondition[] conditions) {
        super(conditions);
    }

    @Override
    public String toSql() {
        return cookSql(conditions, OR);
    }
}
