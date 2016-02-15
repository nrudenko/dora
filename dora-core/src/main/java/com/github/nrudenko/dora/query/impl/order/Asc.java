package com.github.nrudenko.dora.query.impl.order;

import com.github.nrudenko.dora.commons.Column;
import com.github.nrudenko.dora.query.ISql;

public class Asc implements ISql {

    private final Column column;

    public Asc(Column column) {
        this.column = column;
    }

    @Override
    public String toSql() {
        return String.format("%s ASC", column.name);
    }
}
