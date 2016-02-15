package com.github.nrudenko.dora.query.impl.group;


import com.github.nrudenko.dora.commons.Column;
import com.github.nrudenko.dora.query.ISql;

import static com.github.nrudenko.dora.TextUtils.concat;

public class Count implements ISql {
    private Column column;

    public Count(Column column) {
        this.column = column;
    }

    public Count() {
    }

    @Override
    public String toSql() {
        if (this.column != null) {
            return concat("count(", column.name, ")");
        } else {
            return "count(*)";
        }
    }
}
