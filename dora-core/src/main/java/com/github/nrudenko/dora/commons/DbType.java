package com.github.nrudenko.dora.commons;

public enum DbType {
    INT_DEFAULT_ZERO("INTEGER DEFAULT 0", FieldType.INTEGER, FieldType.SHORT, FieldType.BOOLEAN),
    INT_DEFAULT_NEGATIVE("INTEGER DEFAULT -1", FieldType.INTEGER, FieldType.SHORT),
    NUMERIC("NUMERIC", FieldType.LONG, FieldType.DATE),
    REAL("REAL", FieldType.FLOAT, FieldType.DOUBLE),
    TEXT("TEXT", FieldType.STRING, FieldType.ENUM),
    TEXT_NOT_NULL("TEXT NOT NULL", FieldType.STRING),
    TEXT_DEFAULT_EMPTY("TEXT DEFAULT \"\"", FieldType.STRING),
    BLOB("BLOB", FieldType.BLOB),
    SERIALIZED("BLOB", FieldType.SERIALIZED),
    NO_TYPE(""); // just for default annotation value

    private final String sqlRepresentation;
    private final FieldType[] fieldTypes;

    DbType(String value, FieldType... fieldTypes) {
        this.sqlRepresentation = value;
        this.fieldTypes = fieldTypes;
    }

    public String getSqlRep() {
        return sqlRepresentation;
    }

    public static DbType byFieldType(FieldType fieldType) {
        for (DbType dbType : DbType.values()) {
            for (FieldType type : dbType.fieldTypes) {
                if (fieldType == type) {
                    return dbType;
                }
            }
        }
        return NO_TYPE;
    }
}
