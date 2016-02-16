package com.github.nrudenko.dora.query.impl.where;

import com.github.nrudenko.dora.commons.Column;
import com.github.nrudenko.dora.query.ICondition;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class AndTest {

    @Test
    public void testAnd() throws Exception {
        Column column = new Column("table.column");
        ICondition[] conditions = {
                column.is("testValue1"),
                column.is("testValue2")
        };
        And and = new And(conditions);

        String actual = and.toSql();
        String expected = "(table.column=? AND table.column=?)";

        assertThat(and.getArgs()).isEqualTo(new String[]{"testValue1", "testValue2"});
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testAndSingleCondition() throws Exception {
        Column column = new Column("table.column");
        ICondition[] conditions = {
                column.is("testValue1")
        };
        And and = new And(conditions);

        String actual = and.toSql();
        String expected = "table.column=?";

        assertThat(and.getArgs()).isEqualTo(new String[]{"testValue1"});
        assertThat(actual).isEqualTo(expected);
    }
}