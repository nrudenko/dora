package com.github.nrudenko.dora.query.impl.where;

import com.github.nrudenko.dora.query.ICondition;

import java.util.Arrays;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Condition)) return false;

        Condition condition = (Condition) o;

        if (!Arrays.equals(args, condition.args)) return false;
        if (pattern != null ? !pattern.equals(condition.pattern) : condition.pattern != null) return false;
        return name != null ? name.equals(condition.name) : condition.name == null;

    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(args);
        result = 31 * result + (pattern != null ? pattern.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "args=" + Arrays.toString(args) +
                ", pattern='" + pattern + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
