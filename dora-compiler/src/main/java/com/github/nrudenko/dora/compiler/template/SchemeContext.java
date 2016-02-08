package com.github.nrudenko.dora.compiler.template;

import com.github.nrudenko.dora.TextUtils;
import com.github.nrudenko.dora.commons.Column;
import com.github.nrudenko.dora.commons.DbType;
import com.github.nrudenko.dora.commons.IScheme;
import com.github.nrudenko.dora.compiler.model.TableColumn;
import com.github.nrudenko.dora.compiler.model.TableModel;

import java.util.LinkedHashSet;
import java.util.Set;

public class SchemeContext extends BaseTemplateContext {
    public static final String SCHEME_TEMPLATE_NAME = "mustache_scheme_template";

    public static final String CREATE_TABLE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS ";
    public static final String SCHEME_SUFFIX = "Scheme";
    public static final String PACKAGE = ".schemes";

    public final String schemePackage;
    public final String schemeClassName;

    public final Set<String> imports = new LinkedHashSet<>();

    public final String createSql;
    public final Set<TableColumn> columns;
    public final String tableName;

    public final TableModel tableModel;

    public SchemeContext(TableModel tableModel) {

        this.tableModel = tableModel;

        this.schemeClassName = TextUtils.concat(tableModel.modelName, SCHEME_SUFFIX);
        this.schemePackage = TextUtils.concat(tableModel.modelPackage, PACKAGE);

        this.columns = tableModel.columns;
        this.tableName = tableModel.tableName;
        this.createSql = getCreateSql(tableModel.columns);

        imports.add(Column.class.getName());
        imports.add(IScheme.class.getName());
        imports.add(DbType.class.getName());
    }

    protected String getCreateSql(Set<TableColumn> columns) {
        StringBuilder columnsSql = new StringBuilder();

        for (TableColumn column : columns) {
            columnsSql.append(column.getColumnSql());
            columnsSql.append(",");
        }
        StringBuilder tableSql = new StringBuilder();
        if (columnsSql.length() > 0) {

            columnsSql.setLength(columnsSql.length() - 1);

            tableSql.append(CREATE_TABLE_IF_NOT_EXISTS)
                    .append(this.tableName)
                    .append(" (")
                    .append(columnsSql)
                    .append(")");
            //TODO: fix custom sql
//            String customSql = schemeWrapper.getCustomSql();
//            if (!TextUtils.isEmpty(customSql)) {
//                tableSql.append(" ")
//                        .append(customSql);
//            }
            tableSql.append(";");
        }
        return tableSql.toString();
    }

    @Override
    public String getGenerateName() {
        return this.schemePackage + "." + this.schemeClassName;
    }

    @Override
    public String getTemplateName() {
        return SCHEME_TEMPLATE_NAME;
    }
}
