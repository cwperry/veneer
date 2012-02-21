package com.nextpression.veneer.domain;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

//http://developer.android.com/reference/android/graphics/drawable/GradientDrawable.html
/*
    <gradient key="gradientName" orientation="BOTTOM_TOP">
        <color>#FF012853</color>
        <color>#FF708f96</color>
    </gradient>
 */
@Root
public class Gradient {

    @Attribute
    private String key;

    @Attribute
    private String orientation;

    @ElementList(inline = true, entry = "color")
    private List<String> colors;

    public String getKey() {
        return key;
    }

    public String getOrientation() {
        return orientation;
    }

    public List<String> getColors() {
        return colors;
    }

    public GradientDrawable drawableFromGradient() {
        return new GradientDrawable(determineOrientation(), buildColors());
    }

    private int[] buildColors() {
        List<Integer> concreteColors = Lists.newArrayList();
        for (String colorString : colors) {
            concreteColors.add(Color.parseColor(colorString));
        }
        return Ints.toArray(concreteColors);
    }

    private GradientDrawable.Orientation determineOrientation() {
        return GradientDrawable.Orientation.valueOf(orientation);
    }

    //0 = LEFT_RIGHT
    //45 = BL_TR
    //90 = BOTTOM_TOP
    //135 = BR_TL
    //180 = RIGHT_LEFT
    //225 = TR_BL
    //270 = TOP_BOTTOM
    //315 = TL_BR

}
