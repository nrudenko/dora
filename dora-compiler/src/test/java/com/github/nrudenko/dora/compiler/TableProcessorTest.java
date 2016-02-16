package com.github.nrudenko.dora.compiler;

import com.google.testing.compile.JavaFileObjects;
import junit.framework.TestCase;
import org.junit.Test;

import javax.tools.JavaFileObject;

import static com.google.common.truth.Truth.assert_;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

public class TableProcessorTest extends TestCase {

    @Test
    public void testGenerateScheme() {
        JavaFileObject source = JavaFileObjects.forResource("fixtures/TestModel.java");
        JavaFileObject expectedSchemeSource
                = JavaFileObjects.forResource("fixtures/schemes/TestModelScheme.java");
        JavaFileObject expectedConverterSource
                = JavaFileObjects.forResource("fixtures/TestModel$$Converter.java");

        assert_().about(javaSource())
                .that(source)
                .processedWith(new TableProcessor())
                .compilesWithoutError()
                .and()
                .generatesSources(expectedConverterSource);

        assert_().about(javaSource())
                .that(source)
                .processedWith(new TableProcessor())
                .compilesWithoutError()
                .and()
                .generatesSources(expectedSchemeSource);
    }

    @Test
    public void testGenerateSchemeWithoutEmptyConstructor() {
        JavaFileObject source = JavaFileObjects.forResource("fixtures/TestModelWithoutEmptyConstructor.java");

        assert_().about(javaSource())
                .that(source)
                .processedWith(new TableProcessor())
                .failsToCompile()
                .withErrorContaining("Table model should have an empty constructor.");
    }

    @Test
    public void testGenerateSchemeWithUnknownFieldType() {
        JavaFileObject source = JavaFileObjects.forResource("fixtures/TestModelWithUnknownFieldType.java");

        assert_().about(javaSource())
                .that(source)
                .processedWith(new TableProcessor())
                .failsToCompile()
                .withErrorContaining("\"fixtures.TestModel field\" unknown field type. Please use IAdapter or add @DbSkipField.");
    }
}
