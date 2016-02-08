package com.github.nrudenko.orm.query.impl.order;

import com.github.nrudenko.orm.commons.Column;
import com.github.nrudenko.orm.query.ISql;

public class ASC implements ISql {

    private final Column column;

    public ASC(Column column) {
        this.column = column;
    }

    @Override
    public String toSql() {
        return String.format("%s ASC", column.name);
    }
}
