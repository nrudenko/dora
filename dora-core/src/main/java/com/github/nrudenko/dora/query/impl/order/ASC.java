package com.github.nrudenko.dora.query.impl.order;

import com.github.nrudenko.dora.query.ISql;

public class ASC implements ISql {

    private final com.github.nrudenko.dora.commons.Column column;

    public ASC(com.github.nrudenko.dora.commons.Column column) {
        this.column = column;
    }

    @Override
    public String toSql() {
        return String.format("%s ASC", column.name);
    }
}
