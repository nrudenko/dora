package com.github.nrudenko.dora.android.db;

import android.content.Context;
import android.content.CursorLoader;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import com.github.nrudenko.dora.android.UriHelper;

import java.util.List;

public class QueryCursorLoader extends CursorLoader {

    private final boolean notifyFromAllTables;
    private Uri mainUri;
    private ContentObserver joinContentObserver;

    public QueryCursorLoader(Context context, final QueryBuilder builder) {
        this(context, builder, false);
    }

    public QueryCursorLoader(Context context, final QueryBuilder builder, boolean notifyFromAllTables) {
        super(context, builder.getUri(), builder.getProjection(), builder.getWhere(), builder.getWhereArgs(), builder.getOrderBy());
        this.notifyFromAllTables = notifyFromAllTables;
        this.mainUri = builder.getUri();
    }

    @Override
    protected void onStartLoading() {
        if (notifyFromAllTables) {
            registerJoinObserver(getContext(), mainUri);
        }
        super.onStartLoading();
    }

    @Override
    protected void onReset() {
        if (notifyFromAllTables) {
            unregisterJoinObserver();
        }
        super.onReset();
    }

    private void registerJoinObserver(Context context, final Uri mainUri) {
        if (UriHelper.isJoinUri(mainUri)) {
            joinContentObserver = new SimpleNotifyingObserver(context, mainUri);
            List<String> tables = UriHelper.getAllTables(mainUri);
            for (String table : tables) {
                Uri uri = new UriHelper.Builder(context).setTable(table).build();
                context.getContentResolver().registerContentObserver(uri, true, joinContentObserver);
            }
        }
    }

    private void unregisterJoinObserver() {
        if (joinContentObserver != null) {
            getContext().getContentResolver().unregisterContentObserver(joinContentObserver);
            joinContentObserver = null;
        }
    }

    private static class SimpleNotifyingObserver extends ContentObserver {

        private final Context context;
        private final Uri uri;

        /**
         * Creates a content observer.
         */
        public SimpleNotifyingObserver(Context context, Uri uri) {
            super(new Handler());
            this.context = context;
            this.uri = uri;
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            context.getContentResolver().notifyChange(this.uri, null);
        }

        @Override
        public void onChange(boolean selfChange) {
            onChange(selfChange, null);
        }
    }
}
