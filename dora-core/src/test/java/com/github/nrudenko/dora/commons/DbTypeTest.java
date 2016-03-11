package com.github.nrudenko.dora.commons;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class DbTypeTest {

    @Test
    public void testFindByFieldType() {
        assertThat(DbType.byFieldType(FieldType.INTEGER)).isEqualTo(DbType.INT_DEFAULT_ZERO);
        assertThat(DbType.byFieldType(FieldType.SHORT)).isEqualTo(DbType.INT_DEFAULT_ZERO);
        assertThat(DbType.byFieldType(FieldType.BOOLEAN)).isEqualTo(DbType.INT_DEFAULT_ZERO);
        assertThat(DbType.byFieldType(FieldType.LONG)).isEqualTo(DbType.NUMERIC);
        assertThat(DbType.byFieldType(FieldType.DATE)).isEqualTo(DbType.NUMERIC);
        assertThat(DbType.byFieldType(FieldType.FLOAT)).isEqualTo(DbType.REAL);
        assertThat(DbType.byFieldType(FieldType.DOUBLE)).isEqualTo(DbType.REAL);
        assertThat(DbType.byFieldType(FieldType.STRING)).isEqualTo(DbType.TEXT);
        assertThat(DbType.byFieldType(FieldType.ENUM)).isEqualTo(DbType.TEXT);
        assertThat(DbType.byFieldType(FieldType.BLOB)).isEqualTo(DbType.BLOB);
        assertThat(DbType.byFieldType(FieldType.SERIALIZED)).isEqualTo(DbType.SERIALIZED);
        assertThat(DbType.byFieldType(null)).isEqualTo(DbType.NO_TYPE);
    }

    @Test
    public void testSqlRepresentation() {
        assertThat(DbType.INT_DEFAULT_ZERO.getSqlRep()).isEqualTo("INTEGER DEFAULT 0");
        assertThat(DbType.INT_DEFAULT_NEGATIVE.getSqlRep()).isEqualTo("INTEGER DEFAULT -1");
        assertThat(DbType.NUMERIC.getSqlRep()).isEqualTo("NUMERIC");
        assertThat(DbType.REAL.getSqlRep()).isEqualTo("REAL");
        assertThat(DbType.TEXT.getSqlRep()).isEqualTo("TEXT");
        assertThat(DbType.TEXT_NOT_NULL.getSqlRep()).isEqualTo("TEXT NOT NULL");
        assertThat(DbType.TEXT_DEFAULT_EMPTY.getSqlRep()).isEqualTo("TEXT DEFAULT \"\"");
        assertThat(DbType.BLOB.getSqlRep()).isEqualTo("BLOB");
        assertThat(DbType.SERIALIZED.getSqlRep()).isEqualTo("BLOB");
        assertThat(DbType.NO_TYPE.getSqlRep()).isEqualTo("");
    }
}