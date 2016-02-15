package com.github.nrudenko.dora.commons;

import org.junit.Test;

import java.util.Date;

import static com.google.common.truth.Truth.assertThat;

public class AdaptersTest {
    enum SimpleEnum {
        a, b
    }

    @Test
    public void testEnumAdapter() throws Exception {
        Adapters.EnumAdapter<SimpleEnum> simpleEnumEnumAdapter = new Adapters.EnumAdapter<>(SimpleEnum.class);

        assertThat(simpleEnumEnumAdapter.from(SimpleEnum.a)).isEqualTo("a");
        assertThat(simpleEnumEnumAdapter.to("a")).isEqualTo(SimpleEnum.a);

        assertThat(simpleEnumEnumAdapter.from(SimpleEnum.b)).isEqualTo("b");
        assertThat(simpleEnumEnumAdapter.to("b")).isEqualTo(SimpleEnum.b);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEnumAdapterFail() throws Exception {
        Adapters.EnumAdapter<SimpleEnum> simpleEnumEnumAdapter = new Adapters.EnumAdapter<>(SimpleEnum.class);
        simpleEnumEnumAdapter.to("c");
    }

    @Test
    public void testBooleanAdapter() throws Exception {
        Adapters.BooleanAdapter booleanAdapter = new Adapters.BooleanAdapter();

        assertThat(booleanAdapter.from(true)).isEqualTo(1);
        assertThat(booleanAdapter.from(Boolean.FALSE)).isEqualTo(0);
        assertThat(booleanAdapter.to(1)).isEqualTo(Boolean.TRUE);
        assertThat(booleanAdapter.to(0)).isEqualTo(false);
    }

    @Test
    public void testDateAdapterClass() throws Exception {
        Date expectedDate = new Date();

        Adapters.DateAdapter dateAdapter = new Adapters.DateAdapter();
        Long from = dateAdapter.from(expectedDate);
        Date actualDate = dateAdapter.to(from);
        assertThat(expectedDate).isEqualTo(actualDate);
    }
}