package com.nextpression.veneer.domain;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.util.Dictionary;

import java.util.Map;

@Root(name = "veneer_manifest")
public class XmlVeneer {

    @Element
    private String name;

    @ElementMap(name = "strings", entry = "string", key = "name", attribute = true, required = false)
    private Map<String, String> strings;

    @ElementList(name = "gradient_definitions", required = false)
    private Dictionary<Gradient> gradients;

    @ElementList(name = "colors", required = false)
    private Dictionary<Color> colors;

    @ElementList(name = "images", required = false)
    private Dictionary<Image> images;

    public String string(String key) {
        return strings.get(key);
    }

    public GradientDrawable gradientColor(String key) {
        return gradients.get(key).drawableFromGradient();
    }

    public int color(String key) {
        return colors.get(key).parseColor();
    }

    public Drawable drawableImage(String key) {
        return images.get(key).drawable();
    }
}
