package com.github.nrudenko.dora.commons;

import com.github.nrudenko.dora.query.impl.where.Condition;
import org.junit.Test;

import java.util.Arrays;

import static com.google.common.truth.Truth.assertThat;

public class ColumnTest {

    @Test
    public void testGetName() throws Exception {
        String expected = "table.column";
        String actual = new Column(expected).name;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testAs() throws Exception {
        Column column = new Column("table.column");

        String expected = "table.column AS tempName";
        String actual = column.as("tempName").name;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testLess() throws Exception {
        Column column = new Column("table.column");

        Condition expected = new Condition("table.column", "%s<?", new String[]{"5"});
        Condition actual = column.less(5);

        assertThat(actual).isEqualTo(expected);
    }

    @Test(expected = AssertionError.class)
    public void testLessWithNull() throws Exception {
        Column column = new Column("");
        column.less(null);
    }

    @Test
    public void testMore() throws Exception {
        Column column = new Column("table.column");

        Condition expected = new Condition("table.column", "%s>?", new String[]{"5"});
        Condition actual = column.more(5);

        assertThat(actual).isEqualTo(expected);
    }

    @Test(expected = AssertionError.class)
    public void testMoreWithNull() throws Exception {
        Column column = new Column("");
        column.less(null);
    }

    @Test
    public void testIs() throws Exception {
        Column column = new Column("table.column");

        Condition expected = new Condition("table.column", "%s=?", new String[]{"5"});
        Condition actual = column.is(5);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testIsWithNull() throws Exception {
        Column column = new Column("table.column");

        Condition expected = new Condition("table.column", "%s=?", new String[]{"null"});
        Condition actual = column.is(null);

        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testIsNot() throws Exception {
        Column column = new Column("table.column");

        Condition expected = new Condition("table.column", "%s<>?", new String[]{"5"});
        Condition actual = column.isNot(5);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testIsNotWithNull() throws Exception {
        Column column = new Column("table.column");

        Condition expected = new Condition("table.column", "%s<>?", new String[]{"null"});
        Condition actual = column.isNot(null);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testIsNull() throws Exception {
        Column column = new Column("table.column");

        Condition expected = new Condition("table.column", "%s is null");
        Condition actual = column.isNull();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testIsNotNull() throws Exception {
        Column column = new Column("table.column");

        Condition expected = new Condition("table.column", "%s is NOT null");
        Condition actual = column.isNotNull();

        assertThat(actual).isEqualTo(expected);
    }

    @Test()
    public void testLike() throws Exception {
        Column column = new Column("table.column");

        Condition expected = new Condition("table.column", "%s LIKE ?", new String[]{"%a%"});
        Condition actual = column.like("%a%");

        assertThat(actual).isEqualTo(expected);
    }

    @Test(expected = AssertionError.class)
    public void testLikeWithNull() throws Exception {
        new Column("table.column").like(null);
    }

    @Test
    public void testIn() throws Exception {
        Column column = new Column("table.column");
        String[] list = {"a", "b"};

        Condition expected = new Condition("table.column", "%s IN (?,?)", list);

        Condition actual1 = column.in(list);
        Condition actual2 = column.in(Arrays.asList(list));

        assertThat(actual1).isEqualTo(expected);
        assertThat(actual2).isEqualTo(expected);
    }

    @Test
    public void testGetTableName() throws Exception {
        Column column = new Column("table.column");
        assertThat(column.getTableName()).isEqualTo("table");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTableNameFailed() throws Exception {
        new Column("table").getTableName();
    }
}