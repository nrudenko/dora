package com.github.nrudenko.orm.query.impl.group;

import com.github.nrudenko.orm.query.ISql;

public class MAX implements ISql {
    @Override
    public String toSql() {
        return null;
    }
//    public MAX(Column column) {
//        super(TextUtils.concat("MAX(", column.name, ")").toString(), column.getType());
//    }
}
