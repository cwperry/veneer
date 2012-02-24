package com.nextpression.veneer.domain;

import android.graphics.drawable.GradientDrawable;
import com.nextpression.veneer.util.ColorUtils;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;

import java.util.Map;

@Root(name = "veneer_manifest")
public class XmlVeneer {

    @Element
    private String name;

    @ElementMap(entry = "string", key = "id", attribute = true, inline = true, required = false)
    private Map<String, String> strings;

    @ElementMap(entry = "gradient", key = "id", attribute = true, inline = true, valueType = Gradient.class, required = false)
    private Map<String, Gradient> gradients;

    @ElementMap(entry = "color", key = "id", attribute = true, inline = true, required = false)
    private Map<String, String> colors;

    public String string(String key) {
        return strings.get(key);
    }

    public GradientDrawable gradientColor(String key) {
        return gradients.get(key).drawableFromGradient();
    }

    public int color(String key) {
        return ColorUtils.parseColor(colors.get(key));
    }


}
