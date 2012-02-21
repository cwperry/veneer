package com.nextpression.veneer.domain;

import org.junit.Test;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GradientTest {

    @Test
    public void testGradientUnmarshallingFromXml() throws Exception {
        Serializer serializer = new Persister();
        Gradient read = serializer.read(Gradient.class, createXml());

        assertThat(read.getKey(), is("gradientName"));
        assertThat(read.getOrientation(), is("BOTTOM_TOP"));
        assertThat(read.getColors().size(), is(2));
        assertThat(read.getColors().get(0), is("#FF012853"));
        assertThat(read.getColors().get(1), is("#FF708f96"));
    }

    private String createXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<gradient key=\"gradientName\" orientation=\"BOTTOM_TOP\">");
        sb.append("<color>#FF012853</color>");
        sb.append("<color>#FF708f96</color> ");
        sb.append("</gradient>");
        return sb.toString();
    }
}
