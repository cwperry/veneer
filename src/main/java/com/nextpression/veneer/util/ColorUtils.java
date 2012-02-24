package com.nextpression.veneer.util;

import com.google.common.annotations.VisibleForTesting;

public class ColorUtils {

    @VisibleForTesting
    static final String UNKNOWN_COLOR = "Unknown color";

    public static int parseColor(String colorString) {
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
        throw new IllegalArgumentException(UNKNOWN_COLOR);
    }
}
