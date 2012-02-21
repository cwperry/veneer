package com.nextpression.veneer;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

public interface ResourceManager {

    int color(String name);

    GradientDrawable gradient(String name);

    Drawable icon(String name);

    String string(String name);

}
