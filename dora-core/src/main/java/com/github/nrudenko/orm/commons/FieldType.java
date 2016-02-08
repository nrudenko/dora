package com.github.nrudenko.orm.commons;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import java.util.Date;

public enum FieldType {
    INTEGER(DbType.INT, Integer.class, int.class),
    STRING(DbType.TEXT, String.class),
    BOOLEAN(DbType.INT, Boolean.class, boolean.class),
    LONG(DbType.NUMERIC, Long.class, long.class),
    BYTE(DbType.INT, Byte.class, byte.class),
    SHORT(DbType.INT, Short.class, short.class),
    FLOAT(DbType.REAL, Float.class, float.class),
    DOUBLE(DbType.REAL, Double.class, double.class),
    BLOB(DbType.BLOB, Byte[].class, byte[].class),
    DATE(DbType.NUMERIC, Date.class),
    ENUM(DbType.TEXT, Enum.class),
    SERIALIZED(DbType.SERIALIZED, new Class[0]); // no automatic assignation


    // PREDICATES
    static FieldTypeClassPredicate<String> classNamePredicate = new FieldTypeClassPredicate<String>() {
        @Override
        public boolean isConditionEquals(Class cls, String condition) {
            if (condition.equals(cls.getCanonicalName())) {
                return true;
            }
            return false;
        }
    };
    static FieldTypeClassPredicate<Class> classPredicate = new FieldTypeClassPredicate<Class>() {
        @Override
        public boolean isConditionEquals(Class cls, Class condition) {
            if (cls.isAssignableFrom(condition)) {
                return true;
            }
            return false;
        }
    };
    // PREDICATES
    public final Class[] cls;
    public final DbType dbType;

    FieldType(DbType dbType, Class... cls) {
        this.dbType = dbType;
        this.cls = cls;
    }

    public static FieldType byDbType(DbType type) {
        for (FieldType fieldType : FieldType.values()) {
            if (fieldType.dbType.equals(type)) {
                return fieldType;
            }
        }
        return null;
    }

    public static FieldType byTypeClass(Class cls) {
        return findBy(classPredicate, cls);
    }

    public static FieldType byTypeName(String clsName) {
        return findBy(classNamePredicate, clsName);
    }

    public static FieldType byElement(Element fieldElement) {
        //TODO: rework logic
        FieldType fieldType = null;
        TypeMirror typeMirror = fieldElement.asType();
        if (typeMirror instanceof DeclaredType) {
            Element element = ((DeclaredType) typeMirror).asElement();
            if (ElementKind.ENUM.equals(element.getKind())) {
                fieldType = ENUM;
            }
        }
        if (fieldType == null) {
            fieldType = byTypeName(typeMirror.toString());
        }

        return fieldType;
    }

    private static FieldType findBy(FieldTypeClassPredicate predicate, Object condition) {
        if (condition != null) {
        for (FieldType b : FieldType.values()) {
            Class[] typeClass = b.cls;
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

    interface FieldTypeClassPredicate<T> {
        boolean isConditionEquals(Class cls, T condition);
    }
}

