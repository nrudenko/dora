package com.github.nrudenko.dora.compiler.model;


import com.github.nrudenko.dora.TextUtils;
import com.github.nrudenko.dora.annotation.DbColumn;
import com.github.nrudenko.dora.commons.Adapters;
import com.github.nrudenko.dora.commons.DbType;
import com.github.nrudenko.dora.commons.FieldType;
import com.github.nrudenko.dora.compiler.exception.UnknownFieldType;

import javax.lang.model.element.Element;

import static com.github.nrudenko.dora.commons.FieldType.byElement;

public class TableColumn {

    public String fieldName;
    public String fieldTypeName;

    public String name;
    public String tableName;

    public DbType dbType;
    public FieldType fieldType;

    public String additional;
    public boolean isVirtual;

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
        this.tableName = tableName;

        this.fieldName = fieldElement.getSimpleName().toString();
        this.fieldTypeName = fieldElement.asType().toString();

        this.name = fieldElement.getSimpleName().toString();

        this.fieldType = byElement(fieldElement);
        if (this.fieldType == null) {
            throw new UnknownFieldType(fieldTypeName, name);
        }
        this.dbType = fieldType.dbType;

        this.adapter = getAdapter(name, fieldType);

        tryUpdateFromAnnotation(fieldElement);
    }

    private void tryUpdateFromAnnotation(Element fieldElement) {
        DbColumn dbColumn = fieldElement.getAnnotation(DbColumn.class);
        if (dbColumn != null) {
            this.name = TextUtils.isNotEmpty(dbColumn.name()) ? dbColumn.name() : this.name;
            this.dbType = dbColumn.type() != DbType.NO_TYPE ? dbColumn.type() : this.dbType;
            this.additional = dbColumn.additional();
            this.isVirtual = dbColumn.isVirtual();
        }
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

    public String underscoreName() {
        return TextUtils.toUnderscore(this.name).toUpperCase();
    }

    /**
     * Template needed method
     **/
    public boolean hasAdditional() {
        return TextUtils.isNotEmpty(this.additional);
    }

}
