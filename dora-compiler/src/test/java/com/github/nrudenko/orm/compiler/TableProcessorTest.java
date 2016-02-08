package com.github.nrudenko.orm.compiler;

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
}
