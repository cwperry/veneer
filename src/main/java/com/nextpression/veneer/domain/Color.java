package com.nextpression.veneer.domain;

import com.google.common.annotations.VisibleForTesting;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.util.Entry;

@Root
public class Color implements Entry {

    @VisibleForTesting
    static final String UNKNOWN_COLOR = "Unknown color";

    @Attribute(name = "name")
    private String name;

    @Attribute(name = "value")
    private String value;

    public Color() {
    }

    public Color(@Attribute(name = "name") String name, @Attribute(name = "value") String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }


    public int parseColor() {
        if (value.charAt(0) == '#') {
            // Use a long to avoid rollovers on #ffXXXXXX
            long color = Long.parseLong(value.substring(1), 16);
            if (value.length() == 7) {
                // Set the alpha value
                color |= 0x00000000ff000000;
            } else if (value.length() != 9) {
                throw new IllegalArgumentException(UNKNOWN_COLOR);
            }
            return (int) color;
        }
        throw new IllegalArgumentException(UNKNOWN_COLOR);
    }
}
