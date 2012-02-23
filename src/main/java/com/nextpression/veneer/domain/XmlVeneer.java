package com.nextpression.veneer.domain;

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

//    @ElementMap(entry = "gradient", key = "id", attribute = true, inline = true, required = false)
//    private Map<String, Gradient> gradients;

    @ElementMap(entry = "color", key = "id", attribute = true, inline = true, required = false)
    private Map<String, String> colors;

    public String string(String key) {
        return strings.get(key);
    }

//    public GradientDrawable gradientColor(String key) {
//        return gradients.get(key).drawableFromGradient();
//    }

    public int color(String key) {
        return parseColor(colors.get(key));
    }

    private int parseColor(String colorString) {
        if (colorString.charAt(0) == '#') {
            // Use a long to avoid rollovers on #ffXXXXXX
            long color = Long.parseLong(colorString.substring(1), 16);
            if (colorString.length() == 7) {
                // Set the alpha value
                color |= 0x00000000ff000000;
            } else if (colorString.length() != 9) {
                throw new IllegalArgumentException("Unknown color");
            }
            return (int) color;
        }
        throw new IllegalArgumentException("Unknown color");
    }
}
