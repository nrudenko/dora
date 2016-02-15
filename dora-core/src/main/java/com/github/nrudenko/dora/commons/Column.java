package com.github.nrudenko.dora.commons;

import com.github.nrudenko.dora.query.impl.where.Condition;

import java.util.List;

import static com.github.nrudenko.dora.TextUtils.concat;
import static com.github.nrudenko.dora.TextUtils.prepareSqlPlaceHolders;

public class Column {

    public final String name;
    public final DbType type;
    public final String additional;

    public Column(String name) {
        this.name = name;
        this.type = DbType.NO_TYPE;
        this.additional = null;
    }

    public Column(String name, DbType type) {
        this.name = name;
        this.type = type;
        this.additional = null;
    }

    public Column(String name, DbType type, String additional) {
        this.name = name;
        this.type = type;
        this.additional = additional;
    }

    public Column as(String newName) {
        return new Column(concat(name, " AS ", newName));
    }

    public Condition less(Object value) {
        assert value != null : "less value can\'t be null.";
        return new Condition(name, "%s<?", String.valueOf(value));
    }

    public Condition more(Object value) {
        assert value != null : "more value can\'t be null.";
        return new Condition(name, "%s>?", String.valueOf(value));
    }

    public Condition is(Object value) {
        return new Condition(name, "%s=?", String.valueOf(value));
    }

    public Condition isNot(Object value) {
        return new Condition(name, "%s<>?", String.valueOf(value));
    }

    public Condition isNull() {
        return new Condition(name, "%s is null");
    }

    public Condition isNotNull() {
        return new Condition(name, "%s is NOT null");
    }

    public Condition like(String value) {
        assert value != null : "like value can\'t be null.";
        return new Condition(name, "%s LIKE ?", value);
    }

    public Condition in(List in) {
        assert in != null : "in can\'t be null.";
        String placeHolders = prepareSqlPlaceHolders(in.size());
        String[] params = new String[in.size()];
        for (int i = 0; i < in.size(); i++) {
            params[i] = String.valueOf(in.get(i));
        }
        return new Condition(name, concat("%s IN (", placeHolders, ")"), params);
    }

    public Condition in(String[] in) {
        assert in != null : "in can\'t be null.";
        String placeHolders = prepareSqlPlaceHolders(in.length);
        return new Condition(name, concat("%s IN (", placeHolders, ")"), in);
    }

    public String getTableName() {
        String[] colParts = name.split("\\.");
        if (colParts.length < 2) {
            throw new IllegalArgumentException("Column name should looks like table_name.column_name: " + name);
        }
        return colParts[0];
    }

    @Override
    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", additional='" + additional + '\'' +
                '}';
    }
}

