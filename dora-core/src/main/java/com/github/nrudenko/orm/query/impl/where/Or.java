package com.github.nrudenko.orm.query.impl.where;

import com.github.nrudenko.orm.query.ICondition;

public class Or extends AbsCondition {
    public static final String OR = " OR ";

    public Or(ICondition[] conditions) {
        super(conditions);
    }

    @Override
    public String toSql() {
        return cookSql(conditions, OR);
    }
}
