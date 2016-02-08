package com.github.nrudenko.orm.compiler.model;


import com.github.nrudenko.orm.TextUtils;
import com.github.nrudenko.orm.annotation.DbColumn;
import com.github.nrudenko.orm.commons.Adapters;
import com.github.nrudenko.orm.commons.DbType;
import com.github.nrudenko.orm.commons.FieldType;
import com.github.nrudenko.orm.compiler.exception.UnknownFieldType;

import javax.lang.model.element.Element;

import static com.github.nrudenko.orm.commons.FieldType.byElement;

public class TableColumn {

    public final String fieldName;
    public final String fieldTypeName;

    public final String name;
    public final String tableName;

    public final DbType dbType;
    public final FieldType fieldType;

    public final String additional;
    public final boolean isVirtual;

    public Adapter adapter;


    public TableColumn(String fieldName, String fieldTypeName, String name,
                       String tableName, DbType dbType, FieldType fieldType,
                       String additional, boolean isVirtual, Adapter adapter) {
        this.fieldTypeName = fieldTypeName;
        this.fieldName = fieldName;
        this.name = name;
        this.tableName = tableName;
        this.dbType = dbType;
        this.fieldType = fieldType;
        this.additional = additional;
        this.isVirtual = isVirtual;
        this.adapter = adapter;
    }

    public TableColumn(Element fieldElement, String tableName) throws UnknownFieldType {
        this.fieldName = fieldElement.getSimpleName().toString();
        this.fieldTypeName = fieldElement.asType().toString();

        String name = fieldElement.getSimpleName().toString();
        FieldType fieldType = byElement(fieldElement);
        DbType type = null;
        if (fieldType != null) {
            type = fieldType.dbType;
        }
        String additional = null;
        boolean isVirtual = false;

        DbColumn dbColumn = fieldElement.getAnnotation(DbColumn.class);
        if (dbColumn != null) {
            String customName = dbColumn.name();
            DbType customType = dbColumn.type();
            boolean customIsVirtual = dbColumn.isVirtual();
            additional = dbColumn.additional();

            if (customName.length() > 0) {
                name = customName;
            }

            if (customType != DbType.NO_TYPE) {
                type = customType;
            }

            isVirtual = customIsVirtual;
        }

        this.name = name;
        this.tableName = tableName;
        if (type == null) {
            throw new UnknownFieldType(fieldElement.asType().toString(), name);
        } else {
            this.dbType = type;
        }
        this.fieldType = fieldType;
        this.additional = additional;
        this.isVirtual = isVirtual;
        this.adapter = getAdapter(name, fieldType);
    }

    private Adapter getAdapter(String name, FieldType fieldType) {
        Adapter adapter;
        switch (fieldType) {
            case ENUM:
                String[] typeNameParts = fieldTypeName.split("\\.");
                String enumTypeName = typeNameParts[typeNameParts.length - 1];
                adapter = new Adapter(
                        Adapters.EnumAdapter.class.getSimpleName() + "<" + enumTypeName + ">",
                        name + "Adapter",
                        "(" + enumTypeName + ".class)");
                adapter.imports.add(Adapters.EnumAdapter.class.getName().replace("$", "."));
                adapter.imports.add(fieldTypeName);
                break;
            case DATE:
                adapter = new Adapter(Adapters.DateAdapter.class.getSimpleName(), "dateAdapter", "()");
                adapter.imports.add(Adapters.DateAdapter.class.getName().replace("$", "."));
                break;
            case BOOLEAN:
                adapter = new Adapter(Adapters.BooleanAdapter.class.getSimpleName(), "boolAdapter", "()");
                adapter.imports.add(Adapters.BooleanAdapter.class.getName().replace("$", "."));
                break;
            default:
                adapter = null;
        }
        return adapter;
    }

    public String underscoreName() {
        return TextUtils.toUnderscore(this.name).toUpperCase();
    }

    public boolean hasAdditional() {
        return TextUtils.isNotEmpty(this.additional);
    }

    public String getColumnSql() {
        StringBuilder columnsSql = new StringBuilder();
        columnsSql
                .append(this.name)
                .append(" ")
                .append(this.dbType.getSqlRep());

        if (additional != null) {
            columnsSql
                    .append(" ")
                    .append(additional);
        }
        return columnsSql.toString();
    }

}
