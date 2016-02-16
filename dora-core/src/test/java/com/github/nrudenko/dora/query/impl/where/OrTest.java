package com.github.nrudenko.dora.query.impl.where;

import com.github.nrudenko.dora.commons.Column;
import com.github.nrudenko.dora.query.ICondition;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class OrTest {

    @Test
    public void testOr() throws Exception {
        Column column = new Column("table.column");
        ICondition[] conditions = {
                column.is("testValue1"),
                column.is("testValue2")
        };
        Or or = new Or(conditions);

        String actual = or.toSql();
        String expected = "(table.column=? OR table.column=?)";

        assertThat(or.getArgs()).isEqualTo(new String[]{"testValue1", "testValue2"});
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testOrSingleCondition() throws Exception {
        Column column = new Column("table.column");
        ICondition[] conditions = {
                column.is("testValue1")
        };
        Or or = new Or(conditions);

        String actual = or.toSql();
        String expected = "table.column=?";

        assertThat(or.getArgs()).isEqualTo(new String[]{"testValue1"});
        assertThat(actual).isEqualTo(expected);
    }
}