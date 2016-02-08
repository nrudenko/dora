package com.github.nrudenko.dora.query.impl.group;


import com.github.nrudenko.dora.TextUtils;
import com.github.nrudenko.dora.query.ISql;

public class COUNT implements ISql {
    private com.github.nrudenko.dora.commons.Column column;

    public COUNT(com.github.nrudenko.dora.commons.Column column) {
        this.column = column;
    }

    public COUNT() {
    }

    @Override
    public String toSql() {
        if (this.column != null) {
            return TextUtils.concat("count(", column.name, ")").toString();
        } else {
            return "count(*)";
        }
    }
}
