package com.github.nrudenko.orm.query.impl.where;

import com.github.nrudenko.orm.query.ICondition;

public class Condition implements ICondition {
    private String[] args = new String[0];
    private final String pattern;
    private String name;

    public Condition(String name, String pattern, String... args) {
        this.args = args;
        this.pattern = pattern;
        this.name = name;
    }

    @Override
    public String toSql() {
        return String.format(pattern, name);
    }

    @Override
    public String[] getArgs() {
        return args;
    }
}
