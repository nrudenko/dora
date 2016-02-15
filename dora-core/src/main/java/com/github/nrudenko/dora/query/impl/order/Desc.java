package com.github.nrudenko.dora.query.impl.order;

import com.github.nrudenko.dora.commons.Column;
import com.github.nrudenko.dora.query.ISql;

public class Desc implements ISql {

    private final Column column;

    public Desc(Column column) {
        this.column = column;
    }

    @Override
    public String toSql() {
        return String.format("%s DESC", column.name);
    }
}
