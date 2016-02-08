package com.github.nrudenko.orm.query.impl.order;

import com.github.nrudenko.orm.commons.Column;
import com.github.nrudenko.orm.query.ISql;

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
