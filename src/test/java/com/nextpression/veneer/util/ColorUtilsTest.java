package com.nextpression.veneer.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ColorUtilsTest {

    private static final int RED = -65536;

    @Test(expected = IllegalArgumentException.class)
    public void testParseColorWithoutAPoundSymbolAsFirstCharacterRaisesException() {
        ColorUtils.parseColor("foo");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseColorWithAColorDefinitionLessThanNineCharactersRaisesException() {
        ColorUtils.parseColor("#1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseColorWithAColorDefinitionMoreThanNineCharactersRaisesException() {
        ColorUtils.parseColor("#FF0000000");
    }

    @Test
    public void testParseColorWithOnlySevenCharactersAddsOpaqueMaskToInt() {
        assertThat(ColorUtils.parseColor("#FF0000"), is(RED));
    }

    @Test
    public void testParseColorForRedReturnsCorrectInt() {
        assertThat(ColorUtils.parseColor("#FFFF0000"), is(RED));
    }
}
