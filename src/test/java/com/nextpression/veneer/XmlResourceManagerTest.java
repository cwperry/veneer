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
        sb.append("<strings>");
        sb.append("<string name=\"foo\">Foo</string>");
        sb.append("<string name=\"bar\">Bar</string>");
        sb.append("<string name=\"foo2\">Foo2</string>");
        sb.append("<string name=\"bar2\">Bar2</string>");
        sb.append("</strings>");
        return sb.toString();
    }

    private String initializeGradientXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<gradient_definitions>");
        sb.append("<gradient name=\"redGradient\" orientation=\"BOTTOM_TOP\">");
        sb.append("<color>#FFFF0000</color>");
        sb.append("<color>#FFFFFFFF</color>");
        sb.append("</gradient>");
        sb.append("<gradient name=\"greenGradient\" orientation=\"TOP_BOTTOM\">");
        sb.append("<color>#FF00FF00</color>");
        sb.append("<color>#FFFFFFFF</color>");
        sb.append("</gradient>");
        sb.append("<gradient name=\"blueGradient\" orientation=\"LEFT_RIGHT\">");
        sb.append("<color>#FF0000FF</color>");
        sb.append("<color>#FFFFFFFF</color>");
        sb.append("</gradient>");
        sb.append("</gradient_definitions>");
        return sb.toString();
    }

    private String initializeColorXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<colors>");
        sb.append("<color name=\"red\" value=\"#FFFF0000\"/>");
        sb.append("<color name=\"green\" value=\"#FF00FF00\"/>");
        sb.append("<color name=\"blue\" value=\"#FF0000FF\"/>");
        sb.append("<color name=\"white\" value=\"#FF000000\"/>");
        sb.append("</colors>");
        return sb.toString();
    }

    private String initializeImageXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<images>");
        sb.append("<image name=\"icon\" file=\"icon.png\"/>");
        sb.append("</images>");
        return sb.toString();
    }
}
