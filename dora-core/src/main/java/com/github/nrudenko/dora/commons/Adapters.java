package com.github.nrudenko.dora.commons;

import java.util.Date;

public class Adapters {

    public static class EnumAdapter<F extends Enum<F>> implements IAdapter<F, String> {

        private final Class<F> enumClass;

        public EnumAdapter(Class<F> enumClass) {
            this.enumClass = enumClass;
        }

        @Override
        public String from(F from) {
            return from.toString();
        }

        @Override
        public F to(String to) {
            return F.valueOf(enumClass, to);
        }
    }

    public static class DateAdapter implements IAdapter<Date, Long> {

        @Override
        public Long from(Date from) {
            return from.getTime();
        }

        @Override
        public Date to(Long to) {
            return new Date(to);
        }
    }

    public static class BooleanAdapter implements IAdapter<Boolean, Integer> {

        @Override
        public Integer from(Boolean from) {
            return from ? 1 : 0;
        }

        @Override
        public Boolean to(Integer to) {
            return to == 1;
        }
    }

}
