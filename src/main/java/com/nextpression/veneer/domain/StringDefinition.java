package com.nextpression.veneer.domain;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name = "string")
public class StringDefinition {

    @Attribute
    private String key;

    @Text
    private String string;

    public String getKey() {
        return key;
    }

    public String getString() {
        return string;
    }
}
