package com.github.nrudenko.dora.compiler.template;


import android.content.ContentValues;
import android.database.Cursor;
import com.github.nrudenko.dora.commons.FieldType;
import com.github.nrudenko.dora.commons.IConverter;
import com.github.nrudenko.dora.compiler.model.Adapter;
import com.github.nrudenko.dora.compiler.model.TableColumn;
import com.github.nrudenko.dora.compiler.model.TableModel;

import java.util.LinkedHashSet;
import java.util.Set;

public class ConverterContext extends BaseTemplateContext {

    public static final String CONVERTER_TEMPLATE_NAME = "mustache_converter_template";

//    private final SchemeContext schemeContext;

    public final String converterPackage;
    public final String converterClassName;

    public final String modelClassName;

    public final Set<String> imports = new LinkedHashSet<>();
    public final Set<Adapter> adapters = new LinkedHashSet<>();
    private Set<ConverterLine> lines = new LinkedHashSet<>();

    public ConverterContext(SchemeContext schemeContext) {
        TableModel tableModel = schemeContext.tableModel;
        this.converterClassName = tableModel.modelName + "$$Converter";
        this.converterPackage = tableModel.modelPackage;
        this.modelClassName = tableModel.modelName;

        fillImports(tableModel, schemeContext);
        fillLines(schemeContext);
    }

    @Override
    public String getGenerateName() {
        return this.converterPackage + "." + this.converterClassName;
    }

    @Override
    public String getTemplateName() {
        return CONVERTER_TEMPLATE_NAME;
    }

    private void fillLines(SchemeContext schemeContext) {

        for (TableColumn column : schemeContext.columns) {
            String adapterName = null;
            if (column.fieldName == null) {
                continue;
            }
            if (column.adapter != null) {
                adapterName = column.adapter.adapterName;
                adapters.add(column.adapter);
                imports.addAll(column.adapter.imports);
            }
            ConverterLine line = new ConverterLine(
                    column.fieldName,
                    getCursorMethod(column.fieldType),
                    schemeContext.schemeClassName + "." + column.underscoreName(),
                    adapterName);
            lines.add(line);
        }
    }

//    private void fillPuts(Set<TableColumn> columns) {
//        for (TableColumn column : columns) {
//            String adapterName = null;
//            if (column.adapter != null) {
//                adapterName = column.adapter.adapterName;
//            }
//            puts.add(new PutLine(
//                    column.fieldName + ".toString()",
//                    schemeClassPresenter.converterClassName + "." + column.underscoreName(),
//                    adapterName));
//        }
//    }

    private void fillImports(TableModel tableModel, SchemeContext schemeContext) {
        this.imports.add(schemeContext.getGenerateName());
        this.imports.add(tableModel.modelPackage + "." + tableModel.modelName);
        this.imports.add(Cursor.class.getName());
        this.imports.add(ContentValues.class.getName());
        this.imports.add(IConverter.class.getName());
    }

    public String getCursorMethod(FieldType fieldType) {
        String cursorMethod;
        switch (fieldType) {
            case INTEGER:
                cursorMethod = "getInt";
                break;
            case STRING:
                cursorMethod = "getString";
                break;
            case BOOLEAN:
                cursorMethod = "getInt";
                break;
            case LONG:
                cursorMethod = "getLong";
                break;
            case SHORT:
                cursorMethod = "getShort";
                break;
            case FLOAT:
                cursorMethod = "getFloat";
                break;
            case DOUBLE:
                cursorMethod = "getDouble";
                break;
            case BLOB:
                cursorMethod = "getBlob";
                break;
            case DATE:
                cursorMethod = "getLong";
                break;
            case ENUM:
                cursorMethod = "getString";
                break;
            case SERIALIZED:
                cursorMethod = "getBlob";
                break;
            default:
                cursorMethod = null;
        }
        return cursorMethod;
    }

    public static class ConverterLine {
        String fieldName;
        String cursorMethod;
        String schemeField;
        String adapterName;

        public ConverterLine(String fieldName, String cursorMethod, String schemeField, String adapterName) {
            this.fieldName = fieldName;
            this.cursorMethod = cursorMethod;
            this.schemeField = schemeField;
            this.adapterName = adapterName;
        }
    }

}
