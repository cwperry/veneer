package com.nextpression.veneer;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import com.nextpression.veneer.util.ColorUtils;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.graphics.drawable.GradientDrawable.Orientation.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class XmlResourceManagerTest {

    private XmlResourceManager resourceManager;

    @Before
    public void beforeTests() throws Exception {
        resourceManager = new XmlResourceManager();
        StringBuilder manifest = new StringBuilder();
        manifest.append(createHeader());
        manifest.append(initializeStringXml());
        manifest.append(initializeColorXml());
        manifest.append(initializeGradientXml());
        manifest.append(createFooter());

        resourceManager.buildVeneer(manifest.toString());
    }

    @Test
    public void testStringReturnsCorrectValue() {
        assertThat(resourceManager.string("foo"), is("Foo"));
        assertThat(resourceManager.string("foo2"), is("Foo2"));
        assertThat(resourceManager.string("bar"), is("Bar"));
        assertThat(resourceManager.string("bar2"), is("Bar2"));
    }

    @Test
    public void testGradientReturnsCorrectValue() {
        assertThat(resourceManager.gradient("redGradient"), is(createGradient(BOTTOM_TOP, "#FFFF0000")));
        assertThat(resourceManager.gradient("greenGradient"), is(createGradient(TOP_BOTTOM, "#FF00FF00")));
        assertThat(resourceManager.gradient("blueGradient"), is(createGradient(LEFT_RIGHT, "#FF0000FF")));
    }

    @Test
    public void testColorReturnsCorrectValue() {
        assertThat(resourceManager.color("red"), is(Color.RED));
        assertThat(resourceManager.color("green"), is(Color.GREEN));
        assertThat(resourceManager.color("blue"), is(Color.BLUE));
    }

    private GradientDrawable createGradient(GradientDrawable.Orientation orientation, String color) {
        return new GradientDrawable(orientation, new int[]{ColorUtils.parseColor(color), Color.WHITE});
    }

    private String createHeader() {
        return "<veneer_manifest><name>test</name>";
    }

    private String createFooter() {
        return "</veneer_manifest>";
    }

    private String initializeStringXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<string id=\"foo\">Foo</string>");
        sb.append("<string id=\"bar\">Bar</string>");
        sb.append("<string id=\"foo2\">Foo2</string>");
        sb.append("<string id=\"bar2\">Bar2</string>");
        return sb.toString();
    }

    private String initializeGradientXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<gradient id=\"redGradient\" orientation=\"BOTTOM_TOP\">");
        sb.append("<color ref=\"red\"/>");
        sb.append("<color ref=\"white\"/>");
        sb.append("</gradient>");
        sb.append("<gradient id=\"greenGradient\" orientation=\"TOP_BOTTOM\">");
        sb.append("<color ref=\"green\"/>");
        sb.append("<color ref=\"white\"/>");
        sb.append("</gradient>");
        sb.append("<gradient id=\"blueGradient\" orientation=\"LEFT_RIGHT\">");
        sb.append("<color ref=\"blue\"/>");
        sb.append("<color ref=\"white\"/>");
        sb.append("</gradient>");
        return sb.toString();
    }

    private String initializeColorXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<color id=\"red\">#FFFF0000</color>");
        sb.append("<color id=\"green\">#FF00FF00</color>");
        sb.append("<color id=\"blue\">#FF0000FF</color>");
        sb.append("<color id=\"white\">#FF000000</color>");
        return sb.toString();
    }
}
