package com.github.nrudenko.dora.query.impl.where;

import com.github.nrudenko.dora.query.ICondition;

public abstract class AbsWhereCondition implements ICondition {
    protected ICondition[] conditions;
    protected String[] args = new String[0];

    protected AbsWhereCondition(ICondition[] conditions) {
        this.conditions = conditions;
        for (int i = 0; i < conditions.length; i++) {
            ICondition condition = conditions[i];
            this.args = concat(args, condition.getArgs());
        }
    }

    public String[] concat(String[] a, String[] b) {
        int aLen = a.length;
        int bLen = b.length;
        String[] c = new String[aLen + bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }

    @Override
    public String[] getArgs() {
        return this.args;
    }

    protected String cookSql(ICondition[] conditions, String delimeter) {
        StringBuilder sql = new StringBuilder();
        boolean firstTime = true;
        for (int i = 0; i < conditions.length; i++) {
            if (firstTime) {
                firstTime = false;
            } else {
                sql.append(delimeter);
            }
            ICondition condition = conditions[i];
            sql.append(condition.toSql());
        }
        if (conditions.length > 1) {
            sql.insert(0, "(");
            sql.append(")");
        }
        return sql.toString();
    }
}
