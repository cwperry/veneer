package com.nextpression.veneer.domain;

import org.junit.Test;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ColorTest {

    private static final int RED = -65536;

    @Test(expected = IllegalArgumentException.class)
    public void testParseColorWithoutAPoundSymbolAsFirstCharacterRaisesException() {
        Color color = new Color("foo", "bar");
        color.parseColor();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseColorWithAColorDefinitionLessThanNineCharactersRaisesException() {
        Color color = new Color("foo", "#1");
        color.parseColor();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseColorWithAColorDefinitionMoreThanNineCharactersRaisesException() {
        Color color = new Color("foo", "#FF0000000");
        color.parseColor();
    }

    @Test
    public void testParseColorWithOnlySevenCharactersAddsOpaqueMaskToInt() {
        Color color = new Color("foo", "#FF0000");
        assertThat(color.parseColor(), is(RED));
    }

    @Test
    public void testParseColorForRedReturnsCorrectInt() {
        Color color = new Color("foo", "#FFFF0000");
        assertThat(color.parseColor(), is(RED));
    }

    @Test
    public void testDeserialization() throws Exception {
        Serializer serializer = new Persister();
        Color color = serializer.read(Color.class, "<color name=\"red\" value=\"#FFFF0000\"/>");
        assertThat(color.parseColor(), is(RED));
    }
}
