package com.github.nrudenko.dora.commons;

import java.util.Date;

public enum FieldType {
    INTEGER(Integer.class, int.class),
    STRING(String.class),
    BOOLEAN(Boolean.class, boolean.class),
    LONG(Long.class, long.class),
    SHORT(Short.class, short.class),
    FLOAT(Float.class, float.class),
    DOUBLE(Double.class, double.class),
    BLOB(Byte[].class, byte[].class),
    DATE(Date.class),
    ENUM(Enum.class),
    SERIALIZED(new Class[0]); // no automatic assignation

    public final Class[] classes;

    FieldType(Class... classes) {
        this.classes = classes;
    }

    public static FieldType byTypeName(String clsName) {
        return findBy(new FieldTypeClassPredicate<String>() {
            @Override
            public boolean isConditionEquals(Class cls, String condition) {
                if (condition.equals(cls.getCanonicalName())) {
                    return true;
                }
                return false;
            }
        }, clsName);
    }

    private static FieldType findBy(FieldTypeClassPredicate predicate, Object condition) {
        if (condition != null) {
            for (FieldType b : FieldType.values()) {
                Class[] typeClass = b.classes;
                for (int i = 0; i < typeClass.length; i++) {
                    Class typeClazz = typeClass[i];
                    if (predicate.isConditionEquals(typeClazz, condition)) {
                        return b;
                    }
                }
            }
        }
        return null;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Misc
    ///////////////////////////////////////////////////////////////////////////

    interface FieldTypeClassPredicate<T> {
        boolean isConditionEquals(Class cls, T condition);
    }
}

