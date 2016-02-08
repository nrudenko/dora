package com.github.nrudenko.orm.compiler.template;


import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

public abstract class BaseTemplateContext {

    public abstract String getGenerateName();

    public abstract String getTemplateName();

    @Override
    public String toString() {
        StringWriter writer = new StringWriter();

        InputStream templateStream = getClass().getResourceAsStream(getTemplateName());
        Mustache.Compiler compiler = Mustache.compiler()
                .strictSections(false)
                .nullValue("");
        Template template = compiler.compile(new InputStreamReader(templateStream));
        template.execute(this, writer);

        return writer.toString();
    }

}
