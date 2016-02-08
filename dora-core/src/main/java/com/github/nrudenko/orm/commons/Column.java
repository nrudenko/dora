package com.github.nrudenko.orm.commons;

import com.github.nrudenko.orm.TextUtils;
import com.github.nrudenko.orm.query.impl.Sql;
import com.github.nrudenko.orm.query.impl.where.Condition;

import java.util.List;

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

    @Deprecated
    public String getName() {
        return name;
    }

    public boolean isCorrect() {
        return name != null && name.length() > 0 && type != null && type != DbType.NO_TYPE;
    }

    public Column as(String newName) {
        return new Column(name + " AS " + newName);
    }

    public Condition less(Object value) {
        return new Condition(name, "%s<?", value.toString());
    }

    public Condition more(Object value) {
        return new Condition(name, "%s>?", value.toString());
    }

    public Condition is(Object value) {
        return new Condition(name, "%s=?", value.toString());
    }

    public Condition isNot(Object value) {
        return new Condition(name, "%s<>?", value.toString());
    }

    public Condition isNull() {
        return new Condition(name, "%s is NULL");
    }

    public Condition isNotNull() {
        return new Condition(name, "%s is NOT NULL");
    }

    public Condition like(String value) {
        return new Condition(name, "%s LIKE ?", value);
    }

    public Condition in(List<Object> in) {
        String placeHolders = Sql.preparePlaceHolders(in.size());
        String[] params = new String[in.size()];
        for (int i = 0; i < in.size(); i++) {
            params[i] = in.get(i).toString();
        }
        return new Condition(name, TextUtils.concat("%s IN (", placeHolders, ")").toString(), params);
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

