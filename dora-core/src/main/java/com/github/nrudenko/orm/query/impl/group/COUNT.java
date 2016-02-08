package com.github.nrudenko.orm.query.impl.group;


import com.github.nrudenko.orm.TextUtils;
import com.github.nrudenko.orm.commons.Column;
import com.github.nrudenko.orm.query.ISql;

public class COUNT implements ISql {
    private Column column;

    public COUNT(Column column) {
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
