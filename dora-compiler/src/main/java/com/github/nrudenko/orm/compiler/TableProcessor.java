package com.github.nrudenko.orm.compiler;

import com.github.nrudenko.orm.annotation.DbColumn;
import com.github.nrudenko.orm.annotation.DbSkipField;
import com.github.nrudenko.orm.annotation.Table;
import com.github.nrudenko.orm.compiler.exception.BaseCompilerException;
import com.github.nrudenko.orm.compiler.model.TableModel;
import com.github.nrudenko.orm.compiler.template.BaseTemplateContext;
import com.github.nrudenko.orm.compiler.template.ConverterContext;
import com.github.nrudenko.orm.compiler.template.SchemeContext;
import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@AutoService(Processor.class)
public class TableProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> tableElements = roundEnv.getElementsAnnotatedWith(Table.class);
        for (Element element : tableElements) {
            if (element.getKind() == ElementKind.CLASS) {
                try {
                    processTable(element);
                } catch (IOException e) {
                    error("Error on generating source code.", element);
                } catch (BaseCompilerException e) {
                    error(e.getMessage(), element);
                }
            }
        }
        return false;
    }

    private void processTable(Element element) throws IOException, BaseCompilerException {
        TableModel tableModel = new TableModel(element);

        SchemeContext schemeContext = new SchemeContext(tableModel);
        ConverterContext converterContext = new ConverterContext(schemeContext);

        generate(schemeContext);
        generate(converterContext);
    }

    private void generate(BaseTemplateContext templateContext) throws IOException {
        JavaFileObject jfo = processingEnv
                .getFiler()
                .createSourceFile(templateContext.getGenerateName());

        BufferedWriter bw = new BufferedWriter(jfo.openWriter());
        String content = templateContext.toString();
        bw.append(content);
        bw.newLine();
        bw.close();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new HashSet<>();
        annotations.add(Table.class.getName());
        annotations.add(DbSkipField.class.getName());
        annotations.add(DbColumn.class.getName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private void error(String message, Element element) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, message, element);
    }
}
