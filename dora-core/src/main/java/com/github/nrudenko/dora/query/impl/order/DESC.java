package com.github.nrudenko.dora.query.impl.order;

import com.github.nrudenko.dora.commons.Column;
import com.github.nrudenko.dora.query.ISql;

public class DESC implements ISql {

    private final Column column;

    public DESC(Column column) {
        this.column = column;
    }

    @Override
    public String toSql() {
        return String.format("%s DESC", column.name);
    }
}
