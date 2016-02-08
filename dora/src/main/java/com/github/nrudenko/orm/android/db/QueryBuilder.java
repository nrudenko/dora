package com.github.nrudenko.orm.android.db;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import com.github.nrudenko.orm.android.UriHelper;
import com.github.nrudenko.orm.query.ICondition;
import com.github.nrudenko.orm.query.ISql;
import com.github.nrudenko.orm.query.ITable;
import com.github.nrudenko.orm.query.impl.where.And;
import com.github.nrudenko.orm.query.impl.where.Or;

import java.util.Arrays;
import java.util.List;

import static com.github.nrudenko.orm.android.CursorUtils.listToContentValuesArray;
import static com.github.nrudenko.orm.android.CursorUtils.objectToContentValues;


public class QueryBuilder {

    ITable[] tables = new ITable[0];
    String table;
    String where;
    String[] whereArgs = new String[0];
    String groupBy;
    String orderBy;
    String having;
    String[] projection;
    String limit;
    private UriHelper.Builder uriBuilder;
    private ContentResolver contentResolver;

    public QueryBuilder(Context context) {
        contentResolver = context.getContentResolver();
        this.uriBuilder = new UriHelper.Builder(context);
    }

    public Uri getUri() {
        return uriBuilder.setTable(table, tables).build();
    }

    private String concateSqlable(ISql[] where) {
        StringBuilder whereBuilder = new StringBuilder();
        for (int i = 0; i < where.length; i++) {
            ISql sqlable = where[i];
            whereBuilder.append(sqlable.toSql());
        }
        return whereBuilder.toString();
    }

    public QueryBuilder table(String table, ITable... tableables) {
        if (tableables != null) {
            this.tables = tableables;
        }
        this.table = table;
        return this;
    }

    public QueryBuilder where(ICondition... where) {
        And and = new And(where);
        this.where = and.toSql();
        whereArgs = and.getArgs();
        return this;
    }

    public QueryBuilder whereOr(ICondition... where) {
        Or and = new Or(where);
        this.where = and.toSql();
        whereArgs = and.getArgs();
        return this;
    }

    public QueryBuilder groupBy(ISql... groupBy) {
        this.groupBy = concateSqlable(groupBy);
        return this;
    }

    public QueryBuilder orderBy(ISql... orderBy) {
        this.orderBy = concateSqlable(orderBy);
        return this;
    }

    public QueryBuilder having(ISql having) {
        this.having = having.toSql();
        return this;
    }

    public QueryBuilder projection() {
        return null;
    }

    public QueryBuilder limit(int limit) {
        this.limit = String.format("LIMIT %d", limit);
        return this;
    }

    public String toRawQuery() {
        String bindedWhere = this.where;
        for (int i = 0; i < this.whereArgs.length; i++) {
            String arg = this.whereArgs[i];
            bindedWhere = bindedWhere.replaceFirst("\\?", arg);
        }

        return SQLiteQueryBuilder.buildQueryString(
                false, this.table, this.projection, bindedWhere,
                this.groupBy, this.having, this.orderBy, this.limit
        );

    }

    public Cursor query() {
        return contentResolver.query(getUri(), this.projection,
                this.where, this.whereArgs,
                this.orderBy);
    }

    ///////////////////////////////////////////////////////////////////////////
    // select
    ///////////////////////////////////////////////////////////////////////////

    public void query(OnFinishedListener loadFinishedListener) {
        new QueryLoader(contentResolver, loadFinishedListener).startQuery(0, null, getUri(),
                this.projection, this.where, this.whereArgs, this.orderBy);
    }

    public Uri insert(Object object) {
        return this.contentResolver.insert(getUri(), objectToContentValues(object));
    }

    ///////////////////////////////////////////////////////////////////////////
    // insert
    ///////////////////////////////////////////////////////////////////////////

    public int insert(List objects) {
        return contentResolver.bulkInsert(getUri(), listToContentValuesArray(objects));
    }

    public <T> int insert(T[] objects) {
        return contentResolver.bulkInsert(getUri(),
                listToContentValuesArray(Arrays.asList(objects)));
    }

    /**
     * Async insert into database
     *
     * @param object
     * @param insertFinishedListener
     */
    public void insert(Object object, OnFinishedListener insertFinishedListener) {
        uriBuilder.setTable(object.getClass().getSimpleName());
        new QueryLoader(contentResolver, insertFinishedListener)
                .startInsert(0, null, getUri(), objectToContentValues(object));
    }

    public void insert(List objects, OnFinishedListener loadFinishedListener) {
        new QueryLoader(contentResolver, loadFinishedListener)
                .startBulkInsert(0, null, getUri(), listToContentValuesArray(objects));
    }

    public <T> void insert(T[] object, OnFinishedListener loadFinishedListener) {
        new QueryLoader(contentResolver, loadFinishedListener)
                .startInsert(0, null, getUri(), objectToContentValues(object));
    }

    public int update(Object object) {
        return contentResolver.update(getUri(), objectToContentValues(object),
                this.where, this.whereArgs);
    }

    ///////////////////////////////////////////////////////////////////////////
    // update
    ///////////////////////////////////////////////////////////////////////////

    public void update(Object object, OnFinishedListener loadFinishedListener) {
        new QueryLoader(contentResolver, loadFinishedListener).startUpdate
                (0, null, getUri(), objectToContentValues(object), this.where, this.whereArgs);
    }

    public int delete() {
        return contentResolver.delete(getUri(), this.where, this.whereArgs);
    }

    public void delete(OnFinishedListener loadFinishedListener) {
        new QueryLoader(contentResolver, loadFinishedListener)
                .startDelete(0, null, getUri(), this.where, this.whereArgs);
    }

    public String getLimit() {
        return limit;
    }

    ///////////////////////////////////////////////////////////////////////////
    // misc
    ///////////////////////////////////////////////////////////////////////////

    public String[] getProjection() {
        return projection;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getGroupBy() {
        return groupBy;
    }

    ///////////////////////////////////////////////////////////////////////////
    // getters
    ///////////////////////////////////////////////////////////////////////////

    public String[] getWhereArgs() {
        return whereArgs;
    }

    public String getWhere() {
        return where;
    }

    enum Type {
        INS, UPD, QUE, DEL
    }

    public interface OnFinishedListener {

        public void onQueryFinished(Cursor cursor);
    }

    public static class SimpleOnFinishedListener implements OnFinishedListener {

        public void onQueryFinished(Cursor cursor) {
        }

        public void onInsertFinished() {
        }

        public void onUpdateFinished() {
        }

        public void onDeleteFinished() {
        }
    }

    class QueryLoader extends AsyncQueryHandler {

        private final OnFinishedListener onFinishedListener;

        public QueryLoader(ContentResolver cr, OnFinishedListener onFinishedListener) {
            super(cr);
            this.onFinishedListener = onFinishedListener;
        }

        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            if (onFinishedListener != null) {
                onFinishedListener.onQueryFinished(cursor);
            }
        }

        @Override
        protected void onInsertComplete(int token, Object cookie, Uri uri) {
            if (onFinishedListener != null && onFinishedListener instanceof SimpleOnFinishedListener) {
                ((SimpleOnFinishedListener) onFinishedListener).onInsertFinished();
            }
        }

        @Override
        protected void onUpdateComplete(int token, Object cookie, int result) {
            if (onFinishedListener != null && onFinishedListener instanceof SimpleOnFinishedListener) {
                ((SimpleOnFinishedListener) onFinishedListener).onUpdateFinished();
            }
        }

        @Override
        protected void onDeleteComplete(int token, Object cookie, int result) {
            if (onFinishedListener != null && onFinishedListener instanceof SimpleOnFinishedListener) {
                ((SimpleOnFinishedListener) onFinishedListener).onDeleteFinished();
            }
        }
    }
}
