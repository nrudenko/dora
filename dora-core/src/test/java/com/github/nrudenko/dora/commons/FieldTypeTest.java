package com.github.nrudenko.dora.commons;

import org.junit.Test;

import java.util.Date;

import static com.google.common.truth.Truth.assertThat;

public class FieldTypeTest {

    @Test
    public void testFindByTypeName() throws Exception {
        assertThat(FieldType.byTypeName(Integer.class.getCanonicalName())).isEqualTo(FieldType.INTEGER);
        assertThat(FieldType.byTypeName(int.class.getCanonicalName())).isEqualTo(FieldType.INTEGER);
        assertThat(FieldType.byTypeName(String.class.getCanonicalName())).isEqualTo(FieldType.STRING);
        assertThat(FieldType.byTypeName(Boolean.class.getCanonicalName())).isEqualTo(FieldType.BOOLEAN);
        assertThat(FieldType.byTypeName(boolean.class.getCanonicalName())).isEqualTo(FieldType.BOOLEAN);
        assertThat(FieldType.byTypeName(Long.class.getCanonicalName())).isEqualTo(FieldType.LONG);
        assertThat(FieldType.byTypeName(long.class.getCanonicalName())).isEqualTo(FieldType.LONG);
        assertThat(FieldType.byTypeName(Short.class.getCanonicalName())).isEqualTo(FieldType.SHORT);
        assertThat(FieldType.byTypeName(short.class.getCanonicalName())).isEqualTo(FieldType.SHORT);
        assertThat(FieldType.byTypeName(Float.class.getCanonicalName())).isEqualTo(FieldType.FLOAT);
        assertThat(FieldType.byTypeName(float.class.getCanonicalName())).isEqualTo(FieldType.FLOAT);
        assertThat(FieldType.byTypeName(Double.class.getCanonicalName())).isEqualTo(FieldType.DOUBLE);
        assertThat(FieldType.byTypeName(double.class.getCanonicalName())).isEqualTo(FieldType.DOUBLE);
        assertThat(FieldType.byTypeName(Byte[].class.getCanonicalName())).isEqualTo(FieldType.BLOB);
        assertThat(FieldType.byTypeName(byte[].class.getCanonicalName())).isEqualTo(FieldType.BLOB);
        assertThat(FieldType.byTypeName(Date.class.getCanonicalName())).isEqualTo(FieldType.DATE);
        assertThat(FieldType.byTypeName(Enum.class.getCanonicalName())).isEqualTo(FieldType.ENUM);

        assertThat(FieldType.byTypeName(UnknownClass.class.getCanonicalName())).isNull();
        assertThat(FieldType.byTypeName(null)).isNull();
    }

    class UnknownClass {}
}