package com.github.nrudenko.orm.query.impl;

import com.github.nrudenko.orm.TextUtils;
import com.github.nrudenko.orm.commons.Column;
import com.github.nrudenko.orm.query.ITable;

public class Join implements ITable {

    public enum JoinType {
        LEFT, RIGHT
    }

    public final JoinType type;
    public final Column columnLeft;
    public final Column columnRight;

    public Join(Column columnLeft, Column columnRight) {
        this(JoinType.LEFT, columnLeft, columnRight);
    }

    public Join(JoinType type, Column columnLeft, Column columnRight) {
        this.type = type;
        this.columnLeft = columnLeft;
        this.columnRight = columnRight;
    }

    @Override
    public String toSql() {
        return TextUtils.concat(
                type.toString(),
                " JOIN ",
                columnRight.getTableName(),
                " ON ",
                columnLeft.name,
                "=",
                columnRight.name
        ).toString();
    }

    public JoinType getType() {
        return type;
    }

    public String getLeftTable() {
        return columnLeft.getTableName();
    }

    public String getRightTable() {
        return columnRight.getTableName();
    }
}
