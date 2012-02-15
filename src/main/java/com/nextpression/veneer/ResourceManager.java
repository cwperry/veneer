package com.nextpression.veneer;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

public interface ResourceManager {

    Color color(String name);

    Drawable icon(String name);

    String string(String name);

}
