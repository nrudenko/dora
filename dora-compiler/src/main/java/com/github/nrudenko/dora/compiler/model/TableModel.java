package com.github.nrudenko.dora.compiler.model;

import com.github.nrudenko.dora.TextUtils;
import com.github.nrudenko.dora.annotation.DbSkipField;
import com.github.nrudenko.dora.annotation.Table;
import com.github.nrudenko.dora.commons.DbType;
import com.github.nrudenko.dora.commons.FieldType;
import com.github.nrudenko.dora.compiler.exception.AbsentConstructorException;
import com.github.nrudenko.dora.compiler.exception.UnknownFieldTypeException;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.ElementFilter;
import java.lang.reflect.Modifier;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TableModel {

    public final String modelName;
    public final String modelPackage;
    public final Set<TableColumn> columns = new LinkedHashSet<>();
    public final String tableName;

    public TableModel(Element classElement) throws AbsentConstructorException, UnknownFieldTypeException {
        assertHasEmptyConstructor(classElement);

        PackageElement packageElement = (PackageElement) classElement.getEnclosingElement();
        this.modelPackage = packageElement.getQualifiedName().toString();

        this.tableName = getTableName(classElement);
        this.modelName = classElement.getSimpleName().toString();

        prepareColumns(classElement);
    }

    private void prepareColumns(Element classElement) throws UnknownFieldTypeException {
        TableColumn _idColumn = new TableColumn(null, "int", "_id",
                tableName, DbType.INT,
                FieldType.INTEGER, "PRIMARY KEY AUTOINCREMENT", false, null);
        columns.add(_idColumn);

        List<? extends Element> classFields = classElement.getEnclosedElements();
        for (Element classField : classFields) {
            if (shouldSkipElement(classField)) continue;
            TableColumn tableColumn = new TableColumn(classField, tableName);
            if (!tableColumn.name.equals("_id")) {
                columns.add(tableColumn);
            }
        }
    }

    public String getTableName(Element typeElement) {
        Table table = typeElement.getAnnotation(Table.class);
        String tableName;
        if (table.name().trim().length() > 0) {
            tableName = table.name();
        } else {
            tableName = typeElement.getSimpleName().toString();
        }
        return TextUtils.toUnderscore(tableName).toLowerCase();
    }

    private boolean shouldSkipElement(Element schemaField) {
        return !(schemaField.getKind().isField() &&
                !schemaField.getModifiers().contains(Modifier.STATIC) &&
                schemaField.getAnnotation(DbSkipField.class) == null);
    }

    private void assertHasEmptyConstructor(Element classElement) throws AbsentConstructorException {
        for (ExecutableElement cons :
                ElementFilter.constructorsIn(classElement.getEnclosedElements())) {
            if (cons.getParameters().isEmpty()) {
                return;
            }
        }
        throw new AbsentConstructorException();
    }

}
