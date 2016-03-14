package com.github.nrudenko.dora.android;

import android.content.Context;
import com.github.nrudenko.dora.android.db.QueryBuilder;
import fixtures.schemes.AttachScheme;
import fixtures.schemes.ExampleModelScheme;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import java.util.ArrayList;
import java.util.List;

import static com.github.nrudenko.dora.query.impl.Sql.*;
import static org.junit.Assert.assertEquals;


@RunWith(DoraTestRunner.class)
public class QueriesTest {
    private Context context;

    @Before
    public void setUp() {
        this.context = Robolectric.getShadowApplication().getApplicationContext();
    }

    @Test
    public void testSimpleSelect() {
        QueryBuilder q = new QueryBuilder(context)
                .table(AttachScheme.TABLE)
                .where(AttachScheme.MESSAGE_ID.is(1));

        String rawQuery = q.toRawQuery();
        assertEquals("simple select", rawQuery, "SELECT * FROM attach WHERE attach.messageId=1");
    }

    @Test
    public void testSimpleSelect2() {
        QueryBuilder queryBuilder1 = new QueryBuilder(context)
                .table(AttachScheme.TABLE)
                .where(
                        or(
                                and(AttachScheme._ID.is(1),
                                        or(AttachScheme._ID.less(2), AttachScheme._ID.less(3))),
                                and(AttachScheme._ID.is(4), AttachScheme._ID.less(5)),
                                and(AttachScheme._ID.is(6), AttachScheme._ID.isNot(7))
                        )
                )
                .orderBy(desc(AttachScheme._ID));
        System.out.println(queryBuilder1.toRawQuery());

        QueryBuilder queryBuilder2 = new QueryBuilder(context)
                .table(AttachScheme.TABLE)
                .where(AttachScheme.MESSAGE_ID.less(123),
                        AttachScheme._ID.isNull());

        System.out.println(queryBuilder2.toRawQuery());

        List<Object> list = new ArrayList<Object>();
        list.add(1);
        list.add(2);
        list.add(3);
        QueryBuilder queryBuilder3 = new QueryBuilder(context)
                .table(AttachScheme.TABLE)
                .where(AttachScheme.MESSAGE_ID.in(list));

        System.out.println(queryBuilder3.toRawQuery());

        QueryBuilder queryBuilder4 = new QueryBuilder(context)
                .table(AttachScheme.TABLE)
                .where(AttachScheme.MESSAGE_ID.like("%trololo%"));

        System.out.println(queryBuilder4.toRawQuery());
    }

    @Test
    public void testJoin() {
        QueryBuilder t = new QueryBuilder(context).table("t", join(AttachScheme.URL, ExampleModelScheme.DATE));
        System.out.println(t.toRawQuery());
    }
}
