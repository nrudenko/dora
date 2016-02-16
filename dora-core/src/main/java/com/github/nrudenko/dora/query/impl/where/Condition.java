package com.github.nrudenko.dora.query.impl.where;

import com.github.nrudenko.dora.query.ICondition;

import java.util.Arrays;

public class Condition implements ICondition {
    private String[] args = new String[0];
    private final String pattern;
    private String columnName;

    public Condition(String columnName, String pattern, String... args) {
        this.args = args;
        this.pattern = pattern;
        this.columnName = columnName;
    }

    @Override
    public String toSql() {
        return String.format(pattern, columnName);
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
        return columnName != null ? columnName.equals(condition.columnName) : condition.columnName == null;

    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(args);
        result = 31 * result + (pattern != null ? pattern.hashCode() : 0);
        result = 31 * result + (columnName != null ? columnName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "args=" + Arrays.toString(args) +
                ", pattern='" + pattern + '\'' +
                ", columnName='" + columnName + '\'' +
                '}';
    }
}
